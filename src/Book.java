package src;

public class Book {
    public int id;
    public String title;
    public String author;
    public int yearofpublication;
    public String genre;
    public boolean isBorrowed;


//getters and setters

//id getter
public int getId() {
    return id;
}

//id setter
public void setId(int id) {
    this.id=id;
}

//title getter
public String getTitle() {
    return title;
}

//title setter
public void setTitle(String title) {
    this.title=title;
}

//author getter
public String getAuthor() {
    return author;
}

//author setter
public void setAuthor(String author) {
    this.author=author;
}

//year getter
public int getYear() {
    return yearofpublication;
}

//year setter
public void setYear(int yearofpublication) {
    this.yearofpublication=yearofpublication;
}

//genre getter
public String getGenre() {
    return genre;
}

//genre setter
public void setGenre(String genre) {
    this.genre=genre;
}

}