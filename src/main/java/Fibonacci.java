import java.math.BigInteger;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Fibonacci {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.print("Enter the number of rows (should be an odd number): ");
                int rows = scanner.nextInt();

                // exception for negative rows
                if (rows < 0) {
                    throw new IllegalArgumentException("Please enter a positive number only.");
                }

                // exception for even rows
                if (rows % 2 != 1) {
                    throw new IllegalArgumentException("Number of rows must be odd.");
                }

                // calculate the index of the middle row
                int midRow = rows / 2;

                // initial Fibonacci sequence values
                // represents the value 0
                BigInteger num1 = BigInteger.ZERO;
                // represents the value 1
                BigInteger num2 = BigInteger.ONE;

                // Upper half of the diamond

                // rows of the upper half
                for (int i = 0; i <= midRow; i++) {
                    // for spaces
                    for (int j = 0; j < midRow - i; j++) {
                        System.out.print("  ");
                    }

                    // to print the numbers
                    for (int j = 0; j <= i; j++) {
                        // %4 is for fixed width of 4 character
                        System.out.printf("%4s", num1);
                        BigInteger sum = num1.add(num2);
                        num1 = num2;
                        num2 = sum;
                    }

                    // move to the new line
                    System.out.println();
                }

                // Lower half of the diamond

                // rows of the lower half
                for (int i = midRow - 1; i >= 0; i--) {
                    for (int j = 0; j < midRow - i; j++) {
                        System.out.print("  ");
                    }

                    for (int j = 0; j <= i; j++) {
                        System.out.printf("%4s", num1);
                        BigInteger sum = num1.add(num2);
                        num1 = num2;
                        num2 = sum;
                    }

                    System.out.println();
                }

                // user wants to continue or exit
                boolean validChoice = false;
                while (!validChoice) {
                    System.out.println("Do you want to continue? (y/n)");

                    // trim() removes whitespace from the string before or after the input.
                    // toLowerCase() converts the string to lowercase. so that y,Y,n,N all are allowed.
                    String choice = scanner.next().trim().toLowerCase();
                    if (choice.equals("n")) {
                        System.out.println("Exiting the program...");
                        return;
                    } else if (choice.equals("y")) {
                        validChoice = true;
                    } else {
                        System.out.println("Invalid choice. Please enter 'y' or 'n'.");
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}