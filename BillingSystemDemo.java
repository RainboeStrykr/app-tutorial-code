class BillingCounter {

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
        System.out.println(item1 + " - ₹" + price1 + ", " + item2 + " - ₹" + price2 + ", "
                + item3 + " - ₹" + price3 + ", " + item4 + " - ₹" + price4);
        System.out.println("Total: ₹" + (price1 + price2 + price3 + price4));
    }
}

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
        System.out.println("Compile-time Polymorphism with Threads: \n");

        // First, display all available items and their prices
        System.out.println("Available Items and Prices:");
        System.out.println("Bread - ₹25.0");
        System.out.println("Milk - ₹50.0");
        System.out.println("Rice - ₹80.0");
        System.out.println("Dal - ₹60.0");
        System.out.println("Oil - ₹120.0");
        System.out.println("Laptop - ₹1000.0");
        System.out.println("Mouse - ₹500.0");
        System.out.println("Keyboard - ₹800.0");
        System.out.println("Headphones - ₹700.0");

        System.out.println("Now generating bills using different threads...\n");

        BillingCounter counter = new BillingCounter();
        TwoItemThread t1 = new TwoItemThread(counter);
        ThreeItemThread t2 = new ThreeItemThread(counter);
        FourItemThread t3 = new FourItemThread(counter);

        // Start threads (concurrent execution)
        t1.start();
        t2.start();
        t3.start();

    }
}
