package gitDemo.implementations;

import gitDemo.interfaces.IC;
import gitDemo.types.DriverTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ICTest {

  private IC provideIc() {
    return new TeacherC();
  }

  @Test
  void arrangeSimple() {
    IC ic = provideIc();

    List<DriverTime> data = List.of(
            new DriverTime("Lewis Hamilton", 90456),
            new DriverTime("Lewis Hamilton", 90300),
            new DriverTime("Max Verstappen", 90200)
    );

    List<DriverTime> expected = List.of(
            new DriverTime("Max Verstappen", 90200),
            new DriverTime("Lewis Hamilton", 90300)
    );

    List<DriverTime> actual = ic.filterRecordsByDriver(data);

    Assertions.assertNotNull(actual);
    Assertions.assertEquals(expected.size(), actual.size());
    for (int i = 0; i < expected.size(); i++) {
      DriverTime expectedDt = expected.get(i);
      DriverTime actualDt = actual.get(i);
      Assertions.assertEquals(expectedDt.getName(), actualDt.getName());
      Assertions.assertEquals(expectedDt.getTimeInMs(), actualDt.getTimeInMs());
    }
  }
}
