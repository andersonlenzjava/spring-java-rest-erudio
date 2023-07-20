package br.com.erudio.springjavaerudio.controllers;

import br.com.erudio.springjavaerudio.PersonService.PersonServices;
import br.com.erudio.springjavaerudio.converters.NumberConverter;
import br.com.erudio.springjavaerudio.exceptions.handler.UnsupportedMathOperationException;
import br.com.erudio.springjavaerudio.math.SimpleMath;
import br.com.erudio.springjavaerudio.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonServices services;

    @GetMapping(value = "/{id}")
    public Person findById(
            @PathVariable(value = "id") Long id) throws Exception {
        return services.findById(id);
    }

    @GetMapping()
    public List<Person> findAll() {
        return services.findAll();
    }

    @PostMapping()
    public Person create(
            @RequestBody() Person person) throws Exception {
        return services.create(person);
    }

    @PutMapping()
    public Person update(
            @RequestBody() Person person) throws Exception {
        return services.update(person);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> Delete(@PathVariable(value = "id") Long id) {
        services.delete(id);
        return ResponseEntity.noContent().build();
    }



}
