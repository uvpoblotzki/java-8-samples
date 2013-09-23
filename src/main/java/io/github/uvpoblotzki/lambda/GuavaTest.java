package io.github.uvpoblotzki.lambda;

import com.google.common.collect.*;

import static java.lang.System.out;

public class GuavaTest {

  public static void main(String[] args) {
    filterTest();
  }

  public static void filterTest() {
    Iterable<Integer> numbers = ContiguousSet.create(Range.closed(1, 100), DiscreteDomain.integers());
    FluentIterable
        .from(numbers)
        .filter(n -> n == 2 || n % 2 != 0) // remove all even numbers but 2
        .filter(GuavaTest::isPrime)
        .forEach(out::println);

    FluentIterable
        .from(numbers)
        .transform(n -> ImmutableMap.of(n, factors(n)))
        .forEach(out::println);
  }

  private static boolean isPrime(int n) {
    if (n == 1) return false;
    if (n == 2) return true;
    int upper = Math.max(2, (int) Math.sqrt(n));
    Iterable<Integer> factors = ContiguousSet.create(Range.closed(2, upper), DiscreteDomain.integers());
    return !FluentIterable.from(factors).anyMatch(i -> n % i == 0);
  }

  private static Iterable<Integer> factors(int n) {
    Iterable<Integer> factors = ContiguousSet.create(Range.closed(1, n), DiscreteDomain.integers());
    return FluentIterable.from(factors).filter(i -> n % i == 0);
  }
}
