package src;

import java.util.ArrayList;

import src.GUIs.MainGUI;

public class Main {
    public static void main(String[] args) {
        ArrayList<Book> books = Datafile.readBooks();
        ArrayList<Account> accounts=Datafile.readAccounts();
        javax.swing.SwingUtilities.invokeLater(() -> new MainGUI().display());
    }
}
