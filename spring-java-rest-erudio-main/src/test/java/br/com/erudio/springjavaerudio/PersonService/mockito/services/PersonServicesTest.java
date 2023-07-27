package br.com.erudio.springjavaerudio.PersonService.mockito.services;

import br.com.erudio.springjavaerudio.services.PersonService.PersonServices;
import br.com.erudio.springjavaerudio.data.vo.v1.PersonVO;
import br.com.erudio.springjavaerudio.exceptions.handler.RequiredObjectIsNullException;
import br.com.erudio.springjavaerudio.model.person.Person;
import br.com.erudio.springjavaerudio.repository.PersonRepository;
import br.com.erudio.springjavaerudio.unittest.mapper.mocks.MockPerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

// importação manual disto
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

// fazer a comparação do comportamento em relação a dados mockados padrão
// mapear senário feliz e senário de exception
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServicesTest {

    //inserção dos objetos já mocados para teste
    MockPerson input;

    // injeção do service
    @InjectMocks
    private PersonServices service;

    // injeção do mock de repository
    @Mock
    PersonRepository repository;

    @BeforeEach
    void setUp() {
        input = new MockPerson();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll() {
        // peça a lista do mock
        List<Person> ListEntity = input.mockEntityList();

        // vincula o retorno do repository a este objeto mockado
        when(repository.findAll()).thenReturn(ListEntity);

        //retorno do metodo testar de acordo com a service dentro dele
        var people = service.findAll();
        assertNotNull(people);
        assertEquals(14, people.size());

        //------------------------Fim teste inicial -- inicio teste dentro da lista em elementos individuais aleatórios

        //retorno do metodo testar de acordo com a service dentro dele
        var personOne = people.get(1);
        assertNotNull(personOne);
        assertNotNull(personOne.getKey());
        assertNotNull(personOne.getLinks());

        assertTrue(personOne.toString().contains("</person/v1/1>;rel=\"self\""));
        assertEquals("Addres Test1",personOne.getAdress());
        assertEquals("First Name Test1",personOne.getFirstName());
        assertEquals("Last Name Test1",personOne.getLastName());
        assertEquals("Female",personOne.getGender());

        //retorno do metodo testar de acordo com a service dentro dele
        var personFour = people.get(4);
        assertNotNull(personFour);
        assertNotNull(personFour.getKey());
        assertNotNull(personFour.getLinks());

        assertTrue(personFour.toString().contains("</person/v1/4>;rel=\"self\""));
        assertEquals("Addres Test4",personFour.getAdress());
        assertEquals("First Name Test4",personFour.getFirstName());
        assertEquals("Last Name Test4",personFour.getLastName());
        assertEquals("Male",personFour.getGender());

        //retorno do metodo testar de acordo com a service dentro dele
        var personSeven = people.get(7);
        assertNotNull(personSeven);
        assertNotNull(personSeven.getKey());
        assertNotNull(personSeven.getLinks());

        assertTrue(personSeven.toString().contains("</person/v1/7>;rel=\"self\""));
        assertEquals("Addres Test7",personSeven.getAdress());
        assertEquals("First Name Test7",personSeven.getFirstName());
        assertEquals("Last Name Test7",personSeven.getLastName());
        assertEquals("Female",personSeven.getGender());

    }

    // a hora que for fazer findbyid o mockito retorna um mock (fixo) ao invés de um valor real
    // ver se este método está adicionando o link hateos
    @Test
    void findById() {
        Person entity = input.mockEntity(1);
        // criação de uma entidade cópia
        entity.setId(1L);

        // vincula o retorno do repository a este objeto mockado
        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        //retorno do metodo testar de acordo com a service dentro dele
        var result = service.findById(1L);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        System.out.println(result.toString());
        assertTrue(result.toString().contains("</person/v1/1>;rel=\"self\""));
        assertEquals("Addres Test1",result.getAdress());
        assertEquals("First Name Test1",result.getFirstName());
        assertEquals("Last Name Test1",result.getLastName());
        assertEquals("Female",result.getGender());
    }

    @Test
    void create() {
        // teste na criação de objetos
        // fazer o mock dos objetos interessados considerando os seus métodos

        // criar objeto e mocar dados
        Person entity = input.mockEntity(1);
        // criação de uma entidade cópia
        Person persisted = entity;
        entity.setId(1L);

        // mockar person VO
        PersonVO vo = input.mockVO(1);
        vo.setKey(1L);

        // vincula o retorno do repository a este objeto mockado
//        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(repository.save(entity)).thenReturn(persisted);

        var result = service.create(vo);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());

        assertTrue(result.toString().contains("</person/v1/1>;rel=\"self\""));
        assertEquals("Addres Test1",result.getAdress());
        assertEquals("First Name Test1",result.getFirstName());
        assertEquals("Last Name Test1",result.getLastName());
        assertEquals("Female",result.getGender());
    }

    @Test
    void createWithNullPerson() {

        // tem de verificar se vai lançar uma exception e armazenar esta exception

        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
            service.create(null);
        });

        String expectedMessage = "It is not allowed to persist a null object!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void update() {
        // teste na criação de objetos
        // fazer o mock dos objetos interessados considerando os seus métodos

        // criar objeto e mocar dados
        Person entity = input.mockEntity(1);
        // criação de uma entidade cópia
        Person persisted = entity;
        entity.setId(1L);

        // mockar person VO
        PersonVO vo = input.mockVO(1);
        vo.setKey(1L);

        // vincula o retorno do repository a este objeto mockado
        // lógica para vinculo nas operações
        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(repository.save(entity)).thenReturn(persisted);

        var result = service.update(vo);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());

//        assertTrue(result.toString().contains("</person/v1/1>;rel=\"self\""));
        assertEquals("Addres Test1",result.getAdress());
        assertEquals("First Name Test1",result.getFirstName());
        assertEquals("Last Name Test1",result.getLastName());
        assertEquals("Female",result.getGender());
    }

    @Test
    void updateWithNullPerson() {

        // tem de verificar se vai lançar uma exception e armazenar esta exception

        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
            service.update(null);
        });

        String expectedMessage = "It is not allowed to persist a null object!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void delete() {
        Person entity = input.mockEntity(1);
        // criação de uma entidade cópia
        entity.setId(1L);

        // vincula o retorno do repository a este objeto mockado
        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        //retorno do metodo testar de acordo com a service dentro dele
        service.delete(1L);
    }

    @Test
    void createV2() {
    }
}