package gitDemo.implementations;

import gitDemo.interfaces.ID;
import gitDemo.types.DriverTime;
import gitDemo.types.Result;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class R24251 implements ID {
    /**
     * Z vstupních dat se vytvoří objekt Result, který obsahuje informace o nejrychleším, druhém a třetím jezdci. Dále obsahuje
     * info o celkovém počtu jezdců a průměrný čas všech jezdců v milisekundách.
     * @param driverTimes Pole jezdců a jejich časů
     * @return Objekt Record obsahující informace.
     * @throws IllegalArgumentException pokud je driverTimes null
     *
     */

    @Override
    public Result evaluateResult(List<DriverTime> driverTimes) {
        if (driverTimes == null) {
            throw new IllegalArgumentException("driverTimes is null");
        }
        ArrayList<DriverTime> myList = new ArrayList<>(driverTimes);

        int numberOfDrivers = driverTimes.size();

        if (numberOfDrivers == 0) {
            return new Result(null, null, null, 0, 0);
        }

        long totalTime = 0;

        for (DriverTime driverTime : myList) {
            totalTime += driverTime.getTimeInMs();
        }
        long averageTime = totalTime / numberOfDrivers;

        myList.sort((driver1, driver2) -> Long.compare(driver1.getTimeInMs(), driver2.getTimeInMs()));

        DriverTime first = myList.get(0);
        DriverTime second = null;
        DriverTime third = null;

        if (numberOfDrivers > 1) {
            second = myList.get(1);
        }
        if (numberOfDrivers > 2) {
            third = myList.get(2);
        }

        return new Result(first, second, third, numberOfDrivers, (int)averageTime);
    }

    public String getStudyNumber() {
        return "R24251";
    }
}
