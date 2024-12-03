package com.mebae.diparitor.model;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public final class Game {
  Set<Pairing> pairings;

  private Game(Set<Pairing> pairings) {
    this.pairings = pairings;
  }

  public static Game of(List<Pairing> pairings, List<Integer> pairingIndexes) {
    Objects.requireNonNull(pairings);
    Objects.requireNonNull(pairingIndexes);
    // TODO faire les vérifs (par ex si valeur null dans la liste)
    return new Game(pairingIndexes.stream().map(pairings::get).collect(Collectors.toSet()));
  }

  public boolean isValid(int powerCount) {
    return pairings.stream().map(Pairing::player).distinct().count() == powerCount
      && pairings.stream().map(Pairing::power).distinct().count() == powerCount;
  }

  @Override
  public String toString() {
    return pairings.stream().map(Pairing::toString).collect(Collectors.joining(", "));
  }
}
