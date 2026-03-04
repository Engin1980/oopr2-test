package gitDemo.interfaces;

import gitDemo.types.DriverTime;
import gitDemo.types.Result;

import java.util.List;

public interface ID extends IWithStudyNumber {
  Result evaluateResult(List<DriverTime> driverTimes);
}
