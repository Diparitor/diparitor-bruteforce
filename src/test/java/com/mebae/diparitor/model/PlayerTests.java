package com.mebae.diparitor.model;

import com.mebae.diparitor.entity.PlayerInfo;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTests {
  private final PlayerInfo playerInfo = new PlayerInfo("John", "Doe");

  @Test
  public void testPlayerConstructorValidInput() {
    var player = new Player(playerInfo, 3);
    assertNotNull(player, "Le joueur ne doit pas être nul");
    assertEquals(3, player.gameCount(), "Le nombre de jeux doit être 3");
  }

  @Test
  public void testPlayerConstructorInvalidGameCount() {
    assertThrows(IllegalArgumentException.class, () -> {new Player(playerInfo, 0);});
  }

  @Test
  public void testGameCountSum() {
    var player1 = new Player(playerInfo, 3);
    var player2 = new Player(playerInfo, 5);
    var players = Set.of(player1, player2);
    var totalGameCount = Player.gameCountSum(players);
    assertEquals(8, totalGameCount, "La somme des gameCount des joueurs doit être 8");
  }

  @Test
  public void testGameCountSumEmptySet() {
    var players = Set.<Player>of();
    var totalGameCount = Player.gameCountSum(players);
    assertEquals(0, totalGameCount, "La somme des gameCount pour un ensemble vide doit être 0");
  }

  @Test
  public void testGameCountSumSinglePlayer() {
    var player = new Player(playerInfo, 4);
    var players = Set.of(player);
    var totalGameCount = Player.gameCountSum(players);
    assertEquals(4, totalGameCount, "La somme des gameCount pour un seul joueur doit être 4");
  }
}