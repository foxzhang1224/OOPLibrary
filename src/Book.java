package src;

public class Book {
    private static int lastID=1;
    private int id;
    private String title;
    private String author;
    private String yearOfPublication;
    private String genre;
    private boolean availability;
    private int borrowerId;

    public Book(String title, String author, String yearOfPublication, String genre) {
        this.id=lastID++;
        this.title=title;
        this.author=author;
        this.yearOfPublication=yearOfPublication;
        this.genre=genre;
        this.availability=false;
        this.borrowerId=-1;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title=title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author=author;
    }

    public String getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(String yearOfPublication) {
        this.yearOfPublication=yearOfPublication;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre=genre;
    }

    public boolean isBorrowed() {
        return availability;
    }

    public void setBorrowed(boolean borrowed) {
        availability=borrowed;
    }

    public int getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(int borrowerId) {
        this.borrowerId=borrowerId;
    }
}
