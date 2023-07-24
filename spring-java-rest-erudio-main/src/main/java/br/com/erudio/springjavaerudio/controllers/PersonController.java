package br.com.erudio.springjavaerudio.controllers;

import br.com.erudio.springjavaerudio.PersonService.PersonServices;
import br.com.erudio.springjavaerudio.data.vo.v1.PersonVO;
import br.com.erudio.springjavaerudio.data.vo.v2.PersonVO2;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person/v1")
@Tag(name = "People", description = "Endpoints for Managing People")
public class PersonController {

    @Autowired
    private PersonServices services;

    @GetMapping()
    @Operation(summary = "Finds all People", description = "Finds all People",
        tags = {"People"},
            responses = {
                @ApiResponse(description = "Sucess", responseCode = "200",
                        content = {
                                @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = PersonVO.class))
                                )
                        }),
                @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public List<PersonVO> findAll()  {
        return services.findAll();
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Finds a Person ", description = "Finds a Person ",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Sucess", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PersonVO.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public PersonVO findById(
            @PathVariable(value = "id") Long id)  {
        return services.findById(id);
    }

    @PostMapping()
    @Operation(summary = "Adds a new Person", description = "Adds a new Person by passing in a JSON representation",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Created", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PersonVO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public PersonVO create(
            @RequestBody() PersonVO person) {
        return services.create(person);
    }

    @PostMapping(value = "/v2")
    @Operation(summary = "Adds a new Person with birth date", description = "Adds a new Person with birth date",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Created", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PersonVO2.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public PersonVO2 createV2(
            @RequestBody() PersonVO2 person)  {
        return services.createV2(person);
    }

    @PutMapping()
    @Operation(summary = "Updates a Person", description = "Updates a Person",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Updated", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PersonVO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public PersonVO update(
            @RequestBody() PersonVO person)  {
        return services.update(person);
    }


    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletes a Person", description = "Deletes a Person",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "No content", responseCode = "204", content = @Content()),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<?> Delete(@PathVariable(value = "id") Long id) {
        services.delete(id);
        return ResponseEntity.noContent().build();
    }



}
