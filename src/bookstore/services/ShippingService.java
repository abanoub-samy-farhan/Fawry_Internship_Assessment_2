package bookstore.services;

import bookstore.models.Book;

public class ShippingService {

    public void shipBook(Book book, String address, int quantity){
        System.out.println(quantity + "X " + book.toString() + "are shipped to " + address);
    }
}
