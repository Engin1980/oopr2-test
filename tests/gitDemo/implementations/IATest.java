package gitDemo.implementations;

import gitDemo.interfaces.IA;
import gitDemo.types.StringDriverTimeTuple;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class IATest {

  private static IA provideIa(){
    return new TeacherA();
  }

  @Test
  void convertNoTime(){
    String s = "John Doe";
    StringDriverTimeTuple expected = new StringDriverTimeTuple("John", "Doe");

    List<StringDriverTimeTuple> actual = provideIa().convert(s);

    Assertions.assertNotNull(actual);
    Assertions.assertEquals(1, actual.size());
    Assertions.assertEquals(expected.driverName(), actual.getFirst().driverName());
    Assertions.assertEquals(expected.time(), actual.getFirst().time());
  }

  @Test
  void convertNoName(){
    String s = "1:23.456";
    StringDriverTimeTuple expected = new StringDriverTimeTuple("", "1:23.456");

    List<StringDriverTimeTuple> actual = provideIa().convert(s);

    Assertions.assertNotNull(actual);
    Assertions.assertEquals(0, actual.size());
  }

  @Test
  void convertSimple(){
    String s = "John Doe 1:23.456";
    StringDriverTimeTuple expected = new StringDriverTimeTuple("John Doe", "1:23.456");

    List<StringDriverTimeTuple> actual = provideIa().convert(s);

    Assertions.assertNotNull(actual);
    Assertions.assertEquals(1, actual.size());
    Assertions.assertEquals(expected.driverName(), actual.getFirst().driverName());
    Assertions.assertEquals(expected.time(), actual.getFirst().time());
  }

  @Test
  void convertComplexA() {

    String s = "John Doe 1:23.456; Jane Smith 2:34,567; Bob Brown 3:45.678; EmptyName 4:56.789";
    List<StringDriverTimeTuple> expected = List.of(
        new StringDriverTimeTuple("John Doe", "1:23.456"),
        new StringDriverTimeTuple("Jane Smith", "2:34,567"),
        new StringDriverTimeTuple("Bob Brown", "3:45.678"),
        new StringDriverTimeTuple("EmptyName", "4:56.789")
    );
    List<StringDriverTimeTuple> actual = provideIa().convert(s);

    Assertions.assertEquals(expected.size(), actual.size());
    for (int i = 0; i < expected.size(); i++) {
      Assertions.assertEquals(expected.get(i).driverName(), actual.get(i).driverName());
      Assertions.assertEquals(expected.get(i).time(), actual.get(i).time());
    }
  }

  @Test
  void convertComplexB() {

    String s = "John Doe 1:23.456;2:34,567;Bob Brown; EmptyName 4:56.789";
    List<StringDriverTimeTuple> expected = List.of(
            new StringDriverTimeTuple("John Doe", "1:23.456"),
            new StringDriverTimeTuple("Bob", "Brown"),
            new StringDriverTimeTuple("EmptyName", "4:56.789")
    );
    List<StringDriverTimeTuple> actual = provideIa().convert(s);

    Assertions.assertEquals(expected.size(), actual.size());
    for (int i = 0; i < expected.size(); i++) {
      Assertions.assertEquals(expected.get(i).driverName(), actual.get(i).driverName());
      Assertions.assertEquals(expected.get(i).time(), actual.get(i).time());
    }
  }

  @Test
  void convertNull() {

    String s = null;
    Assertions.assertThrows(IllegalArgumentException.class, () -> provideIa().convert(s));
  }
}