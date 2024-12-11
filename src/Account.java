package src;

import java.util.ArrayList;

public class Account {
    private static int idCounter=1;
    private int id;
    private String name;
    private ArrayList<Integer> borrowedBookIds;

    public Account(String name) {
        this.id=idCounter++;
        this.name=name;
        this.borrowedBookIds=new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Integer> getBorrowedBookIds() {
        return borrowedBookIds;
    }

    public void borrowBook(Book book) {
        if (!book.isBorrowed()) {
            book.setBorrowed(true);
            book.setBorrowerId(this.id);
            borrowedBookIds.add(book.getId());
        }
    }

    public void returnBook(Book book) {
        if (borrowedBookIds.contains(book.getId())) {
            book.setBorrowed(false);
            book.setBorrowerId(-1);
            borrowedBookIds.remove(Integer.valueOf(book.getId()));
        }
    }
}
