package br.com.erudio.springjavaerudio.PersonService;

import br.com.erudio.springjavaerudio.controllers.PersonController;
import br.com.erudio.springjavaerudio.exceptions.handler.RequiredObjectIsNullException;
import br.com.erudio.springjavaerudio.repository.PersonRepository;
import br.com.erudio.springjavaerudio.data.vo.v1.PersonVO;
import br.com.erudio.springjavaerudio.data.vo.v2.PersonVO2;
import br.com.erudio.springjavaerudio.exceptions.handler.ResurceNotFoundExceptionException;
import br.com.erudio.springjavaerudio.mapper.DozerMapper;
import br.com.erudio.springjavaerudio.mapper.custom.PersonMapper;
import br.com.erudio.springjavaerudio.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonServices {
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    @Autowired
    PersonMapper mapper;

    public List<PersonVO> findAll() {

        // aqui é um objeto que gera logger, que classe gerou o este log
        logger.info("Findding all people!");

        var persons = DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
        persons
                .stream()
                .forEach(p -> {
                    try {
                        p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
        return persons;
    }

    public PersonVO findById(Long id) {

        // aqui é um objeto que gera logger, que classe gerou o este log
        logger.info("Findding Person!");

        Person person = new Person();

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResurceNotFoundExceptionException("No records found for this ID!"));

        // cria o vo convertido
        // retorna um link especifico deste recurso
        var vo = DozerMapper.parseObject(entity, PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return vo;
    }

    public PersonVO create(PersonVO person) {

        // o serto é validar se é nulo, e também se já existe com o optional
        if (person == null) throw new RequiredObjectIsNullException();

        // aqui é um objeto que gera logger, que classe gerou o este log
        logger.info("Creating one Person!");
        var entity = DozerMapper.parseObject(person, Person.class);
        var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public PersonVO update(PersonVO person) {

        if (person == null) throw new RequiredObjectIsNullException();

        logger.info("Updating one Person!");

        Person entity = repository.findById(person.getKey())
                .orElseThrow(() -> new ResurceNotFoundExceptionException("No records found for this ID!"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAdress(person.getAdress());
        entity.setGender(person.getGender());

        var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public void delete(Long id) {

        // aqui é um objeto que gera logger, que classe gerou o este log
        logger.info("Deleting one Person!");

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResurceNotFoundExceptionException("No records found for this ID!"));

        repository.delete(entity);
    }


    public PersonVO2 createV2(PersonVO2 person) {

        // aqui é um objeto que gera logger, que classe gerou o este log
        logger.info("Creating one Person with V2!");

        var entity = mapper.covertVoToEntity(person);
        var vo = mapper.covertEntityToVo(repository.save(entity));
        return vo;
    }

}
