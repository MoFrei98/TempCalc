# TempCalc

Javabasierte Konsolenanwendung zur Umrechnung von Temperaturen zwischen Celsius und Fahrenheit.  
Das Programm liest vom Nutzer einen Temperaturwert sowie Quell- und Zieleinheit ein, validiert Eingaben (inkl. Check auf absoluten Nullpunkt) und gibt das Ergebnis aus.

---

## Inhaltsverzeichnis

- [Voraussetzungen](#voraussetzungen)  
- [Installation](#installation)  
- [Nutzung](#nutzung)  
- [Projektstruktur](#projektstruktur)  
- [Tests](#tests)  
- [Autoren](#autoren)

---

## Voraussetzungen

- Java 11 oder höher  
- Gradle (Wrapper ist bereits enthalten)

---

## Installation

1. Repository klonen  
   ```bash
   git clone <repo-url>
   cd TempCalc
   ```
2. Build ausführen  
   ```bash
   ./gradlew build
   ```

---

## Nutzung

Nach dem Build kannst du das Programm mit dem Java-Launcher starten:

```bash
./gradlew run
```

Oder direkt

```bash
java -jar build/libs/TempCalc-1.0-SNAPSHOT.jar
```

Beispiel-Ablauf:

```
Enter temperature value: 100
Enter source unit (C F ): C
Enter target unit (C F ): F
100.0 °C is equal to 212.0 °F
Convert another temperature? (Y/n) n
```

---

## Projektstruktur

```
├── build.gradle.kts         # Build-Konfiguration (Gradle Kotlin DSL)
├── settings.gradle.kts      # Projektname: TempCalc
├── src
│   ├── main
│   │   └── java
│   │       └── de
│   │           └── temp
│   │               └── calc
│   │                   └── Main.java       # Hauptklasse mit Konsolen-UI
│   └── test
│       └── java
│           └── MainTest.java               # JUnit-Tests für convertTemperature()
└── README.md                # Diese Datei
```

---

## Tests

### Unit-Tests
JUnit 5-Tests liegen in `src/test/java/MainTest.java`.  
Um die Tests auszuführen:
```bash
./gradlew test
```

Erwartete Tests:
- `testConvertCelsiusToFahrenheit()`  
- `testConvertFahrenheitToCelsius()`  
- `testNoConversionSameUnit()`  
- `testCelsiusBelowAbsoluteZero()`  
- `testFahrenheitBelowAbsoluteZero()`  

### Durchgeführte Blackbox-Tests
- add here
- 
---

## Autoren

- Morris Freihoff  
- Artem Grauberger  
- Tajan Biazevic  
- Sinan Isikgel