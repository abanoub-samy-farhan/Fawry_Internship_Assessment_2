package bookstore;

import bookstore.models.*;
import bookstore.services.BookStore;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // Testing the bookstore system

        BookStore store = new BookStore();
        Admin admin = new Admin("Abanoub", "abanoub.admin.email@gmail.com", "Some address");
        Customer customer = new Customer("Abanoub", "abanoub.user.email@gmail.com", "6th of October, Cairo");

        System.out.println("Loading Inventory...");
        // PaperBooks loading
        PaperBook book1 = new PaperBook("978-0-321-76572-3", "Tale of two cities", 59.5f,
                LocalDate.of(2025, 3, 8));
        PaperBook book2 = new PaperBook("978-0-123-45678-1", "Pride and Prejudice", 49.9f, LocalDate.of(1995, 6, 12));
        PaperBook book3 = new PaperBook("978-1-234-56789-2", "1984", 39.0f, LocalDate.of(1980, 4, 20));
        PaperBook book4 = new PaperBook("978-3-598-21500-1", "The Great Gatsby", 44.5f, LocalDate.of(2000, 7, 30));
        PaperBook book5 = new PaperBook("978-0-14-044926-6", "Crime and Punishment", 60.0f, LocalDate.of(2010, 9, 15));
        PaperBook book6 = new PaperBook("978-0-06-112008-4", "To Kill a Mockingbird", 35.5f, LocalDate.of(1998, 2, 17));
        PaperBook book7 = new PaperBook("978-0-452-28423-4", "Brave New World", 42.3f, LocalDate.of(1988, 11, 5));
        PaperBook book8 = new PaperBook("978-0-7432-7356-5", "The Road", 29.9f, LocalDate.of(2018, 1, 22));
        PaperBook book9 = new PaperBook("978-1-56619-909-4", "Lord of the Flies", 48.2f, LocalDate.of(2005, 10, 10));
        PaperBook book10 = new PaperBook("978-0-525-47556-9", "The Catcher in the Rye", 55.0f, LocalDate.of(2022, 8, 3));

        store.addBookToStore(admin, book1, 5);
        store.addBookToStore(admin, book2, 10);
        store.addBookToStore(admin, book3, 5);
        store.addBookToStore(admin, book4, 9);
        store.addBookToStore(admin, book5, 12);
        store.addBookToStore(admin, book6, 19);
        store.addBookToStore(admin, book7, 11);
        store.addBookToStore(admin, book8, 3);
        store.addBookToStore(admin, book9, 0); // used for testing later
        store.addBookToStore(admin, book10, 1);

        // Ebooks Loading
        Ebook ebook1 = new Ebook("EBK2001", "Clean Code", 120.0f, ".pdf", LocalDate.of(1990, 6, 10));
        Ebook ebook2 = new Ebook("EBK2002", "Effective Java", 135.5f, ".epub", LocalDate.of(2001, 5, 15));
        Ebook ebook3 = new Ebook("EBK2003", "The Pragmatic Programmer", 99.9f, ".mobi", LocalDate.of(1999, 10, 30));
        Ebook ebook4 = new Ebook("EBK2004", "Introduction to Algorithms", 160.75f, ".pdf", LocalDate.of(2009, 7, 20));
        Ebook ebook5 = new Ebook("EBK2005", "Design Patterns", 110.0f, ".azw3", LocalDate.of(1994, 11, 5));

        store.addBookToStore(admin, ebook1, 1);
        store.addBookToStore(admin, ebook2, 1);
        store.addBookToStore(admin, ebook3, 1);
        store.addBookToStore(admin, ebook4, 1);
        store.addBookToStore(admin, ebook5, 1);

        // Adding demo books
        DemoBook dbook1 = new DemoBook("DEMO9313", "Some Title", LocalDate.of(2024, 1, 1));
        DemoBook dbook2 = new DemoBook("DEMO9314", "Early Preview of Java 21", LocalDate.of(2023, 10, 5));
        DemoBook dbook3 = new DemoBook("DEMO9315", "Sneak Peek: Clean Architecture", LocalDate.of(2025, 3, 12));

        store.addBookToStore(admin, dbook1, 1);
        store.addBookToStore(admin, dbook2, 1);
        store.addBookToStore(admin, dbook3, 1);

        System.out.println("Inventory Loaded");

        // Examples
        // Example 1: Buying a book successfully
        System.out.println("Examples #1: Buying an Item Successfully");
        System.out.println("----------------------------------------");
        System.out.println("Amounts of the book before transaction: " + store.bookStock("978-0-123-45678-1"));
        float paidAmount = store.buyBook(customer, "978-0-123-45678-1", 2);
        if (paidAmount > 0) {
            System.out.println("Total Price: " +  paidAmount);
        }
        System.out.println("Amounts of the book after transaction: " + store.bookStock("978-0-123-45678-1"));

        System.out.println("\n\n");

        // Example 2: Trying to buy something not found in the store
        System.out.println("Examples #2: Buying a book not found in the Store");
        System.out.println("--------------------------------------");
        paidAmount =  store.buyBook(customer, "Notrealisbn", 2);

        System.out.println("\n\n");

        //Example 3: Buying a book where with high quantity, more than stock
        System.out.println("Examples #3: Buying a book more than the amount found in the stock");
        System.out.println("--------------------------------------");
        paidAmount =  store.buyBook(customer, "978-0-525-47556-9", 2); // available 1

        System.out.println("\n\n");

        // Example 4: Buying out of stock item
        System.out.println("Example #4: Buying out of stock item");
        System.out.println("-------------------------------------");
        paidAmount = store.buyBook(customer, "978-1-56619-909-4", 2);

        System.out.println("\n\n");


    }
}