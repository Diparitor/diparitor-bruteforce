package com.mebae.diparitor.model;

import com.mebae.diparitor.utils.Validator;

import java.util.Objects;
import java.util.Set;

public final class Variant {
  public static final Variant STANDARD_VARIANT = new Variant("Standard",
    // TODO préciser d'où viennent ces coefficients (site + date)
    "TODO",
    Set.of(new Power("Austria-Hungary", 2.78),
      new Power("France", 6.14),
      new Power("Germany", 5.56),
      new Power("Great Britain", 4.19),
      new Power("Italy", 5.60),
      new Power("Russia", 5.18),
      new Power("Turkey", 4.56)));

  private String name = "";
  private String source = "";
  private Set<Power> powers;

  public Variant(String name, Set<Power> powers) {
    Objects.requireNonNull(name);
    Validator.checkCollectionElementsNotNull(powers, "A power can't be null");
    if (powers.size() < 2) {
      throw new IllegalArgumentException("There must be at least 2 powers");
    }
    this.name = name;
    this.powers = Set.copyOf(powers);
  }

  public Variant(String name, String source, Set<Power> powers) {
    this(name, powers);
    Objects.requireNonNull(source);
    this.source = source;
  }

  public Set<Power> getPowers() {
    return powers;
  }
}
