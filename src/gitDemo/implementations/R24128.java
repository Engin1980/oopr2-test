package gitDemo.implementations;

import gitDemo.interfaces.IWithStudyNumber;



import gitDemo.types.DriverTime;
import gitDemo.types.StringDriverTimeTuple;

import java.util.ArrayList;
import java.util.List;

public interface R24128 extends IWithStudyNumber {
    /**
     * Převede položky záznamů z volného textu do instancí DriverTime. Položky s neplatným časem se přeskočí
     * a nebudou zahrnuty do výsledného seznamu.
     * @param records Seznam položek s dvojičkami řidič-čas, které mají být převedeny.
     * @return Seznam instancí DriverTime, které byly úspěšně převedeny z původních záznamů. Položky s neplatným časem budou vynechány.
     * @throws IllegalArgumentException Pokud vstupní seznam je null.
     * Předpokládá se, že čas je ve formátu 'm:ss.SSS' nebo 'm:ss,SSS', kde část SSS může být zkrácená, pokud končí nulami.
     * Může být desetinná tečka i čárka.
     */
    default List<DriverTime> convert(List<StringDriverTimeTuple> records) {

        List<DriverTime> result = new ArrayList<>();
        for (StringDriverTimeTuple record : records) {
            try {
                String driverName = record.driverName();
                if (driverName == null || driverName.trim().isEmpty()) {
                    driverName = "Neznámý řidič";
                }

                int totalMs = convertTimeToMs(record.time());
                result.add(new DriverTime(driverName, totalMs));
            } catch (Exception e) {
            }
        }
        return result;
    }

    //PŘEVADĚC ČASU
    default int convertTimeToMs(String time) {
        if (time == null) {
            throw new IllegalArgumentException("Time string cannot be null");
        }
        String timeStr = time.replace(',', '.');
        String[] parts = timeStr.split(":");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid time format: " + time);
        }

        int minutes = Integer.parseInt(parts[0].trim());
        double seconds = Double.parseDouble(parts[1].trim());

        return (int) (minutes * 60 * 1000 + seconds * 1000);
    }
}
