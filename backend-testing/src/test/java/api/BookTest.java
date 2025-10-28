package api;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookTest extends BaseTest {

    private static int createdBookId;

    @Test
    @Order(1)
    @DisplayName("Criar um novo book com POST")
    void createBookTest() {
        Map<String, Object> book = BookFixture.createValidBookData();

        createdBookId = given()
                .contentType(ContentType.JSON)
                .body(book)
            .when()
                .post("/Books")
            .then()
                .statusCode(200)
                .body("title", equalTo(book.get("title")))
                .body("description", equalTo(book.get("description")))
                .body("pageCount", equalTo(book.get("pageCount")))
                .body("publishDate", startsWith(book.get("publishDate").toString()))
                .extract()
                .path("id");
    }

    @Test
    @Order(2)
    @DisplayName("Recuperar book criado com GET e validar todos os campos")
    void getBookTest() {
        Map<String, Object> book = BookFixture.createValidBookData();

        given()
            .pathParam("id", createdBookId)
        .when()
            .get("/Books/{id}")
        .then()
            .statusCode(200)
            .body("id", equalTo(createdBookId))
            .body("title", equalTo(book.get("title")))
            .body("description", equalTo(book.get("description")))
            .body("pageCount", equalTo(book.get("pageCount")))
            .body("publishDate", equalTo(book.get("publishDate")));
    }

    @Test
    @Order(3)
    @DisplayName("Alterar título do book com PUT e validar todos os campos")
    void updateBookTest() {
        Map<String, Object> book = BookFixture.createValidBookData();
        book.put("title", "Updated Book Title");

        given()
            .contentType(ContentType.JSON)
            .body(book)
            .pathParam("id", createdBookId)
        .when()
            .put("/Books/{id}")
        .then()
            .statusCode(200)
            .body("id", equalTo(createdBookId))
            .body("title", equalTo("Updated Book Title"))
            .body("description", equalTo(book.get("description")))
            .body("pageCount", equalTo(book.get("pageCount")))
            .body("publishDate", startsWith(book.get("publishDate").toString()));
    }

    @Test
    @Order(4)
    @DisplayName("Tentar deletar ID inexistente e validar status")
    void deleteNonExistentBookTest() {
        given()
            .pathParam("id", 999999)
        .when()
            .delete("/Books/{id}")
        .then()
            .statusCode(anyOf(is(200), is(400), is(404)));
    }

    @Test
    @Order(5)
    @DisplayName("Tentar criar book inválido e validar erro")
    void createInvalidBookTest() {
        Map<String, Object> invalidBook = BookFixture.createInvalidBookData();

        given()
            .contentType(ContentType.JSON)
            .body(invalidBook)
        .when()
            .post("/Books")
        .then()
            .statusCode(anyOf(is(400), is(422)));
    }
}
