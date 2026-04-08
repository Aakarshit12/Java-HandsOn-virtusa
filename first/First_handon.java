import java.util.*;

abstract class Person {
    private int id;
    private String nm;

    Person(int id, String nm) {
        this.id = id;
        this.nm = nm;
    }

    int getId() {
        return id;
    }

    String getNm() {
        return nm;
    }

    abstract void show();
}

class Book {
    private int bid;
    private String title;
    private String issuedTo;

    Book(int bid, String title) {
        this.bid = bid;
        this.title = title;
        this.issuedTo = null;
    }

    int getBid() {
        return bid;
    }

    String getTitle() {
        return title;
    }

    boolean taken() {
        return issuedTo != null;
    }

    void markIssued(String who) {
        issuedTo = who;
    }

    void markReturned() {
        issuedTo = null;
    }

    public String toString() {
        if (issuedTo == null)
            return "[" + bid + "] " + title + " — on shelf";
        return "[" + bid + "] " + title + " — with " + issuedTo;
    }
}

class Member extends Person {

    Book[] holding = new Book[3]; // max=3
    int cnt = 0;

    Member(int id, String nm) {
        super(id, nm);
    }

    void borrow(Book b) {
        if (b == null || b.taken())
            return;

        if (cnt == holding.length) {
            System.out.println(getNm() + ": can't take more, limit is " + holding.length);
            return;
        }
        b.markIssued(getNm());
        holding[cnt++] = b;
    }

    void giveBack(Book b) {
        if (b == null)
            return;
        for (int i = 0; i < cnt; i++) {
            if (holding[i].getBid() == b.getBid()) {
                b.markReturned();
                for (int j = i; j < cnt - 1; j++)
                    holding[j] = holding[j + 1];
                holding[--cnt] = null;
                return;
            }
        }
        System.out.println(getNm() + ": that book isn't with you");
    }

    void show() {
        System.out.println("Member  : " + getNm() + " (ID " + getId() + ") | holding: " + cnt);
    }
}

class Staff extends Person {

    Book[] rack = new Book[100];
    int sz = 0;

    Staff(int id, String nm) {
        super(id, nm);
    }

    void addBook(Book b) {
        if (b == null)
            return;
        if (sz == rack.length) {
            System.out.println("Rack full");
            return;
        }
        rack[sz++] = b;
    }

    Book locate(int bid) {
        for (int i = 0; i < sz; i++)
            if (rack[i].getBid() == bid)
                return rack[i];
        System.out.println("Book #" + bid + " not found ");
        return null;
    }

    void handOut(Member m, int bid) {
        Book b = locate(bid);
        if (b != null)
            m.borrow(b);
    }

    void takeBack(Member m, int bid) {
        Book b = locate(bid);
        if (b != null)
            m.giveBack(b);
    }

    void show() {
        System.out.println("Staff   : " + getNm() + " | rack size: " + sz);
    }
}

public class First_handon {
    public static void main(String[] args) {

        Staff incharge = new Staff(1, "Mr. Sharma");

        incharge.addBook(new Book(101, "Java Programming"));
        incharge.addBook(new Book(102, "Operating Systems"));
        incharge.addBook(new Book(103, "Computer Networks"));
        incharge.addBook(new Book(104, "DBMS"));

        Member aman = new Member(201, "Aman ");
        Member riya = new Member(202, "Riya ");

        // polymorphism
        Person p1 = aman;
        Person p2 = incharge;
        p1.show();
        p2.show();
        System.out.println();

        incharge.handOut(aman, 101);
        incharge.handOut(riya, 102);
        incharge.handOut(riya, 103);

        System.out.println(incharge.locate(101));
        System.out.println(incharge.locate(102));
        System.out.println();

        incharge.takeBack(aman, 101);
        incharge.takeBack(aman, 102);
        System.out.println(incharge.locate(101));
        System.out.println();

        aman.show();
        riya.show();
    }
}