package br.com.erudio.springjavaerudio.controllers;

import br.com.erudio.springjavaerudio.BooksService.BooksService;
import br.com.erudio.springjavaerudio.data.vo.v1.BookVO;
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
@RequestMapping("/book/v1")
@Tag(name = "Book", description = "Endpoints for Managing Book")
public class BooksController {

    @Autowired
    private BooksService services;

    @GetMapping()
    @Operation(summary = "Finds all Books", description = "Finds all Books",
            tags = {"Book"},
            responses = {
                    @ApiResponse(description = "Sucess", responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = BookVO.class))
                                    )
                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public List<BookVO> findAll()  {
        return services.findAll();
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Finds a Books ", description = "Finds a Books ",
            tags = {"Book"},
            responses = {
                    @ApiResponse(description = "Sucess", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = BookVO.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public BookVO findById(
            @PathVariable(value = "id") Long id)  {
        return services.findById(id);
    }

    @PostMapping()
    @Operation(summary = "Adds a new Books", description = "Adds a new Books by passing in a JSON representation",
            tags = {"Book"},
            responses = {
                    @ApiResponse(description = "Created", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = BookVO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public BookVO create(
            @RequestBody() BookVO book) {
        return services.create(book);
    }

    @PutMapping()
    @Operation(summary = "Updates a Books", description = "Updates a Books",
            tags = {"Book"},
            responses = {
                    @ApiResponse(description = "Updated", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = BookVO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public BookVO update(
            @RequestBody() BookVO book)  {
        return services.update(book);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletes a Books", description = "Deletes a Books",
            tags = {"Book"},
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
