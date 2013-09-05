package io.github.uvpoblotzki.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class CollectionLambdaTest {

  public static void main(String[] args) {
    filterTest();
  }

  private static void filterTest() {
    List<String> names = Arrays.asList("Ulrich", "Sandra", "Elsa");

    // Filter and sort the list of names

    names.stream()
        .filter(containsPredicate("a")) // predicate creator
        .filter(containsPredicate("l"))
        .sorted()
        .forEach(System.out::println); // stream methods call have to be terminates, e.g. `foreach` or `reduce`
  }


  private static Predicate<String> containsPredicate(String part) {
    // using lambda syntax to implement predicate
    return (n) -> {return n.contains(part);};
  }

}
