package de.workshops.bookshelf.book;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookRestControllerJUnitTest {

    @Mock
    BookService service;

    @InjectMocks
    BookRestController controller;

    @Captor
    ArgumentCaptor<String> isbnCaptor;
    
    @Test
    void shouldGetAllBooks() {
        final var book = new Book();
        book.setTitle("My Book");

        when(service.getAllBooks()).thenReturn(List.of(book));

        final var allBooks = controller.getAllBooks();

        assertThat(allBooks.getBody()).isEmpty();
    }

    @Test
    void shouldGetByIsbn() {
        String isbn = "1234567890";

        when(service.getByIsbn(isbnCaptor.capture())).thenReturn(new Book());

        controller.getByIsbn(isbn);

        final var value = isbnCaptor.getValue();

        assertThat(value).isEqualTo(isbn);
        verify(service).getByIsbn(anyString());
    }

}