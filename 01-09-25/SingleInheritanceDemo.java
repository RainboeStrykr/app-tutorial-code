// Base class Teacher - This is the parent class that contains common properties and methods

class Teacher {

    // Protected variables - accessible by child classes but not by outside classes
    protected String name;
    protected String subject;

    // Constructor - This method is called when a Teacher object is created
    // It initializes the name and subject of the teacher
    public Teacher(String name, String subject) {
        this.name = name;
        this.subject = subject;
    }

    // Method to display teacher information
    // This method can be overridden by child classes to add more details
    public void displayDetails() {
        System.out.println("Name: " + name + ", Subject: " + subject);
    }

    // Method that represents the teaching action
    // This method will be inherited by all child classes
    public void teach() {
        System.out.println(name + " is teaching " + subject);
    }
}

// Derived class MathTeacher - This is the child class that inherits from Teacher
// The 'extends' keyword establishes the inheritance relationship
class MathTeacher extends Teacher {

    // Private variable specific to MathTeacher - not accessible outside this class
    private int numberOfClasses;

    // Constructor for MathTeacher - This calls the parent constructor first
    // 'super()' is used to call the parent class constructor
    public MathTeacher(String name, int numberOfClasses) {
        super(name, "Mathematics"); // Call parent constructor with name and "Mathematics"
        this.numberOfClasses = numberOfClasses; // Initialize child-specific variable
    }

    // Overridden method - This replaces the parent's displayDetails method
    // @Override annotation ensures we're actually overriding a parent method
    @Override
    public void displayDetails() {
        super.displayDetails(); // Call parent's displayDetails method first
        System.out.println("Classes handled: " + numberOfClasses); // Add child-specific info
    }

    // Child-specific method - This method is unique to MathTeacher class
    // It's not inherited from parent, it's a new functionality added by child
    public void solveMathProblem() {
        System.out.println(name + " is solving math problems");
    }
}

// Main class demonstrating single inheritance
public class SingleInheritanceDemo {

    // Main method - Entry point of the program where execution begins
    // This method demonstrates how inheritance works in practice
    public static void main(String[] args) {
        System.out.println("=== Single Inheritance Demo ===\n");

        // Creating an object of the base class (Teacher)
        // This shows how the parent class works independently
        Teacher teacher = new Teacher("John", "English");
        teacher.displayDetails(); // Calls Teacher's displayDetails method
        teacher.teach(); // Calls Teacher's teach method

        // Creating an object of the derived class (MathTeacher)
        // This demonstrates inheritance - MathTeacher gets all Teacher's properties and methods
        MathTeacher mathTeacher = new MathTeacher("Sarah", 5);
        mathTeacher.displayDetails(); // Calls overridden method (MathTeacher's version)
        mathTeacher.teach(); // Calls inherited method (Teacher's teach method)
        mathTeacher.solveMathProblem(); // Calls child-specific method (only in MathTeacher)

        System.out.println("\nSingle inheritance demonstrated!");
    }
}
