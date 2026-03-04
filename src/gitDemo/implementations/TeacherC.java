package gitDemo.implementations;

import gitDemo.interfaces.IC;
import gitDemo.types.DriverTime;
import gitDemo.types.StringDriverTimeTuple;

import java.util.List;

public class TeacherC implements IC {
  @Override
  public List<DriverTime> filterRecordsByDriver(List<DriverTime> driverTimes) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public String getStudyNumber() {
    return "Teacher-C";
  }
}
