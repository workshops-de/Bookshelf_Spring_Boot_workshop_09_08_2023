package de.workshops.bookshelf.book;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.workshops.bookshelf.ObjectMapperTestConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@Import(ObjectMapperTestConfiguration.class)
class BookRestControllerMockMvcMockitoTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    BookService service;

    @Test
    void shouldGetAllBooks() throws Exception {
        final var expectedBook = new Book();
        expectedBook.setIsbn("1234567890");

        when(service.getAllBooks()).thenReturn(List.of(expectedBook));
        mockMvc.perform(get("/book"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].isbn", is("1234567890")));
    }

    @Test
    void shouldCreateBook() throws Exception {
        MvcResult result = mockMvc.perform(post("/book")
                        .content("""
                        {
                            "isbn": "1234567890",
                            "author": "Birgit",
                            "title": "My first book",
                            "description": "This is my first book"
                        }
                        """)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        final var payload = result.getResponse().getContentAsString();
        final var createdBook = mapper.readValue(payload, Book.class);

        assertThat(createdBook)
                .isNotNull()
                .hasFieldOrPropertyWithValue("isbn", "1234567890")
                .hasFieldOrPropertyWithValue("author", "Birgit");
    }

}