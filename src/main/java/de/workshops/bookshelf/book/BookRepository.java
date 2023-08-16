package de.workshops.bookshelf.book;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
class BookRepository {
    private final JdbcTemplate template;
    private final NamedParameterJdbcTemplate namedTemplate;

    BookRepository(JdbcTemplate template, NamedParameterJdbcTemplate namedTemplate) {
        this.template = template;
        this.namedTemplate = namedTemplate;
    }

    List<Book> findAllBooks() {
        var query = "select * from book";

        final var books = template.query(query, new BeanPropertyRowMapper<>(Book.class));
        return books;
    }

    void saveBook(Book book) {
        var query = "INSERT INTO book (title, description, author, isbn) VALUES (:title, :description, :author, :isbn)";
        final var map = Map.of("title", book.getTitle(),
                "description", book.getDescription(),
                "author", book.getAuthor(),
                "isbn", book.getIsbn());
        namedTemplate.update(query, map);
//        template.update(query, book.getTitle(), book.getDescription(), book.getAuthor(), book.getIsbn());
    }
}
