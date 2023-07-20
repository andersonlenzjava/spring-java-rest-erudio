package br.com.erudio.springjavaerudio.PersonService;

import br.com.erudio.springjavaerudio.PersonRepository;
import br.com.erudio.springjavaerudio.exceptions.handler.ResurceNotFoundExceptionException;
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

    public List<Person> findAll(){

        // aqui é um objeto que gera logger, que classe gerou o este log
        logger.info("Findding all people!");

        return repository.findAll();
    }

    public Person findById(Long id){

        // aqui é um objeto que gera logger, que classe gerou o este log
        logger.info("Findding Person!");

        Person person = new Person();

        return repository.findById(id)
                .orElseThrow(() -> new ResurceNotFoundExceptionException("No records found for this ID!"));
    }

    public Person create(Person person) {

        // aqui é um objeto que gera logger, que classe gerou o este log
        logger.info("Creating one Person!");
        return repository.save(person);
    }

    public Person update(Person person) {

        logger.info("Updating one Person!");

        Person entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResurceNotFoundExceptionException("No records found for this ID!"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAdress(person.getAdress());
        entity.setGender(person.getGender());

        return repository.save(person);
    }

    public void delete(Long id) {

        // aqui é um objeto que gera logger, que classe gerou o este log
        logger.info("Deleting one Person!");

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResurceNotFoundExceptionException("No records found for this ID!"));

        repository.delete(entity);
    }

}
