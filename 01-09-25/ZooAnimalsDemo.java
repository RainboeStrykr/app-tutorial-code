// Base class Animal
class Animal {
    protected String name;
    protected int age;
    
    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    public void displayInfo() {
        System.out.println("Name: " + name + ", Age: " + age + " years");
    }
    
    public void eat() {
        System.out.println(name + " is eating");
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
    
    public Lion(String name, int age) {
        super(name, age);
    }
    
    public void roar() {
        System.out.println(name + " roars");
    }
    
    @Override
    public void hunt() {
        System.out.println(name + " is hunting");
    }
}

// Elephant class - inherits from Animal and implements Herbivorous
class Elephant extends Animal implements Herbivorous {
    
    public Elephant(String name, int age) {
        super(name, age);
    }
    
    public void trumpet() {
        System.out.println(name + " trumpets");
    }
    
    @Override
    public void graze() {
        System.out.println(name + " is grazing");
    }
}

// Monkey class - inherits from Animal and implements multiple interfaces
class Monkey extends Animal implements Climber {
    
    public Monkey(String name, int age) {
        super(name, age);
    }
    
    public void swing() {
        System.out.println(name + " swings");
    }
    
    @Override
    public void climb() {
        System.out.println(name + " climbs");
    }
    
}

// Main class demonstrating inheritance and interfaces
public class ZooAnimalsDemo {
    public static void main(String[] args) {
        System.out.println("Zoo Animals: \n");
        
        // Create animal objects
        Lion lion = new Lion("Simba", 5);
        Elephant elephant = new Elephant("Jumbo", 12);
        Monkey monkey = new Monkey("George", 3);
        
        // Display animal info and behaviors
        lion.displayInfo();
        lion.roar();
        lion.hunt();
        
        System.out.println();
        
        elephant.displayInfo();
        elephant.trumpet();
        elephant.graze();
        
        System.out.println();
        
        monkey.displayInfo();
        monkey.swing();
        monkey.climb();
        
        System.out.println("\nInheritance with interfaces demonstrated!");
    }
}