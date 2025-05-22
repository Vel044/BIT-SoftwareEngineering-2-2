import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public List<Book> findBook(String name) {
        List<Book> foundBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getName().equals(name)) {
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }

    public List<Book> getAllBooks() {
        return books;
    }
}