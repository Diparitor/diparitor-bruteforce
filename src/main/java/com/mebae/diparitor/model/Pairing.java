package com.mebae.diparitor.model;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public record Pairing(Player player, Power power) {
  public Pairing {
    Objects.requireNonNull(player);
    Objects.requireNonNull(power);
  }

  public static Set<Pairing> computePairingsForPlayer(Player player, Set<Power> powers) {
    return powers.stream().map(power -> new Pairing(player, power)).collect(Collectors.toSet());
  }

  public static List<Pairing> computePairingsForAllPlayers(Set<Player> players, Set<Power> powers) {
    return players.stream()
            .flatMap(player -> computePairingsForPlayer(player, powers).stream())
            .collect(Collectors.toList());
  }

  @Override
  public String toString() {
    return player + " : " + power;
  }
}
