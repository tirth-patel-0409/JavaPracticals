import java.util.InputMismatchException;
import java.util.Scanner;

public class SpiralProgram {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // to check if the user wants to exit
        boolean exit = false;

        while (!exit) {
            // to store the size of the matrix
            int n = 0;

            // used to validate the user input for the size of the matrix.
            boolean isValidInput = false;

            while (!isValidInput) {
                try {
                    System.out.print("Enter the value of 'n': ");
                    n = scanner.nextInt();
                    if (n <= 0 || n > 999 ) {
                        System.out.println("Only 1 to 999 numbers are allowed.");
                    }
                    else{
                        isValidInput = true;
                    }

                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter an integer.");
                    // Clear the input buffer
                    scanner.nextLine();
                }
            }

            // ask the user that which type of spiral he wants
            System.out.println("Select the type of spiral:");
            System.out.println("1. Normal Spiral");
            System.out.println("2. Reverse Spiral");
            System.out.print("Enter your choice (1 or 2): ");

            int choice = 0;
            boolean isValidChoice = false;

            while (!isValidChoice) {
                try {
                    choice = scanner.nextInt();
                    if (choice == 1 || choice == 2) {
                        isValidChoice = true;
                    } else {
                        System.out.print("Invalid choice. Please enter 1 or 2: ");
                    }
                } catch (InputMismatchException e) {
                    System.out.print("Invalid input. Please enter 1 or 2: ");
                    scanner.nextLine();
                }
            }

            // initializing 2D matrix
            int[][] matrix = new int[n][n];
            int num = 1;
            int rowStart = 0, rowEnd = n - 1;
            int colStart = 0, colEnd = n - 1;
            int maxNum = n * n;

            while (num <= maxNum) {
                // Traverse right
                for (int i = colStart; i <= colEnd; i++) {
                    matrix[rowStart][i] = num++;
                }
                rowStart++;

                // Traverse down
                for (int i = rowStart; i <= rowEnd; i++) {
                    matrix[i][colEnd] = num++;
                }
                colEnd--;

                // Traverse left
                for (int i = colEnd; i >= colStart; i--) {
                    matrix[rowEnd][i] = num++;
                }
                rowEnd--;

                // Traverse up
                for (int i = rowEnd; i >= rowStart; i--) {
                    matrix[i][colStart] = num++;
                }
                colStart++;
            }

            System.out.println("Spiral Matrix:");

            if (choice == 2) {
                // Reverse each row to create a mirror image
                for (int i = 0; i < n; i++) {
                    // leftmost column
                    int left = 0;
                    // rightmost column
                    int right = n - 1;
                    while (left < right) {
                        int temp = matrix[i][left];
                        matrix[i][left] = matrix[i][right];
                        matrix[i][right] = temp;
                        left++;
                        right--;
                    }
                }
            }

            // Find the maximum number to determine the spacing
            int maxDigits = String.valueOf(maxNum).length();
            String formatSpecifier = "%-" + (maxDigits + 1) + "d";

            // Print the matrix with proper spacing
            for (int[] row : matrix) {
                for (int element : row) {
                    System.out.printf(formatSpecifier, element);
                }
                System.out.println();
            }

            System.out.println("Select an option:");
            System.out.println("1. Continue with a new matrix");
            System.out.println("2. Exit");
            System.out.print("Enter your choice (1 or 2): ");

            int optionChoice;
            boolean isValidOptionChoice = false;

            while (!isValidOptionChoice) {
                try {
                    optionChoice = scanner.nextInt();
                    if (optionChoice == 1) {
                        isValidOptionChoice = true;
                    } else if (optionChoice == 2) {
                        exit = true;
                        isValidOptionChoice = true;
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
}