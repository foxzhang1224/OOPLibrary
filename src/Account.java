package src;

import java.util.ArrayList;
import java.util.List;

public class Account {
    static int idcounter=1;
    public int id=1;
    public String name;
    public ArrayList<Book> borrowedBooks;

    public Account(String name) {
        this.name=name;
        this.id=id++;
        this.borrowedBooks=new ArrayList<Book>();
    }

    //setters and getters

    //id getter
    public int getId(){
        return id;
    }

    //id setter
    public void setId(int id){
        this.id=id;
    }

    //name getter
    public String getName(){
        return name;
    }

    //name setter
    public void setName(String name){
        this.name=name;
    }

    //borrowedBooks getter
    public List<Book> getBorrowedBooks(){
        return borrowedBooks;
    }

    //borrowedBooks setter
    public void setBorrowedBooks(List<Book> borrowedBooks){
        this.borrowedBooks=borrowedBooks;
    }

    //borrow book
    public void borrowBook(Book book){
        borrowedBooks.add(book);
    }

    //borrow book
    public void returnBook(Book book){
        borrowedBooks.remove(book);
    }

}
