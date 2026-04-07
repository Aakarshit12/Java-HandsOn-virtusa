
import java.util.*;

// base class
abstract class Person {
    private int id;
    private String name;

    Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    int getId() {
        return id;
    }

    String getName() {
        return name;
    }

    abstract void display();
}

// Book
class Book {
    private int bookId;
    private String title;
    private boolean issued;

    Book(int bookId, String title) {
        this.bookId = bookId;
        this.title = title;
        this.issued = false;
    }

    int getBookId() {
        return bookId;
    }

    String getTitle() {
        return title;
    }

    boolean isIssued() {
        return issued;
    }

    void issue() {
        issued = true;
    }

    void giveBack() {
        issued = false;
    }

    public String toString() {
        return title + "-" + (issued ? "Out" : "In");
    }
}

// Member
class Member extends Person {
    private Book[] takenBooks = new Book[10];
    private int count = 0;

    Member(int id, String name) {
        super(id, name);
    }

    void borrowBook(Book b) {
        if (b == null)
            return;
        if (b.isIssued())
            return;
        if (count >= takenBooks.length)
            return;

        b.issue();
        takenBooks[count] = b;
        count++;
    }

    void returnBook(Book b) {
        if (b == null)
            return;

        for (int i = 0; i < count; i++) {
            if (takenBooks[i] == b) {
                b.giveBack();
                for (int j = i; j < count - 1; j++) {
                    takenBooks[j] = takenBooks[j + 1];
                }
                takenBooks[count - 1] = null;
                count--;
                break;
            }
        }
    }

    void display() {
        System.out.println("Member " + getName() + " books:" + count);
    }
}

// Librarian
class Librarian extends Person {
    private Book[] stock = new Book[20];
    private int size = 0;

    Librarian(int id, String name) {
        super(id, name);
    }

    void addBook(Book b) {
        if (b == null)
            return;
        if (size >= stock.length)
            return;

        stock[size] = b;
        size++;
    }

    Book findBook(int id) {
        for (int i = 0; i < size; i++) {
            if (stock[i].getBookId() == id) {
                return stock[i];
            }
        }
        return null;
    }

    void issueBook(Member m, int id) {
        Book b = findBook(id);
        if (b != null)
            m.borrowBook(b);
    }

    void returnBook(Member m, int id) {
        Book b = findBook(id);
        if (b != null)
            m.returnBook(b);
    }

    void display() {
        System.out.println("Librarian " + getName() + " total:" + size);
    }
}

// main Class
public class First_HandsOn {
    public static void main(String[] args) {

        Librarian lib = new Librarian(1, "Sir");

        Book b1 = new Book(1, "Java");
        Book b2 = new Book(2, "OS");
        Book b3 = new Book(3, "CN");

        lib.addBook(b1);
        lib.addBook(b2);
        lib.addBook(b3);

        Member m1 = new Member(101, "Aman");
        Member m2 = new Member(102, "Riya");

        // polymorphism example
        Person p1 = m1;
        Person p2 = lib;

        p1.display();
        p2.display();

        // borrowing the book
        lib.issueBook(m1, 1);
        lib.issueBook(m2, 2);

        System.out.println(b1);
        System.out.println(b2);

        // returning the book
        lib.returnBook(m1, 1);

        System.out.println(b1);

        m1.display();
        m2.display();
    }
}
