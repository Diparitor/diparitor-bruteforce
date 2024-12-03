package com.mebae.diparitor.model;

import java.util.Objects;

public record Pairing(Player player, Power power) {
  public Pairing {
    Objects.requireNonNull(player);
    Objects.requireNonNull(power);
  }

  @Override
  public String toString() {
    return player + " : " + power;
  }
}
