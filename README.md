# Fawry Internship Assessment 2

This is a simple book store application built with Java, illustrating the core OOP principles such as encapsulation, inheritance, and polymorphism. The project is aimed for the Fawry internship assessment.

# Table of Contents
1. [Project Structure](#project-structure)
1. [Models](#models)
2. [Design Choices](#design-choices)
3. [Examples](#examples)
4. [Screenshots](#screenshots)
5. [Installation and Usage](#installation-and-usage)

# Project Structure
```
bookstore/
├── src/
│   ├── bookstore/
│   │   ├── models/
│   │   │   ├── Book.java
│   │   │   ├── Person.java
│   │   │   ├── Customer.java
│   │   │   ├── Admin.java
│   │   │   ├── PaperBook.java
│   │   │   ├── EBook.java
|   │   │   ├── DemoBook.java
│   │   |   ├── Sellable.java
│   │   ├── services/
│   │   │   ├── BookStore.java
│   │   |   ├── Inventory.java
│   │   |   ├── ShippingService.java
│   │   |   ├── MailService.java
│   ├──  Main.java
├── README.md 

```

# Models
The models in this project represent the core entities of the bookstore application:
- `Book`: An abstract class representing a book with common attributes and methods.
- `PaperBook`: A concrete class extending `Book`, representing physical books.
- `EBook`: A concrete class extending `Book`, representing digital books.
- `DemoBook`: A concrete class extending `Book`, representing demo books that are not yet offered for sale.
- `Person`: A base class representing a person with common attributes.
- `Customer`: A class extending `Person`, representing a customer who interact to buy books from the store.
- `Admin`: A class extending `Person`, representing an admin who manages the bookstore, add/remove books from the store, remove outdated books.
- `Sellable`: An interface that defines the contract for sellable items in the bookstore.
- `Inventory`: A class that manages the collection of books in the bookstore.
- `ShippingService`: A class that handles the shipping of books to customers.
- `MailService`: A class that handles email notifications for customers.

# Design Choices
pass for now

# Examples
The following examples illustrate how the bookstore application operates against different scenarios:

### Examples #1: Buying an Item Successfully
```java
PaperBook book2 = new PaperBook("978-0-123-45678-1", "Pride and Prejudice", 49.9f, LocalDate.of(1995, 6, 12));
store.addBookToStore(admin, book2, 10);
...
System.out.println("Amounts of the book before transaction: " + store.bookStock("978-0-123-45678-1"));
float paidAmount = store.buyBook(customer, "978-0-123-45678-1", 2);
if (paidAmount > 0) {
    System.out.println("Total Price: " +  paidAmount);
}
System.out.println("Amounts of the book after transaction: " + store.bookStock("978-0-123-45678-1"));
```

Output:
```
Examples #1: Buying an Item Successfully
----------------------------------------
Amounts of the book before transaction: 10
Transaction Done Successfully

2X Pride and Prejudice (978-0-123-45678-1) are shipped to 6th of October, Cairo
Total Price: 99.8
Amounts of the book after transaction: 8
``` 

### Examples #2: Buying an item that doesn't exist
```java
paidAmount =  store.buyBook(customer, "Notrealisbn", 2); // -1
```

Output:
```
Examples #2: Buying a book not found in the Store
--------------------------------------
Book doesn't exist
```

### Examples #3: Buying an item with insufficient stock
```java
PaperBook book10 = new PaperBook("978-0-525-47556-9", "The Catcher in the Rye", 55.0f, LocalDate.of(2022, 8, 3));
store.addBookToStore(admin, book10, 1);
...
paidAmount =  store.buyBook(customer, "978-0-525-47556-9", 2);
```

Output:
``` 
Examples #3: Buying a book more than the amount found in the stock
--------------------------------------
No enough stock for the given book, Available: 1 Requested: 2
```

### Examples #4: Buying out of stock item
```java
PaperBook book9 = new PaperBook("978-1-56619-909-4", "Lord of the Flies", 48.2f, LocalDate.of(2005, 10, 10));
store.addBookToStore(admin, book9, 0);
...
paidAmount = store.buyBook(customer, "978-1-56619-909-4", 2); \\ -1
```
Output:
```
Example #4: Buying out of stock item
-------------------------------------
Book 978-1-56619-909-4 is out of stock
```
### Examples #5: Buying an Ebook
```java
Ebook ebook2 = new Ebook("EBK2002", "Effective Java", 135.5f, ".epub", LocalDate.of(2001, 5, 15));
store.addBookToStore(admin, ebook2, 100); // quantity is ignored for Ebook
...
paidAmount = store.buyBook(customer, "EBK2002", 1824231); // quantity ignored in Ebook transactions
```
Output:
```
Example #5: Buying an Ebook successfully
-------------------------------------
Sending Effective Java (EBK2002).epub to > abanoub.user.email@gmail.com
Total Price: 135.5
```

### Examples #6: Buying a Demo Book
```java
DemoBook dbook1 = new DemoBook("DEMO9313", "Some Title", LocalDate.of(2024, 1, 1));
store.addBookToStore(admin, dbook1, 100);
...
paidAmount = store.buyBook(customer, "DEMO9313", 1); // -1
```

Output:
```
Example #6: Buying a book not for sale
-------------------------------------
This book is not for sale right now
```

### Example #7: Removing a book
```java
store.removeBookFromStore(admin, "978-0-123-45678-1");
int amount = store.bookStock("978-0-123-45678-1"); // -1 because it's not found
if (amount < 0) {
    System.out.println("Amount " +  amount);
}
```

Output:
```
Example #7: Removing a book
-------------------------------------
This book is out of stock or not existed
Amount -1
```

### Example #8: Removing Outdated Books
```java
store.removeOutdatedBooks(admin);
```

This method will remove all books that are older than 25 years from the current date. It will also print the names of the removed books.

Output:
```
Example #8: Removing outdated books
------------------------------------
There are 7 outdated books removed
	- Design Patterns (EBK2005).azw3 > 1994-11-05
	- The Pragmatic Programmer (EBK2003).mobi > 1999-10-30
	- To Kill a Mockingbird (978-0-06-112008-4) > 1998-02-17
	- Clean Code (EBK2001).pdf > 1990-06-10
	- The Great Gatsby (978-3-598-21500-1) > 2000-07-30
	- Brave New World (978-0-452-28423-4) > 1988-11-05
	- 1984 (978-1-234-56789-2) > 1980-04-20
```

# Screenshot

![Working App Screenshot](/working_app_screenshot.png)