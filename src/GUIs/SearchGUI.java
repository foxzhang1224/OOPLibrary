package src.GUIs;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.stream.Collectors;
import src.Book;
import src.Account;

public class SearchGUI {
    private JFrame searchFrame;

    public SearchGUI(ArrayList<Book> books, ArrayList<Account> accounts, Account currentAccount) {
        searchFrame=new JFrame("Search Books");
        searchFrame.setSize(600, 400);
        searchFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        searchFrame.setLayout(new BorderLayout());

        JPanel searchPanel=new JPanel(new FlowLayout());

        //search dropdown menu
        JComboBox<String> searchType=new JComboBox<>(
                new String[] { "ID", "Title", "Genre", "Author", "Year of Publication" });
        searchType.setFont(new Font("Arial", Font.PLAIN, 14));
        searchPanel.add(searchType);

        //search
        JTextField searchField=new JTextField(20);
        searchField.setFont(new Font("Arial", Font.PLAIN, 16));
        searchPanel.add(searchField);
        searchFrame.add(searchPanel, BorderLayout.NORTH);


        JPanel buttonPanel=new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton searchButton=new JButton("Search");
        searchButton.setPreferredSize(new Dimension(100, 30));
        searchButton.addActionListener(e -> {
            String selectedSearchType=(String) searchType.getSelectedItem();
            String query=searchField.getText().trim().toLowerCase();
            searchBooks(books, accounts, currentAccount, selectedSearchType, query);
        });
        buttonPanel.add(searchButton);

        //back button
        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(100, 30));
        backButton.addActionListener(e -> searchFrame.dispose());
        buttonPanel.add(backButton);
        searchFrame.add(buttonPanel, BorderLayout.SOUTH);

        searchFrame.setVisible(true);
    }

    private void searchBooks(ArrayList<Book> books, ArrayList<Account> accounts, Account currentAccount,
            String searchType, String query) {
        ArrayList<Book> results=new ArrayList<>();

        switch (searchType){
            case "ID":
                try {
                    int id = Integer.parseInt(query);
                    results = books.stream()
                            .filter(book -> book.getId() == id)
                            .collect(Collectors.toCollection(ArrayList::new));
                } 
                catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(searchFrame, "ID must be a valid number.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                break;

            case "Title":
                results=books.stream()
                        .filter(book -> book.getTitle().toLowerCase().contains(query))
                        .collect(Collectors.toCollection(ArrayList::new));
                break;

            case "Genre":
                results=books.stream()
                        .filter(book -> book.getGenre().toLowerCase().contains(query))
                        .collect(Collectors.toCollection(ArrayList::new));
                break;

            case "Author":
                results=books.stream()
                        .filter(book -> book.getAuthor().toLowerCase().contains(query))
                        .collect(Collectors.toCollection(ArrayList::new));
                break;

            case "Year of Publication":
                results=books.stream()
                        .filter(book -> book.getYearOfPublication().toLowerCase().contains(query))
                        .collect(Collectors.toCollection(ArrayList::new));
                break;

            default:
                JOptionPane.showMessageDialog(searchFrame, "Invalid search type.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
        }

        if (results.isEmpty()){
            JOptionPane.showMessageDialog(searchFrame, "No results found.", "Search Results",
                    JOptionPane.INFORMATION_MESSAGE);
        } 
        else{
            new BrowseGUI(results, accounts, currentAccount);
        }
    }
}