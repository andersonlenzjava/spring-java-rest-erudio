package br.com.erudio.springjavaerudio.BooksService;

import br.com.erudio.springjavaerudio.controllers.BooksController;
import br.com.erudio.springjavaerudio.data.vo.v1.BookVO;
import br.com.erudio.springjavaerudio.exceptions.handler.RequiredObjectIsNullException;
import br.com.erudio.springjavaerudio.exceptions.handler.ResurceNotFoundExceptionException;
import br.com.erudio.springjavaerudio.mapper.DozerMapper;
import br.com.erudio.springjavaerudio.model.book.Book;
import br.com.erudio.springjavaerudio.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BooksService {

    private Logger logger = Logger.getLogger(BooksService.class.getName());

    @Autowired
    BookRepository repository;

    public List<BookVO> findAll() {

        // aqui é um objeto que gera logger, que classe gerou o este log
        logger.info("Findding all Books!");

        var books = DozerMapper.parseListObjects(repository.findAll(), BookVO.class);
        books
                .stream()
                .forEach(p -> {
                    try {
                        p.add(linkTo(methodOn(BooksController.class).findById(p.getKey())).withSelfRel());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
        return books;
    }

    public BookVO findById(Long id) {

        // aqui é um objeto que gera logger, que classe gerou o este log
        logger.info("Findding Book!");

        Book book = new Book();

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResurceNotFoundExceptionException("No records found for this ID!"));

        // cria o vo convertido
        // retorna um link especifico deste recurso
        var vo = DozerMapper.parseObject(entity, BookVO.class);
        vo.add(linkTo(methodOn(BooksController.class).findById(id)).withSelfRel());
        return vo;
    }

    public BookVO create(BookVO book) {

        // o serto é validar se é nulo, e também se já existe com o optional
        if (book == null) throw new RequiredObjectIsNullException();

        // aqui é um objeto que gera logger, que classe gerou o este log
        logger.info("Creating one Book!");
        var entity = DozerMapper.parseObject(book, Book.class);
        var vo = DozerMapper.parseObject(repository.save(entity), BookVO.class);
        vo.add(linkTo(methodOn(BooksController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public BookVO update(BookVO book) {

        if (book == null) throw new RequiredObjectIsNullException();

        logger.info("Updating one Person!");

        Book entity = repository.findById(book.getKey())
                .orElseThrow(() -> new ResurceNotFoundExceptionException("No records found for this ID!"));

        entity.setAuthor(book.getAuthor());
        entity.setLaunchDate(book.getLaunchDate());
        entity.setPrice(book.getPrice());
        entity.setTitle(book.getTitle());

        var vo = DozerMapper.parseObject(repository.save(entity), BookVO.class);
//        vo.add(linkTo(methodOn(BooksController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public void delete(Long id) {

        // aqui é um objeto que gera logger, que classe gerou o este log
        logger.info("Deleting one Person!");

        Book entity = repository.findById(id)
                .orElseThrow(() -> new ResurceNotFoundExceptionException("No records found for this ID!"));

        repository.delete(entity);
    }

}
