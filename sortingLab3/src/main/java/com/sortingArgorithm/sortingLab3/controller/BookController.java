package com.sortingArgorithm.sortingLab3.controller;

import com.sortingArgorithm.sortingLab3.model.Book;
import com.sortingArgorithm.sortingLab3.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/books", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<EntityModel<Book>>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        List<EntityModel<Book>> bookModels = books.stream()
                .map(book -> EntityModel.of(book,
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).getBookById(book.getId())).withSelfRel(),
                        WebMvcLinkBuilder.linkTo(BookController.class).withRel("allBooks")))
                .collect(Collectors.toList());
        return ResponseEntity.ok(bookModels);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Book>> getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        if (book != null) {
            EntityModel<Book> bookModel = EntityModel.of(book,
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).getBookById(id)).withSelfRel(),
                    WebMvcLinkBuilder.linkTo(BookController.class).withRel("allBooks"));
            return ResponseEntity.ok(bookModel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<EntityModel<Book>> addBook(@Valid @RequestBody Book book) {
        Book savedBook = bookService.addBook(book);
        EntityModel<Book> bookModel = EntityModel.of(savedBook,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).getBookById(savedBook.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(BookController.class).withRel("allBooks"));
        return ResponseEntity.status(HttpStatus.CREATED).body(bookModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBookById(id);
        return ResponseEntity.noContent().build();
    }

    // Update Book (PUT) method
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Book>> updateBook(@PathVariable Long id, @Valid @RequestBody Book updatedBook) {
        Book existingBook = bookService.getBookById(id);
        if (existingBook != null) {
            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setPublishedDate(updatedBook.getPublishedDate());
            existingBook.setIsbn(updatedBook.getIsbn());
            existingBook.setAvailability(updatedBook.isAvailability()); // Assuming this is a boolean field
            Book savedBook = bookService.saveBook(existingBook);
            EntityModel<Book> bookModel = EntityModel.of(savedBook,
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).getBookById(savedBook.getId())).withSelfRel(),
                    WebMvcLinkBuilder.linkTo(BookController.class).withRel("allBooks"));
            return ResponseEntity.ok(bookModel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
