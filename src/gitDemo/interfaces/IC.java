package gitDemo.interfaces;

import gitDemo.types.DriverTime;

import java.util.List;

public interface IC extends IWithStudyNumber {
  /**
   * Probere seznam řidičů a vybere pro každého řidiče pouze ten záznam, který má nejnižší čas. Vrací seznam unikátních
   * řidičů s jejich nejlepším časem.
   * @param driverTimes
   * @return
   */
  List<DriverTime> filterRecordsByDriver(List<DriverTime> driverTimes);
}
