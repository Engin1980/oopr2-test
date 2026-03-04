package gitDemo.implementations;

import gitDemo.interfaces.IB;
import gitDemo.types.DriverTime;
import gitDemo.types.StringDriverTimeTuple;

import java.util.List;

public class TeacherB implements IB {
  @Override
  public int getStudyNumber() {
    return "Teacher-B";
  }

  @Override
  public List<DriverTime> convert(List<StringDriverTimeTuple> records) {
    throw new UnsupportedOperationException("Not supported yet.");
  }
}
