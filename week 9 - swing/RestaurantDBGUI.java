import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class RestaurantDBGUI extends JFrame {
    private JTextField orderIdField, customerField, itemField, amountField;
    private JComboBox<String> statusCombo;
    private JTable ordersTable;
    private DefaultTableModel tableModel;
    private JTextArea logArea;
    
    private static final String DB_URL = "jdbc:mysql://localhost:3306/restaurantdb";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "abhiraj27";
    
    public RestaurantDBGUI() {
        System.out.println("Starting RestaurantDBGUI...");
        initializeGUI();
        System.out.println("GUI initialized");
        setupDatabase();
        System.out.println("Database setup completed");
        loadOrdersFromDB();
        System.out.println("Orders loaded from database");
    }
    
    private void initializeGUI() {
        setTitle("Restaurant Order Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        JPanel inputPanel = createInputPanel();
        JPanel tablePanel = createTablePanel();
        JPanel buttonPanel = createButtonPanel();
        JPanel logPanel = createLogPanel();
        
        add(inputPanel, BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.EAST);
        add(logPanel, BorderLayout.SOUTH);
        
        setSize(900, 600);
        setLocationRelativeTo(null);
    }
    
    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Add New Order"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Order ID:"), gbc);
        gbc.gridx = 1;
        orderIdField = new JTextField(10);
        panel.add(orderIdField, gbc);
        gbc.gridx = 2; gbc.gridy = 0;
        panel.add(new JLabel("Customer:"), gbc);
        gbc.gridx = 3;
        customerField = new JTextField(15);
        panel.add(customerField, gbc);
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Item:"), gbc);
        gbc.gridx = 1;
        itemField = new JTextField(15);
        panel.add(itemField, gbc);
        gbc.gridx = 2; gbc.gridy = 1;
        panel.add(new JLabel("Amount ($):"), gbc);
        gbc.gridx = 3;
        amountField = new JTextField(10);
        panel.add(amountField, gbc);
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Status:"), gbc);
        gbc.gridx = 1;
        statusCombo = new JComboBox<>(new String[]{"Pending", "Completed", "Cancelled"});
        panel.add(statusCombo, gbc);
        
        return panel;
    }
    
    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Orders from Database"));
        
        String[] columns = {"Order ID", "Customer", "Item", "Amount", "Status"};
        tableModel = new DefaultTableModel(columns, 0);
        ordersTable = new JTable(tableModel);
        ordersTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        JScrollPane scrollPane = new JScrollPane(ordersTable);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new GridLayout(6, 1, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Database Actions"));
        
        JButton addBtn = new JButton("Add to DB");
        JButton updateBtn = new JButton("Update Status");
        JButton deleteBtn = new JButton("Delete Order");
        JButton calculateBtn = new JButton("Calculate Bill");
        JButton refreshBtn = new JButton("Refresh Data");
        JButton exitBtn = new JButton("Exit");
        
        addBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addOrderToDB();
            }
        });
        
        updateBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateOrderStatus();
            }
        });
        
        deleteBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteOrderFromDB();
            }
        });
        
        calculateBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculateCustomerBill();
            }
        });
        
        refreshBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadOrdersFromDB();
            }
        });
        
        exitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        panel.add(addBtn);
        panel.add(updateBtn);
        panel.add(deleteBtn);
        panel.add(calculateBtn);
        panel.add(refreshBtn);
        panel.add(exitBtn);
        
        return panel;
    }
    
    private JPanel createLogPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Database Activity Log"));
        
        logArea = new JTextArea(5, 50);
        logArea.setEditable(false);
        logArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        logArea.setBackground(Color.BLACK);
        logArea.setForeground(Color.GREEN);
        
        JScrollPane scrollPane = new JScrollPane(logArea);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private void setupDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");            
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 Statement stmt = conn.createStatement()) {
                
                log("Database Connection Established");
                
                String createTable = "CREATE TABLE IF NOT EXISTS Orders("
                        + "order_id INT PRIMARY KEY, "
                        + "customer_name VARCHAR(50), "
                        + "item VARCHAR(50), "
                        + "amount DECIMAL(8,2), "
                        + "status VARCHAR(20))";
                stmt.executeUpdate(createTable);                
            }
        } catch (Exception e) {
            log("Database setup error: " + e.getMessage());
            JOptionPane.showMessageDialog(this, "Database connection failed: " + e.getMessage(), 
                                        "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void addOrderToDB() {
        try {
            String orderIdText = orderIdField.getText().trim();
            String customer = customerField.getText().trim();
            String item = itemField.getText().trim();
            String amountText = amountField.getText().trim();
            String status = (String) statusCombo.getSelectedItem();
            
            if (orderIdText.isEmpty() || customer.isEmpty() || item.isEmpty() || amountText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields!", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            int orderId = Integer.parseInt(orderIdText);
            double amount = Double.parseDouble(amountText);
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement pstmt = conn.prepareStatement(
                     "INSERT INTO Orders VALUES (?, ?, ?, ?, ?)")) {
                
                pstmt.setInt(1, orderId);
                pstmt.setString(2, customer);
                pstmt.setString(3, item);
                pstmt.setDouble(4, amount);
                pstmt.setString(5, status);
                pstmt.executeUpdate();
                log("Order added to DB: ID " + orderId + " for " + customer);
                orderIdField.setText("");
                customerField.setText("");
                itemField.setText("");
                amountField.setText("");
                statusCombo.setSelectedIndex(0);
                
                loadOrdersFromDB();
                
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for Order ID and Amount!", 
                                        "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            log("Database error: " + e.getMessage());
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(), 
                                        "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void loadOrdersFromDB() {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Orders ORDER BY order_id")) {
            
            tableModel.setRowCount(0);
            
            int count = 0;
            while (rs.next()) {
                Object[] row = {
                    rs.getInt("order_id"),
                    rs.getString("customer_name"),
                    rs.getString("item"),
                    "$" + String.format("%.2f", rs.getDouble("amount")),
                    rs.getString("status")
                };
                tableModel.addRow(row);
                count++;
            }
            
            log("Loaded " + count + " orders from database");
            
        } catch (SQLException e) {
            log("Error loading orders: " + e.getMessage());
        }
    }
    
    private void updateOrderStatus() {
        int selectedRow = ordersTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an order to update!", 
                                        "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int orderId = (Integer) tableModel.getValueAt(selectedRow, 0);
        String[] statuses = {"Pending", "Completed", "Cancelled"};
        String newStatus = (String) JOptionPane.showInputDialog(
            this, "Select new status for Order ID " + orderId + ":",
            "Update Status", JOptionPane.QUESTION_MESSAGE, null, statuses, statuses[0]);
        
        if (newStatus != null) {
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement pstmt = conn.prepareStatement(
                     "UPDATE Orders SET status = ? WHERE order_id = ?")) {
                
                pstmt.setString(1, newStatus);
                pstmt.setInt(2, orderId);
                int updated = pstmt.executeUpdate();
                
                if (updated > 0) {
                    log("Order " + orderId + " status updated to: " + newStatus);
                    loadOrdersFromDB();
                } else {
                    log("No order found with ID: " + orderId);
                }
                
            } catch (SQLException e) {
                log("Error updating status: " + e.getMessage());
            }
        }
    }
    
    private void deleteOrderFromDB() {
        int selectedRow = ordersTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an order to delete!", 
                                        "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int orderId = (Integer) tableModel.getValueAt(selectedRow, 0);
        String customer = (String) tableModel.getValueAt(selectedRow, 1);
        
        int confirm = JOptionPane.showConfirmDialog(
            this, "Delete Order ID " + orderId + " for " + customer + "?",
            "Confirm Delete", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement pstmt = conn.prepareStatement(
                     "DELETE FROM Orders WHERE order_id = ?")) {
                
                pstmt.setInt(1, orderId);
                int deleted = pstmt.executeUpdate();
                
                if (deleted > 0) {
                    log("Order deleted from DB: ID " + orderId + " for " + customer);
                    loadOrdersFromDB();
                } else {
                    log("No order found with ID: " + orderId);
                }
                
            } catch (SQLException e) {
                log("Error deleting order: " + e.getMessage());
            }
        }
    }
    
    private void calculateCustomerBill() {
        String customer = JOptionPane.showInputDialog(this, "Enter customer name to calculate total bill:");
        if (customer != null && !customer.trim().isEmpty()) {
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement pstmt = conn.prepareStatement(
                     "SELECT customer_name, COUNT(*) as order_count, SUM(amount) AS total_bill " +
                     "FROM Orders WHERE customer_name = ? GROUP BY customer_name")) {
                
                pstmt.setString(1, customer.trim());
                ResultSet rs = pstmt.executeQuery();
                
                if (rs.next()) {
                    int orderCount = rs.getInt("order_count");
                    double totalBill = rs.getDouble("total_bill");
                    String message = "Customer: " + customer + "\n" +
                                   "Number of Orders: " + orderCount + "\n" +
                                   "Total Bill: $" + String.format("%.2f", totalBill);
                    JOptionPane.showMessageDialog(this, message, "Customer Bill", JOptionPane.INFORMATION_MESSAGE);
                    log("Bill calculated for " + customer + ": " + orderCount + " orders, Total: $" + String.format("%.2f", totalBill));
                } else {
                    JOptionPane.showMessageDialog(this, "No orders found for customer: " + customer, 
                                                "No Orders", JOptionPane.INFORMATION_MESSAGE);
                    log("No orders found for customer: " + customer);
                }
                
            } catch (SQLException e) {
                log("Error calculating bill: " + e.getMessage());
            }
        }
    }
    private void log(String message) {
        logArea.append(message + "\n");
        logArea.setCaretPosition(logArea.getDocument().getLength());
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new RestaurantDBGUI().setVisible(true);
            }
        });
    }
}