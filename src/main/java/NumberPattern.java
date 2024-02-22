import java.util.InputMismatchException;
import java.util.Scanner;

public class NumberPattern {
    public static void main(String[] args) {
        // Create a scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        boolean exitProgram = false;

        while (!exitProgram) {
            // used to validate the user input for the size of the matrix.
            boolean isValidInput = false;

            int num = 0;
            while (!isValidInput) {
                try {
                    System.out.print("Enter a number: ");
                    num = scanner.nextInt();

                    if (num <= 1) {
                        throw new IllegalArgumentException("0,1 and Negative numbers are not allowed.");
                    }

                    if (num > 999) {
                        throw new IllegalArgumentException("Number greater than 999 is not allowed.");
                    }

                    isValidInput = true;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter an integer.");
                    scanner.nextLine(); // Clear the input buffer
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    scanner.nextLine();
                }
            }

            // Display pattern
            displayPattern(num);

            // ask the user whether he wants to continue or exit
            System.out.println("Do you want to continue?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            System.out.print("Enter your choice (1 or 2): ");

            int choice;
            boolean isValidChoice = false;

            while (!isValidChoice) {
                try {
                    choice = scanner.nextInt();
                    if (choice == 1) {
                        // Continue running the program
                        isValidChoice = true;
                    } else if (choice == 2) {
                        // Exit the program
                        exitProgram = true;
                        isValidChoice = true;
                    } else {
                        System.out.print("Invalid choice. Please enter 1 or 2: ");
                    }
                } catch (InputMismatchException e) {
                    System.out.print("Invalid input. Please enter 1 or 2: ");
                    scanner.nextLine();
                }
            }
        }
    }

    public static void displayPattern(int n) {
        // Calculate the number of digits in the largest number
        int maxDigits = String.valueOf(n).length();

        // Generate the pattern
        for (int i = 1; i <= n; i++) {
            // Print the numbers in ascending order
            for (int j = 1; j <= i; j++) {
                System.out.printf("%-" + (maxDigits + 1) + "d", j);
            }

            // Print the spaces
            int spaces = 0;
            if (n < 10) {
                spaces = 2 * (n - i) * (maxDigits + 1) - 2;
            }

            if ((n >= 10) && (n < 100)) {
                spaces = 2 * (n - i) * (maxDigits + 1) - 3;
            }

            if (n >= 100) {
                spaces = 2 * (n - i) * (maxDigits + 1) - 4;
            }

            for (int j = 1; j <= spaces; j++) {
                System.out.print(" ");
            }

            // Print the numbers in descending order
            for (int j = i; j >= 1; j--) {
                if (j == n) {
                    // Skip printing the input value again
                    continue;
                }

                System.out.printf("%-" + (maxDigits + 1) + "d", j);
            }

            // move to the new line
            System.out.println();
        }
    }
}