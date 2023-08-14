package de.workshops.bookshelf.book;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookRestControllerRestAssuredTest {

    @LocalServerPort
    private int port;

    @Autowired
    ObjectMapper mapper;

    @Test
    void shouldGetAllBooks() throws JsonProcessingException {
        final var books = RestAssured
                .with().port(port)
                .given()
                .log().all()
                .when().get("/book")
                .then()
                .statusCode(200)
                .extract().as(new TypeRef<List<Book>>() {});

        assertThat(books).hasSize(3);
    }
}