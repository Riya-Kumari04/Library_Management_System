
    import java.util.*;

public class Console {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BookDAO bookDAO = new BookDAO();
        MemberDAO memberDAO = new MemberDAO();
        BorrowDAO borrowDAO = new BorrowDAO();

        while (true) {
            System.out.println("\n===== Library Menu =====");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Add Member");
            System.out.println("4. View Members");
            System.out.println("5. Issue Book");
            System.out.println("6. Return Book");
            System.out.println("7. Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // flush

            switch (choice) {
                case 1:
                    System.out.print("Title: ");
                    String title = sc.nextLine();
                    System.out.print("Author: ");
                    String author = sc.nextLine();
                    System.out.print("Publisher: ");
                    String publisher = sc.nextLine();
                    System.out.print("Quantity: ");
                    int qty = sc.nextInt();
                    Book book = new Book(title, author, publisher, qty);
                    bookDAO.addBook(book);
                    break;

                case 2:
                    List<Book> books = bookDAO.getAllBooks();
                    for (Book b : books) {
                        System.out.println("ID: " + b.getBookId() + ", Title: " + b.getTitle() + ", Author: " + b.getAuthor() + ", Qty: " + b.getQuantity());
                    }
                    break;

                case 3:
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    System.out.print("Phone: ");
                    String phone = sc.nextLine();
                    Member member = new Member(name, email, phone);
                    memberDAO.addMember(member);
                    break;

                case 4:
                    List<Member> members = memberDAO.getAllMembers();
                    for (Member m : members) {
                        System.out.println("ID: " + m.getMemberId() + ", Name: " + m.getName() + ", Email: " + m.getEmail());
                    }
                    break;

                case 5:
                    System.out.print("Book ID: ");
                    int bookId = sc.nextInt();
                    System.out.print("Member ID: ");
                    int memberId = sc.nextInt();
                    borrowDAO.issueBook(bookId, memberId);
                    break;

                case 6:
                    System.out.print("Borrow ID: ");
                    int borrowId = sc.nextInt();
                    borrowDAO.returnBook(borrowId);
                    break;

                case 7:
                    System.out.println("Goodbye!");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}


