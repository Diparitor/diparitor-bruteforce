package com.mebae.diparitor.model;

import com.mebae.diparitor.entity.PlayerInfo;

import java.util.Objects;
import java.util.Set;

public record Player(PlayerInfo playerInfo, int gameCount) {
  public Player {
    Objects.requireNonNull(playerInfo);
    if (gameCount < 1) {
      throw new IllegalArgumentException("A player must join at least one game");
    }
  }

  public static int gameCountSum(Set<Player> players) {
    return players.stream().map(Player::gameCount).reduce(0, Integer::sum);
  }

  @Override
  public String toString() {
    return playerInfo.firstName() + " " + playerInfo.lastName();
  }
}
