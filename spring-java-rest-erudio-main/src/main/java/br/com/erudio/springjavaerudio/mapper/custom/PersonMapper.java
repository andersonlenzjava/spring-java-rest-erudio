package br.com.erudio.springjavaerudio.mapper.custom;

import br.com.erudio.springjavaerudio.data.vo.v2.PersonVO2;
import br.com.erudio.springjavaerudio.model.person.Person;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersonMapper {

    public PersonVO2 covertEntityToVo(Person person){
        PersonVO2 vo = new PersonVO2();
        vo.setId(person.getId());
        vo.setAdress(person.getAdress());
        vo.setBirthDay(new Date());
        vo.setGender(person.getGender());
        vo.setFirstName(person.getFirstName());
        vo.setLastName(person.getLastName());
        return vo;
    }

    public Person covertVoToEntity(PersonVO2 person){
        Person entity = new Person();
        entity.setId(person.getId());
        entity.setAdress(person.getAdress());
        //entity.setBirthDay(new Date()); (aqui seria setado este campo na base de dados, é só uma rapida demonstração
        entity.setGender(person.getGender());
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        return entity;
    }
}
