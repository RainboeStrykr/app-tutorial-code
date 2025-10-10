import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SimpleRestaurantGUI extends JFrame {

    private JTextField orderIdField, customerField, itemField, amountField;
    private JComboBox<String> statusCombo;
    private JTable ordersTable;
    private DefaultTableModel tableModel;
    private JTextArea logArea;
    private ArrayList<Order> orders;

    class Order {

        int orderId;
        String customer;
        String item;
        double amount;
        String status;

        Order(int orderId, String customer, String item, double amount, String status) {
            this.orderId = orderId;
            this.customer = customer;
            this.item = item;
            this.amount = amount;
            this.status = status;
        }
    }

    public SimpleRestaurantGUI() {
        orders = new ArrayList<>();
        initializeGUI();
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

        setSize(800, 600);
        setLocationRelativeTo(null);
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Add New Order"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Order ID:"), gbc);
        gbc.gridx = 1;
        orderIdField = new JTextField(10);
        panel.add(orderIdField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        panel.add(new JLabel("Customer:"), gbc);
        gbc.gridx = 3;
        customerField = new JTextField(15);
        panel.add(customerField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Item:"), gbc);
        gbc.gridx = 1;
        itemField = new JTextField(15);
        panel.add(itemField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        panel.add(new JLabel("Amount ($):"), gbc);
        gbc.gridx = 3;
        amountField = new JTextField(10);
        panel.add(amountField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Status:"), gbc);
        gbc.gridx = 1;
        statusCombo = new JComboBox<>(new String[]{"Pending", "Completed", "Cancelled"});
        panel.add(statusCombo, gbc);

        return panel;
    }
    
    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Orders List"));

        String[] columns = {"Order ID", "Customer", "Item", "Amount", "Status"};
        tableModel = new DefaultTableModel(columns, 0);
        ordersTable = new JTable(tableModel);
        ordersTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(ordersTable);
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 1, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Actions"));

        JButton addBtn = new JButton("Add Order");
        JButton updateBtn = new JButton("Update Status");
        JButton deleteBtn = new JButton("Delete Order");
        JButton calculateBtn = new JButton("Calculate Bill");
        JButton exitBtn = new JButton("Exit");
        addBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addOrder();
            }
        });

        updateBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateOrderStatus();
            }
        });

        deleteBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteOrder();
            }
        });

        calculateBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculateCustomerBill();
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
        panel.add(exitBtn);

        return panel;
    }

    private JPanel createLogPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Activity Log"));
        logArea = new JTextArea(5, 50);
        logArea.setEditable(false);
        logArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        logArea.setBackground(Color.BLACK);
        logArea.setForeground(Color.GREEN);
        JScrollPane scrollPane = new JScrollPane(logArea);
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }

    private void addOrder() {
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
            for (Order order : orders) {
                if (order.orderId == orderId) {
                    JOptionPane.showMessageDialog(this, "Order ID " + orderId + " already exists!", "Duplicate ID", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            Order newOrder = new Order(orderId, customer, item, amount, status);
            orders.add(newOrder);
            orderIdField.setText("");
            customerField.setText("");
            itemField.setText("");
            amountField.setText("");
            statusCombo.setSelectedIndex(0);
            refreshTable();
            log("Order added: ID " + orderId + " for " + customer + " - " + item + " ($" + amount + ")");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for Order ID and Amount!", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateOrderStatus() {
        int selectedRow = ordersTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an order to update!", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Order selectedOrder = orders.get(selectedRow);
        String[] statuses = {"Pending", "Completed", "Cancelled"};
        String newStatus = (String) JOptionPane.showInputDialog(
                this, "Select new status for Order ID " + selectedOrder.orderId + ":",
                "Update Status", JOptionPane.QUESTION_MESSAGE, null, statuses, selectedOrder.status);

        if (newStatus != null && !newStatus.equals(selectedOrder.status)) {
            String oldStatus = selectedOrder.status;
            selectedOrder.status = newStatus;
            refreshTable();
            log("Order " + selectedOrder.orderId + " status updated: " + oldStatus + " → " + newStatus);
        }
    }

    private void deleteOrder() {
        int selectedRow = ordersTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an order to delete!", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Order selectedOrder = orders.get(selectedRow);
        int confirm = JOptionPane.showConfirmDialog(
                this, "Delete Order ID " + selectedOrder.orderId + " for " + selectedOrder.customer + "?",
                "Confirm Delete", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            orders.remove(selectedRow);
            refreshTable();
            log("Order deleted: ID " + selectedOrder.orderId + " for " + selectedOrder.customer);
        }
    }

    private void calculateCustomerBill() {
        String customer = JOptionPane.showInputDialog(this, "Enter customer name to calculate total bill:");
        if (customer != null && !customer.trim().isEmpty()) {
            customer = customer.trim();
            double totalBill = 0;
            int orderCount = 0;

            for (Order order : orders) {
                if (order.customer.equalsIgnoreCase(customer)) {
                    totalBill += order.amount;
                    orderCount++;
                }
            }

            if (orderCount > 0) {
                String message = "Customer: " + customer + "\n"
                        + "Number of Orders: " + orderCount + "\n"
                        + "Total Bill: $" + String.format("%.2f", totalBill);
                JOptionPane.showMessageDialog(this, message, "Customer Bill", JOptionPane.INFORMATION_MESSAGE);
                log("Bill calculated for " + customer + ": " + orderCount + " orders, Total: $" + String.format("%.2f", totalBill));
            } else {
                JOptionPane.showMessageDialog(this, "No orders found for customer: " + customer, "No Orders", JOptionPane.INFORMATION_MESSAGE);
                log("No orders found for customer: " + customer);
            }
        }
    }

    private void clearAllOrders() {
        if (orders.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No orders to clear!", "Empty List", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this, "Are you sure you want to delete all " + orders.size() + " orders?",
                "Confirm Clear All", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            int count = orders.size();
            orders.clear();
            refreshTable();
            log("All orders cleared (" + count + " orders deleted)");
        }
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        for (Order order : orders) {
            Object[] row = {
                order.orderId,
                order.customer,
                order.item,
                "$" + String.format("%.2f", order.amount),
                order.status
            };
            tableModel.addRow(row);
        }
    }
    private void log(String message) {
        logArea.append(message + "\n");
        logArea.setCaretPosition(logArea.getDocument().getLength());
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SimpleRestaurantGUI().setVisible(true);
            }
        });
    }
}
