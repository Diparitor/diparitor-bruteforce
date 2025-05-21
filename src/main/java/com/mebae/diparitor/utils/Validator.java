package com.mebae.diparitor.utils;

import java.util.Collection;
import java.util.Objects;

public final class Validator {
  public static void checkNonNullElements(Collection<?> collection) {
    checkNonNullElements(collection, null);
  }

  public static void checkNonNullElements(Collection<?> collection, String message) {
    Objects.requireNonNull(collection);
    if (collection.stream().anyMatch(Objects::isNull)) {
      throw new IllegalArgumentException(message != null ? message : "Collection contains null elements");
    }
  }
}
