package src.GUIs;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import src.Book;
import src.Account;
import src.Datafile;

public class ReturnGUI {
    private JFrame returnFrame;
    private JTextField bookIdField;
    private ArrayList<Book> books;
    private ArrayList<Account> accounts;
    private Account currentAccount;

    public ReturnGUI(ArrayList<Book> books, ArrayList<Account> accounts, Account currentAccount) {
        this.books=books;
        this.accounts=accounts;
        this.currentAccount=currentAccount;

        returnFrame=new JFrame("Return Book");
        returnFrame.setSize(400, 200);
        returnFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        returnFrame.setLayout(new GridLayout(3, 2, 10, 10));
        returnFrame.add(new JLabel("Book ID:"));
        bookIdField=new JTextField();
        returnFrame.add(bookIdField);

        JButton returnButton=new JButton("Return");
        returnButton.addActionListener(e -> returnBook());
        returnFrame.add(returnButton);

        JButton backButton=new JButton("Back");
        backButton.addActionListener(e -> returnFrame.dispose());
        returnFrame.add(backButton);

        returnFrame.setVisible(true);
    }

    private void returnBook(){
        try{
            int bookId=Integer.parseInt(bookIdField.getText().trim());
            Book bookToReturn=books.stream()
                    .filter(book -> book.getId()==bookId&&book.getBorrowerId()==currentAccount.getId())
                    .findFirst()
                    .orElse(null);

            if (bookToReturn==null){
                JOptionPane.showMessageDialog(returnFrame, "You cannot return this book.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } 
            else{
                bookToReturn.setBorrowed(false);
                bookToReturn.setBorrowerId(-1);
                currentAccount.returnBook(bookToReturn);
                JOptionPane.showMessageDialog(returnFrame, "Book returned successfully!", "Success",
                        JOptionPane.INFORMATION_MESSAGE);

                returnFrame.dispose();
            }
        } 
        catch (NumberFormatException ex){
            JOptionPane.showMessageDialog(returnFrame, "Invalid book ID. Please enter a numeric ID.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
