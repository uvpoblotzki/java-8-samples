package io.github.uvpoblotzki.time;

import java.time.LocalDate;
import java.time.Period;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.JapaneseDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.HashMap;
import java.util.Map;

import static java.lang.System.out;

public class DateTimeTest {

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

    birthDays.entrySet().forEach(e -> {
      TemporalAccessor bDay = stringToTime(e.getValue());
      Period since = Period.between(LocalDate.from(bDay), LocalDate.now());
      out.printf("%s was born %s years, %s months, %s days ago.\n", e.getKey(),
          since.getYears(), since.getMonths(), since.getDays());
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
