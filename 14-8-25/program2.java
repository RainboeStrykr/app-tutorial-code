class Student {
    private int marks;  // Private variable - cannot be accessed directly
    
    // Setter method to assign marks
    public void setMarks(int marks) {
        this.marks = marks;
    }
    
    // Getter method to retrieve marks
    public int getMarks() {
        return marks;
    }
}

public class program2 {
    public static void main(String[] args) {
        Student student = new Student();
        
        // Using setter to assign marks
        student.setMarks(85);
        
        // Using getter to display marks
        System.out.println("Student marks: " + student.getMarks());
        
        // The following line would cause a compilation error
        // because marks is private and cannot be accessed directly:
        // student.marks = 90;  // Error: marks has private access in Student
        // System.out.println(student.marks);  // Error: marks has private access in Student
        
    }
}