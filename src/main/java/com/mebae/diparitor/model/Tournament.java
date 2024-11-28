package com.mebae.diparitor.model;

import java.util.Objects;
import java.util.Set;

import com.mebae.diparitor.algorithm.Algorithm;
import com.mebae.diparitor.algorithm.BruteForceAlgorithm;
import com.mebae.diparitor.utils.Validator;

public final class Tournament {
    private int nbRounds;
    private Variant variant;
    private Registrations registrations;
    private Algorithm algorithm = new BruteForceAlgorithm();
    private Set<Round> rounds = Set.of();

    public Tournament(int nbRounds, Variant variant, Registrations registrations) {
        Validator.checkPositive(nbRounds, "A tournament must have at least one round");
        Objects.requireNonNull(variant);
        Objects.requireNonNull(registrations);
        this.nbRounds = nbRounds;
        this.variant = variant;
        this.registrations = registrations;
    }

    public Set<Round> generateRounds() {
        rounds = algorithm.run(nbRounds, variant.getPowers(), registrations.getPlayerGameCounts());
        return rounds;
    }

    public Set<Round> getRounds() {
        return rounds;
    }
}
