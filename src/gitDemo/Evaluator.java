package gitDemo;

import gitDemo.interfaces.IA;
import gitDemo.interfaces.IB;
import gitDemo.interfaces.IC;
import gitDemo.interfaces.ID;
import gitDemo.types.DriverTime;
import gitDemo.types.Result;
import gitDemo.types.StringDriverTimeTuple;

import java.io.File;
import java.net.URL;
import java.util.*;

public class Evaluator {
  private static <T> List<T> getAllImplementationsOfInterface(Class<T> interfaceClass) {
    List<T> ret = new ArrayList<>();

    try {
      // Získání seznamu tříd v balíčku gitDemo.implementations
      String packageName = "gitDemo.implementations";
      ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
      String path = packageName.replace('.', '/');
      URL resource = classLoader.getResource(path);

      if (resource == null) {
        return ret;
      }

      File directory = new File(resource.getFile());

      if (directory.exists()) {
        File[] files = directory.listFiles();
        if (files != null) {
          for (File file : files) {
            String fileName = file.getName();
            // Zpracovat pouze .class soubory
            if (fileName.endsWith(".class")) {
              String className = fileName.substring(0, fileName.length() - 6);
              String fullClassName = packageName + '.' + className;

              try {
                Class<?> cls = Class.forName(fullClassName);
                // Zkontrolovat, zda třída implementuje dané rozhraní
                if (interfaceClass.isAssignableFrom(cls) && !cls.isInterface() && !cls.equals(interfaceClass)) {
                  @SuppressWarnings("unchecked")
                  T instance = (T) cls.getDeclaredConstructor().newInstance();
                  ret.add(instance);
                }
              } catch (Exception e) {
                // Ignorovat třídy, které nelze načíst nebo instancovat
              }
            }
          }
        }
      }
    } catch (Exception e) {
      // Vrátit prázdný seznam v případě chyby
    }

    return ret;
  }

  public static void evaluate() {
    List<IA> iAs = getAllImplementationsOfInterface(IA.class);
    List<IB> iBs = getAllImplementationsOfInterface(IB.class);
    List<IC> iCs = getAllImplementationsOfInterface(IC.class);
    List<ID> iDs = getAllImplementationsOfInterface(ID.class);

    List<SequenceIn> sequenceIns = getAllCombinations(iAs, iBs, iCs, iDs);
    DataSet dataSet = provideDataSetA();

    List<SequenceOut> sequenceOuts = new ArrayList<>();
    for (SequenceIn sequenceIn : sequenceIns) {
      Optional<Result> result = evaluateSequence(sequenceIn, dataSet.inputText());
      if (result.isPresent()) {
        SequenceOut sequenceOut = new SequenceOut(sequenceIn.a(), sequenceIn.b(), sequenceIn.c(), sequenceIn.d(), result.get());
        sequenceOuts.add(sequenceOut);
      }
    }

    Map<String, Integer> scoreBoard = evaluateScoreboard(sequenceOuts, dataSet.expectedResult());
    printScoreBoard(scoreBoard);
  }

  private static void printScoreBoard(Map<String, Integer> scoreBoard) {
    List<Map.Entry<String, Integer>> sortedEntries = scoreBoard.entrySet()
        .stream()
        .sorted((a, b) -> Integer.compare(b.getValue(), a.getValue()))
        .toList();

    // Tisk výsledků s pořadím
    System.out.println("\n=== VÝSLEDKOVÁ TABULKA ===");
    System.out.printf("%-3s | %-20s | %s%n", "Pořadí", "Jméno", "Body");
    System.out.println("-".repeat(50));

    int rank = 1;
    for (Map.Entry<String, Integer> entry : sortedEntries) {
      System.out.printf("%-3d | %-20s | %d%n", rank, entry.getKey(), entry.getValue());
      rank++;
    }
    System.out.println();
  }

  private static Map<String, Integer> evaluateScoreboard(List<SequenceOut> sequenceOuts, Result expectedResult) {
    Map<String, Integer> ret = new HashMap<>();

    for (SequenceOut sequenceOut : sequenceOuts) {
      if (!ret.containsKey(sequenceOut.a().getStudyNumber())) {
        ret.put(sequenceOut.a().getStudyNumber(), 0);
      }
      if (!ret.containsKey(sequenceOut.b().getStudyNumber())) {
        ret.put(sequenceOut.b().getStudyNumber(), 0);
      }
      if (!ret.containsKey(sequenceOut.c().getStudyNumber())) {
        ret.put(sequenceOut.c().getStudyNumber(), 0);
      }
      if (!ret.containsKey(sequenceOut.d().getStudyNumber())) {
        ret.put(sequenceOut.d().getStudyNumber(), 0);
      }
    }

    for (SequenceOut sequenceOut : sequenceOuts) {
      if (Result.compare(sequenceOut.result(), expectedResult)) {
        ret.put(sequenceOut.a().getStudyNumber(), ret.get(sequenceOut.a().getStudyNumber()) + 1);
        ret.put(sequenceOut.b().getStudyNumber(), ret.get(sequenceOut.b().getStudyNumber()) + 1);
        ret.put(sequenceOut.c().getStudyNumber(), ret.get(sequenceOut.c().getStudyNumber()) + 1);
        ret.put(sequenceOut.d().getStudyNumber(), ret.get(sequenceOut.d().getStudyNumber()) + 1);
      }
    }

    return ret;
  }

  private static Optional<Result> evaluateSequence(SequenceIn sequenceIn, String s) {
    Result ret;
    try {
      List<StringDriverTimeTuple> tmpA = sequenceIn.a().convert(s);
      List<DriverTime> tmpB = sequenceIn.b().convert(tmpA);
      List<DriverTime> tmpC = sequenceIn.c().filterRecordsByDriver(tmpB);
      ret = sequenceIn.d().evaluateResult(tmpC);
      return Optional.of(ret);
    } catch (Exception ex) {
      System.out.println("Sequence analysis failed for " + sequenceIn + ". Reason: " + ex.getMessage());
      return Optional.empty();
    }
  }

  private static DataSet provideDataSetA() {
    DataSet ret = new DataSet(
        "Jan Petr Novák 1:32,456; Michael Smith 1:30.123; Carlos Miguel de la Vega 1:29,654; Tomáš Karel Svoboda 1:31.002; David Lee Johnson 1:28,777; Jan Petr Novák 1:31,998; Alessandro Marco Rossi 1:30.445; Pierre Louis Dubois 1:29,332; Michael Smith 1:30,777; Carlos Miguel de la Vega 1:29.101; Lukas Martin Schneider 1:33,210; Tomáš Karel Svoboda 1:30.889; David Lee Johnson 1:28.654; Jan Petr Novák 1:32,001; Pierre Louis Dubois 1:29.876; Marek Adam Kowalski 1:31,345; Alessandro Marco Rossi 1:30,998; Michael Smith 1:29.999; Carlos Miguel de la Vega 1:28,950; Tomáš Karel Svoboda 1:30,112;1:28.432; Lukas Martin Schneider 1:32,876; Marek Adam Kowalski 1:31.004; Pierre Louis Dubois 1:29.543; Jan Petr Novák 1:31.567; Alessandro Marco Rossi 1:30,210; Michael Smith 1:29,654; Carlos Miguel de la Vega 1:28.777; Tomáš Karel Svoboda 1:30,998; David Lee Johnson 1:28,321; Marek Adam Kowalski 1:30.876; Lukas Martin Schneider 1:32,445; Pierre Louis Dubois 1:29.210; Jan Petr Novák 1:31,222; Alessandro Marco Rossi 1:29.888; Michael Smith 1:29.432; Carlos Miguel de la Vega 1:28,654; Tomáš Karel Svoboda 1-30.334; David Lee Johnson 1:28,210; Marek Adam Kowalski 1:30,543; Lukas Martin Schneider 1:32.001; Pierre Louis Dubois 1:29,654; Jan Petr Novák 1:30.998; Alessandro Marco Rossi 1:29,765; Michael Smith 1:29.210; Carlos Miguel de la Vega 1:28,543; Tomáš Karel Svoboda 1:30,210; David Lee Johnson 1:28.109; Marek Adam Kowalski 1:30,112; Lukas Martin Schneider 1:31,876",
        new Result(
            new DriverTime("David Lee Johnson", 88109),
            new DriverTime("Carlos Miguel de la Vega", 88543),
            new DriverTime("Pierre Louis Dubois", 89210),
            9,
            89770
        )
    );
    return ret;
  }

  private static List<SequenceIn> getAllCombinations(List<IA> iAs, List<IB> iBs, List<IC> iCs, List<ID> iDs) {
    List<SequenceIn> ret = new ArrayList<>();

    for (IA a : iAs)
      for (IB b : iBs)
        for (IC c : iCs)
          for (ID d : iDs)
            ret.add(new SequenceIn(a, b, c, d));


    return ret;
  }
}

record DataSet(String inputText, Result expectedResult) {
}

record SequenceIn(IA a, IB b, IC c, ID d) {
  public String toString() {
    return String.format("SequenceIn(%s => %s => %s =>%s)", a.getStudyNumber(), b.getStudyNumber(), c.getStudyNumber(), d.getStudyNumber());
  }
}

record SequenceOut(IA a, IB b, IC c, ID d, Result result) {
}
