import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InsertionSort {

    // used to store the numbers entered by the user.
    private static ArrayList<Double> numbers = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Insertion Sort Program");

        boolean validInput = false;
        while (!validInput) {
            System.out.println("Enter numbers separated by spaces (e.g., 5.2 2.9 1.7):");
            try {
                String input = scanner.nextLine();

                // splits it into individual number strings using the space
                String[] numStrings = input.split(" ");

                // iterates over each numString in the numStrings array.
                for (String numString : numStrings) {
                    // It converts the numString to a double value
                    double number = Double.parseDouble(numString);
                    // current number will be inserted to this index
                    int index = 0;
                    insertSorted(number, index);
                }

                // exit the loop if input is valid
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter numbers only.");
            }
        }

        // prints sorted numbers
        printSortedNumbers();

        while (true) {
            try {
                System.out.println("Menu:");
                System.out.println("1. Add number");
                System.out.println("2. Remove number");
                System.out.println("3. Exit");
                System.out.println("Values after spaces will be ignored.");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {

                    // to add the number in the list
                    case 1:
                        scanner.nextLine();
                        boolean validIndex = false;

                        // it will store the index at which the number will be inserted.
                        int insertIndex = 0;
                        while (!validIndex) {
                            try {
                                System.out.print("Enter the index at which you want to add the number(ignore the values after spaces if any): ");
                                insertIndex = scanner.nextInt();
                                // not count the values after spaces
                                scanner.nextLine();
                                if (insertIndex >= 0 && insertIndex <= numbers.size()) {
                                    // exit the loop if input index is valid
                                    validIndex = true;
                                } else {
                                    System.out.println("Invalid index. Please enter a valid index.");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input. Please enter a valid index.");
                                scanner.nextLine();
                            }
                        }

                        boolean validNumberToAdd = false;
                        while (!validNumberToAdd) {
                            try {
                                // ask user to add the number
                                System.out.print("Enter the number to add: ");
                                double numberToAdd = scanner.nextDouble();
                                insertSorted(numberToAdd, insertIndex);
                                printUnsortedNumbers();
                                printSortedNumbers();
                                validNumberToAdd = true;
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input. Please enter a valid number.");
                                scanner.nextLine();
                            }
                        }
                        break;

                    // to remove the number from the list
                    case 2:
                        scanner.nextLine();
                        boolean validNumberToRemove = false;
                        while (!validNumberToRemove) {
                            try {
                                // ask user to remove the number
                                System.out.print("Enter the number to remove(ignore the values after spaces if any): ");
                                double numberToRemove = scanner.nextDouble();
                                scanner.nextLine();
                                removeNumber(numberToRemove);
                                printSortedNumbers();
                                validNumberToRemove = true;
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input. Please enter a valid number.");
                                scanner.nextLine();
                            }
                        }
                        break;
                    case 3:
                        scanner.nextLine();
                        System.out.println("Exiting the program.");
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid choice.");
                scanner.nextLine();
            }
        }
    }

    // insert number at specific index
    private static void insertSorted(double number, int index) {
        numbers.add(index, number);
    }

    private static void removeNumber(double number) {
        boolean found = true;
        while (found) {
            // find the index of that number
            int index = numbers.indexOf(number);
            if (index >= 0) {
                // remove number from that index
                numbers.remove(index);
            } else {
                found = false;
                if(numbers.size()>0){
                    System.out.println("Number not found in the list.");
                }

            }
        }
    }


    // sort the numbers
    private static void sortNumbers() {
        // to sort the numbers list in ascending order
        Collections.sort(numbers);
    }

    // print the unsorted numbers
    private static void printUnsortedNumbers() {
        System.out.print("Unsorted Numbers: ");
        // iterates over each element in the list
        for (int i = 0; i < numbers.size(); i++) {
            // retrieves the element at the current index i
            System.out.print(numbers.get(i));
            // checks if the current index i is not the last index in the list
            if (i != numbers.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }

    // print the sorted numbers
    private static void printSortedNumbers() {
        // sorts the number before printing them
        sortNumbers();

        if (numbers.isEmpty()) {
            System.out.println("There are no numbers to show.");
            System.exit(0);
        }

        System.out.print("Sorted Numbers: ");
        for (int i = 0; i < numbers.size(); i++) {
            System.out.print(numbers.get(i));
            if (i != numbers.size() - 1) {
                System.out.print(", ");
            }
        }

        System.out.println();
    }
}