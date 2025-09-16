import java.sql.*;

public class WorkingMySQLDemo {
    // Database configuration - UPDATE THESE VALUES
    private static final String DB_URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_NAME = "restaurantdb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "password"; // CHANGE THIS TO YOUR MYSQL PASSWORD
    
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        
        try {
            // Load MySQL Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("✅ MySQL Driver Loaded");

            // First, connect to MySQL server (without database)
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            stmt = conn.createStatement();
            
            // Create database if it doesn't exist
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DB_NAME);
            System.out.println("✅ Database created/verified");
            
            // Close initial connection
            stmt.close();
            conn.close();
            
            // Connect to the specific database
            conn = DriverManager.getConnection(DB_URL + DB_NAME, USERNAME, PASSWORD);
            stmt = conn.createStatement();
            System.out.println("✅ Connected to " + DB_NAME);

            // 1. Create Table
            String createTable = "CREATE TABLE IF NOT EXISTS Orders(" +
                    "order_id INT PRIMARY KEY, " +
                    "customer_name VARCHAR(50), " +
                    "item VARCHAR(50), " +
                    "amount DECIMAL(8,2), " +
                    "status VARCHAR(20))";
            stmt.executeUpdate(createTable);
            System.out.println("✅ Orders table created");

            // Clear existing data for demo
            stmt.executeUpdate("DELETE FROM Orders");

            // 2. Insert Orders
            String[] inserts = {
                "INSERT INTO Orders VALUES (101, 'Alice', 'Pizza', 250.50, 'Pending')",
                "INSERT INTO Orders VALUES (102, 'Bob', 'Burger', 120.00, 'Completed')",
                "INSERT INTO Orders VALUES (103, 'Alan', 'Pasta', 180.00, 'Pending')"
            };
            
            for (String insert : inserts) {
                stmt.executeUpdate(insert);
            }
            System.out.println("✅ " + inserts.length + " orders inserted");

            // 3. Update Order Status
            String update = "UPDATE Orders SET status='Completed' WHERE order_id=101";
            int rowsUpdated = stmt.executeUpdate(update);
            System.out.println("✅ Order status updated (" + rowsUpdated + " rows affected)");

            // 4. Calculate Bill for Alice
            String billQuery = "SELECT customer_name, SUM(amount) AS TotalBill FROM Orders " +
                              "WHERE customer_name='Alice' GROUP BY customer_name";
            ResultSet rs1 = stmt.executeQuery(billQuery);
            
            while (rs1.next()) {
                System.out.println("Customer: " + rs1.getString("customer_name") +
                        " | Total Bill = $" + rs1.getDouble("TotalBill"));
            }
            rs1.close();

            // 5. Display All Orders
            ResultSet rs2 = stmt.executeQuery("SELECT * FROM Orders ORDER BY order_id");
            System.out.println("\n--- All Orders ---");
            
            while (rs2.next()) {
                System.out.println(
                        "Order ID: " + rs2.getInt("order_id") +
                        ", Customer: " + rs2.getString("customer_name") +
                        ", Item: " + rs2.getString("item") +
                        ", Amount: $" + rs2.getDouble("amount") +
                        ", Status: " + rs2.getString("status")
                );
            }
            rs2.close();

        } catch (ClassNotFoundException e) {
            System.err.println("❌ MySQL Driver not found!");
            System.err.println("Download MySQL Connector/J from: https://dev.mysql.com/downloads/connector/j/");
        } catch (SQLException e) {
            System.err.println("❌ Database error: " + e.getMessage());
            if (e.getMessage().contains("Access denied")) {
                System.err.println("💡 Check your MySQL username and password in the code");
            } else if (e.getMessage().contains("Connection refused")) {
                System.err.println("💡 Make sure MySQL server is running");
            }
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
                System.out.println("✅ Database connection closed");
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }
}