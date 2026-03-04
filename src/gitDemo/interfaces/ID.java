package gitDemo.interfaces;

import gitDemo.types.DriverTime;
import gitDemo.types.Result;

import java.util.List;

public interface ID extends IWithStudyNumber {
  /**
   * Z vstupních dat se vytvoří objekt Result, který obsahuje informace o nejrychleším, druhém a třetím jezdci. Dále obsahuje
   * info o celkovém počtu jezdců a průměrný čas všech jezdců v milisekundách.
   * @param driverTimes Pole jezdců a jejich časů
   * @return Objekt Record obsahující informace.
   * @throws IllegalArgumentException pokud je driverTimes null
   * 
   */
  Result evaluateResult(List<DriverTime> driverTimes);
}
