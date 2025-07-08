package bookstore.services;

import bookstore.models.Book;

public class MailService {
    public void sendEmail(String email, Book book){
        System.out.println("Sending " + book.toString() + " to > " + email);
    }
}
