package com.mebae.diparitor.model;

import com.mebae.diparitor.entity.Variant;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public final class Tournament {
  private final List<Game> games;
  private final Set<Player> players;
  private final Variant variant;
  private final Coefficient coefficient;

  public Tournament(Variant variant, Set<Player> players, List<Game> games) {
    Objects.requireNonNull(variant);
    Objects.requireNonNull(games);
    this.players = Set.copyOf(players);
    this.variant = variant;
    this.games = games;
    this.coefficient = Coefficient.of(variant, players, games);
  }

  public static boolean isTournamentValid(Tournament tournament, Set<Player> players) {
    return players.stream()
            .allMatch(player -> computeGamePlayersNotDistinct(tournament.games).stream()
                                        .filter(gamePlayer -> player == gamePlayer)
                                        .count() == player.gameCount());
  }

  public static Tournament computeTournamentWithIndexes(List<Game> allPossibleGames, List<Integer> gamesIndexes,
                                                        Set<Player> players, Variant variant) {
    Objects.requireNonNull(allPossibleGames);
    Objects.requireNonNull(gamesIndexes);
    return new Tournament(variant, players,
            gamesIndexes.stream().map(allPossibleGames::get).collect(Collectors.toList()));
  }

  public static List<Player> computeGamePlayersNotDistinct(List<Game> games) {
    return games.stream().flatMap(game -> game.players().stream()).toList();
  }

  public Coefficient getCoefficient() {
    return coefficient;
  }

  @Override
  public String toString() {
    return games.stream().map(Objects::toString).collect(Collectors.joining(" - "));
  }
}
