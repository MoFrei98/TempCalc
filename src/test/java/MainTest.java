import java.util.InputMismatchException;

import de.temp.calc.Main;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    void testConvertCelsiusToFahrenheit() {
        double result = Main.convertTemperature(0, "C", "F");
        assertEquals(32, result, 0.01);
    }

    @Test
    void testConvertFahrenheitToCelsius() {
        double result = Main.convertTemperature(32, "F", "C");
        assertEquals(0, result, 0.01);
    }

    @Test
    void testNoConversionSameUnit() {
        double result = Main.convertTemperature(100, "C", "C");
        assertEquals(100, result, 0.01);
    }

    @Test
    void testCelsiusBelowAbsoluteZero() {
        Exception exception = assertThrows(InputMismatchException.class, () -> {
            Main.convertTemperature(-274, "C", "F");
        });
        assertEquals("Temperature in Celsius cannot be below absolute zero (-273.15 °C)", exception.getMessage());
    }

    @Test
    void testFahrenheitBelowAbsoluteZero() {
        Exception exception = assertThrows(InputMismatchException.class, () -> {
            Main.convertTemperature(-460, "F", "C");
        });
        assertEquals("Temperature in Fahrenheit cannot be below absolute zero (-459.67 °F)", exception.getMessage());
    }
}
