import java.util.ArrayList;
import java.util.Scanner;

public class StudentGradeManager{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("===== Student Grade Manager =====");
        System.out.println("Choose an implementation:");
        System.out.println("1. Array-based");
        System.out.println("2. ArrayList-based");
        
        int choice = getIntInput(scanner, "Enter your choice (1 or 2): ");
        
        if (choice == 1) {
            arrayBasedImplementation(scanner);
        } else if (choice == 2) {
            arrayListBasedImplementation(scanner);
        } else {
            System.out.println("Invalid choice. Program terminated.");
        }
        
        scanner.close();
    }
    
    // Array-based implementation
    private static void arrayBasedImplementation(Scanner scanner) {
        System.out.println("\n=== Array-Based Implementation ===");
        int numStudents = getIntInput(scanner, "Enter the number of students: ");
        
        String[] names = new String[numStudents];
        double[] grades = new double[numStudents];
        
        // Input student data
        for (int i = 0; i < numStudents; i++) {
            System.out.println("\nStudent #" + (i + 1));
            System.out.print("Enter student name: ");
            scanner.nextLine(); // Consume the newline character
            names[i] = scanner.nextLine();
            grades[i] = getDoubleInput(scanner, "Enter grade for " + names[i] + ": ");
        }
        
        // Calculate statistics
        double sum = 0;
        double highest = grades[0];
        double lowest = grades[0];
        int highestIndex = 0;
        int lowestIndex = 0;
        
        for (int i = 0; i < grades.length; i++) {
            sum += grades[i];
            
            if (grades[i] > highest) {
                highest = grades[i];
                highestIndex = i;
            }
            
            if (grades[i] < lowest) {
                lowest = grades[i];
                lowestIndex = i;
            }
        }
        
        double average = sum / grades.length;
        
        // Display results
        displayResults(names, grades, average, highest, lowest, highestIndex, lowestIndex);
    }
    
    // ArrayList-based implementation
    private static void arrayListBasedImplementation(Scanner scanner) {
        System.out.println("\n=== ArrayList-Based Implementation ===");
        ArrayList<Student> students = new ArrayList<>();
        
        System.out.println("Enter student information (type 'done' for name when finished):");
        
        while (true) {
            System.out.print("\nEnter student name (or 'done' to finish): ");
            scanner.nextLine(); // Consume the newline character
            String name = scanner.nextLine();
            
            if (name.equalsIgnoreCase("done")) {
                break;
            }
            
            double grade = getDoubleInput(scanner, "Enter grade for " + name + ": ");
            students.add(new Student(name, grade));
        }
        
        if (students.isEmpty()) {
            System.out.println("No students entered. Program terminated.");
            return;
        }
        
        // Calculate statistics
        double sum = 0;
        double highest = students.get(0).getGrade();
        double lowest = students.get(0).getGrade();
        int highestIndex = 0;
        int lowestIndex = 0;
        
        for (int i = 0; i < students.size(); i++) {
            double currentGrade = students.get(i).getGrade();
            sum += currentGrade;
            
            if (currentGrade > highest) {
                highest = currentGrade;
                highestIndex = i;
            }
            
            if (currentGrade < lowest) {
                lowest = currentGrade;
                lowestIndex = i;
            }
        }
        
        double average = sum / students.size();
        
        // Convert ArrayList data to arrays for display
        String[] names = new String[students.size()];
        double[] grades = new double[students.size()];
        
        for (int i = 0; i < students.size(); i++) {
            names[i] = students.get(i).getName();
            grades[i] = students.get(i).getGrade();
        }
        
        // Display results
        displayResults(names, grades, average, highest, lowest, highestIndex, lowestIndex);
    }
    
    // Helper method to display results
    private static void displayResults(String[] names, double[] grades, double average, 
                                     double highest, double lowest, int highestIndex, int lowestIndex) {
        System.out.println("\n===== Results =====");
        System.out.println("Student Grades:");
        for (int i = 0; i < names.length; i++) {
            System.out.printf("%-20s: %.2f\n", names[i], grades[i]);
        }
        
        System.out.printf("\nClass Average: %.2f\n", average);
        System.out.printf("Highest Grade: %.2f (Student: %s)\n", highest, names[highestIndex]);
        System.out.printf("Lowest Grade: %.2f (Student: %s)\n", lowest, names[lowestIndex]);
    }
    
    // Helper method for integer input with validation
    private static int getIntInput(Scanner scanner, String prompt) {
        int input = 0;
        boolean validInput = false;
        
        while (!validInput) {
            try {
                System.out.print(prompt);
                input = Integer.parseInt(scanner.next());
                if (input <= 0) {
                    System.out.println("Please enter a positive number.");
                } else {
                    validInput = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
        
        return input;
    }
    
    // Helper method for double input with validation
    private static double getDoubleInput(Scanner scanner, String prompt) {
        double input = 0;
        boolean validInput = false;
        
        while (!validInput) {
            try {
                System.out.print(prompt);
                input = Double.parseDouble(scanner.next());
                if (input < 0 || input > 100) {
                    System.out.println("Please enter a grade between 0 and 100.");
                } else {
                    validInput = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
        
        return input;
    }
}

// Student class for the ArrayList implementation
class Student {
    private String name;
    private double grade;
    
    public Student(String name, double grade) {
        this.name = name;
        this.grade = grade;
    }
    
    public String getName() {
        return name;
    }
    
    public double getGrade() {
        return grade;
    }
}
