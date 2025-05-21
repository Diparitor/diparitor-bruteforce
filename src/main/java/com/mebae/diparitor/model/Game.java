package com.mebae.diparitor.model;

import com.mebae.diparitor.utils.Validator;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public final class Game {
  private final Set<Pairing> pairings;
  private Set<Player> players = null;

  private Game(Set<Pairing> pairings) {
    this.pairings = Set.copyOf(pairings);
  }

  public static Game of(List<Pairing> pairings, List<Integer> pairingIndexes) {
    Objects.requireNonNull(pairings);
    Objects.requireNonNull(pairingIndexes);
    Validator.checkNonNullElements(pairings);
    Validator.checkNonNullElements(pairingIndexes);
    return new Game(pairingIndexes.stream().map(pairings::get).collect(Collectors.toSet()));
  }

  public boolean isValid(int powerCount) {
    return pairings.stream().map(Pairing::player).distinct().count() == powerCount &&
           pairings.stream().map(Pairing::power).distinct().count() == powerCount;
  }

  public Set<Player> players() {
    if (players == null) {
      players = pairings.stream().map(Pairing::player).collect(Collectors.toSet());
    }
    return players;
  }

  public Set<Pairing> pairings() {
    return pairings;
  }

  public boolean contains(Player player) {
    return players().contains(player);
  }

  @Override
  public String toString() {
    return pairings.stream().map(Pairing::toString).collect(Collectors.joining(", ", "[", "]"));
  }
}
