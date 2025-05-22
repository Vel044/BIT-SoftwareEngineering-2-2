import java.util.List;

public class Tests {
    public static void main(String[] args) {
        // 创建图书馆实例
        Library myLittleLibrary = new Library();

        // 添加预存的书籍
        Book book1 = new Book("Java程序设计", "张三", 45);
        Book book2 = new Book("Java核心设计", "李四", 50);
        Book book3 = new Book("Java程序设计", "王五", 38);

        myLittleLibrary.addBook(book1);
        myLittleLibrary.addBook(book2);
        myLittleLibrary.addBook(book3);

        // 显示图书馆所有图书信息
        List<Book> allBooks = myLittleLibrary.getAllBooks();
        for (Book book : allBooks) {
            System.out.println(book.toString());
        }

        // 创建读者实例
        Reader mary = new Reader("Mary");

        // 查找书籍
        List<Book> foundBooks = myLittleLibrary.findBook("Java程序设计");
        if (foundBooks.isEmpty()) {
            System.out.println("未找到相关书籍");
        } else {
            System.out.println("找到以下书籍：");
            for (Book book : foundBooks) {
                System.out.println(book.toString());
            }
        }

        // 借书
        if (!foundBooks.isEmpty()) {
            Book borrowedBook = foundBooks.get(0);
            mary.borrowBook(borrowedBook);
        }

        // 再次显示图书馆所有图书信息
        System.out.println("借书后图书馆所有图书信息：");
        allBooks = myLittleLibrary.getAllBooks();
        for (Book book : allBooks) {
            System.out.println(book.toString());
        }

        // 还书
        if (!foundBooks.isEmpty()) {
            Book returnedBook = foundBooks.get(0);
            mary.returnBook(returnedBook);
        }

        // 再次显示图书馆所有图书信息
        System.out.println("还书后图书馆所有图书信息：");
        allBooks = myLittleLibrary.getAllBooks();
        for (Book book : allBooks) {
            System.out.println(book.toString());
        }
    }
}
