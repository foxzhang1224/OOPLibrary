package src;

import java.io.*;
import java.util.ArrayList;

public class Datafile {
    private static final String BOOKS_FILE="bookdata.txt";
    private static final String ACCOUNTS_FILE="user_info.txt";

    // Load books from file
    public static ArrayList<Book> loadBooks() {
        ArrayList<Book> books = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(BOOKS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String title = parts[0].trim();
                String author = parts[1].trim();
                String year = parts[2].trim();
                String genre = parts[3].trim();
                boolean isBorrowed = Boolean.parseBoolean(parts[4].trim());
                int borrowedBy = Integer.parseInt(parts[5].trim());

                // Create and set book details
                Book book = new Book(title, author, year, genre);
                book.setIsBorrowed(isBorrowed);
                book.borrowedBy = borrowedBy; 
                books.add(book);
            }
        } catch (IOException e) {
            System.out.println("Error reading books: " + e.getMessage());
        }
        return books;
    }

    // Load accounts from file
    public static ArrayList<Account> loadAccounts(ArrayList<Book> books) {
        ArrayList<Account> accounts = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ACCOUNTS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0].trim();
                Account account = new Account(name);

                // Link borrowed books
                if (parts.length > 1 && !parts[1].trim().isEmpty()) {
                    for (String bookIdStr : parts[1].split(";")) {
                        int bookId = Integer.parseInt(bookIdStr.trim());
                        for (Book book : books) {
                            if (book.getId() == bookId) {
                                account.borrowBook(book);
                            }
                        }
                    }
                }
                accounts.add(account);
            }
        } catch (IOException e) {
            System.out.println("Error reading accounts: " + e.getMessage());
        }
        return accounts;
    }

    // Write books to file
    public static void saveBooks(ArrayList<Book> books) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(BOOKS_FILE))) {
            for (Book book : books) {
                writer.write(book.getTitle() + "," +
                             book.getAuthor() + "," +
                             book.getYear() + "," +
                             book.getGenre() + "," +
                             book.getIsBorrowed() + "," +
                             book.getBorrowedBy());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving books: " + e.getMessage());
        }
    }

    // Write accounts to file
    public static void saveAccounts(ArrayList<Account> accounts) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ACCOUNTS_FILE))) {
            for (Account account : accounts) {
                StringBuilder borrowedBooksStr = new StringBuilder();
                for (Book book : account.getBorrowedBooks()) {
                    borrowedBooksStr.append(book.getId()).append(";");
                }
                if (borrowedBooksStr.length() > 0) {
                    borrowedBooksStr.deleteCharAt(borrowedBooksStr.length() - 1);
                }

                writer.write(account.getName() + "," + borrowedBooksStr);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving accounts: " + e.getMessage());
        }
    }
}
