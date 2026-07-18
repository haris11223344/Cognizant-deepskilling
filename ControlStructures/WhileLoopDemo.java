/**
 * Exercise 1: Control Structures
 * Demo: while loop
 *
 * Program: Sum of Digits
 * Uses a while loop to compute the sum of digits of a number.
 */
public class WhileLoopDemo {
    public static void main(String[] args) {
        int number = 48729;
        int original = number;
        int sum = 0;

        while (number != 0) {
            int digit = number % 10;
            sum += digit;
            number /= 10;
        }

        System.out.println("Number: " + original);
        System.out.println("Sum of digits: " + sum);
    }
}
