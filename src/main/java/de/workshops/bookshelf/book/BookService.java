package de.workshops.bookshelf.book;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
class BookService {
    private final BookJpaRepository repository;

    BookService(BookJpaRepository repository) {
        this.repository = repository;
    }

    List<Book> getAllBooks() {
        return repository.findAll();
    }

    Book getByIsbn( String isbn) {
        final var book = repository.findByIsbn(isbn);
        if (book == null) {
            throw new BookException("Das Buch mit der ISBN %s steht nicht im Regal".formatted(isbn));
        }
        return book;
    }
    Book getByAuthor( String author) {
        final var book = repository.findByAuthorContaining(author);
        if (book == null) {
            throw new BookException("Kein Buch für Autor %s gefunden".formatted(author));
        }
        return book;
    }

    List<Book> searchBooks(BookSearchRequest searchRequest) {
        final var books = repository.findAll();
        return books.stream()
                .filter(book -> book.getIsbn().equals(searchRequest.isbn()) || book.getAuthor().contains(searchRequest.author()))
                .toList();
    }

    public Book createBook(Book book) {
        repository.save(book);
        return book;
    }
}
