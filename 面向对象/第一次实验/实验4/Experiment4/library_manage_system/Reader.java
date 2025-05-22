public class Reader {
    private String name;

    public Reader(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void borrowBook(Book book) {
        if (!book.isBorrowed()) {
            book.setBorrowed(true);
            System.out.println(name + "借阅了《" + book.getName() + "》");
        } else {
            System.out.println("《" + book.getName() + "》已经被借出");
        }
    }

    public void returnBook(Book book) {
        if (book.isBorrowed()) {
            book.setBorrowed(false);
            System.out.println(name + "归还了《" + book.getName() + "》");
        } else {
            System.out.println("《" + book.getName() + "》未被借出");
        }
    }
}

