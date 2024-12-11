package src.GUIs;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import src.Book;

public class ModifyGUI {
    private JFrame modifyFrame;
    private Book book;
    private ArrayList<Book> books;

    public ModifyGUI(Book book, ArrayList<Book> books) {
        this.book=book;
        this.books=books;

        modifyFrame=new JFrame("Modify Book Information");
        modifyFrame.setSize(400, 300);
        modifyFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        modifyFrame.setLayout(new GridLayout(6, 2, 10, 10));

        modifyFrame.add(new JLabel("Title:"));
        JTextField titleField=new JTextField(book.getTitle());
        modifyFrame.add(titleField);

        modifyFrame.add(new JLabel("Author:"));
        JTextField authorField=new JTextField(book.getAuthor());
        modifyFrame.add(authorField);

        modifyFrame.add(new JLabel("Year:"));
        JTextField yearField=new JTextField(book.getYearOfPublication());
        modifyFrame.add(yearField);

        modifyFrame.add(new JLabel("Genre:"));
        JTextField genreField=new JTextField(book.getGenre());
        modifyFrame.add(genreField);

        JButton saveButton=new JButton("Save Changes");
        saveButton.addActionListener(e -> {
            String newTitle=titleField.getText().trim();
            String newAuthor=authorField.getText().trim();
            String newYear=yearField.getText().trim();
            String newGenre=genreField.getText().trim();
            save(newTitle, newAuthor, newYear, newGenre);
        });
        modifyFrame.add(saveButton);

        JButton backButton=new JButton("Back");
        backButton.addActionListener(e -> modifyFrame.dispose());
        modifyFrame.add(backButton);
        modifyFrame.setVisible(true);
    }

    private void save(String newTitle, String newAuthor, String newYear, String newGenre) {
        if (newTitle.isEmpty()||newAuthor.isEmpty()||newYear.isEmpty()||newGenre.isEmpty()) {
            JOptionPane.showMessageDialog(modifyFrame, "All fields must be filled out.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        //modify book info
        book.setTitle(newTitle);
        book.setAuthor(newAuthor);
        book.setYearOfPublication(newYear);
        book.setGenre(newGenre);
        JOptionPane.showMessageDialog(modifyFrame, "Book information updated successfully!", "Success",
                JOptionPane.INFORMATION_MESSAGE);
        modifyFrame.dispose();
    }
}
