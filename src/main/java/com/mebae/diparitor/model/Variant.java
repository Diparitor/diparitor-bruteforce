package com.mebae.diparitor.model;

import java.util.Set;

import com.mebae.diparitor.utils.Validator;

public final class Variant {
    private String name = "";
    private Set<Power> powers;

    // TODO préciser d'où viennent ces coefficients (site + date)
    public static final Variant STANDARD_VARIANT = new Variant("Standard", Set.of(
        new Power("Austria-Hungary", 2.78),
        new Power("France", 6.14),
        new Power("Germany", 5.56),
        new Power("Great Britain", 4.19),
        new Power("Italy", 5.60),
        new Power("Russia", 5.18),
        new Power("Turkey", 4.56)));

    public Variant(String name, Set<Power> powers) {
        Validator.checkCollectionElementsNotNull(powers, "A power can't be null");
        if (powers.size() < 2) {
            throw new IllegalArgumentException("There must be at least 2 powers");
        }
        this.powers = Set.copyOf(powers);
    }

    public Set<Power> getPowers() {
        return powers;
    }
}
