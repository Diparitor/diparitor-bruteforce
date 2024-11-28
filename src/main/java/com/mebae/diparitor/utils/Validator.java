package com.mebae.diparitor.utils;

import java.util.Collection;
import java.util.Objects;

public final class Validator {
    public static void checkPositive(int x, String message) {
        if (x <= 0) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void checkCollectionElementsNotNull(Collection<?> collection, String message) {
        Objects.requireNonNull(collection);
        if (collection.stream().anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException(message);
        }
    }
}
