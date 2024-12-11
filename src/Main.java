package src;

import src.GUIs.AccountGUI;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Book> books=Datafile.loadBooks();
        ArrayList<Account> accounts=Datafile.loadAccounts();

        for (Account account:accounts) {
            for (int bookId:account.getBorrowedBookIds()) {
                Book borrowedBook=books.stream().filter(book -> book.getId()==bookId).findFirst().orElse(null);
                if (borrowedBook!=null) {
                    account.borrowBook(borrowedBook);
                }
            }
        }
        javax.swing.SwingUtilities.invokeLater(() -> new AccountGUI(books, accounts));
    }
}
