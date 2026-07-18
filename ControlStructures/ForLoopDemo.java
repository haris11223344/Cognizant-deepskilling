/**
 * Exercise 1: Control Structures
 * Demo: for loop
 *
 * Program: Multiplication Table Generator
 * Prints the multiplication table for a given number.
 */
public class ForLoopDemo {
    public static void main(String[] args) {
        int number = 7;   // change this value to generate a table for a different number
        int limit = 10;

        System.out.println("Multiplication table of " + number + ":");
        for (int i = 1; i <= limit; i++) {
            System.out.println(number + " x " + i + " = " + (number * i));
        }
    }
}
