package bookstore.models;

import java.time.LocalDate;

public class Ebook extends Book implements Sellable {
    private final String filetype;
    private float price;
    public Ebook(String isbn, String title, float price, String filetype,  LocalDate publishDate) {
        super(isbn, title,  publishDate);
        this.filetype = filetype;
        this.price = price;
    }

    public String getFiletype() {
        return filetype;
    }

    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return this.title + " (" + this.isbn + ")" + this.filetype;
    }
}
