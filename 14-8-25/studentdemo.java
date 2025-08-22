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

public class studentdemo {
    public static void main(String[] args) {
        Student student = new Student();
        
        student.marks = 90;  
        System.out.println(student.marks); 
        
    }
}