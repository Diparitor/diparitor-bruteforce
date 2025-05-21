package com.mebae.diparitor.model;

import java.util.Objects;

public final class Power {
  private final String name;
  private double coefficient = 0d;

  public Power(String name) {
    Objects.requireNonNull(name);
    this.name = name;
  }

  public Power(String name, double coefficient) {
    this(name);
    this.coefficient = coefficient;
  }

  public double getCoefficient() {
    return coefficient;
  }

  @Override
  public String toString() {
    return name;
  }
}
