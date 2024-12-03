package com.mebae.diparitor.model;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

import com.mebae.diparitor.algorithm.Algorithm;
import com.mebae.diparitor.algorithm.BruteForceAlgorithm;

public final class Tournament {
    private Variant variant;
    private Map<Player, Integer> playerGameCounts;
    private Algorithm algorithm = new BruteForceAlgorithm();
    private Set<Game> games = Set.of();

    public Tournament(Variant variant, Map<Player, Integer> playerGameCounts) {
        Objects.requireNonNull(variant);
        Objects.requireNonNull(playerGameCounts); // TODO faire les vérifs & copie
        this.variant = variant;
        this.playerGameCounts = playerGameCounts;
    }

    public Set<Game> generateGames() {
        return algorithm.computeBestTournamentGames(variant.getPowers(), playerGameCounts);
    }

    public Set<Game> getGames() {
        return games;
    }
}
