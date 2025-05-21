package com.mebae.diparitor.utils;

import org.junit.jupiter.api.Test;

import java.util.List;

import static com.mebae.diparitor.utils.ListUtils.computeFirstIndexes;
import static com.mebae.diparitor.utils.ListUtils.computeNextIndexes;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ListUtilsTests {
  @Test
  void computeNextIndexesTest_ShouldReturnNextIndexes() {
    var nextIndexes = computeNextIndexes(List.of(0, 1, 2), 5);
    assertEquals(List.of(0, 1, 3), nextIndexes);
  }

  @Test
  void computeNextIndexesTest_ShouldReturnNextIndexes_WhenLastIndexesAreMaxed() {
    var nextIndexes1 = computeNextIndexes(List.of(1, 2, 5), 5); // The last index is maxed
    var nextIndexes2 = computeNextIndexes(List.of(0, 4, 5), 5); // the two last indexes are maxed
    var nextIndexes3 = computeNextIndexes(List.of(4, 6, 7, 8, 9), 9); // the four last indexes are maxed
    assertEquals(List.of(1, 3, 4), nextIndexes1);
    assertEquals(List.of(1, 2, 3), nextIndexes2);
    assertEquals(List.of(5, 6, 7, 8, 9), nextIndexes3);
  }

  @Test
  void computeNextIndexesTest_ShouldReturnEmpty_WhenNoNextIndexPossible() {
    var nextIndexes = computeNextIndexes(List.of(3, 4, 5), 5);
    assertEquals(List.of(), nextIndexes);
  }

  @Test
  void computeNextIndexesTest_ShouldHandleSingleElementList() {
    var nextIndexes = computeNextIndexes(List.of(0), 5);
    assertEquals(List.of(1), nextIndexes);
  }

  @Test
  void computeNextIndexesTest_ShouldThrowException_WhenNullList() {
    assertThrows(NullPointerException.class, () -> computeNextIndexes(null, 5));
  }

  @Test
  void computeNextIndexesTest_ShouldThrowException_WhenEmptyList() {
    assertThrows(IllegalArgumentException.class, () -> computeNextIndexes(List.of(), 5));
  }

  @Test
  void computeNextIndexesTest_ShouldThrowException_WhenNegativeIndex() {
    assertThrows(IllegalArgumentException.class, () -> computeNextIndexes(List.of(0, 1, 2), -1));
  }

  @Test
  void computeNextIndexesTest_ShouldThrowException_WhenNegativeElementInList() {
    assertThrows(IllegalArgumentException.class, () -> computeNextIndexes(List.of(-1, 1, 2), 5));
  }

  @Test
  void computeNextIndexesTest_ShouldThrowException_WhenTooBigElementInList() {
    assertThrows(IllegalArgumentException.class, () -> computeNextIndexes(List.of(0, 1, 6), 5));
  }

  @Test
  void computeFirstIndexesTest_ShouldReturnFirstIndexes() {
    assertEquals(List.of(0), computeFirstIndexes(1));
    assertEquals(List.of(0, 1, 2, 3, 4, 5), computeFirstIndexes(6));
  }

  @Test
  void computeFirstIndexesTest_ShouldThrowException_WhenZeroOrNegativeIndex() {
    assertThrows(IllegalArgumentException.class, () -> computeFirstIndexes(0));
    assertThrows(IllegalArgumentException.class, () -> computeFirstIndexes(-1));
    assertThrows(IllegalArgumentException.class, () -> computeFirstIndexes(-16));
  }
}
