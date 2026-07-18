import java.util.Scanner;

/**
 * Exercise 1: Control Structures
 * Demo: if / else if / else
 *
 * Program: Grade Calculator
 * Takes a numeric score as input and prints the corresponding grade.
 */
public class IfElseDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your score (0-100): ");
        int score = sc.nextInt();

        char grade;

        if (score >= 90) {
            grade = 'A';
        } else if (score >= 80) {
            grade = 'B';
        } else if (score >= 70) {
            grade = 'C';
        } else if (score >= 60) {
            grade = 'D';
        } else {
            grade = 'F';
        }

        System.out.println("Score: " + score);
        System.out.println("Grade: " + grade);

        sc.close();
    }
}
