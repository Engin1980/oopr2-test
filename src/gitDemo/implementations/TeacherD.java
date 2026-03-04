package gitDemo.implementations;

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
    throw new UnsupportedOperationException("Not supported yet.");
  }
}
