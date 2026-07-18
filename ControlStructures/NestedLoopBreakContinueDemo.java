/**
 * Exercise 1: Control Structures
 * Demo: nested loops with break and continue
 *
 * Program: Pattern Printing + Prime Number Check
 * Demonstrates nested for loops, break, and continue statements.
 */
public class NestedLoopBreakContinueDemo {
    public static void main(String[] args) {

        // Part 1: Pattern printing using nested loops
        System.out.println("Right-angled triangle pattern:");
        int rows = 5;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }

        // Part 2: continue statement - print only odd numbers from 1 to 10
        System.out.println("\nOdd numbers from 1 to 10 (using continue):");
        for (int i = 1; i <= 10; i++) {
            if (i % 2 == 0) {
                continue; // skip even numbers
            }
            System.out.print(i + " ");
        }
        System.out.println();

        // Part 3: break statement - find the first prime number greater than 50
        System.out.println("\nFirst prime number greater than 50 (using break):");
        int num = 51;
        while (true) {
            boolean isPrime = true;
            for (int i = 2; i <= Math.sqrt(num); i++) {
                if (num % i == 0) {
                    isPrime = false;
                    break; // exit inner loop early, no need to check further
                }
            }
            if (isPrime) {
                System.out.println(num);
                break; // exit outer loop once found
            }
            num++;
        }
    }
}
