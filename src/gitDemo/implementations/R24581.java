package gitDemo.implementations;

import gitDemo.Evaluator;
import gitDemo.interfaces.IB;
import gitDemo.types.DriverTime;
import gitDemo.types.StringDriverTimeTuple;

import java.util.ArrayList;
import java.util.List;

public class R24581 implements IB {

    public static void main(String[] args) {
        // 1. Testovací data (simulace vstupu)
        List<StringDriverTimeTuple> testData = new ArrayList<>();
        testData.add(new StringDriverTimeTuple("Jan Petr Novák", "1:32,456"));
        testData.add(new StringDriverTimeTuple("Michael Smith", "1:30.123"));
        testData.add(new StringDriverTimeTuple("Carlos Miguel de la Vega", "1:29,654"));
        testData.add(new StringDriverTimeTuple("Chybný Záznam", "invalid-time")); // Pro test přeskočení

        // 2. Spuštění vaší implementace
        R24581 myImplementation = new R24581();
        System.out.println("=== TEST IMPLEMENTACE R24581 ===");
        try {
            List<DriverTime> results = myImplementation.convert(testData);
            
            // 3. Výpis výsledků
            for (DriverTime dt : results) {
                System.out.println("Jméno: " + dt.getName() + " | Čas: " + dt.getTimeInMs() + " ms");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("================================\n");

        // 4. Spuštění celkového hodnocení
        Evaluator.evaluate(true);
    }

    @Override
    public String getStudyNumber() {
        return "R24581";
    }

    @Override
    public List<DriverTime> convert(List<StringDriverTimeTuple> records) {
        if (records == null) {
            throw new IllegalArgumentException("Input list cannot be null");
        }

        List<DriverTime> result = new ArrayList<>();

        for (StringDriverTimeTuple record : records) {
            if (record == null || record.driverName() == null || record.time() == null) {
                continue;
            }

            try {
                int timeInMs = parseTime(record.time());
                result.add(new DriverTime(record.driverName(), timeInMs));
            } catch (Exception e) {
                // Invalid time format, skip this record
            }
        }

        return result;
    }

    private int parseTime(String timeString) {
        // Expected format: 'm:ss.SSS' or 'm:ss,SSS'
        // SSS part can be shortened if it ends with zeros.

        String[] parts = timeString.split(":");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid format: missing colon");
        }

        int minutes = Integer.parseInt(parts[0].trim());
        
        String secondsPart = parts[1].trim();
        int seconds = 0;
        int milliseconds = 0;

        // Replace comma with dot for easier parsing
        secondsPart = secondsPart.replace(',', '.');

        if (secondsPart.contains(".")) {
            String[] secParts = secondsPart.split("\\.");
            if (secParts.length != 2) {
                 throw new IllegalArgumentException("Invalid format: multiple decimal separators");
            }
            
            seconds = Integer.parseInt(secParts[0]);
            
            String msString = secParts[1];
            
            // Pad with zeros to the right to ensure 3 digits (e.g. "5" -> "500")
            if (msString.length() > 3) {
                msString = msString.substring(0, 3);
            }
            while (msString.length() < 3) {
                msString += "0";
            }
            milliseconds = Integer.parseInt(msString);
        } else {
            seconds = Integer.parseInt(secondsPart);
        }

        if (minutes < 0 || seconds < 0 || seconds >= 60 || milliseconds < 0 || milliseconds >= 1000) {
             throw new IllegalArgumentException("Invalid time values");
        }

        return (minutes * 60 * 1000) + (seconds * 1000) + milliseconds;
    }
}
