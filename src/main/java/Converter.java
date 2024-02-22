import java.util.InputMismatchException;
import java.util.Scanner;

public class Converter {
    public static void main(String[] args) {

        // Create a scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        boolean exitProgram = false;

        while (!exitProgram) {
            double number = 0;
            boolean isValidInput = false;

            while (!isValidInput) {
                try {
                    System.out.println("Enter the number you want to convert: ");
                    number = scanner.nextDouble();
                    if(number == 0){
                        System.out.println("For 0, the output will be 0 for all bases.");
                        continue;
                    }
                    if(number == 1){
                        System.out.println("For 1, the output will be 1 for all bases.");
                        continue;
                    }
                    isValidInput = true;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input.");

                    // Clear the input buffer
                    scanner.nextLine();
                }
            }

            boolean convertAgain = true;

            while (convertAgain) {
                // Display options and get the choice from the user
                System.out.println("Choose an option:");
                System.out.println("Enter 1 to Binary");
                System.out.println("Enter 2 Octal");
                System.out.println("Enter 3 Hexadecimal");
                System.out.println("Enter 4 Others: ");


                int choice = 0;
                boolean isValidChoice = false;

                while (!isValidChoice) {
                    try {
                        choice = scanner.nextInt();
                        if (choice == 1 || choice == 2 || choice == 3 || choice == 4) {
                            isValidChoice = true;
                        } else {
                            System.out.println("Invalid choice. Enter choice again.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Enter the choice again");
                        scanner.nextLine();
                    }
                }

                // Convert the number based on the choice
                String convertedNumber = null;
                switch (choice) {
                    case 1:
                        convertedNumber = doubleToBinary(number);
                        break;

                    case 2:
                        convertedNumber = doubleToOctal(number);
                        break;

                    case 3:
                        convertedNumber = doubleToHexadecimal(number);
                        break;

                    case 4:

                        isValidInput = false;
                        while (!isValidInput) {
                            try {
                                System.out.println("Enter the custom base:");
                                int base = scanner.nextInt();
                                if (base < 2) {
                                    System.out.println("Base 0 and 1 are not allowed.");
                                    continue; // Restart the loop to ask for input again
                                }
                                if (base > 36) {
                                    System.out.println("Base greater than 36 is not allowed.");
                                    continue;
                                }
                                isValidInput = true;
                                convertedNumber = doubleToCustomBase(number, base);
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input.");
                                scanner.nextLine();
                            }
                        }
                        break;

                    default:
                        System.out.println("Invalid choice!");
                        return;
                }

                // Display the converted number
                System.out.println("Converted number: " + convertedNumber);

                System.out.println("Do you want to convert the same input to another base?");
                System.out.println("1. Yes");
                System.out.println("2. No");
                System.out.print("Enter your choice (1 or 2): ");

                int continueChoice;
                boolean isValidContinueChoice = false;

                while (!isValidContinueChoice) {
                    try {
                        continueChoice = scanner.nextInt();
                        if (continueChoice == 1) {
                            // Convert again
                            isValidContinueChoice = true;
                        } else if (continueChoice == 2) {
                            // Exit the convert again loop
                            convertAgain = false;
                            isValidContinueChoice = true;
                        } else {
                            System.out.print("Invalid choice. Please enter 1 or 2: ");
                        }
                    } catch (InputMismatchException e) {
                        System.out.print("Invalid input. Please enter 1 or 2: ");
                        scanner.nextLine();
                    }
                }
            }

            System.out.println("Do you want to continue?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            System.out.print("Enter your choice (1 or 2): ");

            int continueChoice;
            boolean isValidContinueChoice = false;

            while (!isValidContinueChoice) {
                try {
                    continueChoice = scanner.nextInt();
                    if (continueChoice == 1) {
                        // Continue running the program
                        isValidContinueChoice = true;
                    } else if (continueChoice == 2) {
                        // Exit the program
                        exitProgram = true;
                        isValidContinueChoice = true;
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

    // Convert the double to binary
    private static String doubleToBinary(double number) {
        // gives the double number without fraction part
        long wholePart = (long) number;
        double fractionalPart = number - wholePart;

        // Math.abs() is used to ensure that the whole part and fractional part of the number are positive
        String binaryWholePart = Long.toBinaryString(Math.abs(wholePart));
        String binaryFractionalPart = convertFractionalToBase(Math.abs(fractionalPart), 2);

        if (binaryFractionalPart.isEmpty()) {
            return getSignedNumber(binaryWholePart, number);
        } else {
            return getSignedNumber(binaryWholePart + "." + binaryFractionalPart, number);
        }
    }

    // Convert the double to octal
    private static String doubleToOctal(double number) {
        long wholePart = (long) number;
        double fractionalPart = number - wholePart;

        String octalWholePart = Long.toOctalString(Math.abs(wholePart));
        String octalFractionalPart = convertFractionalToBase(Math.abs(fractionalPart), 8);

        if (octalFractionalPart.isEmpty()) {
            return getSignedNumber(octalWholePart, number);
        } else {
            return getSignedNumber(octalWholePart + "." + octalFractionalPart, number);
        }
    }

    // Convert the double to hexadecimal
    private static String doubleToHexadecimal(double number) {
        long wholePart = (long) number;
        double fractionalPart = number - wholePart;

        String hexadecimalWholePart = Long.toHexString(Math.abs(wholePart));
        String hexadecimalFractionalPart = convertFractionalToBase(Math.abs(fractionalPart), 16);

        if (hexadecimalFractionalPart.isEmpty()) {
            return getSignedNumber(hexadecimalWholePart, number);
        } else {
            return getSignedNumber(hexadecimalWholePart + "." + hexadecimalFractionalPart, number);
        }
    }

    // Convert the double to a custom base with mixed digits and alphabets
    private static String doubleToCustomBase(double number, int base) {
        long wholePart = (long) number;
        double fractionalPart = number - wholePart;

        // Long.toString() is used to convert the number to its desired base, it supports till base 36
        String customBaseWholePart = Long.toString(Math.abs(wholePart), base);
        String customBaseFractionalPart = convertFractionalToBase(Math.abs(fractionalPart), base);

        if (customBaseFractionalPart.isEmpty()) {
            return getSignedNumber(customBaseWholePart, number);
        } else {
            return getSignedNumber(customBaseWholePart + "." + customBaseFractionalPart, number);
        }
    }

    private static String convertFractionalToBase(double fractionalPart, int base) {
        if (fractionalPart == 0.0) {
            return "";
        }

        StringBuilder convertedFractionalPart = new StringBuilder();
        // Set the desired precision for fractional part
        int precision = 5;

        for (int i = 0; i < precision; i++) {
            // fractionalPart = fractionalPart * base;
            fractionalPart *= base;
            int digit = (int) fractionalPart;

            // used to convert an integer digit to its character representation in the specified base.
            char digitCh = Character.forDigit(digit, base);
            convertedFractionalPart.append(digitCh);
            // fractionalPart = fractionalPart - digit;
            fractionalPart -= digit;
        }

        return convertedFractionalPart.toString();
    }

    // Add the sign to the converted number if the original number was negative
    private static String getSignedNumber(String convertedNumber, double originalNumber) {
        if (originalNumber < 0) {
            return "-" + convertedNumber;
        } else {
            return convertedNumber;
        }
    }
}