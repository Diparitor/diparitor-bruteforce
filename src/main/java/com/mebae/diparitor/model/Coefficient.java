package com.mebae.diparitor.model;

import com.mebae.diparitor.entity.Variant;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Value opponent diversity then power difficulty
 */
public final class Coefficient implements Comparable<Coefficient> {
  private final double opponentDiversity;
  private final double powerDifficultyVariance;

  private Coefficient(double opponentDiversity, double powerDifficultyVariance) {
    this.opponentDiversity = opponentDiversity;
    this.powerDifficultyVariance = powerDifficultyVariance;
  }

  public static Coefficient of(Variant variant, Set<Player> players, List<Game> games) {
    return new Coefficient(computeOpponentDiversity(players, games, variant.powerCount()),
            computePowerDifficultyVariance(variant, games));
  }

  private static double computeOpponentDiversity(Set<Player> players, List<Game> games, int powerCount) {
    return players.stream()
            .map(player -> computePlayerOpponentDiversity(player, games, powerCount))
            .mapToDouble(Double::doubleValue)
            .sum();
  }

  private static double computePlayerOpponentDiversity(Player player, List<Game> games, int powerCount) {
    return games.stream()
            .filter(game -> game.contains(player))
            .flatMap(game -> game.players().stream())
            .filter(opponent -> !opponent.equals(player))
            .collect(Collectors.groupingBy(opponent -> opponent, Collectors.counting()))
            .values()
            .stream()
            .mapToDouble(matchCount -> computeMatchCountOpponentDiversity(matchCount, powerCount))
            .sum();
  }

  /**
   * +0 if encountered 1 time
   * +1 if encountered 2 times
   * +nbPower if encountered 3 times
   * +nbPower^2 if encountered 4 times
   * +nbPower^3 if encountered 5 times and so on...
   */
  private static double computeMatchCountOpponentDiversity(double opponentMatchCount, int powerCount) {
    if (opponentMatchCount == 1) {
      return 0;
    }
    if (opponentMatchCount == 2) {
      return 1;
    }
    else {
      return Math.pow(powerCount, opponentMatchCount - 2);
    }
  }

  private static double computePowerDifficultyVariance(Variant variant, List<Game> games) {
    if (!variant.hasCoefficient()) {
      return 0;
    }
    var powerDifficultyByPlayer = computePowerDifficultyAverageByPlayer(games);
    var powerDifficultyAverage =
            powerDifficultyByPlayer.stream().mapToDouble(Double::doubleValue).average().orElseThrow();
    return powerDifficultyByPlayer.stream()
            .mapToDouble(x -> Math.pow(x - powerDifficultyAverage, 2))
            .average()
            .orElseThrow();
  }

  private static List<Double> computePowerDifficultyAverageByPlayer(List<Game> games) {
    return games.stream()
            .flatMap(game -> game.pairings().stream())
            .collect(Collectors.groupingBy(Pairing::player, Collectors.mapping(Pairing::power, Collectors.toList())))
            .values()
            .stream()
            .map(powers -> powers.stream().mapToDouble(Power::getCoefficient).average().orElseThrow())
            .toList();
  }

  /**
   * Compares this Coefficient to the specified Coefficient.
   *
   * @param val the Coefficient to compare to
   * @return -1, 0, or 1 as this Coefficient is less than, equal to, or greater than val.
   */
  @Override
  public int compareTo(Coefficient val) {
    var comparison = Double.compare(opponentDiversity, val.opponentDiversity);
    if (comparison != 0) {
      return comparison;
    }
    return Double.compare(powerDifficultyVariance, val.powerDifficultyVariance);
  }

  @Override
  public boolean equals(Object o) {
    return this == o ||
           (o instanceof Coefficient && Double.compare(opponentDiversity, ((Coefficient) o).opponentDiversity) == 0 &&
            Double.compare(powerDifficultyVariance, ((Coefficient) o).powerDifficultyVariance) == 0);
  }

  @Override
  public int hashCode() {
    return Objects.hash(opponentDiversity, powerDifficultyVariance);
  }

  @Override
  public String toString() {
    return "opponentDiversity=" + opponentDiversity + ", powerDifficultyVariance=" + powerDifficultyVariance;
  }
}
