package gitDemo.implementations;

import gitDemo.interfaces.ID;
import gitDemo.types.DriverTime;
import gitDemo.types.Result;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class R24255 implements ID {
    @Override
    public String getStudyNumber() {
        return "R24255";
    }

    @Override
    public Result evaluateResult(List<DriverTime> driverTimes) {
        if (driverTimes == null) {
            throw new IllegalArgumentException("Driver times list cannot be null.");
        }

        int numberOfDrivers = driverTimes.size();
        if (numberOfDrivers < 3) {
            throw new IllegalArgumentException("Number of drivers must be at least 3.");
        }

        List<DriverTime> sortedTimes = new ArrayList<>(driverTimes);
        Collections.sort(sortedTimes);

        DriverTime firstPlace = sortedTimes.get(0);
        DriverTime secondPlace = sortedTimes.get(1);
        DriverTime thirdPlace = sortedTimes.get(2);

        long totalTimeMs = 0;
        for (DriverTime dt : driverTimes) {
            totalTimeMs += dt.getTimeInMs();
        }

        int averageTimeInMs = (int) (totalTimeMs / numberOfDrivers);

        return new Result(firstPlace, secondPlace, thirdPlace, numberOfDrivers, averageTimeInMs);
    }
}
