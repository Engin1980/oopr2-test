package gitDemo;

public class Contracts {
  public static class Argument {
    public static void isNotNull(Object o, String argumentName) {
      if (o == null) {
        throw new IllegalArgumentException(argumentName + " cannot be null");
      }
    }

    public static void isNotEmpty(String s, String argumentName) {
      if (s == null || s.isEmpty()) {
        throw new IllegalArgumentException(argumentName + " cannot be null or empty");
      }
    }

    public static void isTrue(boolean condition, String argumentName, String message) {
      if (!condition) {
        throw new IllegalArgumentException(argumentName + "; Reason: " + message);
      }
    }
  }
}
