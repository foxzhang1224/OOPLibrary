package src.GUIs;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import src.Book;
import src.Account;

public class BrowseGUI {
    private JFrame browseFrame;
    private JTable table;
    private ArrayList<Book> books;
    private ArrayList<Account> accounts;
    private Account currentAccount;

    public BrowseGUI(ArrayList<Book> books, ArrayList<Account> accounts, Account currentAccount) {
        this.books=books;
        this.accounts=accounts;
        this.currentAccount=currentAccount;

        browseFrame=new JFrame("Browse Books");
        browseFrame.setSize(600, 400);
        browseFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        browseFrame.setLayout(new BorderLayout());

        String[] columns={ "ID", "Title", "Author", "Year", "Genre", "Available" };
        updateTable(columns);

        JPanel buttonPanel=new JPanel(new FlowLayout());

        // borrow button
        JButton borrowButton=new JButton("Borrow");
        borrowButton.setPreferredSize(new Dimension(100, 30));
        borrowButton.addActionListener(e -> borrowBook());
        buttonPanel.add(borrowButton);

        // modify button
        JButton modifyButton=new JButton("Modify");
        modifyButton.addActionListener(e -> modifyBook());
        buttonPanel.add(modifyButton);

        // back button
        JButton backButton=new JButton("Back");
        backButton.addActionListener(e -> browseFrame.dispose());
        buttonPanel.add(backButton);

        browseFrame.add(buttonPanel, BorderLayout.SOUTH);
        browseFrame.setVisible(true);
    }

    //update
    private void updateTable(String[] columns) {
        String[][] data=new String[books.size()][columns.length];
        for (int i=0;i<books.size();i++) {
            Book book=books.get(i);
            data[i][0]=String.valueOf(book.getId());
            data[i][1]=book.getTitle();
            data[i][2]=book.getAuthor();
            data[i][3]=book.getYearOfPublication();
            data[i][4]=book.getGenre();
            data[i][5]=book.isBorrowed() ? "No" : "Yes";
        }

        table=new JTable(data, columns);
        browseFrame.add(new JScrollPane(table), BorderLayout.CENTER);
    }

    private void borrowBook() {
        int selectedRow=table.getSelectedRow();
        if (selectedRow==-1) {
            JOptionPane.showMessageDialog(browseFrame, "Please select a book to borrow.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        Book selectedBook = books.get(selectedRow);

        if (selectedBook.isBorrowed()){
            JOptionPane.showMessageDialog(browseFrame, "This book is already borrowed.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } 
        else{
            currentAccount.borrowBook(selectedBook);
            JOptionPane.showMessageDialog(browseFrame, "Book borrowed successfully!", "Success",
                    JOptionPane.INFORMATION_MESSAGE);

            updateTable(new String[] { "ID", "Title", "Author", "Year", "Genre", "Available" });
            browseFrame.revalidate();
            browseFrame.repaint();
        }
    }

    private void modifyBook(){
        int selectedRow=table.getSelectedRow();
        if (selectedRow==-1) {
            JOptionPane.showMessageDialog(browseFrame, "Please select a book to modify.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        Book selectedBook=books.get(selectedRow);
        new ModifyGUI(selectedBook, books);
        updateTable(new String[] { "ID", "Title", "Author", "Year", "Genre", "Available" });
        browseFrame.revalidate();
        browseFrame.repaint();
    }
}
