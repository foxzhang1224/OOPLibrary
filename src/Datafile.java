package src;

import java.io.*;
import java.util.ArrayList;

public class Datafile {
    private static final String BOOKS_FILE = "bookdata.txt";
    private static final String ACCOUNTS_FILE = "userdata.txt";

    public static ArrayList<Book> loadBooks() {
        ArrayList<Book> books=new ArrayList<>();
        try (FileReader fileReader=new FileReader(BOOKS_FILE)) {
            String content="";
            int character;

            while ((character=fileReader.read())!=-1) {
                content+=(char) character;
            }

            String[] lines=content.split(System.lineSeparator());
            for (String line:lines) {
                if (line.trim().isEmpty()){
                    continue;
                }
                String[] info=line.split(",");
                String title=info[0].trim();
                String author=info[1].trim();
                String year=info[2].trim();
                String genre=info[3].trim();
                boolean isBorrowed=Boolean.parseBoolean(info[4].trim());
                int borrowerId=Integer.parseInt(info[5].trim());

                Book book=new Book(title, author, year, genre);
                book.setBorrowed(isBorrowed);
                book.setBorrowerId(borrowerId);
                books.add(book);
            }
        } catch (IOException e) {
            System.out.println("Error reading books: "+e.getMessage());
        }
        return books;
    }

    public static ArrayList<Account> loadAccounts() {
        ArrayList<Account> accounts=new ArrayList<>();
        try (FileReader fileReader=new FileReader(ACCOUNTS_FILE)) {
            String content="";
            int character;
            while ((character=fileReader.read())!=-1) {
                content+=(char) character;
            }
            String[] lines=content.split(System.lineSeparator());
            for (String line : lines) {
                if (line.trim().isEmpty()){
                    continue;
                }
                String[] info=line.split(",");
                String name=info[0].trim();
                Account account=new Account(name);

                if (info.length>1&&!info[1].trim().isEmpty()){
                    for (String bookIdStr:info[1].split(";")) {
                        int bookId=Integer.parseInt(bookIdStr.trim());
                        account.getBorrowedBookIds().add(bookId);
                    }
                }
                accounts.add(account);
            }
        } catch (IOException e) {
            System.out.println("Error reading accounts: "+e.getMessage());
        }
        return accounts;
    }

    public static void saveBooks(ArrayList<Book> books){
        try (FileWriter writer = new FileWriter(BOOKS_FILE)){
            for (Book book : books){
                String bookData=book.getTitle()+","+book.getAuthor()+","+book.getYearOfPublication()+","
                        +book.getGenre()+","+book.isBorrowed()+","+book.getBorrowerId()
                        +System.lineSeparator();
                writer.write(bookData);
            }
        } 
        catch (IOException e) {
            System.out.println("Error saving books: "+e.getMessage());
        }
    }

    public static void saveAccounts(ArrayList<Account> accounts) {
        try (FileWriter writer=new FileWriter(ACCOUNTS_FILE)) {
            for (Account account : accounts) {
                String borrowedBookIds="";
                for (int bookId : account.getBorrowedBookIds()) {
                    borrowedBookIds+=bookId + ";";
                }
                if (!borrowedBookIds.isEmpty()) {
                    borrowedBookIds=borrowedBookIds.substring(0, borrowedBookIds.length() - 1);
                }
                writer.write(account.getName() + "," + borrowedBookIds + System.lineSeparator());
            }
        } 
        catch (IOException e) {
            System.out.println("Error saving accounts: "+e.getMessage());
        }
    }
}
