package br.com.erudio.springjavaerudio.unittest.mapper;

import br.com.erudio.springjavaerudio.data.vo.v1.BookVO;
import br.com.erudio.springjavaerudio.mapper.DozerMapper;
import br.com.erudio.springjavaerudio.model.book.Book;
import br.com.erudio.springjavaerudio.unittest.mapper.mocks.MockBook;
import br.com.erudio.springjavaerudio.unittest.mapper.mocks.MockPerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DozerConverterTestBook {

    MockBook inputObject;

    @BeforeEach
    public void setUp() {
        inputObject = new MockBook();
    }

    @Test
    public void parseEntityToVOTest() {
        BookVO output = DozerMapper.parseObject(inputObject.mockEntity(), BookVO.class);
        assertEquals(Long.valueOf(0L), output.getKey());
        assertEquals("Title test0", output.getTitle());
        assertEquals("Author name0", output.getAuthor());
        assertEquals("2010-04-25", output.getLaunchDate());
        assertEquals("150", output.getPrice());
    }

    @Test
    public void parseEntityListToVOListTest() {
        List<BookVO> outputList = DozerMapper.parseListObjects(inputObject.mockEntityList(), BookVO.class);
        BookVO outputZero = outputList.get(0);

        assertEquals(Long.valueOf(0L), outputZero.getKey());
        assertEquals("Title test0", outputZero.getTitle());
        assertEquals("Author name0", outputZero.getAuthor());
        assertEquals("2010-04-25", outputZero.getLaunchDate());
        assertEquals("150", outputZero.getPrice());

        BookVO outputFive = outputList.get(5);

        assertEquals(Long.valueOf(5L), outputFive.getKey());
        assertEquals("Title test5", outputFive. getTitle());
        assertEquals("Author name5", outputFive.getAuthor());
        assertEquals("2010-04-25", outputFive.getLaunchDate());
        assertEquals("150", outputFive.getPrice());

        BookVO outputTen = outputList.get(10);

        assertEquals(Long.valueOf(10L), outputTen.getKey());
        assertEquals("Title test10", outputTen.getTitle());
        assertEquals("Author name10", outputTen.getAuthor());
        assertEquals("2010-04-25", outputTen.getLaunchDate());
        assertEquals("150", outputTen.getPrice());
    }

    @Test
    public void parseVOToEntityTest() {
        Book output = DozerMapper.parseObject(inputObject.mockVO(), Book.class);
        assertEquals(Long.valueOf(0L), output.getId());
        assertEquals("Title test0", output. getTitle());
        assertEquals("Author name0", output.getAuthor());
        assertEquals("2010-04-25", output.getLaunchDate());
        assertEquals("150", output.getPrice());
    }

    @Test
    public void parserVOListToEntityListTest() {
        List<Book> outputList = DozerMapper.parseListObjects(inputObject.mockVOList(), Book.class);
        Book outputZero = outputList.get(0);
        
        assertEquals(Long.valueOf(0L), outputZero.getId());
        assertEquals("Title test0", outputZero.getTitle());
        assertEquals("Author name0", outputZero.getAuthor());
        assertEquals("2010-04-25", outputZero.getLaunchDate());
        assertEquals("150", outputZero.getPrice());

        Book outputFive = outputList.get(5);
        
        assertEquals(Long.valueOf(5L), outputFive.getId());
        assertEquals("Title test5", outputZero.getTitle());
        assertEquals("Author name5", outputZero.getAuthor());
        assertEquals("2010-04-25", outputZero.getLaunchDate());
        assertEquals("150", outputZero.getPrice());

        Book outputTen = outputList.get(10);
        
        assertEquals(Long.valueOf(10L), outputTen.getId());
        assertEquals("Title test10", outputTen.getTitle());
        assertEquals("Author name10", outputTen.getAuthor());
        assertEquals("2010-04-25", outputTen.getLaunchDate());
        assertEquals("150", outputTen.getPrice());
    }
}
