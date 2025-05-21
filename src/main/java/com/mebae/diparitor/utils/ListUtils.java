package com.mebae.diparitor.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public final class ListUtils {
  /**
   * Computes the next list of indexes in a strictly increasing sequence.
   *
   * <p>This method calculates the next valid sequence of integers from the given list of indexes,
   * ensuring that the result remains strictly increasing. If no valid next sequence exists, an empty
   * list is returned.</p>
   *
   * <h3>Example usage:</h3>
   * <pre>{@code
   * var nextIndexes = computeNextIndexes(List.of(0, 1, 2), 5);
   * System.out.println(nextIndexes); // Output: [0, 1, 3]
   * }</pre>
   *
   * @param indexesList       the current strictly increasing list of indexes
   * @param lastPossibleIndex the maximum allowable value for any index in the sequence
   * @return the next strictly increasing list of indexes, or an empty list if no valid sequence exists
   * @throws IllegalArgumentException if {@code lastPossibleIndex} is negative or if {@code indexesList} is not
   *                                  strictly increasing
   */
  public static List<Integer> computeNextIndexes(List<Integer> indexesList, int lastPossibleIndex) {
    if (lastPossibleIndex < 0) {
      throw new IllegalArgumentException("lastPossibleIndex cannot be negative");
    }
    if (!isStrictlyIncreasingAndIncludedBetween(0, lastPossibleIndex, indexesList)) {
      throw new IllegalArgumentException(
              "The input list must be strictly increasing and included between 0 and " + lastPossibleIndex);
    }
    var lastIndexPosition = indexesList.size() - 1;
    var indexesListCopy = new ArrayList<>(indexesList);
    var currentIndexPosition = lastIndexPosition;
    while (currentIndexPosition >= 0) {
      if (indexesListCopy.get(currentIndexPosition) < lastPossibleIndex - (lastIndexPosition - currentIndexPosition)) {
        indexesListCopy.set(currentIndexPosition, indexesListCopy.get(currentIndexPosition) + 1);

        for (int i = currentIndexPosition + 1; i < indexesListCopy.size(); i++) {
          indexesListCopy.set(i, indexesListCopy.get(i - 1) + 1);
        }
        return indexesListCopy;
      }
      currentIndexPosition--;
    }
    return List.of();
  }

  private static boolean isStrictlyIncreasingAndIncludedBetween(int min, int max, List<Integer> list) {
    Objects.requireNonNull(list);
    return !list.isEmpty() && list.getFirst() >= min && list.getLast() <= max &&
           IntStream.range(1, list.size()).allMatch(i -> list.get(i) > list.get(i - 1));
  }

  /**
   * Computes a list of integers from 0 (inclusive) to {@code size} (exclusive).
   *
   * @param size the number of integers to generate (must be positive)
   * @return a list of integers from 0 to {@code size - 1}
   * @throws IllegalArgumentException if {@code size} is 0 or negative
   */
  public static List<Integer> computeFirstIndexes(int size) {
    if (size <= 0) {
      throw new IllegalArgumentException("size cannot be 0 or negative");
    }
    return IntStream.range(0, size).boxed().toList();
  }
}
