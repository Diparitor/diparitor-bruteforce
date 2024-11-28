package com.mebae.diparitor.algorithm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Map.Entry;

import com.mebae.diparitor.model.Player;
import com.mebae.diparitor.model.Power;
import com.mebae.diparitor.model.Round;
import com.mebae.diparitor.utils.Validator;

public final class BruteForceAlgorithm implements Algorithm {
    public Set<Round> run(int nbRounds, Set<Power> powers, Map<Player, Integer> playerGameCounts) {
        Validator.checkPositive(nbRounds, "A tournament must have at least one round");
        Objects.requireNonNull(powers);
        Objects.requireNonNull(playerGameCounts);
        Validator.checkCollectionElementsNotNull(powers, null);
        Validator.checkCollectionElementsNotNull(playerGameCounts.keySet(), null);
        Validator.checkCollectionElementsNotNull(playerGameCounts.values(), null);
        var allPossiblePairings = runAllPossiblePairings(powers, playerGameCounts.keySet());
        System.out.println("All possible pairings : ");
        System.out.println(allPossiblePairings);

        var allPossibleRounds = runAllPossibleGameIndexes(
            new HashSet<>(),
            new ArrayList<>(List.of(0, 1, 2)), // TODO générer par rapport au nombre de pouvoirs 0 ... p
            allPossiblePairings,
            powers.size()
        );

        // System.out.println(allPossibleRounds);
        return Set.of();
    }

    
    private Set<List<Entry<Player, Power>>> runAllPossibleGameIndexes(
        Set<List<Entry<Player, Power>>> allPossibleGames,
        ArrayList<Integer> currentGameIndexesList,
        List<Entry<Player, Power>> allPossiblePairings,
        int nbPowers
    ) {
        var nbPossiblePairings = allPossiblePairings.size();
        if (currentGameIndexesList.get(0) == nbPossiblePairings - nbPowers + 1) {
            return allPossibleGames;
        }
        if (true) {
            System.out.println(currentGameIndexesList);
            // TODO vérifier si la liste courante est cohérente
            // allPossibleGames.add(currentGameIndexesList); // TODO faire ne sorte de ne pas seulement donner les index
        }
        return runAllPossibleGameIndexes(
            allPossibleGames,
            nextGameIndexes(currentGameIndexesList, nbPowers - 1, nbPossiblePairings - 1),
            allPossiblePairings,
            nbPowers
        );
    }

    private ArrayList<Integer> nextGameIndexes(
        ArrayList<Integer> currentGameIndexesList,
        int currentPairingIndex,
        int lastPossiblePairingIndex
    ) {
        if (currentGameIndexesList.get(currentPairingIndex) == lastPossiblePairingIndex - (nombreDindex - currentPairingIndex)) {
            // TODO à optimiser / corriger
            currentGameIndexesList.set(currentPairingIndex, Math.max(currentGameIndexesList.get(currentPairingIndex - 1), currentGameIndexesList.getFirst()));
            return nextGameIndexes(currentGameIndexesList, currentPairingIndex - 1, lastPossiblePairingIndex);
        }

        currentGameIndexesList.set(currentPairingIndex, currentGameIndexesList.get(currentPairingIndex) + 1);
        return currentGameIndexesList;
    }

    // Génère un flux de toutes les paires possibles entre joueurs et puissances
    private List<Entry<Player, Power>> runAllPossiblePairings(Set<Power> powerList, Set<Player> playerList) {
        return playerList.stream()
            .flatMap(player -> createPairingsForPlayer(player, powerList))
            .collect(Collectors.toList());
    }

    // Crée un flux de paires pour un joueur donné avec toutes les puissances
    private Stream<Entry<Player, Power>> createPairingsForPlayer(Player player, Set<Power> powerList) {
        return powerList.stream()
                .map(power -> Map.entry(player, power));
    }
}
