package gitDemo.types;

import gitDemo.Contracts;

public class Result {
  private final DriverTime firstPlace;
  private final DriverTime secondPlace;
  private final DriverTime thirdPlace;
  private final int numberOfDrivers;
  private final int averageTimeInMs;

  public Result(DriverTime firstPlace, DriverTime secondPlace, DriverTime thirdPlace, int numberOfDrivers, int averageTimeInMs) {
    Contracts.Argument.isNotNull(firstPlace, "firstPlace");
    Contracts.Argument.isNotNull(secondPlace, "secondPlace");
    Contracts.Argument.isNotNull(thirdPlace, "thirdPlace");
    Contracts.Argument.isTrue(numberOfDrivers >= 3, "numberOfDrivers", "Number of drivers must be at least 3.");
    Contracts.Argument.isTrue(averageTimeInMs >= 0, "averageTimeInMs", "Average time in milliseconds must be non-negative.");

    this.firstPlace = firstPlace;
    this.secondPlace = secondPlace;
    this.thirdPlace = thirdPlace;
    this.numberOfDrivers = numberOfDrivers;
    this.averageTimeInMs = averageTimeInMs;
  }

  public static boolean compare(Result a, Result b) {
    return (a.firstPlace.getName().equals(b.firstPlace.getName()) && a.firstPlace.getTimeInMs() == b.firstPlace.getTimeInMs()
        && a.secondPlace.getName().equals(b.secondPlace.getName()) && a.secondPlace.getTimeInMs() == b.secondPlace.getTimeInMs())
        && (a.thirdPlace.getName().equals(b.thirdPlace.getName()) && a.thirdPlace.getTimeInMs() == b.thirdPlace.getTimeInMs())
        && Math.abs(a.averageTimeInMs - b.averageTimeInMs) < 5;
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

  public int getNumberOfDrivers() {
    return numberOfDrivers;
  }

  public int getAverageTimeInMs() {
    return averageTimeInMs;
  }
}
