
import java.sql.*;

public class p1 {

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            // Load MySQL Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("✅ MySQL Driver Loaded");

            // Connect to MySQL DB
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurantdb", "root", "abhiraj27");
            stmt = conn.createStatement();
            System.out.println("✅ MySQL Connection Established");

            // 1. Create Table
            String createTable = "CREATE TABLE IF NOT EXISTS Orders("
                    + "order_id INT PRIMARY KEY, "
                    + "customer_name VARCHAR(50), "
                    + "item VARCHAR(50), "
                    + "amount DECIMAL(8,2), "
                    + "status VARCHAR(20))";
            stmt.executeUpdate(createTable);
            System.out.println("✅ Table Created");

            // 2. Clear existing data and insert fresh orders
            stmt.executeUpdate("DELETE FROM Orders");
            System.out.println("✅ Existing data cleared");

            String insert1 = "INSERT INTO Orders VALUES (101, 'Alice', 'Pizza', 250.50, 'Pending')";
            String insert2 = "INSERT INTO Orders VALUES (102, 'Bob', 'Burger', 120.00, 'Completed')";
            String insert3 = "INSERT INTO Orders VALUES (103, 'Alan', 'Pasta', 180.00, 'Pending')";

            stmt.executeUpdate(insert1);
            stmt.executeUpdate(insert2);
            stmt.executeUpdate(insert3);
            System.out.println("✅ Orders Inserted");

            // 3. Update Order Status
            String update = "UPDATE Orders SET status='Completed' WHERE order_id=101";
            stmt.executeUpdate(update);
            System.out.println("✅ Order Status Updated");

            // 4. Calculate Bill for a Customer
            String totalbill = "SELECT customer_name, SUM(amount) AS TotalBill FROM Orders "
                    + "WHERE customer_name='Alice' GROUP BY customer_name";
            ResultSet rs1 = stmt.executeQuery(totalbill);
            while (rs1.next()) {
                System.out.println("Customer: " + rs1.getString("customer_name")
                        + " | Total Bill = $" + rs1.getDouble("TotalBill"));
            }

            // 5. Display All Orders
            ResultSet rs2 = stmt.executeQuery("SELECT * FROM Orders");
            System.out.println("\n--- All Orders ---");
            while (rs2.next()) {
                System.out.println(
                        "Order ID: " + rs2.getInt("order_id")
                        + ", Customer: " + rs2.getString("customer_name")
                        + ", Item: " + rs2.getString("item")
                        + ", Amount: $" + rs2.getDouble("amount")
                        + ", Status: " + rs2.getString("status")
                );
            }

            // Close ResultSets
            rs1.close();
            rs2.close();

        } catch (ClassNotFoundException e) {
            System.err.println("❌ MySQL Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("❌ Database error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("❌ Unexpected error: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
                System.out.println("✅ Connection Closed");
            } catch (SQLException e) {
                System.err.println("❌ Error closing connection: " + e.getMessage());
            }
        }
    }
}
