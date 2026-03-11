package gitDemo.implementations;

import gitDemo.Contracts;
import gitDemo.interfaces.IA;
import gitDemo.types.StringDriverTimeTuple;

import java.util.ArrayList;
import java.util.List;

public class TeacherA implements IA {
  @Override
  public List<StringDriverTimeTuple> convert(String text) {
    Contracts.Argument.isNotNull(text, "text");

    List<StringDriverTimeTuple> ret = new ArrayList<>();

    String[] blocks = text.split(";");
    for (String block : blocks) {
      int lastSpaceIndex = block.lastIndexOf(' ');
      if (lastSpaceIndex == -1) continue;
      String driverName = block.substring(0, lastSpaceIndex).trim();
      String timeString = block.substring(lastSpaceIndex + 1).trim();
      if (driverName.isEmpty()) continue;
      StringDriverTimeTuple tuple = new StringDriverTimeTuple(driverName, timeString);
      ret.add(tuple);
    }

    return ret;
  }

  @Override
  public String getStudyNumber() {
    return "Teacher-A";
  }
}
