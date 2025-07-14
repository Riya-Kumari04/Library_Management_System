import java.sql.*;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/librarydb";
    private static final String USER = "root";
    private static final String PASSWORD = "Riyakumari##123@"; // ✅ Your actual password

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void main(String[] args) {
        try (Connection con = getConnection()) {
            System.out.println("✅ Connected to database successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
