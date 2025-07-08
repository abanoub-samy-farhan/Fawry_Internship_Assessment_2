package bookstore.services;

import bookstore.models.*;

import java.util.Map;

public class BookStore {
    private final Inventory inventory;

    public BookStore() {
        inventory = new Inventory();
    }

    // buying books
    public float buyBook(Person user, String isbn, int quantity) {
        Book book = inventory.getBook(isbn);
        if (book == null) {
            System.out.println("Book doesn't exist");
            return -1;
        }

        if (book instanceof Sellable){
            if (book instanceof Ebook){
                // mailing service works here
                MailService mailService = new MailService();
                mailService.sendEmail(user.getEmail(), book);
                return ((Sellable) book).getPrice();
            }
            else {
                if (quantity <=0){
                    System.out.println("Quantity can't be less than 0");
                }
                try {
                    inventory.deduceStock(isbn, quantity);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    return -1;
                }
                // shipping service works here
                System.out.println("Transaction Done Successfully\n");
                ShippingService shipping = new ShippingService();
                shipping.shipBook(book, user.getAddress(), quantity);
                return  ((Sellable) book).getPrice() * quantity;
            }
        } else {
            System.out.println("This book is not for sale right now");
            return -1;
        }
    }

    public void addBookToStore(Person user, Book book, int quantity) {
        if (user instanceof Admin){
            if (book instanceof Ebook){
                inventory.addBook((Ebook) book);
            } else inventory.addBook(book, quantity);
        }
        else {
            System.out.println("You are not admin");
        }
    }

    public void removeBookFromStore(Person user, String isbn) {
        if (user instanceof Admin){
            inventory.removeBook(isbn);
        } else{
            System.out.println("You are not admin");
        }
    }

    public void removeOutdatedBooks(Person user) {
        if (user instanceof Admin){
            Map<String, Book> outdated = inventory.removeOutdated();
            if (outdated.isEmpty()) {
                System.out.println("There are no outdated books to be removed");
                return;
            }
            System.out.println("There are " + outdated.size() + " outdated books to be removed");
            for (Book book : outdated.values()){
                System.out.println(book.toString());
            }
        }
        else  {
            System.out.println("You are not admin");
        }
    }

    public int bookStock(String isbn){
        return inventory.getBookAmount(isbn);
    }
}
