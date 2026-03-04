package gitDemo.implementations;

import gitDemo.interfaces.IA;
import gitDemo.types.StringDriverTimeTuple;

import java.util.List;

public class TeacherA implements IA {
  @Override
  public List<StringDriverTimeTuple> convert(String text) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public String getStudyNumber() {
    return "Teacher-A";
  }
}
