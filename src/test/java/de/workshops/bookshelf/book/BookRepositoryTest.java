package de.workshops.bookshelf.book;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Repository;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(
        includeFilters = @ComponentScan.Filter(
                type = FilterType.ANNOTATION,
                classes = Repository.class
        )
)
// Abschalten der InMemory DB für Tests
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BookRepositoryTest {

    @Autowired
    private BookRepository repository;
    @Test
    void shouldFindAllBooks() {
        final var books = repository.findAllBooks();

        assertThat(books).isNotEmpty();
    }
}