package gitDemo.implementations;

import gitDemo.Contracts;
import gitDemo.interfaces.IC;
import gitDemo.types.DriverTime;
import gitDemo.types.StringDriverTimeTuple;

import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TeacherC implements IC {
  @Override
  public List<DriverTime> filterRecordsByDriver(List<DriverTime> driverTimes) {
    Contracts.Argument.isNotNull(driverTimes, "driverTimes");
    Contracts.Argument.isTrue(driverTimes.stream().noneMatch(q -> q == null), "driverTimes","driverTimes contains null");

    List<DriverTime> ret = new ArrayList<>();
    List<String> addedDrivers = new ArrayList<>();
    List<DriverTime> sorted = new ArrayList<>(driverTimes);
    Collections.sort(sorted);

    for (DriverTime driverTime : sorted) {
      String name = driverTime.getName();
      if (addedDrivers.contains(name)) continue;
      addedDrivers.add(name);
      ret.add(driverTime);
    }

    return ret;
  }

  @Override
  public String getStudyNumber() {
    return "Teacher-C";
  }
}
