package gitDemo.interfaces;

import gitDemo.types.DriverTime;

import java.util.List;

public interface IC extends IWithStudyNumber {
  /**
   * Projde seznam řidičů a vybere pro každého řidiče pouze ten záznam, který má nejnižší čas. Vrací seznam unikátních
   * řidičů s jejich nejlepším časem.
   * @param driverTimes
   * @return Seznam instancí DriverTime, kde každý řidič se vyskytuje pouze jednou a je zde uveden jeho nejlepší čas.
   */
  List<DriverTime> filterRecordsByDriver(List<DriverTime> driverTimes);
}
