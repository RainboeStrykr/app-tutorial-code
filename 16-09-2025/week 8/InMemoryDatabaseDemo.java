import java.util.*;

// Simple in-memory database simulation without external dependencies
class Order {
    int orderId;
    String customerName;
    String item;
    double amount;
    String status;
    
    public Order(int orderId, String customerName, String item, double amount, String status) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.item = item;
        this.amount = amount;
        this.status = status;
    }
    
    @Override
    public String toString() {
        return "Order ID: " + orderId + 
               ", Customer: " + customerName + 
               ", Item: " + item + 
               ", Amount: $" + amount + 
               ", Status: " + status;
    }
}

public class InMemoryDatabaseDemo {
    private static List<Order> orders = new ArrayList<>();
    
    public static void main(String[] args) {
        System.out.println("=== Restaurant Order Management System ===\n");
        
        // Simulate database operations
        createTable();
        insertOrders();
        updateOrderStatus();
        calculateCustomerBill();
        displayAllOrders();
    }
    
    // 1. Create Table (Initialize data structure)
    public static void createTable() {
        orders = new ArrayList<>();
        System.out.println("✅ Orders table created (in-memory)");
    }
    
    // 2. Insert Orders
    public static void insertOrders() {
        orders.add(new Order(101, "Alice", "Pizza", 250.50, "Pending"));
        orders.add(new Order(102, "Bob", "Burger", 120.00, "Completed"));
        orders.add(new Order(103, "Alan", "Pasta", 180.00, "Pending"));
        System.out.println("✅ Orders inserted: " + orders.size() + " records");
    }
    
    // 3. Update Order Status
    public static void updateOrderStatus() {
        for (Order order : orders) {
            if (order.orderId == 101) {
                order.status = "Completed";
                System.out.println("✅ Order 101 status updated to Completed");
                break;
            }
        }
    }
    
    // 4. Calculate Bill for a Customer
    public static void calculateCustomerBill() {
        String customerName = "Alice";
        double totalBill = 0;
        
        for (Order order : orders) {
            if (order.customerName.equals(customerName)) {
                totalBill += order.amount;
            }
        }
        
        if (totalBill > 0) {
            System.out.println("Customer: " + customerName + " | Total Bill = $" + totalBill);
        }
    }
    
    // 5. Display All Orders
    public static void displayAllOrders() {
        System.out.println("\n--- All Orders ---");
        for (Order order : orders) {
            System.out.println(order);
        }
        
        System.out.println("\n✅ Database operations completed successfully!");
        System.out.println("This demonstrates the same logic as SQL operations:");
        System.out.println("- CREATE TABLE (data structure initialization)");
        System.out.println("- INSERT (adding records)");
        System.out.println("- UPDATE (modifying records)");
        System.out.println("- SELECT with GROUP BY (calculating totals)");
        System.out.println("- SELECT * (displaying all records)");
    }
}