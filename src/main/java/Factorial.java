import java.util.Scanner;

public class Factorial {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            boolean continueCalculating = true;

            while (continueCalculating) {
                System.out.print("Enter a number to calculate its factorial: ");
                int number = scanner.nextInt();

                if (number < 0) {
                    throw new IllegalArgumentException("Factorial is not defined for negative numbers.");
                }

                long factorial = 1;
                for (int i = 1; i <= number; i++) {
                    factorial *= i;
                }

                System.out.println("Factorial of " + number + " is: " + factorial);

                System.out.println("Do you want to calculate factorial of another number?");
                System.out.println("1. Yes");
                System.out.println("2. No");
                System.out.print("Enter your choice (1 or 2): ");
                int choice = scanner.nextInt();

                if (choice != 1) {
                    continueCalculating = false;
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
