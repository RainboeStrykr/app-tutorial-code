import java.awt.*;
import java.sql.*;
import javax.swing.*;

public class SimpleDBTest extends JFrame {
    private JTextArea logArea;
    
    public SimpleDBTest() {
        setTitle("Database Connection Test");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        
        logArea = new JTextArea();
        logArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        logArea.setEditable(false);
        
        JScrollPane scrollPane = new JScrollPane(logArea);
        add(scrollPane, BorderLayout.CENTER);
        
        JButton testBtn = new JButton("Test Database Connection");
        testBtn.addActionListener(e -> testConnection());
        add(testBtn, BorderLayout.SOUTH);
        
        log("Click 'Test Database Connection' to check MySQL connection...");
    }
    
    private void testConnection() {
        log("\n=== Testing Database Connection ===");
        
        String DB_URL = "jdbc:mysql://localhost:3306/restaurantdb";
        String DB_USER = "root";
        String DB_PASSWORD = "abhiraj27";
        
        try {
            // Load MySQL Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            log("✅ MySQL Driver Loaded Successfully");
            
            // Test connection
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            log("✅ Database Connection Successful!");
            
            // Test query
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) as count FROM Orders");
            if (rs.next()) {
                int count = rs.getInt("count");
                log("✅ Found " + count + " orders in database");
            }
            
            // Show some sample data
            ResultSet dataRs = stmt.executeQuery("SELECT * FROM Orders LIMIT 3");
            log("\n--- Sample Orders ---");
            while (dataRs.next()) {
                log("ID: " + dataRs.getInt("order_id") + 
                    ", Customer: " + dataRs.getString("customer_name") + 
                    ", Item: " + dataRs.getString("item") + 
                    ", Amount: $" + dataRs.getDouble("amount") + 
                    ", Status: " + dataRs.getString("status"));
            }
            
            dataRs.close();
            rs.close();
            stmt.close();
            conn.close();
            log("✅ Connection closed successfully");
            log("\n🎉 Database is working perfectly!");
            
        } catch (ClassNotFoundException e) {
            log("❌ MySQL Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            log("❌ Database error: " + e.getMessage());
            log("Error Code: " + e.getErrorCode());
            log("SQL State: " + e.getSQLState());
        } catch (Exception e) {
            log("❌ Unexpected error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void log(String message) {
        logArea.append(message + "\n");
        logArea.setCaretPosition(logArea.getDocument().getLength());
        System.out.println(message); // Also print to console
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SimpleDBTest().setVisible(true);
        });
    }
}