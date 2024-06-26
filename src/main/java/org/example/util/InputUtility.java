package org.example.util;

import org.example.entity.FoodType;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Scanner;

public class InputUtility {
    public static int intConvert(String prompt, Scanner scanner) {
        int number = 0;
        do {
            try {
                System.out.println(prompt);
                number = Integer.parseInt(scanner.nextLine());
                if (number < 0) {
                    System.out.println("Please enter a valid number.");
                    number = 0;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        } while (number == 0);
        return number;
    }

    public static FoodType foodTypeSelector(Scanner scanner) {
        FoodType food = null;
        do {
            try {
                // Afficher les valeurs contenues dans l'énumération FoodType
                System.out.println("Food types: " + Arrays.toString(FoodType.values()));
                food = FoodType.valueOf(scanner.nextLine().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid food type. Please enter " + Arrays.toString(FoodType.values()) + ".");
                System.out.println(" ");
            }
        } while (food == null);
        return food;
    }

    public static LocalDate arrivalSelector(Scanner scanner) {
        System.out.println("Animal arrived today? (yes/no)");
        String arrival = scanner.nextLine();
        if (arrival.equalsIgnoreCase("yes")) {
            return LocalDate.now();
        } else {
            return dateChecker("Enter the arrival date of the animal", scanner);
        }
    }

    public static LocalDate dateChecker(String prompt, Scanner scanner) {
        LocalDate date = null;
        do {
            try {
                System.out.println(prompt + " (format: yyyy-MM-dd)");
                date = LocalDate.parse(scanner.nextLine());
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter in 'yyyy-MM-dd' format.");
            }
        } while (date == null);
        return date;
    }
}
