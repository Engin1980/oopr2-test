package gitDemo.implementations;

import gitDemo.interfaces.ID;
import gitDemo.types.DriverTime;
import gitDemo.types.Result;

import java.util.ArrayList;
import java.util.List;

public class R24126 implements ID {

    @Override
    public Result evaluateResult(List<DriverTime> driverTimes) {
        if (driverTimes.size() < 3) {
            throw new IllegalArgumentException("At least 3 driver times are required.");
        }

        driverTimes = new ArrayList<>(driverTimes);

        // sort from lowest to highest time
        for (int i = 0; i < driverTimes.size() - 1; i++) {
            for (int j = i + 1; j < driverTimes.size(); j++) {
                if (driverTimes.get(i).getTimeInMs() > driverTimes.get(j).getTimeInMs()) {
                    DriverTime temp = driverTimes.get(i);
                    driverTimes.set(i, driverTimes.get(j));
                    driverTimes.set(j, temp);
                }
            }
        }

        int totalTimeInMs = 0;
        for (DriverTime driverTime : driverTimes) {
            totalTimeInMs += driverTime.getTimeInMs();
        }
        int averageTimeInMs = Math.round((float) totalTimeInMs / driverTimes.size());

        return new Result(driverTimes.get(0), driverTimes.get(1), driverTimes.get(2), driverTimes.size(), averageTimeInMs);
    }

    @Override
    public String getStudyNumber() {
        return "R24126";
    }
}
