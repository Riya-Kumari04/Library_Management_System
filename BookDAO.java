import java.sql.*;
import java.util.*;

public class BookDAO {
    public void addBook(Book book) {
        String sql = "INSERT INTO books (title, author, publisher, quantity) VALUES (?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getPublisher());
            ps.setInt(4, book.getQuantity());
            ps.executeUpdate();
            System.out.println("✅ Book added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Book book = new Book(rs.getInt("book_id"),
                                     rs.getString("title"),
                                     rs.getString("author"),
                                     rs.getString("publisher"),
                                     rs.getInt("quantity"));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public void updateBookQuantity(int bookId, int newQuantity) {
        String sql = "UPDATE books SET quantity = ? WHERE book_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, newQuantity);
            ps.setInt(2, bookId);
            ps.executeUpdate();
            System.out.println("✅ Book quantity updated.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
