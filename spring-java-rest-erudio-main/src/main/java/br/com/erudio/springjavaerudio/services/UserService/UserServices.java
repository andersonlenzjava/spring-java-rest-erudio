package br.com.erudio.springjavaerudio.services.UserService;

import br.com.erudio.springjavaerudio.controllers.PersonController;
import br.com.erudio.springjavaerudio.data.vo.v1.PersonVO;
import br.com.erudio.springjavaerudio.exceptions.handler.ResurceNotFoundExceptionException;
import br.com.erudio.springjavaerudio.mapper.DozerMapper;
import br.com.erudio.springjavaerudio.model.person.Person;
import br.com.erudio.springjavaerudio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class UserServices implements UserDetailsService {
    private Logger logger = Logger.getLogger(UserServices.class.getName());

    @Autowired
    UserRepository repository;

    // pode colocar @autowired aqui também (vai junto com a aplicacao) --> mas pode gerar um null pointer se a instancia não existir
    public UserServices(UserRepository repository) {
        this.repository = repository;
    }
    public PersonVO findById(Long id) {

        Person person = new Person();

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResurceNotFoundExceptionException("No records found for this ID!"));

        // cria o vo convertido
        // retorna um link especifico deste recurso
        var vo = DozerMapper.parseObject(entity, PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return vo;

        // aqui é um objeto que gera logger, que classe gerou o este log

    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        logger.info("Finding one user by name " + userName + "!");
        var user = repository.findByUsername(userName);
        if (user != null) {
            return user;
        } else {
            throw new UsernameNotFoundException("Username " + userName + " not found!");
        }
    }
}
