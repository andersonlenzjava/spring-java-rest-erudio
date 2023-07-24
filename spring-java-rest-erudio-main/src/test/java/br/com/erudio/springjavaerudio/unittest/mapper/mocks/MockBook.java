package br.com.erudio.springjavaerudio.unittest.mapper.mocks;

import br.com.erudio.springjavaerudio.data.vo.v1.BookVO;
import br.com.erudio.springjavaerudio.data.vo.v1.PersonVO;
import br.com.erudio.springjavaerudio.model.book.Book;
import br.com.erudio.springjavaerudio.model.person.Person;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class MockBook {

    //mocka a entidade
    public Book mockEntity() {
        return mockEntity(1);
    }

    //micka o VO
    public BookVO mockVO() {
        return mockVO(1);
    }

    public List<Book> mockEntityList() {
        List<Book> books = new ArrayList<Book>();
        for (int i = 0; i < 14; i++) {
            books.add(mockEntity(i));
        }
        return books;
    }

    public List<BookVO> mockVOList() {
        List<BookVO> books = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            books.add(mockVO(i));
        }
        return books;
    }
    
    public Book mockEntity(Integer number) {
        Book book = new Book();
        book.setId(number.longValue());
        book.setTitle("Title test" + number);
        book.setAuthor("Author name" + number);
        book.setLaunchDate(LocalDate.of(2010, Month.APRIL, 25));
        book.setPrice(BigDecimal.valueOf(150l));
        return book;
    }

    public BookVO mockVO(Integer number) {
        BookVO book = new BookVO();
        book.setKey(number.longValue());
        book.setTitle("Title test" + number);
        book.setAuthor("Author name" + number);
        book.setLaunchDate(LocalDate.of(2010, Month.APRIL, 25));
        book.setPrice(BigDecimal.valueOf(150l));
        return book;
    }

}
