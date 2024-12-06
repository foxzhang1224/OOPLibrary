package src;

import java.util.List;

public class Account {
    static int id;
    public String name;
    public List<Book> borrowedBooks;

    public Account(String name) {
        this.name=name;
        this.id=id++;
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
