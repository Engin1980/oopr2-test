package gitDemo.implementations;

import gitDemo.interfaces.IB;
import gitDemo.types.DriverTime;
import gitDemo.types.StringDriverTimeTuple;

import java.util.ArrayList;
import java.util.List;

public class R24573 implements IB {

    @Override
    public List<DriverTime> convert(List<StringDriverTimeTuple> records) {

        if (records == null) {
            throw new IllegalArgumentException("Input list cannot be null.");
        }

        List<DriverTime> result = new ArrayList<>();

        for (StringDriverTimeTuple record : records) {

            if (record == null) {
                continue;
            }

            String driver = record.driverName();
            String timeString = record.time();

            if (driver == null || driver.isBlank() || timeString == null) {
                continue;
            }

            try {
                timeString = timeString.replace(',', '.');

                String[] minuteParts = timeString.split(":");
                if (minuteParts.length != 2) {
                    continue;
                }

                int minutes = Integer.parseInt(minuteParts[0]);

                String[] secondParts = minuteParts[1].split("\\.");
                int seconds = Integer.parseInt(secondParts[0]);

                if (seconds < 0 || seconds > 59) {
                    continue;
                }

                int milliseconds = 0;

                if (secondParts.length == 2) {
                    String msString = secondParts[1];

                    if (msString.length() == 1) {
                        msString += "00";
                    } else if (msString.length() == 2) {
                        msString += "0";
                    }

                    milliseconds = Integer.parseInt(msString);

                    if (milliseconds < 0 || milliseconds > 999) {
                        continue;
                    }
                }

                long totalMillisLong =
                        minutes * 60_000L +
                                seconds * 1_000L +
                                milliseconds;

                if (totalMillisLong > Integer.MAX_VALUE) {
                    continue;
                }

                int totalMillis = (int) totalMillisLong;

                result.add(new DriverTime(driver, totalMillis));

            } catch (NumberFormatException e) {
                // neplatný formát → přeskočit
            }
        }

        return result;
    }

    @Override
    public String getStudyNumber() {
        return "R24573";
    }
}