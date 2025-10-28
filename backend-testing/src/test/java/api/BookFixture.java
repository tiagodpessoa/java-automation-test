package api;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class BookFixture {

    private static final Faker faker = new Faker();

    public static Map<String, Object> createValidBookData() {
        Map<String, Object> book = new HashMap<>();
        book.put("title", "Test Automation Advanced");
        book.put("description", "Book created via automated test");
        book.put("pageCount", 450);
        book.put("publishDate", LocalDate.now().format(DateTimeFormatter.ISO_DATE));
        return book;
    }

    public static Map<String, Object> createInvalidBookData() {
        Map<String, Object> book = new HashMap<>();
        book.put("title", faker.book().title());
        book.put("description", "Invalid PageCount test");
        book.put("pageCount", -5);
        book.put("publishDate", LocalDate.now().format(DateTimeFormatter.ISO_DATE));
        return book;
    }
}
