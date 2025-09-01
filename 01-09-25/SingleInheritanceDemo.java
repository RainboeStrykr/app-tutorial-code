// Base class Teacher
class Teacher {
    protected String name;
    protected String subject;
    
    // Constructor
    public Teacher(String name, String subject) {
        this.name = name;
        this.subject = subject;
    }
    
    // Getter methods
    public String getName() {
        return name;
    }
    
    public String getSubject() {
        return subject;
    }
    
    // Method to display teacher details
    public void displayDetails() {
        System.out.println("Teacher Name: " + name);
        System.out.println("Subject: " + subject);
    }
    
    // Method to teach
    public void teach() {
        System.out.println(name + " is teaching " + subject);
    }
}

// Derived class MathTeacher that inherits from Teacher
class MathTeacher extends Teacher {
    private int numberOfClasses;
    
    // Constructor
    public MathTeacher(String name, int numberOfClasses) {
        super(name, "Mathematics"); // Call parent constructor
        this.numberOfClasses = numberOfClasses;
    }
    
    // Getter for numberOfClasses
    public int getNumberOfClasses() {
        return numberOfClasses;
    }
    
    // Setter for numberOfClasses
    public void setNumberOfClasses(int numberOfClasses) {
        this.numberOfClasses = numberOfClasses;
    }
    
    // Override displayDetails method to include additional info
    @Override
    public void displayDetails() {
        super.displayDetails(); // Call parent method
        System.out.println("Number of Classes Handled: " + numberOfClasses);
    }
    
    // Additional method specific to MathTeacher
    public void solveMathProblem() {
        System.out.println(name + " is solving mathematical problems for students");
    }
    
    // Method to show class schedule
    public void showSchedule() {
        System.out.println(name + " handles " + numberOfClasses + " mathematics classes");
    }
}

// Main class to demonstrate single inheritance
public class SingleInheritanceDemo {
    public static void main(String[] args) {
        System.out.println("Single Inheritance Demo");
        
        // Create a Teacher object
        Teacher teacher1 = new Teacher("Mr Ravi", "Java");
        teacher1.displayDetails();
        teacher1.teach();
        
        System.out.println("\n");
        
        // Create a MathTeacher object
        MathTeacher mathTeacher = new MathTeacher("Mr Vikas", 5);
        mathTeacher.displayDetails(); // Calls overridden method
        mathTeacher.teach(); // Inherited method
        mathTeacher.solveMathProblem(); // MathTeacher specific method
        mathTeacher.showSchedule(); // MathTeacher specific method
        
        System.out.println("\n");
        
        // Demonstrate polymorphism
        System.out.println("Polymorphism Demo:");
        Teacher teacher2 = new MathTeacher("Mr Anand", 3);
        teacher2.displayDetails(); // Calls MathTeacher's overridden method
        teacher2.teach(); // Inherited method
        
        System.out.println("\nInheritance successfully demonstrated!");
    }
}