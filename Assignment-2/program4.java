import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// 1. Implementing Comparable (Natural Ordering)
// We add "implements Comparable<Book>" to modify the class itself.
class Book implements Comparable<Book> {
    private int id;
    private String title;
    private double price;

    public Book(int id, String title, double price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public double getPrice() { return price; }

    /**
     * This method defines the "Natural Order" of a Book.
     * We will make the Book ID the default way to sort.
     */
    @Override
    public int compareTo(Book other) {
        // Integer.compare handles the mathematical comparison (returns -1, 0, or 1)
        return Integer.compare(this.id, other.id);
    }

    @Override
    public String toString() {
        return String.format("Book[ID: %d, Title: '%s', Price: $%.2f]", id, title, price);
    }
}

// 2. Implementing Comparator (Custom Ordering)
// This is an external class. It does not modify the Book class itself.
class PriceComparator implements Comparator<Book> {
    @Override
    public int compare(Book b1, Book b2) {
        // Sorts books by price in ascending order
        return Double.compare(b1.getPrice(), b2.getPrice());
    }
}

public class SortingDemo {
    public static void main(String[] args) {
        List<Book> library = new ArrayList<>();
        library.add(new Book(301, "The Great Gatsby", 15.99));
        library.add(new Book(105, "1984", 12.50));
        library.add(new Book(202, "To Kill a Mockingbird", 18.00));
        library.add(new Book(404, "Brave New World", 14.25));

        System.out.println("=== 1. Original Unsorted List ===");
        printList(library);

        /* * Using Comparable: 
         * Collections.sort() automatically looks for the compareTo() method inside the Book class.
         */
        Collections.sort(library); 
        System.out.println("\n=== 2. Sorted by Natural Order (Book ID using Comparable) ===");
        printList(library);

        /* * Using Comparator (Traditional Way): 
         * We pass an instance of our custom PriceComparator to dictate the sorting rule.
         */
        Collections.sort(library, new PriceComparator());
        System.out.println("\n=== 3. Sorted by Price (using custom PriceComparator) ===");
        printList(library);

        /* * Using Comparator (Modern Java Lambda Way): 
         * Since Java 8, you don't even need to write a separate Comparator class. 
         * You can define the sorting rule inline using a lambda expression or method reference.
         */
        library.sort(Comparator.comparing(Book::getTitle));
        System.out.println("\n=== 4. Sorted Alphabetically by Title (using Modern Java Lambda) ===");
        printList(library);
    }

    // Helper method to print the list cleanly
    private static void printList(List<Book> books) {
        for (Book book : books) {
            System.out.println(book);
        }
    }
}
