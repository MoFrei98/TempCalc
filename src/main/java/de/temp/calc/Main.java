/**
 * Temperaturumwandler-Anwendung
 * Diese Klasse implementiert eine Konsolenanwendung zur Umrechnung von Temperaturen zwischen
 * verschiedenen Einheiten (derzeit Celsius und Fahrenheit).
 * Die Anwendung validiert Benutzereingaben und verhindert physikalisch unmögliche Werte
 * unterhalb des absoluten Nullpunkts.
 *
 * @version 1.0
 * @author Originaler Entwickler
 */
package de.temp.calc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    /**
     * Liste der unterstützten Temperatureinheiten
     * C = Celsius,
     * F = Fahrenheit
     */
    private static final ArrayList<String> UNITS = new ArrayList<>(Arrays.asList(
            "C",
            "F"
    ));

    /**
     * Absoluter Nullpunkt in Celsius (-273,15 °C)
     * Physikalisch unmöglicher Minimalwert für Celsius-Temperatur
     */
    private static final double CELSIUS_CAP = -273.15;

    /**
     * Absoluter Nullpunkt in Fahrenheit (-459,67 °F)
     * Physikalisch unmöglicher Minimalwert für Fahrenheit-Temperatur
     */
    private static final double FAHRENHEIT_CAP = -459.67;

    /**
     * Hauptmethode der Anwendung
     * Steuert den Programmfluss und die Benutzerinteraktion für die Temperaturumrechnung
     *
     * @param args Kommandozeilenargumente (werden nicht verwendet)
     */
    public static void main(String[] args) {
        boolean runApp = true;
        while (runApp) {
            Scanner scan = new Scanner(System.in);
            boolean askForValue = true;
            double value = 0;

            // Eingabeschleife für den Temperaturwert mit Validierung
            while (askForValue) {
                System.out.print("Enter temperature value: ");
                String strVal = scan.nextLine();
                try {
                    value = Double.parseDouble(strVal);
                    askForValue = false;
                } catch (NumberFormatException e) {
                    // Fehlerbehandlung bei ungültigen Zahleneingaben
                    System.err.println("Invalid input. Please enter a valid temperature value.");
                }
            }

            // Abfrage der Quell- und Zieleinheit für die Temperaturumrechnung
            String sourceUnit = getUnit("Enter source unit").toUpperCase();
            String targetUnit = getUnit("Enter target unit").toUpperCase();

            // Durchführung der Temperaturumrechnung
            double convertedValue = convertTemperature(value, sourceUnit, targetUnit);

            // Ausgabe des Ergebnisses
            System.out.println(value + " °" + sourceUnit + " is equal to " + convertedValue + " °" + targetUnit);

            // Abfrage, ob eine weitere Umrechnung gewünscht ist
            System.out.println("Convert another temperature? (Y/n)");
            runApp = !scan.nextLine().equalsIgnoreCase("n");
        }
    }

    /**
     * Hilfsmethode zur Abfrage und Validierung einer Temperatureinheit
     * Zeigt verfügbare Einheiten an und validiert die Benutzereingabe
     *
     * @param askText Der Eingabeaufforderungstext, der dem Benutzer angezeigt wird
     * @return Die validierte Temperatureinheit (C oder F)
     */
    private static String getUnit(String askText) {
        String unit = null;
        boolean askForUnit = true;
        while (askForUnit) {
            Scanner scan = new Scanner(System.in);

            // Anzeige der verfügbaren Einheiten in der Eingabeaufforderung
            System.out.print(askText + " (");
            for (String unt : UNITS) {
                System.out.print(unt + " ");
            }
            System.out.print("): ");

            try {
                unit = scan.nextLine();
                if (UNITS.contains(unit.toUpperCase())) {
                    // Gültige Einheit eingegeben, Schleife beenden
                    askForUnit = false;
                } else {
                    // Ungültige Einheit, Exception auslösen
                    throw new Exception("Invalid unit. Please enter a valid unit.");
                }
            } catch (Exception e) {
                // Fehlerbehandlung und -ausgabe
                System.err.println("Error entering unit: " + e.getMessage());
            }
        }
        return unit;
    }

    /**
     * Führt die eigentliche Temperaturumrechnung zwischen den angegebenen Einheiten durch
     * Überprüft auch, ob die angegebene Temperatur physikalisch möglich ist (nicht unter dem absoluten Nullpunkt)
     *
     * @param temp Der umzurechnende Temperaturwert
     * @param sourceUnit Die Quelleinheit (C oder F)
     * @param targetUnit Die Zieleinheit (C oder F)
     * @return Der umgerechnete Temperaturwert in der Zieleinheit
     * @throws InputMismatchException Wenn die Temperatur unter dem absoluten Nullpunkt liegt
     */
    public static double convertTemperature(double temp, String sourceUnit, String targetUnit) {
        double convertedTemp = 0;
        try {
            // Umrechnung von Celsius nach Fahrenheit
            if (sourceUnit.equals("C") && targetUnit.equals("F")) {
                // Überprüfung auf Einhaltung des absoluten Nullpunkts für Celsius
                if (temp < CELSIUS_CAP) {
                    throw new InputMismatchException("Temperature in Celsius cannot be below absolute zero (-273.15 °C)");
                }
                // Formel: °F = (°C × 9/5) + 32
                convertedTemp = (temp * 9/5) + 32;
            }
            // Umrechnung von Fahrenheit nach Celsius
            else if (sourceUnit.equals("F") && targetUnit.equals("C")) {
                // Überprüfung auf Einhaltung des absoluten Nullpunkts für Fahrenheit
                if (temp < FAHRENHEIT_CAP) {
                    throw new InputMismatchException("Temperature in Fahrenheit cannot be below absolute zero (-459.67 °F)");
                }
                // Formel: °C = (°F - 32) × 5/9
                convertedTemp = (temp - 32) * 5/9;
            }
            // Keine Umrechnung nötig (gleiche Einheiten)
            else {
                convertedTemp = temp;
            }
        } catch (Exception e) {
            // Fehlerbehandlung bei der Temperaturumrechnung
            System.err.println("Error converting temperature value: " + e.getMessage());
        }
        return convertedTemp;
    }
}