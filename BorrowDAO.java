import java.sql.*;
import java.time.LocalDate;

public class BorrowDAO {
    public void issueBook(int bookId, int memberId) {
        String checkSql = "SELECT quantity FROM books WHERE book_id = ?";
        String updateBookSql = "UPDATE books SET quantity = quantity - 1 WHERE book_id = ?";
        String insertSql = "INSERT INTO borrowed (book_id, member_id, borrow_date, due_date) VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement checkPs = con.prepareStatement(checkSql);
             PreparedStatement updateBookPs = con.prepareStatement(updateBookSql);
             PreparedStatement insertPs = con.prepareStatement(insertSql)) {

            checkPs.setInt(1, bookId);
            ResultSet rs = checkPs.executeQuery();
            if (rs.next() && rs.getInt("quantity") > 0) {
                updateBookPs.setInt(1, bookId);
                updateBookPs.executeUpdate();

                LocalDate borrowDate = LocalDate.now();
                LocalDate dueDate = borrowDate.plusDays(14); // 14-day default

                insertPs.setInt(1, bookId);
                insertPs.setInt(2, memberId);
                insertPs.setDate(3, Date.valueOf(borrowDate));
                insertPs.setDate(4, Date.valueOf(dueDate));
                insertPs.executeUpdate();

                System.out.println("✅ Book issued successfully. Due date: " + dueDate);
            } else {
                System.out.println("❌ Book is not available.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void returnBook(int borrowId) {
        String sql = "UPDATE borrowed SET return_date = ? WHERE borrow_id = ?";
        String updateBookSql = "UPDATE books SET quantity = quantity + 1 WHERE book_id = (SELECT book_id FROM borrowed WHERE borrow_id = ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             PreparedStatement updateBookPs = con.prepareStatement(updateBookSql)) {
            ps.setDate(1, Date.valueOf(LocalDate.now()));
            ps.setInt(2, borrowId);
            ps.executeUpdate();

            updateBookPs.setInt(1, borrowId);
            updateBookPs.executeUpdate();

            System.out.println("✅ Book returned successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
