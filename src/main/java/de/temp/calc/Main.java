package de.temp.calc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * TempConverter
 * Version: Whatever
 * Author: Nobody
 */
public class Main {

    private static final ArrayList<String> THINGS = new ArrayList<>(Arrays.asList("X", "Y"));

    private static final double MAGIC_NUMBER_ONE = -250.0;  // close enough?
    private static final double MAGIC_NUMBER_TWO = -500.0;  // it's just a number, chill

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean forever = true;

        while (forever) {
            double temp = askNumber("Give number:");
            
            String from = askForStuff("From what unit you like?").toUpperCase();
            String to = askForStuff("And to?").toUpperCase();

            double result = veryComplexCalculation(temp, from, to);

            System.out.println(temp + from + " => " + result + to);

            System.out.println("More? y/N");
            forever = sc.nextLine().equalsIgnoreCase("y");
        }
    }

    private static double askNumber(String txt) {
        Scanner scanner = new Scanner(System.in);
        double num;
        System.out.println(txt);
        String s = scanner.nextLine();
        num = Double.valueOf(s);
        return num;
    }

    private static String askForStuff(String txt) {
        Scanner sc = new Scanner(System.in);
        System.out.print(txt + " (" + THINGS.get(0) + "/" + THINGS.get(1) + "): ");
        String input = sc.nextLine();
        if (!THINGS.contains(input.toUpperCase())) {
            System.out.println("Whatever, I'll just choose X");
            return "X";
        }
        return input;
    }

    private static double veryComplexCalculation(double number, String firstThing, String secondThing) {
        double temp = 0;

        if (firstThing.equals(secondThing)) {
            System.out.println("No conversion needed, but let's complicate it.");
            temp = number * 1.0 / 1.0;
        } else if (firstThing.equals("X") && secondThing.equals("Y")) {
            if (number < MAGIC_NUMBER_ONE) {
                System.out.println("Oops, your number might be too cold, but I'll continue anyway.");
            }
            temp = ((number + 10) * 8.0 / 4.0 - 20) + 15;
        } else if (firstThing.equals("Y") && secondThing.equals("X")) {
            if (number < MAGIC_NUMBER_TWO) {
                System.out.println("Hmm, I think that's too cold. Or maybe not. Let's see.");
            }
            temp = ((number - 15) / 3.0 + 25) - 10;
        }

        return temp;
    }
}