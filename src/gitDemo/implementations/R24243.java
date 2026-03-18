package gitDemo.implementations;

import gitDemo.interfaces.IC;
import gitDemo.types.DriverTime;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class R24243 implements IC {
    @Override
    public List<DriverTime> filterRecordsByDriver(List<DriverTime> driverTimes) {
        if (driverTimes == null)
            throw new IllegalArgumentException("Input list cannot be null");

        List<DriverTime> sortedTimes = new ArrayList<>(driverTimes);
        sortedTimes.sort(Comparator.comparingInt(DriverTime::getTimeInMs));

        List<DriverTime> bestTimes = new ArrayList<>();
        for (DriverTime driverTime : sortedTimes) {
            boolean found = false;
            for (DriverTime bestTime : bestTimes) {
                if (bestTime.getName().equals(driverTime.getName())) {
                    found = true;
                    break;
                }
            }
            if (!found)
                bestTimes.add(driverTime);
        }

        return bestTimes;
    }

    @Override
    public String getStudyNumber() {
        return "r24243";
    }
}
