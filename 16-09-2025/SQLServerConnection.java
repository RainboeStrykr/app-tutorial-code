import java.sql.*;

public class SQLServerConnection {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            // Load SQL Server Driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("✅ SQL Server Driver Loaded");

            // Connect to SQL Server DB
            String connectionUrl = "jdbc:sqlserver://localhost:1433;" +
                                 "databaseName=restaurantdb;" +
                                 "user=sa;" +
                                 "password=yourpassword;" +
                                 "encrypt=false;" +
                                 "trustServerCertificate=true;";
            
            conn = DriverManager.getConnection(connectionUrl);
            stmt = conn.createStatement();
            System.out.println("✅ SQL Server Connection Established");

            // 1. Create Table
            String createTable = "IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='Orders' AND xtype='U') " +
                               "CREATE TABLE Orders(" +
                               "order_id INT PRIMARY KEY, " +
                               "customer_name VARCHAR(50), " +
                               "item VARCHAR(50), " +
                               "amount DECIMAL(8,2), " +
                               "status VARCHAR(20))";
            stmt.executeUpdate(createTable);
            System.out.println("✅ Table Created");

            // 2. Insert Orders (with duplicate check)
            String checkAndInsert1 = "IF NOT EXISTS (SELECT 1 FROM Orders WHERE order_id = 101) " +
                                    "INSERT INTO Orders VALUES (101, 'Alice', 'Pizza', 250.50, 'Pending')";
            String checkAndInsert2 = "IF NOT EXISTS (SELECT 1 FROM Orders WHERE order_id = 102) " +
                                    "INSERT INTO Orders VALUES (102, 'Bob', 'Burger', 120.00, 'Completed')";
            String checkAndInsert3 = "IF NOT EXISTS (SELECT 1 FROM Orders WHERE order_id = 103) " +
                                    "INSERT INTO Orders VALUES (103, 'Alan', 'Pasta', 180.00, 'Pending')";

            stmt.executeUpdate(checkAndInsert1);
            stmt.executeUpdate(checkAndInsert2);
            stmt.executeUpdate(checkAndInsert3);
            System.out.println("✅ Orders Inserted");

            // 3. Update Order Status
            String update = "UPDATE Orders SET status='Completed' WHERE order_id=101";
            int rowsUpdated = stmt.executeUpdate(update);
            System.out.println("✅ Order Status Updated (" + rowsUpdated + " rows affected)");

            // 4. Calculate Bill for a Customer
            String totalbill = "SELECT customer_name, SUM(amount) AS TotalBill FROM Orders " +
                             "WHERE customer_name='Alice' GROUP BY customer_name";
            ResultSet rs1 = stmt.executeQuery(totalbill);
            while (rs1.next()) {
                System.out.println("Customer: " + rs1.getString("customer_name") +
                        " | Total Bill = $" + rs1.getDouble("TotalBill"));
            }

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

            // Close ResultSets
            rs1.close();
            rs2.close();

        } catch (ClassNotFoundException e) {
            System.err.println("❌ SQL Server Driver not found: " + e.getMessage());
            System.err.println("Make sure mssql-jdbc jar is in classpath");
        } catch (SQLException e) {
            System.err.println("❌ Database error: " + e.getMessage());
            System.err.println("Check connection string, credentials, and server status");
        } catch (Exception e) {
            System.err.println("❌ Unexpected error: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
                System.out.println("✅ Connection Closed");
            } catch (SQLException e) {
                System.err.println("❌ Error closing connection: " + e.getMessage());
            }
        }
    }
}