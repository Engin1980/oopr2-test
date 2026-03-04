package gitDemo.interfaces;

import gitDemo.types.StringDriverTimeTuple;

import java.util.List;

public interface IA extends IWithStudyNumber {
  /**
   * Převede volný vstup ve formátu textu na jednotlivé dvojičky řidič-čas.
   *
   * @param text Vstupní text, kde jsou řidiči a časy zapsáni volně, například "Lewis Hamilton 1:30.456; Max Verstappen 1:29.789".
   * @return Seznam položek s dvojičkami řidič-čas.
   * @throws IllegalArgumentException Pokud vstupní text je null.
   * Oddělovačem záznamů je střednik. Předpokládá se, že čas v sobě neobsahuje žádnou mezeru a může obsahovat desetinné
   * tečky i čárky. Položky bez jména řidiče (tj. kde nejsou 2 bloky textu se přeskočí a nebudou zahrnuty do výsledného seznamu.
   */
  List<StringDriverTimeTuple> convert(String text);
}
