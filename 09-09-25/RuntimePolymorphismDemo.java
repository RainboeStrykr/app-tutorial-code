// Parent class Bank
class Bank {
    protected String bankName;
    
    public Bank(String bankName) {
        this.bankName = bankName;
    }
    
    public double rateOfInterest() {
        return 5.0;
    }
    
    public void displayRate() {
        System.out.println(bankName + " - Interest Rate: " + rateOfInterest() + "%");
    }
}
class SBI extends Bank {
    public SBI() {
        super("SBI");
    }
    
    @Override
    public double rateOfInterest() {
        return 8.5;
    }
}
class ICICI extends Bank {
    public ICICI() {
        super("ICICI");
    }
    
    @Override
    public double rateOfInterest() {
        return 9.0;
    }
}
class HDFC extends Bank {
    public HDFC() {
        super("HDFC");
    }
    
    @Override
    public double rateOfInterest() {
        return 8.8;
    }
}
class BankTask implements Runnable {
    private Bank bank;
    
    public BankTask(Bank bank) {
        this.bank = bank;
    }
    
    @Override
    public void run() {
        bank.displayRate();
    }
}

// Main class demonstrating runtime polymorphism
public class RuntimePolymorphismDemo {
    public static void main(String[] args) {
        System.out.println("Runtime Polymorphism Demo:\n");
        
        Bank sbi = new SBI();
        Bank icici = new ICICI();
        Bank hdfc = new HDFC();
        Thread t1 = new Thread(new BankTask(sbi));
        Thread t2 = new Thread(new BankTask(icici));
        Thread t3 = new Thread(new BankTask(hdfc));
        t1.start();
        t2.start();
        t3.start();
        
    }
}