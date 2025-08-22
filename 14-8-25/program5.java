class MathUtility {
    // Static method - can be called without creating an object
    public static int add(int a, int b) {
        return a + b;
    }
    
    // Non-static method - requires an object to be called
    public int multiply(int a, int b) {
        return a * b;
    }
}

public class program5 {
    public static void main(String[] args) {
        // Calling static method - no object needed
        int sum = MathUtility.add(10, 20);
        System.out.println("Sum=" + sum);
        
        MathUtility Obj = new MathUtility();
        int product = Obj.multiply(10, 5);
        System.out.println("Non-static method multiply(10, 5): " + product);
        
        // Demonstrating the difference
        System.out.println("\nDemonstrating method usage:");
        System.out.println("Static method called with class name: MathUtility.add(7, 3) = " + MathUtility.add(7, 3));
        System.out.println("Non-static method called with object: mathObj.multiply(7, 3) = " + Obj.multiply(7, 3));
        
        // The following would cause a compilation error:
        // int result = MathUtility.multiply(2, 3); // Error: non-static method cannot be referenced from static context
    }
}