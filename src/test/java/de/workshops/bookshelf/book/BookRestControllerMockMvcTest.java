package de.workshops.bookshelf.book;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.workshops.bookshelf.ObjectMapperTestConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
@Import(ObjectMapperTestConfiguration.class)
class BookRestControllerMockMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Test
    void shouldGetAllBooks() throws Exception {
        mockMvc.perform(get("/book"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    void shouldGetAllBooksWithResult() throws Exception {
        final var mvcResult = mockMvc.perform(get("/book"))
                .andExpect(status().isOk())
                .andReturn();

        final var jsonPayload = mvcResult.getResponse().getContentAsString();

        List<Book> books = mapper.readValue(jsonPayload, new TypeReference<List<Book>>() {});

        assertThat(books).hasSize(3);
    }

    @Test
    void shouldGetByIsbn() throws Exception {
        mockMvc.perform(get("/book/{isbn}", "1234567890"))
                .andExpect(status().isNotFound());
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