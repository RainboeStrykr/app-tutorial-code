// Base class Animal
class Animal {
    protected String name;
    protected int age;
    
    // Constructor
    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    // Getter methods
    public String getName() {
        return name;
    }
    
    public int getAge() {
        return age;
    }
    
    // Common method for all animals
    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age + " years");
    }
    
    // Common method that can be overridden
    public void eat() {
        System.out.println(name + " is eating");
    }
    
    public void sleep() {
        System.out.println(name + " is sleeping");
    }
}

// Interface for carnivorous behavior
interface Carnivorous {
    void hunt();
}

// Interface for herbivorous behavior
interface Herbivorous {
    void graze();
}

// Interface for climbing behavior
interface Climber {
    void climb();
}

// Lion class - inherits from Animal and implements Carnivorous
class Lion extends Animal implements Carnivorous {
    private String Color;
    
    public Lion(String name, int age, String Color) {
        super(name, age);
        this.Color = Color;
    }
    
    // Lion's specific method
    public void roar() {
        System.out.println(name + " roars loudly: ROAAAAR!");
    }
    
    // Override displayInfo to include lion-specific details
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Species: Lion");
        System.out.println("Mane Color: " + Color);
    }
    
    // Implement Carnivorous interface
    @Override
    public void hunt() {
        System.out.println(name + " is hunting for prey");
    }
    
    // Override eat method
    @Override
    public void eat() {
        System.out.println(name + " is eating meat");
    }
}

// Elephant class - inherits from Animal and implements Herbivorous
class Elephant extends Animal implements Herbivorous {
    private double weight;
    
    public Elephant(String name, int age, double weight) {
        super(name, age);
        this.weight = weight;
    }
    
    // Elephant's specific method
    public void trumpet() {
        System.out.println(name + " trumpets loudly: PAWOOOO!");
    }
    
    // Additional elephant method
    public void sprayWater() {
        System.out.println(name + " sprays water with its trunk");
    }
    
    // Override displayInfo to include elephant-specific details
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Species: Elephant");
        System.out.println("Weight: " + weight + " kg");
    }
    
    // Implement Herbivorous interface
    @Override
    public void graze() {
        System.out.println(name + " is grazing on grass and leaves");
    }
    
    // Override eat method
    @Override
    public void eat() {
        System.out.println(name + " is eating plants and fruits");
    }
}

// Monkey class - inherits from Animal and implements multiple interfaces
class Monkey extends Animal implements Climber, Herbivorous {
    private String species;
    
    public Monkey(String name, int age, String species) {
        super(name, age);
        this.species = species;
    }
    
    // Monkey's specific method
    public void swing() {
        System.out.println(name + " swings from branch to branch");
    }
    
    // Additional monkey method
    public void chatter() {
        System.out.println(name + " chatters: Ooh ooh ah ah!");
    }
    
    // Override displayInfo to include monkey-specific details
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Species: " + species);
    }
    
    // Implement Climber interface
    @Override
    public void climb() {
        System.out.println(name + " climbs trees with agility");
    }
    
    // Implement Herbivorous interface
    @Override
    public void graze() {
        System.out.println(name + " forages for fruits and leaves");
    }
    
    // Override eat method
    @Override
    public void eat() {
        System.out.println(name + " is eating bananas and fruits");
    }
}

// Main class to demonstrate inheritance and polymorphism
public class ZooAnimalsDemo {
    public static void main(String[] args) {
        System.out.println("Zoo Animals Inheritance Demo\n");
        
        // Create different animal objects
        Lion lion = new Lion("Simba", 5, "Golden");
        Elephant elephant = new Elephant("Dumbo", 12, 4500.0);
        Monkey monkey = new Monkey("George", 3, "Chimpanzee");
        
        // Array of animals to demonstrate polymorphism
        Animal[] animals = {lion, elephant, monkey};
        
        // Display all animals using polymorphism
        for (int i = 0; i < animals.length; i++) {
            System.out.println("Animal " + (i + 1) + ":");
            animals[i].displayInfo();
            animals[i].eat();
            animals[i].sleep();
            System.out.println();
        }
        
        System.out.println("\n");
        
        // Demonstrate specific behaviors
        System.out.println("Specific Animal Behaviors\n");
        
        System.out.println("Lion behaviors:");
        lion.roar();
        lion.hunt();
        
        System.out.println("\nElephant behaviors:");
        elephant.trumpet();
        elephant.sprayWater();
        elephant.graze();
        
        System.out.println("\nMonkey behaviors:");
        monkey.chatter();
        monkey.swing();
        monkey.climb();
        monkey.graze();
        
        System.out.println("\n");
        
        // Demonstrate interface-based polymorphism
        System.out.println("Interface-based Multiple Inheritance\n");
        
        // Carnivorous animals
        Carnivorous[] carnivores = {lion};
        for (Carnivorous carnivore : carnivores) {
            carnivore.hunt();
        }
        
        // Herbivorous animals
        Herbivorous[] herbivores = {elephant, monkey};
        for (Herbivorous herbivore : herbivores) {
            herbivore.graze();
        }
        
        // Climbing animals
        Climber[] climbers = {monkey};
        for (Climber climber : climbers) {
            climber.climb();
        }
        
        System.out.println("\nMultiple inheritance has been demonstrated using interfaces!");
    }
}