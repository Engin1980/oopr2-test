package gitDemo.implementations;

import gitDemo.interfaces.IB;
import gitDemo.types.DriverTime;
import gitDemo.types.StringDriverTimeTuple;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class IBTest {
  private static IB provideIb() {
    return new TeacherB();
  }

  @Test
  void convertSimpleComma() {
    StringDriverTimeTuple sdtt = new StringDriverTimeTuple("Lewis Hamilton", "1:30,456");
    List<StringDriverTimeTuple> ins = List.of(sdtt);
    IB ib = provideIb();

    List<DriverTime> outs = ib.convert(ins);

    Assertions.assertNotNull(outs);
    Assertions.assertEquals(1, outs.size());
    DriverTime dt = outs.getFirst();
    Assertions.assertEquals("Lewis Hamilton", dt.getName());
    Assertions.assertEquals(90456, dt.getTimeInMs());
  }

  @Test
  void convertSimpleDot() {
    StringDriverTimeTuple sdtt = new StringDriverTimeTuple("Lewis Hamilton", "1:30.456");
    List<StringDriverTimeTuple> ins = List.of(sdtt);
    IB ib = provideIb();

    List<DriverTime> outs = ib.convert(ins);

    Assertions.assertNotNull(outs);
    Assertions.assertEquals(1, outs.size());
    DriverTime dt = outs.getFirst();
    Assertions.assertEquals("Lewis Hamilton", dt.getName());
    Assertions.assertEquals(90456, dt.getTimeInMs());
  }

  @Test
  void convertNotValidTime() {
    StringDriverTimeTuple sdtt = new StringDriverTimeTuple("Lewis Hamilton", "1:3a.456");
    List<StringDriverTimeTuple> ins = List.of(sdtt);
    IB ib = provideIb();

    List<DriverTime> outs = ib.convert(ins);

    Assertions.assertNotNull(outs);
    Assertions.assertEquals(0, outs.size());
  }

  @Test
  void convertEmptyDriver() {
    StringDriverTimeTuple sdtt = new StringDriverTimeTuple("", "1:30.456");
    List<StringDriverTimeTuple> ins = List.of(sdtt);
    IB ib = provideIb();

    List<DriverTime> outs = ib.convert(ins);

    Assertions.assertNotNull(outs);
    Assertions.assertEquals(0, outs.size());
  }

  @Test
  void convertNullDriver() {
    StringDriverTimeTuple sdtt = new StringDriverTimeTuple(null, "1:30.456");
    List<StringDriverTimeTuple> ins = List.of(sdtt);
    IB ib = provideIb();

    List<DriverTime> outs = ib.convert(ins);

    Assertions.assertNotNull(outs);
    Assertions.assertEquals(0, outs.size());
  }

  @Test
  void convertNullTime() {
    StringDriverTimeTuple sdtt = new StringDriverTimeTuple("John", null);
    List<StringDriverTimeTuple> ins = List.of(sdtt);
    IB ib = provideIb();

    List<DriverTime> outs = ib.convert(ins);

    Assertions.assertNotNull(outs);
    Assertions.assertEquals(0, outs.size());
  }
}
