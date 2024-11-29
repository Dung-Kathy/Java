import java.util.ArrayList;
import java.util.Scanner;

// Book class representing a single book
class Book {
    private static int idCounter = 1;
    private int id;
    private String title;
    private String author;
    private String genre;

    public Book(String title, String author, String genre) {
        this.id = idCounter++;
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Title: " + title + ", Author: " + author + ", Genre: " + genre;
    }
}

// LibraryManager class to handle library operations
public class PersonalLibraryManager {
    private ArrayList<Book> library = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        PersonalLibraryManager manager = new PersonalLibraryManager();
        manager.run();
    }

    // Run the main loop of the program
    public void run() {
        System.out.println("Welcome to Personal Library Manager!");
        int choice;
        do {
            printMenu();
            choice = getUserChoice();
            handleChoice(choice);
        } while (choice != 5);

        System.out.println("Thank you for using Personal Library Manager. Goodbye!");
    }

    // Display menu options
    private void printMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Add a book");
        System.out.println("2. Remove a book");
        System.out.println("3. View all books");
        System.out.println("4. Search for a book");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    // Get user input for menu choice
    private int getUserChoice() {
        int choice = -1;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter a number.");
        }
        return choice;
    }

    // Handle user menu choice
    private void handleChoice(int choice) {
        switch (choice) {
            case 1:
                addBook();
                break;
            case 2:
                removeBook();
                break;
            case 3:
                viewAllBooks();
                break;
            case 4:
                searchBooks();
                break;
            case 5:
                // Exit
                break;
            default:
                System.out.println("Invalid choice! Please select from the menu.");
        }
    }

    // Add a new book to the library
    private void addBook() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter book author: ");
        String author = scanner.nextLine();
        System.out.print("Enter book genre: ");
        String genre = scanner.nextLine();

        library.add(new Book(title, author, genre));
        System.out.println("Book added successfully!");
    }

    // Remove a book by ID
    private void removeBook() {
        System.out.print("Enter the ID of the book to remove: ");
        int id;
        try {
            id = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID!");
            return;
        }

        Book toRemove = null;
        for (Book book : library) {
            if (book.getId() == id) {
                toRemove = book;
                break;
            }
        }

        if (toRemove != null) {
            library.remove(toRemove);
            System.out.println("Book removed successfully!");
        } else {
            System.out.println("No book found with the given ID.");
        }
    }

    // Display all books in the library
    private void viewAllBooks() {
        if (library.isEmpty()) {
            System.out.println("No books in the library.");
        } else {
            System.out.println("\nBooks in the library:");
            for (Book book : library) {
                System.out.println(book);
            }
        }
    }

    // Search for books by title or author
    private void searchBooks() {
        System.out.print("Enter title or author to search for: ");
        String query = scanner.nextLine().toLowerCase();

        ArrayList<Book> results = new ArrayList<>();
        for (Book book : library) {
            if (book.getTitle().toLowerCase().contains(query) || book.getAuthor().toLowerCase().contains(query)) {
                results.add(book);
            }
        }

        if (results.isEmpty()) {
            System.out.println("No books found matching the search query.");
        } else {
            System.out.println("\nSearch results:");
            for (Book book : results) {
                System.out.println(book);
            }
        }
    }
}
