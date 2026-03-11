package gitDemo.implementations;

import gitDemo.Contracts;
import gitDemo.interfaces.ID;
import gitDemo.types.DriverTime;
import gitDemo.types.Result;

import java.util.List;

public class TeacherD implements ID {
  @Override
  public String getStudyNumber() {
    return "Teacher-D";
  }

  @Override
  public Result evaluateResult(List<DriverTime> driverTimes) {
    Contracts.Argument.isNotNull(driverTimes, "driverTimes");
    Contracts.Argument.isTrue(driverTimes.size() >= 3, "driverTimes", "DriverTimes must contain at least three records");

    Result ret;

    List<DriverTime> sorted = driverTimes.stream()
        .sorted()
        .toList();

    DriverTime first = sorted.get(0);
    DriverTime second = sorted.get(1);
    DriverTime third = sorted.get(2);
    double averageTime = driverTimes.stream().mapToInt(DriverTime::getTimeInMs).average().orElseThrow();

    ret = new Result(first, second, third, driverTimes.size(), (int) averageTime);

    return ret;
  }
}
