package src.GUIs;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import src.Book;
import src.Account;
import src.Datafile;

public class MainGUI {
    private JFrame mainframe;
    private ArrayList<Book> books;
    private ArrayList<Account> accounts;
    private Account currentAccount;

    public MainGUI(ArrayList<Book> books, ArrayList<Account> accounts, Account currentAccount) {
        this.books=books;
        this.accounts=accounts;
        this.currentAccount=currentAccount;

        mainframe=new JFrame("Library Management System");
        mainframe.setSize(400, 600);
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainframe.setLayout(new BorderLayout());

        JLabel caption=new JLabel("Main Menu", SwingConstants.CENTER);
        caption.setFont(new Font("Arial", Font.BOLD, 18));
        mainframe.add(caption, BorderLayout.NORTH);

        JPanel buttonPanel=new JPanel(new GridLayout(7, 1, 10, 10));

        // Add New
        JButton AddNew=new JButton("Add New Book");
        AddNew.addActionListener(e -> {
            AddBook();
        });
        buttonPanel.add(AddNew);

        // Search
        JButton search=new JButton("Search Books");
        search.addActionListener(e -> {
            SearchBook();
        });
        buttonPanel.add(search);

        // Browse
        JButton Browse=new JButton("Browse Books");
        Browse.addActionListener(e -> {
            BrowseBooks();
        });
        buttonPanel.add(Browse);

        // Return
        JButton returnBookButton=new JButton("Return Book");
        returnBookButton.addActionListener(e -> {
            ReturnBook();
        });
        buttonPanel.add(returnBookButton);

        // Account info
        JButton accountInfoButton=new JButton("Account Information");
        accountInfoButton.addActionListener(e -> {
            AccountInfo();
        });
        buttonPanel.add(accountInfoButton);

        // Save and Exit
        JButton Exit=new JButton("Save and Exit");
        Exit.addActionListener(e -> {
            Datafile.saveBooks(books);
            Datafile.saveAccounts(accounts);
            JOptionPane.showMessageDialog(mainframe, "Data saved successfully. Goodbye!", "Save and Exit",
                    JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        });
        buttonPanel.add(Exit);
        mainframe.add(buttonPanel, BorderLayout.CENTER);
        mainframe.setVisible(true);
    }

    private void AddBook(){
        new AddBookGUI(books);
    }

    private void SearchBook(){
        new SearchGUI(books, accounts, currentAccount);
    }

    private void BrowseBooks(){
        new BrowseGUI(books, accounts, currentAccount);
    }

    private void ReturnBook(){
        new ReturnGUI(books, accounts, currentAccount);
    }

    private void AccountInfo(){
        new AccountInformationGUI(currentAccount, books, accounts);
    }
}
