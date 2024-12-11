package src.GUIs;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import src.Book;
import src.Account;

public class AccountInformationGUI {
    private JFrame AccountInformationFrame;
    private Account account;
    private ArrayList<Book> books;

    public AccountInformationGUI(Account account, ArrayList<Book> books, ArrayList<Account> accounts) {
        this.account=account;
        this.books=books;
        AccountInformationFrame=new JFrame("Account Information");
        AccountInformationFrame.setSize(600, 500);
        AccountInformationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        AccountInformationFrame.setLayout(new BorderLayout(10, 10));

        JPanel headerPanel=new JPanel(new GridLayout(3, 1));
        JLabel accountIdLabel=new JLabel("Account ID: " + account.getId(), SwingConstants.CENTER);
        JLabel accountNameLabel=new JLabel("Name: " + account.getName(), SwingConstants.CENTER);
        JLabel booksBorrowedLabel=new JLabel("Books Borrowed: " + account.getBorrowedBookIds().size(),
                SwingConstants.CENTER);
        accountIdLabel.setFont(new Font("Arial", Font.BOLD, 16));
        accountNameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        booksBorrowedLabel.setFont(new Font("Arial", Font.BOLD, 16));
        headerPanel.add(accountIdLabel);
        headerPanel.add(accountNameLabel);
        headerPanel.add(booksBorrowedLabel);
        AccountInformationFrame.add(headerPanel, BorderLayout.NORTH);

        String[] columns={ "ID", "Title", "Author", "Year", "Genre" };
        ArrayList<Book> borrowedBooks=new ArrayList<>();
        for (Book book : books) {
            if (book.getBorrowerId()==account.getId()){
                borrowedBooks.add(book);
            }
        }
        String[][] data=new String[borrowedBooks.size()][columns.length];
        for (int i=0;i<borrowedBooks.size();i++) {
            Book book=borrowedBooks.get(i);
            data[i][0] = String.valueOf(book.getId());
            data[i][1] = book.getTitle();
            data[i][2] = book.getAuthor();
            data[i][3] = book.getYearOfPublication();
            data[i][4] = book.getGenre();
        }
        JTable table = new JTable(data, columns);
        table.setEnabled(false);
        AccountInformationFrame.add(new JScrollPane(table), BorderLayout.CENTER);

        //back button
        JButton backButton=new JButton("Back");
        backButton.addActionListener(e -> AccountInformationFrame.dispose());
        JPanel buttonPanel=new JPanel(new FlowLayout());
        buttonPanel.add(backButton);
        AccountInformationFrame.add(buttonPanel, BorderLayout.SOUTH);
        AccountInformationFrame.setVisible(true);
    }
}
