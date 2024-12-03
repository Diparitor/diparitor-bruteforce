package com.mebae.diparitor.algorithm;

import com.mebae.diparitor.model.*;
import com.mebae.diparitor.utils.Validator;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public final class BruteForceAlgorithm implements Algorithm {
  public Set<Game> computeBestTournamentGames(Set<Power> powers, Map<Player, Integer> playerGameCounts) {
    Objects.requireNonNull(powers);
    Objects.requireNonNull(playerGameCounts);
    Validator.checkCollectionElementsNotNull(powers, null);
    Validator.checkCollectionElementsNotNull(playerGameCounts.keySet(), null);
    Validator.checkCollectionElementsNotNull(playerGameCounts.values(), null);
    var allTournaments = computeAllPossibleTournaments(playerGameCounts, powers);
    return Set.of();
  }

  /**
   * @return la liste de toutes les rondes possibles.
   */
  private Set<Set<Game>> computeAllPossibleTournaments(Map<Player, Integer> playerGameCounts, Set<Power> powers) {
    var powerCount = powers.size();
    var gameCount = playerGameCounts.values().stream().reduce(0, Integer::sum);
    var allPossibleGames = computeAllPossibleGames(new HashSet<>(),
      computeFirstGameIndexes(powerCount),
      computePairingsForAllPlayers(playerGameCounts.keySet(), powers),
      powerCount);
    return Set.of();
  }

  /**
   * @return la liste de toutes les parties possibles.
   */
  private Set<Game> computeAllPossibleGames(Set<Game> allPossibleGames,
                                            ArrayList<Integer> currentGamePairingIndexesList,
                                            List<Pairing> allPossiblePairings,
                                            int powerCount) {
    var possiblePairingsCount = allPossiblePairings.size();
    if (currentGamePairingIndexesList.isEmpty()) {
      return allPossibleGames;
    }
    var currentGame = Game.of(allPossiblePairings, currentGamePairingIndexesList);
    if (currentGame.isValid(powerCount)) {
      System.out.println(currentGame); // TODO remove
      allPossibleGames.add(currentGame);
    }
    return computeAllPossibleGames(allPossibleGames,
      computeNextGameIndexes(currentGamePairingIndexesList, powerCount - 1, possiblePairingsCount - 1),
      allPossiblePairings,
      powerCount);
  }

  /**
   * @return la liste des index des appariements de la première partie à tester.
   */
  private ArrayList<Integer> computeFirstGameIndexes(int powerCount) {
    return new ArrayList<>(IntStream.range(0, powerCount).boxed().toList());
  }

  /**
   * @return la liste des index des appariements de la prochaine partie à tester.
   */
  private ArrayList<Integer> computeNextGameIndexes(ArrayList<Integer> currentGame,
                                                    int currentPairingIndex,
                                                    int lastPossiblePairing) {
    if (currentGame.get(currentPairingIndex) >= lastPossiblePairing - (currentGame.size() - 1) + currentPairingIndex) {
      if (currentPairingIndex == 0) {
        return new ArrayList<>(List.of());
      }
      IntStream.range(currentPairingIndex, currentGame.size())
        .forEach(i -> currentGame.set(i, currentGame.get(i - 1) + 1));
      return computeNextGameIndexes(currentGame, currentPairingIndex - 1, lastPossiblePairing);
    }
    currentGame.set(currentPairingIndex, currentGame.get(currentPairingIndex) + 1);
    return currentGame;
  }

  /**
   * @return la liste des paires possibles entre joueurs et puissances.
   */
  private List<Pairing> computePairingsForAllPlayers(Set<Player> players, Set<Power> powers) {
    return players.stream().flatMap(player -> computePairingsForPlayer(player, powers)).collect(Collectors.toList());
  }

  /**
   * @return un flux des paires possibles pour un joueur donné et les puissances.
   */
  private Stream<Pairing> computePairingsForPlayer(Player player, Set<Power> powers) {
    return powers.stream().map(power -> new Pairing(player, power));
  }
}
