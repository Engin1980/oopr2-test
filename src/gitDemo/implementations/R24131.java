package gitDemo.implementations;

import gitDemo.interfaces.IB;
import gitDemo.types.DriverTime;
import gitDemo.types.StringDriverTimeTuple;
import java.util.ArrayList;
import java.util.List;

public class R24131 implements IB {

    @Override
    public List<DriverTime> convert(List<StringDriverTimeTuple> records) {
        if (records == null) {
            throw new IllegalArgumentException("Records cannot be null");
        }

        List<DriverTime> result = new ArrayList<>();

        for (StringDriverTimeTuple record : records) {
            if (record == null || record.driverName() == null || record.time() == null) {
                continue;
            }

            try {
                String timeStr = record.time().replace(',', '.').trim();
                String[] parts = timeStr.split(":");
                if (parts.length != 2) continue;

                int minutes = Integer.parseInt(parts[0]);
                double seconds = Double.parseDouble(parts[1]);

                if (minutes >= 0 && seconds >= 0 && seconds < 60) {
                    int totalSeconds = (minutes * 60) + (int) Math.round(seconds);
                    result.add(new DriverTime(record.driverName(), totalSeconds));
                }
            } catch (Exception e) {
            }
        }
        return result;
    }

    @Override
    public String getStudyNumber() {
        return "R24131";
    }
}