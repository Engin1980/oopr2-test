package gitDemo.implementations;

import gitDemo.interfaces.ID;
import gitDemo.types.DriverTime;
import gitDemo.types.Result;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class IDTest {
  private ID provideId() {
    return new TeacherD();
  }

  @Test
  void testEvaluateResultSimple() {
    ID id = provideId();

    DriverTime jh = new DriverTime("Johny Herbert", 1300);
    DriverTime as = new DriverTime("Ayrton Senna", 1100);
    DriverTime np = new DriverTime("Nelson Picquet", 1200);
    DriverTime jd = new DriverTime("John Doe", 1500);
    List<DriverTime> data = List.of(
            jd,
            jh,
            as,
            np
    );

    Result expected = new Result(
            as, np, jh, 4, 1275);
    Result actual = id.evaluateResult(data);

    Assertions.assertNotNull(actual);
    Assertions.assertEquals(expected.getFirstPlace().getName(), actual.getFirstPlace().getName());
    Assertions.assertEquals(expected.getSecondPlace().getName(), actual.getSecondPlace().getName());
    Assertions.assertEquals(expected.getThirdPlace().getName(), actual.getThirdPlace().getName());
    Assertions.assertEquals(expected.getNumberOfDrivers(), actual.getNumberOfDrivers());
    Assertions.assertEquals(expected.getAverageTimeInMs(), actual.getAverageTimeInMs());
  }

  @Test
  void testEvaluateResultOnlyOneDriver() {
    ID id = provideId();

    DriverTime jh = new DriverTime("Johny Herbert", 1300);
    List<DriverTime> data = List.of(jh);
    Assertions.assertThrows(IllegalArgumentException.class, () -> id.evaluateResult(data));
  }

  @Test
  void testEvaluateResultNullData() {
    ID id = provideId();
    Assertions.assertThrows(IllegalArgumentException.class, () -> id.evaluateResult(null));
  }
}
