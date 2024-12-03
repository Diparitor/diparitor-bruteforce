package com.mebae.diparitor.model;

import java.util.Objects;

public record Power(String name, double coefficient) {
  public Power {
    Objects.requireNonNull(name);
  }

  @Override
  public String toString() {
    return name;
  }
}
