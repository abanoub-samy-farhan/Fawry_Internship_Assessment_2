package bookstore.models;

import java.time.LocalDate;

public class Book {
    protected final String isbn;
    protected String title;
    protected LocalDate publishDate;

    Book(String isbn, String title,  LocalDate publishDate) {
        this.isbn = isbn;
        this.title = title;
        this.publishDate = publishDate;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }
    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }
}
