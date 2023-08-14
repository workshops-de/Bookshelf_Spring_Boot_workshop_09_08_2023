package de.workshops.bookshelf.book;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/book")
@Validated
public class BookRestController {

    private final BookService service;

    BookRestController(BookService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return new ResponseEntity<>(service.getAllBooks(), HttpStatus.OK);
    }

    @GetMapping("{isbn}")
    public Book getByIsbn(@PathVariable(name = "isbn") @Size(min = 10, max = 14) String isbnNumber) {
        return service.getByIsbn(isbnNumber);
    }
    @GetMapping(params = "authorName")
    public Book getByAuthor(@RequestParam(name = "authorName") String author) {
        return service.getByAuthor(author);
    }

    @PostMapping("search")
    public List<Book> searchBy(@RequestBody @Valid BookSearchRequest searchRequest) {
        return service.searchBooks(searchRequest);
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @ExceptionHandler(BookException.class)
    public ResponseEntity<String> handleBookException(BookException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
