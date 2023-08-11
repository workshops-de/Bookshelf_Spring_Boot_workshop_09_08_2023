package de.workshops.bookshelf.book;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
class BookService {
    private final BookRepository repository;

    BookService(BookRepository repository) {
        this.repository = repository;
    }

    List<Book> getAllBooks() {
        return repository.findAllBooks();
    }

    Book getByIsbn( String isbn) {
        final var books = repository.findAllBooks();
        return books.stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .findFirst()
                .orElseThrow(() -> new BookException("Das Buch mit der ISBN %s steht nicht im Regal".formatted(isbn)));
    }
    Book getByAuthor( String author) {
        final var books = repository.findAllBooks();
        return books.stream()
                .filter(book -> book.getAuthor().contains(author))
                .findFirst()
                .orElseThrow(() -> new BookException("Kein Buch für Autor %s gefunden".formatted(author)));
    }

    List<Book> searchBooks(BookSearchRequest searchRequest) {
        final var books = repository.findAllBooks();
        return books.stream()
                .filter(book -> book.getIsbn().equals(searchRequest.isbn()) || book.getAuthor().contains(searchRequest.author()))
                .toList();
    }
}
