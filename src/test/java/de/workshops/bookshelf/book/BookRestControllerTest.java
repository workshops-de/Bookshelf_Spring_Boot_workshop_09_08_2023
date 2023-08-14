package de.workshops.bookshelf.book;

import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BookRestControllerTest {

    @Autowired
    private BookRestController controller;

    @Test
    void shouldGetAllBooks() {
        final var allBooks = controller.getAllBooks();
        final var allBooksBody = allBooks.getBody();

        // JUnit-Assert
        assertEquals(3, allBooksBody.size());

        // AssertJ
        assertThat(allBooksBody).hasSize(3);
    }

    @Test
    void shouldGetByIsbn() {
        var isbn = "11111";
        assertThatThrownBy(() ->controller.getByIsbn(isbn)).isInstanceOf(ConstraintViolationException.class);
    }
}