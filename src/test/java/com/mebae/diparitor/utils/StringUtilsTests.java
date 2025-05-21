package com.mebae.diparitor.utils;

import org.junit.jupiter.api.Test;

import static com.mebae.diparitor.utils.StringUtils.isNullOrEmpty;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StringUtilsTests {
  @Test
  public void isNullOrEmptyTest_ShouldReturnTrue() {
    assertTrue(isNullOrEmpty(null));
    assertTrue(isNullOrEmpty(""));
    assertTrue(isNullOrEmpty("  "));
  }

  @Test
  public void isNullOrEmptyTest_ShouldReturnFalse() {
    assertFalse(isNullOrEmpty("Jean"));
    assertFalse(isNullOrEmpty("Jean Dupont"));
    assertFalse(isNullOrEmpty("  Jean Dupont  "));
  }
}
