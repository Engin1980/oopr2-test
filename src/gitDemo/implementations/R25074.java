package gitDemo.implementations;
import gitDemo.interfaces.IC;
import gitDemo.types.StringDriverTimeTuple;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import gitDemo.types.DriverTime;

public class R25074 implements IC {

    @Override
    public List<DriverTime> filterRecordsByDriver(List<DriverTime> driverTimes) {

        Map<String, DriverTime> bestTimes = new HashMap<>();

        for (DriverTime dt : driverTimes) {

            String name = dt.getName();

            if (!bestTimes.containsKey(name)) {
                bestTimes.put(name, dt);
            } else {
                DriverTime best = bestTimes.get(name);

                if (dt.getTimeInMs() < best.getTimeInMs()) {
                    bestTimes.put(name, dt);
                }
            }
        }

        return new ArrayList<>(bestTimes.values());
    }

    @Override
    public String getStudyNumber() {
        return "r25074";
    }
}
