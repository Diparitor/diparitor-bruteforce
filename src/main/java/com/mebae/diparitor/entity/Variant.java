package com.mebae.diparitor.entity;

import com.mebae.diparitor.model.Power;
import com.mebae.diparitor.utils.Validator;

import java.util.Objects;
import java.util.Set;

public final class Variant {
  public static final Variant STANDARD_VARIANT = new Variant("Standard", "Unknown",
          Set.of(new Power("Austria-Hungary", 2.78), new Power("France", 6.14), new Power("Germany", 5.56),
                  new Power("Great Britain", 4.19), new Power("Italy", 5.60), new Power("Russia", 5.18),
                  new Power("Turkey", 4.56)), true);
  private final String name;
  private final Set<Power> powers;
  private final boolean hasCoefficient;
  private final String source;

  public Variant(String name, Set<Power> powers, boolean hasCoefficient) {
    this(name, "Unknown", powers, hasCoefficient);
  }

  public Variant(String name, String source, Set<Power> powers, boolean hasCoefficient) {
    Objects.requireNonNull(name);
    Objects.requireNonNull(source);
    Validator.checkNonNullElements(powers, "A power can't be null");
    if (powers.size() < 2) {
      throw new IllegalArgumentException("There must be at least 2 powers");
    }
    this.name = name;
    this.source = source;
    this.powers = Set.copyOf(powers);
    this.hasCoefficient = hasCoefficient;
  }

  public String name() {
    return name;
  }

  public String source() {
    return source;
  }

  public Set<Power> powers() {
    return powers;
  }

  public int powerCount() {
    return powers.size();
  }

  public boolean hasCoefficient() {
    return hasCoefficient;
  }
}
