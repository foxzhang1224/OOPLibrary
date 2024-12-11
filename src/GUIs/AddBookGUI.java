package src.GUIs;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import src.Book;

public class AddBookGUI {
    private JFrame addframe;
    private ArrayList<Book> books;
    private JTextField titleField, authorField, yearField, genreField;

    public AddBookGUI(ArrayList<Book> books) {
        this.books=books;

        addframe=new JFrame("Add Book");
        addframe.setSize(400, 300);
        addframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addframe.setLayout(new GridLayout(6, 2, 10, 10));

        //title
        addframe.add(new JLabel("Title:"));
        titleField=new JTextField();
        addframe.add(titleField);

        //author
        addframe.add(new JLabel("Author:"));
        authorField=new JTextField();
        addframe.add(authorField);

        //year
        addframe.add(new JLabel("Year of Publication:"));
        yearField=new JTextField();
        addframe.add(yearField);

        //genre
        addframe.add(new JLabel("Genre:"));
        genreField=new JTextField();
        addframe.add(genreField);

        //add button
        JButton addButton=new JButton("Add Book");
        addButton.addActionListener(e -> addBook());
        addframe.add(addButton);

        //cancel button
        JButton cancelButton=new JButton("Cancel");
        cancelButton.addActionListener(e -> addframe.dispose());
        addframe.add(cancelButton);
        addframe.setVisible(true);
    }

    private void addBook(){
        String title=titleField.getText().trim();
        String author=authorField.getText().trim();
        String year=yearField.getText().trim();
        String genre=genreField.getText().trim();

        if (title.isEmpty()||author.isEmpty()||year.isEmpty()||genre.isEmpty()){
            JOptionPane.showMessageDialog(addframe, "Must provide all information!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Integer.parseInt(year);
            Book newBook=new Book(title, author, year, genre);
            books.add(newBook);
            JOptionPane.showMessageDialog(addframe, "Book added successfully!", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            addframe.dispose();
        } 
        catch (NumberFormatException ex){
            JOptionPane.showMessageDialog(addframe, "Invalid Year!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
