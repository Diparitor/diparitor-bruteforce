package com.mebae.diparitor.algorithm;

import com.mebae.diparitor.entity.Variant;
import com.mebae.diparitor.model.*;
import com.mebae.diparitor.utils.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static com.mebae.diparitor.model.Pairing.computePairingsForAllPlayers;
import static com.mebae.diparitor.model.Player.gameCountSum;
import static com.mebae.diparitor.model.Tournament.computeTournamentWithIndexes;
import static com.mebae.diparitor.utils.ListUtils.computeFirstIndexes;
import static com.mebae.diparitor.utils.ListUtils.computeNextIndexes;

public final class BruteForceAlgorithm {
  public static Tournament computeBestTournament(Variant variant, Set<Player> players) {
    Objects.requireNonNull(players);
    Objects.requireNonNull(variant);
    Validator.checkNonNullElements(players, "Players can't contain null");
    var gameCount = gameCountSum(players) / variant.powerCount();
    var allPossiblePairings = computePairingsForAllPlayers(players, variant.powers());
    var allPossibleGames =
            computeAllPossibleGames(computeFirstIndexes(variant.powerCount()), new ArrayList<>(), allPossiblePairings,
                    variant);
    var tournaments =
            computeAllPossibleTournaments(new ArrayList<>(), computeFirstIndexes(gameCount), allPossibleGames, variant,
                    players);
    System.out.println("------------------");
    var minCoefficient = tournaments.stream().map(Tournament::getCoefficient).min(Coefficient::compareTo).orElseThrow();
    return tournaments.stream()
            .filter(tournament -> tournament.getCoefficient().equals(minCoefficient))
            .findFirst()
            .orElseThrow();
  }

  private static ArrayList<Tournament> computeAllPossibleTournaments(ArrayList<Tournament> tournaments,
                                                                     List<Integer> currentTournamentGameIndexesList,
                                                                     List<Game> allPossibleGames, Variant variant,
                                                                     Set<Player> players) {
    if (currentTournamentGameIndexesList.isEmpty()) {
      return tournaments;
    }
    var tournament = computeTournamentWithIndexes(allPossibleGames, currentTournamentGameIndexesList, players, variant);
    if (Tournament.isTournamentValid(tournament, players)) {
      tournaments.add(tournament);
      System.out.println(tournament + " Coefficient : " + tournament.getCoefficient());
    }
    return computeAllPossibleTournaments(tournaments,
            computeNextIndexes(currentTournamentGameIndexesList, allPossibleGames.size() - 1), allPossibleGames,
            variant, players);
  }

  private static ArrayList<Game> computeAllPossibleGames(List<Integer> currentGamePairingIndexesList,
                                                         ArrayList<Game> allPossibleGames,
                                                         List<Pairing> allPossiblePairings, Variant variant) {
    if (currentGamePairingIndexesList.isEmpty()) {
      return allPossibleGames;
    }
    var currentGame = Game.of(allPossiblePairings, currentGamePairingIndexesList);
    if (currentGame.isValid(variant.powerCount())) {
      allPossibleGames.add(currentGame);
    }
    return computeAllPossibleGames(computeNextIndexes(currentGamePairingIndexesList, allPossiblePairings.size() - 1),
            allPossibleGames, allPossiblePairings, variant);
  }
}
