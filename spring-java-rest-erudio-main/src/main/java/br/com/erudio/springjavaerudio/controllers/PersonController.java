package br.com.erudio.springjavaerudio.controllers;

import br.com.erudio.springjavaerudio.PersonService.PersonServices;
import br.com.erudio.springjavaerudio.converters.NumberConverter;
import br.com.erudio.springjavaerudio.exceptions.handler.UnsupportedMathOperationException;
import br.com.erudio.springjavaerudio.math.SimpleMath;
import br.com.erudio.springjavaerudio.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonServices services;

    @GetMapping(value = "/{id}")
    public Person findById(
            @PathVariable(value = "id") String id) throws Exception {

        return services.findById(id);
    }



}
