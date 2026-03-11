package gitDemo.implementations;

import gitDemo.Contracts;
import gitDemo.interfaces.IB;
import gitDemo.types.DriverTime;
import gitDemo.types.StringDriverTimeTuple;

import java.util.ArrayList;
import java.util.List;

public class TeacherB implements IB {
  @Override
  public String getStudyNumber() {
    return "Teacher-B";
  }

  @Override
  public List<DriverTime> convert(List<StringDriverTimeTuple> records) {
    Contracts.Argument.isNotNull(records, "records");

    List<DriverTime> ret = new ArrayList<>();

    for (StringDriverTimeTuple record : records) {
      if (record == null || record.driverName() == null || record.driverName().isEmpty() || record.time() == null) continue;
      String driverName = record.driverName();
      String timeString = record.time().replace(',', '.');

      int colonIndex = timeString.indexOf(':');
      if (colonIndex == -1) continue;
      int commaIndex = timeString.indexOf('.');
      if (commaIndex == -1) continue;
      String minuteString = timeString.substring(0, colonIndex);
      String secondString = timeString.substring(colonIndex + 1, commaIndex);
      String millisecondString = timeString.substring(commaIndex + 1);
      try {
        int minutes = Integer.parseInt(minuteString);
        int seconds = Integer.parseInt(secondString);
        int milliseconds = Integer.parseInt(millisecondString);
        int totalMilliseconds = (minutes * 60 + seconds) * 1000 + milliseconds;
        DriverTime driverTime = new DriverTime(driverName, totalMilliseconds);
        ret.add(driverTime);
      } catch (NumberFormatException e) {
        // silently ignore
      }
    }

    return ret;
  }
}
