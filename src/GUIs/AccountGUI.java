package src.GUIs;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import src.Book;
import src.Account;
import src.GUIs.MainGUI;
import src.Datafile;

public class AccountGUI {
    private JFrame Accountframe;
    private ArrayList<Book> books;
    private ArrayList<Account> accounts;

    public AccountGUI(ArrayList<Book> books, ArrayList<Account> accounts) {
        this.books=books;
        this.accounts=accounts;

        Accountframe=new JFrame("Library Management System");
        Accountframe.setSize(400, 300);
        Accountframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Accountframe.setLayout(new BorderLayout());

        JLabel caption=new JLabel("Library Management System", SwingConstants.CENTER);
        caption.setFont(new Font("Arial", Font.BOLD, 18));
        Accountframe.add(caption, BorderLayout.NORTH);

        JPanel buttonPanel=new JPanel(new GridLayout(3, 1, 10, 10));

        //lgin button
        JButton loginButton=new JButton("Log In");
        loginButton.addActionListener(e -> login());
        buttonPanel.add(loginButton);

        //register button
        JButton registerButton=new JButton("Register");
        registerButton.addActionListener(e -> register());
        buttonPanel.add(registerButton);

        //exit button
        JButton exitButton=new JButton("Exit");
        exitButton.addActionListener(e -> System.exit(0));
        buttonPanel.add(exitButton);

        Accountframe.add(buttonPanel, BorderLayout.CENTER);
        Accountframe.setVisible(true);
    }

    private void login(){
        String name=JOptionPane.showInputDialog(Accountframe, "Enter your name:");
        if (name==null||name.trim().isEmpty())
            return;

        Account account=accounts.stream()
                .filter(acc -> acc.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);

        if (account==null){
            JOptionPane.showMessageDialog(Accountframe, "Account not found.", "Error", JOptionPane.ERROR_MESSAGE);
        } 
        else{
            new MainGUI(books, accounts, account);
            Accountframe.dispose();
        }
    }

    private void register(){
        String name=JOptionPane.showInputDialog(Accountframe, "Enter your name:");
        if (name==null||name.trim().isEmpty())
            return;

        Account newAccount=new Account(name);
        accounts.add(newAccount);
        Datafile.saveAccounts(accounts);
        JOptionPane.showMessageDialog(Accountframe, "Account created successfully! Your ID is: "+newAccount.getId(),
                "Success", JOptionPane.INFORMATION_MESSAGE);
        new MainGUI(books, accounts, newAccount);
        Accountframe.dispose();
    }
}
