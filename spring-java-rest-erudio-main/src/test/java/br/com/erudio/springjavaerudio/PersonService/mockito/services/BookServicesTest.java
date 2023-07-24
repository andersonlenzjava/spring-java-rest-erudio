package br.com.erudio.springjavaerudio.PersonService.mockito.services;

import br.com.erudio.springjavaerudio.BooksService.BooksService;
import br.com.erudio.springjavaerudio.PersonService.PersonServices;
import br.com.erudio.springjavaerudio.data.vo.v1.BookVO;
import br.com.erudio.springjavaerudio.data.vo.v1.PersonVO;
import br.com.erudio.springjavaerudio.exceptions.handler.RequiredObjectIsNullException;
import br.com.erudio.springjavaerudio.model.book.Book;
import br.com.erudio.springjavaerudio.model.person.Person;
import br.com.erudio.springjavaerudio.repository.BookRepository;
import br.com.erudio.springjavaerudio.repository.PersonRepository;
import br.com.erudio.springjavaerudio.unittest.mapper.mocks.MockBook;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

// fazer a comparação do comportamento em relação a dados mockados padrão
// mapear senário feliz e senário de exception
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class BookServicesTest {

    //inserção dos objetos já mocados para teste
    MockBook input;

    // injeção do service
    @InjectMocks
    private BooksService service;

    // injeção do mock de repository
    @Mock
    BookRepository repository;

    @BeforeEach
    void setUp() {
        input = new MockBook();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll() {
        // peça a lista do mock
        List<Book> ListEntity = input.mockEntityList();

        // vincula o retorno do repository a este objeto mockado
        when(repository.findAll()).thenReturn(ListEntity);

        //retorno do metodo testar de acordo com a service dentro dele
        var book = service.findAll();
        assertNotNull(book);
        assertEquals(14, book.size());

        //------------------------Fim teste inicial -- inicio teste dentro da lista em elementos individuais aleatórios

        //retorno do metodo testar de acordo com a service dentro dele
        var bookOne = book.get(1);
        assertNotNull(bookOne);
        assertNotNull(bookOne.getKey());
        assertNotNull(bookOne.getLinks());

        assertTrue(bookOne.toString().contains("</book/v1/1>;rel=\"self\""));
        assertEquals("Title test1", bookOne.getTitle());
        assertEquals("Author name1", bookOne.getAuthor());
        assertEquals("2010-04-25", bookOne.getLaunchDate().toString());
        assertEquals("150", bookOne.getPrice().toString());

        //retorno do metodo testar de acordo com a service dentro dele
        var bookFour = book.get(4);
        assertNotNull(bookFour);
        assertNotNull(bookFour.getKey());
        assertNotNull(bookFour.getLinks());

        assertTrue(bookFour.toString().contains("</book/v1/4>;rel=\"self\""));
        assertEquals("Title test4", bookFour.getTitle());
        assertEquals("Author name4", bookFour.getAuthor());
        assertEquals("2010-04-25", bookFour.getLaunchDate().toString());
        assertEquals("150", bookFour.getPrice().toString());

        //retorno do metodo testar de acordo com a service dentro dele
        var bookSeven = book.get(7);
        assertNotNull(bookSeven);
        assertNotNull(bookSeven.getKey());
        assertNotNull(bookSeven.getLinks());

        assertTrue(bookSeven.toString().contains("</book/v1/7>;rel=\"self\""));
        assertEquals("Title test7", bookSeven.getTitle());
        assertEquals("Author name7", bookSeven.getAuthor());
        assertEquals("2010-04-25", bookSeven.getLaunchDate().toString());
        assertEquals("150", bookSeven.getPrice().toString());
    }

    // a hora que for fazer findbyid o mockito retorna um mock (fixo) ao invés de um valor real
    // ver se este método está adicionando o link hateos
    @Test
    void findById() {
        Book entity = input.mockEntity(1);
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
        assertTrue(result.toString().contains("</book/v1/1>;rel=\"self\""));
        assertEquals("Title test1", result.getTitle());
        assertEquals("Author name1", result.getAuthor());
        assertEquals("2010-04-25", result.getLaunchDate().toString());
        assertEquals("150", result.getPrice().toString());
    }

    @Test
    void create() {
        // teste na criação de objetos
        // fazer o mock dos objetos interessados considerando os seus métodos

        // criar objeto e mocar dados
        Book entity = input.mockEntity(1);
        // criação de uma entidade cópia
        Book persisted = entity;
        entity.setId(1L);

        // mockar person VO
        BookVO vo = input.mockVO(1);
        vo.setKey(1L);

        // vincula o retorno do repository a este objeto mockado
//        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(repository.save(entity)).thenReturn(persisted);

        var result = service.create(vo);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());

        assertTrue(result.toString().contains("</book/v1/1>;rel=\"self\""));
        assertEquals("Title test1", result.getTitle());
        assertEquals("Author name1", result.getAuthor());
        assertEquals("2010-04-25", result.getLaunchDate().toString());
        assertEquals("150", result.getPrice().toString());
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
        Book entity = input.mockEntity(1);
        // criação de uma entidade cópia
        Book persisted = entity;
        entity.setId(1L);

        // mockar person VO
        BookVO vo = input.mockVO(1);
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
        assertEquals("Title test1", result.getTitle());
        assertEquals("Author name1", result.getAuthor());
        assertEquals("2010-04-25", result.getLaunchDate().toString());
        assertEquals("150", result.getPrice().toString());
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
        Book entity = input.mockEntity(1);
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