package bookstore.services;

import bookstore.models.Book;
import bookstore.models.Ebook;
import bookstore.models.Sellable;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private static int expire_threshold = 25;
    private final Map<String, Integer> stock;
    private final Map<String, Book> books;

    Inventory() {
        this.stock = new HashMap<>();
        this.books = new HashMap<>();
    }

    public static int getExpire_threshold() {
        return expire_threshold;
    }

    public static void setExpire_threshold(int expire_threshold) {
        Inventory.expire_threshold = expire_threshold;
    }

    public Book getBook(String isbn) {
        if (this.books.containsKey(isbn)) {
            return this.books.get(isbn);
        }
        return null;
    }

    public int getBookAmount(String isbn) {
        if (this.stock.containsKey(isbn)) {
            return this.stock.get(isbn);
        }
        return -1;
    }

    public void addBook(Book book) {
        if (book instanceof Ebook) {
            this.books.put(book.getIsbn(), book);
        }
        else {
            throw new IllegalArgumentException("Book is not an Ebook, you should provide quantity");
        }
    }

    public void addBook(Book book, int quantity) {
        if (quantity < 0){
            throw  new IllegalArgumentException("Quantity should be greater than 0");
        }
        if (book instanceof Ebook) {
            throw new IllegalArgumentException("Book is an Ebook, it don't have quantity");
        }
        if (this.books.containsKey(book.getIsbn())) {
            this.stock.put(book.getIsbn(),  this.stock.get(book.getIsbn()) + quantity);
            return;
        }

        this.stock.put(book.getIsbn(), quantity);
        this.books.put(book.getIsbn(), book);
    }

    public void removeBook(String isbn){
        this.books.remove(isbn);
        this.stock.remove(isbn);
    }

    public void deduceStock(String isbn, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Enter a valid quantity");
        }

        if (this.stock.containsKey(isbn) &&  this.stock.get(isbn) > 0) {
            if (this.stock.get(isbn) >= quantity) {
                this.stock.put(isbn, this.stock.get(isbn) - quantity);

            }
            else throw new IllegalArgumentException("No enough stock for the given book, Available: "
                    + this.stock.get(isbn) + " Requested: " + quantity);
        } else {
            throw new IllegalArgumentException("Book " + isbn + " is out of stock");
        }

    }

    public Map<String, Book> removeOutdated(){
        Map<String, Book> outdated = new HashMap<>();
        int current_year = LocalDate.now().getYear();
        for (Book book : this.books.values()) {
            LocalDate date = book.getPublishDate();
            int expected_expirey = date.getYear() + expire_threshold;
            if (expected_expirey <= current_year) {
                outdated.put(book.getIsbn(), book);
            }
        }

        for (String  isbn : outdated.keySet()) {
            removeBook(isbn);
        }
        return outdated;
    }
}
