import java.sql.*;

public class SimpleDatabaseDemo {
    public static void main(String[] args) {
        // SQLite database file will be created automatically
        String url = "jdbc:sqlite:restaurant.db";
        
        try {
            // Load SQLite driver
            Class.forName("org.sqlite.JDBC");
            System.out.println("✅ SQLite Driver Loaded");
        } catch (ClassNotFoundException e) {
            System.err.println("❌ SQLite Driver not found: " + e.getMessage());
            return;
        }
        
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            
            System.out.println("✅ SQLite Connection Established");

            // 1. Create Table
            String createTable = "CREATE TABLE IF NOT EXISTS Orders(" +
                    "order_id INTEGER PRIMARY KEY, " +
                    "customer_name TEXT, " +
                    "item TEXT, " +
                    "amount REAL, " +
                    "status TEXT)";
            stmt.executeUpdate(createTable);
            System.out.println("✅ Table Created");

            // 2. Clear existing data (for demo purposes)
            stmt.executeUpdate("DELETE FROM Orders");

            // 3. Insert Orders
            String insert1 = "INSERT INTO Orders VALUES (101, 'Alice', 'Pizza', 250.50, 'Pending')";
            String insert2 = "INSERT INTO Orders VALUES (102, 'Bob', 'Burger', 120.00, 'Completed')";
            String insert3 = "INSERT INTO Orders VALUES (103, 'Alan', 'Pasta', 180.00, 'Pending')";

            stmt.executeUpdate(insert1);
            stmt.executeUpdate(insert2);
            stmt.executeUpdate(insert3);
            System.out.println("✅ Orders Inserted");

            // 4. Update Order Status
            String update = "UPDATE Orders SET status='Completed' WHERE order_id=101";
            int rowsUpdated = stmt.executeUpdate(update);
            System.out.println("✅ Order Status Updated (" + rowsUpdated + " rows affected)");

            // 5. Calculate Bill for a Customer
            String totalbill = "SELECT customer_name, SUM(amount) AS TotalBill FROM Orders " +
                               "WHERE customer_name='Alice' GROUP BY customer_name";
            try (ResultSet rs1 = stmt.executeQuery(totalbill)) {
                while (rs1.next()) {
                    System.out.println("Customer: " + rs1.getString("customer_name") +
                            " | Total Bill = $" + rs1.getDouble("TotalBill"));
                }
            }

            // 6. Display All Orders
            try (ResultSet rs2 = stmt.executeQuery("SELECT * FROM Orders ORDER BY order_id")) {
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
            }

        } catch (SQLException e) {
            System.err.println("❌ Database error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}