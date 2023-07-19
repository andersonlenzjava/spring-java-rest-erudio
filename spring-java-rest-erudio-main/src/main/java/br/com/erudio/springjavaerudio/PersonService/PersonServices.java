package br.com.erudio.springjavaerudio.PersonService;

import br.com.erudio.springjavaerudio.model.Person;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    public Person findById(String id){

        logger.info("Findding Person!");

        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Anderson");
        person.setLastName("Costa");
        person.setAdress("Rua das coisas - Cascavel = Parana = Brasil");
        person.setGender("Male");

        return person;
    }

}
