import java.util.Scanner;

/**
 * Exercise 1: Control Structures
 * Demo: do-while loop
 *
 * Program: Simple Menu-Driven Calculator
 * Repeats the menu at least once and keeps running until the user chooses to exit.
 */
public class DoWhileLoopDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n----- MENU -----");
            System.out.println("1. Add");
            System.out.println("2. Subtract");
            System.out.println("3. Multiply");
            System.out.println("4. Divide");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            if (choice >= 1 && choice <= 4) {
                System.out.print("Enter first number: ");
                double a = sc.nextDouble();
                System.out.print("Enter second number: ");
                double b = sc.nextDouble();
                double result = 0;

                switch (choice) {
                    case 1: result = a + b; break;
                    case 2: result = a - b; break;
                    case 3: result = a * b; break;
                    case 4:
                        if (b == 0) {
                            System.out.println("Error: Division by zero.");
                            continue;
                        }
                        result = a / b;
                        break;
                }
                System.out.println("Result: " + result);
            } else if (choice != 5) {
                System.out.println("Invalid choice, try again.");
            }

        } while (choice != 5);

        System.out.println("Exiting program. Goodbye!");
        sc.close();
    }
}
