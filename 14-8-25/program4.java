
class Employee {

    private String name;
    private static int count = 0; 

    // Setter for name - increments count when called
    public void setName(String name) {
        this.name = name;
        count++;
    }

    public String getName() {
        return name;
    }
    public static int getCount() {
        return count;
    }
}

public class program4 {

    public static void main(String[] args) {

        // Creating Employee objects without constructor
        Employee emp1 = new Employee();
        emp1.setName("John");
        System.out.println("Initial employee count: " + Employee.getCount());

        Employee emp2 = new Employee();
        emp2.setName("Sarah");

        Employee emp3 = new Employee();
        emp3.setName("Mike");

        Employee emp4 = new Employee();
        emp4.setName("Lisa");

        System.out.println("\nTotal number of employees created: " + Employee.getCount());
    }
}
