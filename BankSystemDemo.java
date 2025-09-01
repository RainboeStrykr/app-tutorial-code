// Base class Bank
class Bank {
    protected double interestRate;
    
    // Constructor
    public Bank(double interestRate) {
        this.interestRate = interestRate;
    }
    
    // Default constructor
    public Bank() {
        this.interestRate = 5.0; // Default bank interest rate
    }
    
    // Method to display interest rate
    public void displayRate() {
        System.out.println("Bank Interest Rate: " + interestRate + "%");
    }
    
    // Method to calculate interest
    public double calculateInterest(double amount) {
        return (amount * interestRate) / 100;
    }
    
    // Method to display bank info
    public void displayBankInfo() {
        System.out.println("This is a general bank");
        displayRate();
    }
}

// Subclass SBI that extends Bank
class SBI extends Bank {
    private double interestRate; // SBI's own interest rate variable
    
    // Constructor
    public SBI(double parentRate, double sbiRate) {
        super(parentRate); // Call parent constructor with parent rate
        this.interestRate = sbiRate; // Set SBI's own rate
    }
    
    // Default constructor
    public SBI() {
        super(); // Call parent default constructor
        this.interestRate = 7.5; // SBI's default rate
    }
    
    // Override displayRate method
    @Override
    public void displayRate() {
        // Use super to display parent's interest rate first
        System.out.println("SBI Interest Rate Information");
        System.out.print("Parent Bank Rate: ");
        super.displayRate(); // Call parent's displayRate method
        System.out.println("SBI Specific Rate: " + this.interestRate + "%");
    }
    
    // Method to display both rates using super
    public void displayBothRates() {
        System.out.println("Comparing Interest Rates:");
        System.out.println("General Bank Rate (from parent): " + super.interestRate + "%");
        System.out.println("SBI Specific Rate (child): " + this.interestRate + "%");
        
        // Calculate difference
        double difference = this.interestRate - super.interestRate;
        if (difference > 0) {
            System.out.println("SBI offers " + difference + "% higher rate than general bank");
        } else if (difference < 0) {
            System.out.println("SBI offers " + Math.abs(difference) + "% lower rate than general bank");
        } else {
            System.out.println("Both rates are the same");
        }
    }
    
    // Override calculateInterest to use SBI's rate
    @Override
    public double calculateInterest(double amount) {
        return (amount * this.interestRate) / 100;
    }
    
    // Method to calculate interest using parent's rate
    public double calculateInterestUsingParentRate(double amount) {
        return super.calculateInterest(amount); // Use parent's method
    }
    
    // Override displayBankInfo
    @Override
    public void displayBankInfo() {
        System.out.println("This is State Bank of India (SBI)");
        super.displayBankInfo(); // Call parent's method first
        System.out.println("SBI Additional Services: Online Banking, Mobile Banking, ATM Services");
    }
    
    // SBI specific method
    public void displaySBIServices() {
        System.out.println("SBI Exclusive Services:");
        System.out.println("- Personal Loans at " + this.interestRate + "% interest");
        System.out.println("- Home Loans");
        System.out.println("- Business Banking");
        System.out.println("- International Banking");
    }
}

// Main class to demonstrate super keyword usage
public class BankSystemDemo {
    public static void main(String[] args) {
        System.out.println("=== Bank System - Super Keyword Demo ===\n");
        
        // Create Bank object
        Bank generalBank = new Bank(6.0);
        System.out.println("1. General Bank:");
        generalBank.displayBankInfo();
        
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // Create SBI object with custom rates
        SBI sbi = new SBI(5.5, 8.0);
        System.out.println("2. SBI Bank (with custom rates):");
        sbi.displayBankInfo();
        
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // Demonstrate super keyword usage
        System.out.println("3. Demonstrating Super Keyword Usage:\n");
        
        // Display rates using overridden method
        sbi.displayRate();
        
        System.out.println("\n" + "-".repeat(30) + "\n");
        
        // Display both parent and child rates
        sbi.displayBothRates();
        
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // Demonstrate interest calculation
        double amount = 100000;
        System.out.println("4. Interest Calculation on ₹" + amount + ":\n");
        
        System.out.println("Using General Bank rate:");
        System.out.println("Interest: ₹" + generalBank.calculateInterest(amount));
        
        System.out.println("\nUsing SBI rate:");
        System.out.println("Interest: ₹" + sbi.calculateInterest(amount));
        
        System.out.println("\nUsing SBI's parent rate (via super):");
        System.out.println("Interest: ₹" + sbi.calculateInterestUsingParentRate(amount));
        
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // Create SBI with default constructor
        SBI defaultSBI = new SBI();
        System.out.println("5. SBI with Default Rates:");
        defaultSBI.displayBothRates();
        
        System.out.println("\n" + "-".repeat(30) + "\n");
        defaultSBI.displaySBIServices();
        
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // Demonstrate polymorphism with super
        System.out.println("6. Polymorphism Demo:");
        Bank bankRef = new SBI(4.0, 9.0);
        bankRef.displayRate(); // Calls SBI's overridden method
        
        System.out.println("\nSuper keyword successfully demonstrated!");
    }
}