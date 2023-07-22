package br.com.erudio.springjavaerudio.PersonService;

import br.com.erudio.springjavaerudio.repository.PersonRepository;
import br.com.erudio.springjavaerudio.data.vo.v1.PersonVO;
import br.com.erudio.springjavaerudio.data.vo.v2.PersonVO2;
import br.com.erudio.springjavaerudio.exceptions.handler.ResurceNotFoundExceptionException;
import br.com.erudio.springjavaerudio.mapper.DozerMapper;
import br.com.erudio.springjavaerudio.mapper.custom.PersonMapper;
import br.com.erudio.springjavaerudio.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<PersonVO> findAll(){

        // aqui é um objeto que gera logger, que classe gerou o este log
        logger.info("Findding all people!");

        return DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
    }

    public PersonVO findById(Long id){

        // aqui é um objeto que gera logger, que classe gerou o este log
        logger.info("Findding Person!");

        Person person = new Person();

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResurceNotFoundExceptionException("No records found for this ID!"));

        return DozerMapper.parseObject(entity, PersonVO.class);
    }

    public PersonVO create(PersonVO person) {

        // aqui é um objeto que gera logger, que classe gerou o este log
        logger.info("Creating one Person!");

        var entity = DozerMapper.parseObject(person, Person.class);
        var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        return vo;
    }

    public PersonVO update(PersonVO person) {

        logger.info("Updating one Person!");

        Person entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResurceNotFoundExceptionException("No records found for this ID!"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAdress(person.getAdress());
        entity.setGender(person.getGender());

        var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
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
