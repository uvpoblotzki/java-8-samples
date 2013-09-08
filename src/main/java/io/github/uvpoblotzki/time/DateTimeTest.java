package io.github.uvpoblotzki.time;

import java.time.LocalDate;
import java.time.Period;
import java.time.chrono.JapaneseDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.HashMap;
import java.util.Map;

import static java.lang.System.out;

public class DateTimeTest {

  private static class Tuple<T, R> {
    public final T _1;
    public final R _2;

    private Tuple(T _1, R _2) {
      this._1 = _1;
      this._2 = _2;
    }

    public static <U,V> Tuple<U,V> from(U t, V r) {return new Tuple<>(t, r);}
  }

  // Birthdays
  private static Map<String, String> birthDays = new HashMap<String, String>() {{
    put("Ulrich", "1974-05-09");
    put("Sandra", "1975-01-30");
    put("Elsa", "2010-11-27");
  }};

  public static void main(String[] args) {
    DateTimeTest test = new DateTimeTest();

    test.testFormat();
    test.testPeriod();
    test.testChronology();
  }

  private void testFormat() {
    out.printf("--------------------- Test Format ------------------------------\n");

    birthDays.entrySet().forEach(e -> {
      out.printf("%s was born %s\n", e.getKey(), stringToTime(e.getValue()));
    });
  }

  private void testPeriod() {
    out.printf("--------------------- Test Period ------------------------------\n");

    birthDays.entrySet().stream().map(e -> {
      // string to Time
      return Tuple.from(e.getKey(), stringToTime(e.getValue()));
    }).map(t -> {
      // Time to Period
      return Tuple.from(t._1, Period.between(LocalDate.from(t._2), LocalDate.now()));
    }).forEach(t -> {
      out.printf("%s was born %s years, %s months, %s days ago.\n", t._1,
          t._2.getYears(), t._2.getMonths(), t._2.getDays());
    });
  }

  private void testChronology() {
    out.printf("--------------------- Test Chronology ---------------------------\n");

    birthDays.entrySet().forEach(e -> {
      JapaneseDate japaneseDate = JapaneseDate.from(stringToTime(e.getValue()));
      out.printf("%s was born %s.\n", e.getKey(), japaneseDate);
    });

  }

  private TemporalAccessor stringToTime(String date) {
    DateTimeFormatter isoFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    return isoFormat.parse(date);
  }

}
