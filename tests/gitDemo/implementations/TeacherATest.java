package gitDemo.implementations;

import gitDemo.types.StringDriverTimeTuple;
import org.junit.jupiter.api.Assertions;

import java.util.List;

class TeacherATest {

  @org.junit.jupiter.api.Test
  void convert() {

    String s = "John Doe 1:23.456; Jane Smith 2:34,567; Bob Brown 3:45.678; EmptyName 4:56.789";
    List<StringDriverTimeTuple> expected = List.of(
        new StringDriverTimeTuple("John Doe", "1:23.456"),
        new StringDriverTimeTuple("Jane Smith", "2:34,567"),
        new StringDriverTimeTuple("Bob Brown", "3:45.678"),
        new StringDriverTimeTuple("EmptyName", "4:56.789")
    );
    List<StringDriverTimeTuple> actual = new TeacherA().convert(s);

    Assertions.assertEquals(expected.size(), actual.size());
    for (int i = 0; i < expected.size(); i++) {
      Assertions.assertEquals(expected.get(i).driverName(), actual.get(i).driverName());
      Assertions.assertEquals(expected.get(i).time(), actual.get(i).time());
    }
  }

  @org.junit.jupiter.api.Test
  void convertNull() {

    String s = null;
    Assertions.assertThrows(IllegalArgumentException.class, () -> new TeacherA().convert(s));
  }
}