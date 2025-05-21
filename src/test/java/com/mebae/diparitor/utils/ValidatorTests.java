package com.mebae.diparitor.utils;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static com.mebae.diparitor.utils.Validator.checkNonNullElements;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ValidatorTests {
  @Test
  public void checkNonNullElements_ShouldThrowException() {
    assertThrows(IllegalArgumentException.class, () -> checkNonNullElements(new ArrayList<>(Arrays.asList(null, 0, 2))));
    assertThrows(IllegalArgumentException.class, () -> checkNonNullElements(Arrays.asList("Alice", null, "Bob")));
  }

  @Test
  public void checkNonNullElements_ShouldNotThrowException() {
    assertDoesNotThrow(() -> checkNonNullElements(new ArrayList<>(Arrays.asList(0, 2, 5))));
    assertDoesNotThrow(() -> checkNonNullElements(Arrays.asList("Alice", "Bob", "Charlie")));
  }
}
