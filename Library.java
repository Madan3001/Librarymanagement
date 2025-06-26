import java.util.*;

public class Library {
    private ArrayList<Book> books;
    private ArrayList<User> users;
    private Scanner scanner = new Scanner(System.in);

    public Library() {
        books = new ArrayList<>();
        users = new ArrayList<>();
    }

    public void start() {
        System.out.println("Welcome to the Library System!");
        while (true) {
            System.out.println("\n1. Add Book");
            System.out.println("2. Register User");
            System.out.println("3. Show Books");
            System.out.println("4. Issue Book");
            System.out.println("5. Return Book");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            switch (choice) {
                case 1 -> addBook();
                case 2 -> registerUser();
                case 3 -> showBooks();
                case 4 -> issueBook();
                case 5 -> returnBook();
                case 6 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println(" Invalid choice");
            }
        }
    }

    private void addBook() {
        System.out.print("Enter Book Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Author Name: ");
        String author = scanner.nextLine();
        books.add(new Book(title, author));
        System.out.println("Book added.");
    }

    private void registerUser() {
        System.out.print("Enter User Name: ");
        String name = scanner.nextLine();
        users.add(new User(name));
        System.out.println(" User registered.");
    }

    private void showBooks() {
        if (books.isEmpty()) {
            System.out.println(" No books available.");
            return;
        }
        for (Book b : books) {
            System.out.println(b);
        }
    }

    private void issueBook() {
        System.out.print("Enter User Name: ");
        String name = scanner.nextLine();
        User user = findUser(name);
        if (user == null) {
            System.out.println(" User not found.");
            return;
        }

        System.out.print("Enter Book Title: ");
        String title = scanner.nextLine();
        Book book = findBook(title);
        if (book == null || book.isIssued()) {
            System.out.println(" Book not available.");
            return;
        }

        if (user.issueBook(book)) {
            System.out.println(" Book issued to " + name);
        } else {
            System.out.println(" User already has a book or book is issued.");
        }
    }

    private void returnBook() {
        System.out.print("Enter User Name: ");
        String name = scanner.nextLine();
        User user = findUser(name);
        if (user == null || user.getIssuedBook() == null) {
            System.out.println(" No issued book found for user.");
            return;
        }

        if (user.returnBook()) {
            System.out.println(" Book returned by " + name);
        } else {
            System.out.println(" Return failed.");
        }
    }

    private Book findBook(String title) {
        for (Book b : books) {
            if (b.getTitle().equalsIgnoreCase(title)) {
                return b;
            }
        }
        return null;
    }

    private User findUser(String name) {
        for (User u : users) {
            if (u.getName().equalsIgnoreCase(name)) {
                return u;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        new Library().start();
    }
}