package appLogic;

import DatabaseAction.*;
import java.util.Scanner;

// appLogic.Main menu class, orchestrating the application logic

public class Main {

    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {
        menuLogic();

    }

    public static void menuLogic() {
        // Objects from the database operation classes
        MockData mock = new MockData();
        DatabaseAdding dbAdding = new DatabaseAdding();
        DatabaseBooking dbBooking = new DatabaseBooking();
        DatabaseDisplay dbDisplay = new DatabaseDisplay();
        DatabaseUpdate dbUpdate = new DatabaseUpdate();
        DatabaseDelete dbDelete = new DatabaseDelete();

        // appLogic.Main menu
        int userChoice = 0;
        while (userChoice != 7) {
            displayMainMenu();
            userChoice = validateUserIntegerChoice(7);
            switch (userChoice) {
                case 1 -> mock.createMockData();
                case 2 -> dbAdding.addData();
                case 3 -> dbBooking.bookConcert();
                case 4 -> dbDisplay.displayData();
                case 5 -> dbUpdate.updateData();
                case 6 -> dbDelete.deleteData();
                case 7 -> {
                    System.out.println("Closing Wigells Concert application");
                    System.exit(0);
                }
            }
        }
    }

    private static void displayMainMenu() {
        System.out.println("\nChoose task: ");
        System.out.println("1: Create mock data");
        System.out.println("2: Add customer or concert");
        System.out.println("3: Make a booking");
        System.out.println("4: Display data");
        System.out.println("5: Update data");
        System.out.println("6: Delete data or empty tables");
    }

    // Validate input action choice
    public static int validateUserIntegerChoice(int maxNum) {

        boolean loop = true;
        int currentValue = 0;
        while (loop) { // Loop, ask for input until less than maxNum
            if (scan.hasNextInt()) {
                currentValue = scan.nextInt();
                if (currentValue > 0 && currentValue <= maxNum)
                    loop = false; // Only via here the loop stops
                else
                    System.out.println("Must be maximum " + maxNum);
            } else { // If input is not digits
                System.out.println("Only numbers are allowed");
                scan.nextLine();
            }
        }
        scan.nextLine();
        return currentValue;
    }

}
