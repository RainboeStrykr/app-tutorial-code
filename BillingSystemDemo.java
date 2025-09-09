// Billing counter with method overloading (compile-time polymorphism)
class BillingCounter {
    
    // Method overloading - same method name, different parameters
    public void generateBill(String item1, double price1, String item2, double price2) {
        System.out.println("Bill with 2 items:");
        System.out.println(item1 + " - ₹" + price1 + ", " + item2 + " - ₹" + price2);
        System.out.println("Total: ₹" + (price1 + price2));
    }
    
    public void generateBill(String item1, double price1, String item2, double price2, 
                           String item3, double price3) {
        System.out.println("Bill with 3 items:");
        System.out.println(item1 + " - ₹" + price1 + ", " + item2 + " - ₹" + price2 + ", " + item3 + " - ₹" + price3);
        System.out.println("Total: ₹" + (price1 + price2 + price3));
    }
    
    public void generateBill(String item1, double price1, String item2, double price2,
                           String item3, double price3, String item4, double price4) {
        System.out.println("Bill with 4 items:");
        System.out.println(item1 + " - ₹" + price1 + ", " + item2 + " - ₹" + price2 + ", " + 
                          item3 + " - ₹" + price3 + ", " + item4 + " - ₹" + price4);
        System.out.println("Total: ₹" + (price1 + price2 + price3 + price4));
    }
}

// Thread for 2-item billing
class TwoItemThread extends Thread {
    private BillingCounter counter;
    
    public TwoItemThread(BillingCounter counter) {
        this.counter = counter;
    }
    
    public void run() {
        System.out.println("Processing 2-item bill...");
        counter.generateBill("Bread", 25.0, "Milk", 50.0);
    }
}

// Thread for 3-item billing
class ThreeItemThread extends Thread {
    private BillingCounter counter;
    
    public ThreeItemThread(BillingCounter counter) {
        this.counter = counter;
    }
    
    public void run() {
        System.out.println("Processing 3-item bill...");
        counter.generateBill("Rice", 80.0, "Dal", 60.0, "Oil", 120.0);
    }
}

// Thread for 4-item billing
class FourItemThread extends Thread {
    private BillingCounter counter;
    
    public FourItemThread(BillingCounter counter) {
        this.counter = counter;
    }
    
    public void run() {
        System.out.println("Processing 4-item bill...");
        counter.generateBill("Laptop", 1000.0, "Mouse", 500.0, "Keyboard", 800.0, "Headphones", 700.0);
    }
}

// Main class demonstrating compile-time polymorphism with threads
public class BillingSystemDemo {
    public static void main(String[] args) {
        System.out.println("Compile-time Polymorphism with Threads:");
        System.out.println("Shopping Billing System using Method Overloading\n");
        
        BillingCounter counter = new BillingCounter();
        
        // Create thread objects
        TwoItemThread t1 = new TwoItemThread(counter);
        ThreeItemThread t2 = new ThreeItemThread(counter);
        FourItemThread t3 = new FourItemThread(counter);
        
        // Call run() method directly (sequential execution)
        t1.run();
        System.out.println();
        t2.run();
        System.out.println();
        t3.run();
    }
}