package bookstore.models;

import java.time.LocalDate;

public class DemoBook extends Book {
    DemoBook(String isbn, String title,  LocalDate publishDate) {
        super(isbn, title,  publishDate);
    }
}
