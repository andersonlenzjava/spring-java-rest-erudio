package br.com.erudio.springjavaerudio.controllers;

import br.com.erudio.springjavaerudio.PersonService.PersonServices;
import br.com.erudio.springjavaerudio.data.vo.v1.PersonVO;
import br.com.erudio.springjavaerudio.data.vo.v2.PersonVO2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonServices services;

    @GetMapping(value = "/{id}")
    public PersonVO findById(
            @PathVariable(value = "id") Long id) throws Exception {
        return services.findById(id);
    }

    @GetMapping()
    public List<PersonVO> findAll() {
        return services.findAll();
    }

    @PostMapping()
    public PersonVO create(
            @RequestBody() PersonVO person) throws Exception {
        return services.create(person);
    }

    @PostMapping("/v2")
    public PersonVO2 createV2(
            @RequestBody() PersonVO2 person) throws Exception {
        return services.createV2(person);
    }

    @PutMapping()
    public PersonVO update(
            @RequestBody() PersonVO person) throws Exception {
        return services.update(person);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> Delete(@PathVariable(value = "id") Long id) {
        services.delete(id);
        return ResponseEntity.noContent().build();
    }



}
