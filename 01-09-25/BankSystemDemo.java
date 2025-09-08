// Base class Bank
class Bank {
    protected double interestRate;
    
    public Bank(double interestRate) {
        this.interestRate = interestRate;
    }
    
    public void displayRate() {
        System.out.println("Bank Interest Rate: " + interestRate + "%");
    }
    
    public double calculateInterest(double amount) {
        return (amount * interestRate) / 100;
    }
}

// Subclass SBI that extends Bank
class SBI extends Bank {
    private double interestRate; // SBI's own interest rate
    
    public SBI(double parentRate, double sbiRate) {
        super(parentRate); // Call parent constructor
        this.interestRate = sbiRate; // Set SBI's rate
    }
    
    @Override
    public void displayRate() {
        System.out.print("Parent Rate: ");
        super.displayRate(); // Call parent's method
        System.out.println("SBI Rate: " + this.interestRate + "%");
    }
    
    public void displayBothRates() {
        System.out.println("General Bank Rate: " + super.interestRate + "%");
        System.out.println("SBI Rate: " + this.interestRate + "%");
    }
    
    public double calculateInterestUsingParentRate(double amount) {
        return super.calculateInterest(amount); // Use parent's method
    }
}

// Main class demonstrating super keyword
public class BankSystemDemo {
    public static void main(String[] args) {
        System.out.println("Super Keyword Demo:\n");
        
        // Create SBI object
        SBI sbi = new SBI(5.0, 8.0);
        
        // Demonstrate super keyword usage
        sbi.displayRate(); // Calls overridden method that uses super
        
        System.out.println();
        
        sbi.displayBothRates(); // Shows both parent and child variables
        
        System.out.println();
        
        // Interest calculation comparison
        double amount = 10000;
        System.out.println("Interest on ₹" + amount + ":");
        System.out.println("Using SBI rate: ₹" + sbi.calculateInterest(amount));
        System.out.println("Using parent rate: ₹" + sbi.calculateInterestUsingParentRate(amount));
    }
}