package gitDemo.implementations;

import gitDemo.interfaces.IC;
import gitDemo.types.DriverTime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class R24265 implements IC {

    @Override
    public List<DriverTime> filterRecordsByDriver(List<DriverTime> driverTimes) {
        if (driverTimes == null) {
            throw new IllegalArgumentException("Vstupní seznam nesmí být null!");
        }
        Map<String, DriverTime> bestTimes = new HashMap<>();

        for (DriverTime driverTime : driverTimes) {
            String name = driverTime.getName();
            if (!bestTimes.containsKey(name) || driverTime.getTimeInMs() < bestTimes.get(name).getTimeInMs()) {
                bestTimes.put(name, driverTime);
            }
        }
        return new ArrayList<>(bestTimes.values());
    }

    @Override
    public String getStudyNumber() {
        return "R24265";
    }
}