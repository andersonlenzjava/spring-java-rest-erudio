package br.com.erudio.springjavaerudio.controllers.calculadora;

import br.com.erudio.springjavaerudio.converters.NumberConverter;
import br.com.erudio.springjavaerudio.exceptions.handler.UnsupportedMathOperationException;
import br.com.erudio.springjavaerudio.math.SimpleMath;
import org.springframework.web.bind.annotation.*;

import java.beans.SimpleBeanInfo;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class MathController {

    private final AtomicLong counter = new AtomicLong();

    private SimpleMath math = new SimpleMath();

    @GetMapping(value = "/sum/{numberOne}/{numberTwo}")
    public Double sum(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo) throws Exception {

        // validacao
        if(!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)){
            throw new UnsupportedMathOperationException("Please seta a numeric value");
        }
        return math.sum(NumberConverter.converToDouble(numberOne), NumberConverter.converToDouble(numberTwo));
    }

    @GetMapping(value = "/subtraction/{numberOne}/{numberTwo}")
    public Double subtraction(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo) throws Exception {

        // validacao
        if(!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)){
            throw new UnsupportedMathOperationException("Please seta a numeric value");
        }
        return math.subtraction(NumberConverter.converToDouble(numberOne), NumberConverter.converToDouble(numberTwo));
    }

    @GetMapping(value = "/multipli/{numberOne}/{numberTwo}")
    public Double multipli(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo) throws Exception {

        // validacao
        if(!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)){
            throw new UnsupportedMathOperationException("Please seta a numeric value");
        }
        return math.multiplication(NumberConverter.converToDouble(numberOne), NumberConverter.converToDouble(numberTwo));
    }

    @GetMapping(value = "/avg/{numberOne}/{numberTwo}")
    public Double avg(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo) throws Exception {

        // validacao
        if(!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)){
            throw new UnsupportedMathOperationException("Please seta a numeric value");
        }
        return math.mean(NumberConverter.converToDouble(numberOne), NumberConverter.converToDouble(numberTwo));
    }

    @GetMapping(value = "/sqrt/{number}")
    public Double sqrt(
            @PathVariable(value = "number") String number) throws Exception {

        // validacao
        if(!NumberConverter.isNumeric(number)){
            throw new UnsupportedMathOperationException("Please seta a numeric value");
        }
        return math.sqrt(NumberConverter.converToDouble(number));
    }



    ///----------------------------

}
