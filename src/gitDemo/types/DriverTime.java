package gitDemo.types;

import gitDemo.Contracts;

public class DriverTime implements Comparable<DriverTime> {
  private final String name;
  private final int timeInMs;

  public DriverTime(String name, int timeInMs) {
    Contracts.Argument.isNotEmpty(name, "name");
    Contracts.Argument.isTrue(timeInMs >= 0, "timeInMs", "Time in milliseconds must be greater or equal than zero.");

    this.name = name;
    this.timeInMs = timeInMs;
  }

  public String getName() {
    return name;
  }

  public int getTimeInMs() {
    return timeInMs;
  }

  @Override
  public int compareTo(DriverTime o) {
    return Integer.compare(this.getTimeInMs(), o.getTimeInMs());
  }
}
