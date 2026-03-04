package gitDemo.types;

import gitDemo.Contracts;

public class Result {
  private final DriverTime firstPlace;
  private final DriverTime secondPlace;
  private final DriverTime thirdPlace;
  private final DriverTime lastPlace;
  private final int averageTimeInMs;

  public Result(DriverTime firstPlace, DriverTime secondPlace, DriverTime thirdPlace, DriverTime lastPlace, int averageTimeInMs) {
    Contracts.Argument.isNotNull(firstPlace, "firstPlace");
    Contracts.Argument.isNotNull(secondPlace, "secondPlace");
    Contracts.Argument.isNotNull(thirdPlace, "thirdPlace");
    Contracts.Argument.isNotNull(lastPlace, "lastPlace");
    Contracts.Argument.isTrue(averageTimeInMs >= 0, "averageTimeInMs", "Average time in milliseconds must be non-negative.");

    this.firstPlace = firstPlace;
    this.secondPlace = secondPlace;
    this.thirdPlace = thirdPlace;
    this.lastPlace = lastPlace;
    this.averageTimeInMs = averageTimeInMs;
  }

  public DriverTime getFirstPlace() {
    return firstPlace;
  }

  public DriverTime getSecondPlace() {
    return secondPlace;
  }

  public DriverTime getThirdPlace() {
    return thirdPlace;
  }

  public DriverTime getLastPlace() {
    return lastPlace;
  }

  public int getAverageTimeInMs() {
    return averageTimeInMs;
  }
}
