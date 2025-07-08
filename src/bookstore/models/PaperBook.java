package bookstore.models;

import java.time.LocalDate;

public class PaperBook extends Book implements Sellable {
    private float price;
    public PaperBook(String isbn, String title, float price, LocalDate publishDate) {
        super(isbn, title, publishDate);
        this.price = price;
    }

    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return this.title + " (" + this.isbn + ")";
    }
}
