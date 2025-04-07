package de.temp.calc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static final ArrayList<String> UNITS = new ArrayList<>(Arrays.asList("C", "F"));

    public static void main(String[] args) {
        boolean askForValue = true;
        double value = 0;
        while (askForValue) {
            Scanner scan = new Scanner(System.in);
            System.out.print("Enter temperature value: ");
            String strVal = scan.nextLine();
            try {
              value = Double.parseDouble(strVal);
              askForValue = false;
            } catch (NumberFormatException e) {
                System.err.println("Invalid input. Please enter a valid temperature value.");
            }
        }

        String sourceUnit = getUnit("Enter source unit").toUpperCase();
        String targetUnit = getUnit("Enter target unit").toUpperCase();

        double convertedValue = convertTemperature(value, sourceUnit, targetUnit);

        System.out.println(value + " °" + sourceUnit + " is equal to " + convertedValue + " °" + targetUnit);
    }

    private static String getUnit(String askText) {
        String unit = null;
        boolean askForUnit = true;
        while (askForUnit) {
            Scanner scan = new Scanner(System.in);
            System.out.print(askText + " (");
            for (String unt : UNITS) {
                System.out.print(unt + " ");
            }
            System.out.print("): ");
            try {
                unit = scan.nextLine();
                if (UNITS.contains(unit.toUpperCase())) {
                    askForUnit = false;
                } else {
                    throw new Exception("Invalid unit. Please enter a valid unit.");
                }
            } catch (Exception e) {
                System.err.println("Error entering unit: " + e.getMessage());
            }
        }
        return unit;
    }

    private static double convertTemperature(double value, String sourceUnit, String targetUnit) {
        if (sourceUnit.equals("C") && targetUnit.equals("F")) {
            return (value * 9/5) + 32;
        } else if (sourceUnit.equals("F") && targetUnit.equals("C")) {
            return (value - 32) * 5/9;
        } else {
            return value; // No conversion needed
        }
    }


}