public class Book {
    private String name;
    private String author;
    private double price;
    private boolean isBorrowed;

    public Book(String name, String author, double price) {
        this.name = name;
        this.author = author;
        this.price = price;
        this.isBorrowed = false;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }

    @Override
    public String toString() {
        String status = isBorrowed ? "Î´»¹" : "¿É½è";
        return name + "£¬" + author + "Öø£¬" + price + "Ôª£¬" + status;
    }
}