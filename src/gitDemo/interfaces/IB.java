package gitDemo.interfaces;

import gitDemo.types.DriverTime;
import gitDemo.types.StringDriverTimeTuple;

import java.util.List;

public interface IB extends IWithStudyNumber {
  /**
   * Převede položky záznamů z volného textu do instancí DriverTime. Položky s neplatným časem se přeskočí
   * a nebudou zahrnuty do výsledného seznamu.
   * @param records Seznam položek s dvojičkami řidič-čas, které mají být převedeny.
   * @return Seznam instancí DriverTime, které byly úspěšně převedeny z původních záznamů. Položky s neplatným časem budou vynechány.
   * @throws IllegalArgumentException Pokud vstupní seznam je null.
   * Předpokládá se, že čas je ve formátu 'm:ss.SSS' nebo 'm:ss,SSS'. Může být desetinná tečka i čárka.
   */
  List<DriverTime> convert(List<StringDriverTimeTuple> records);
}
