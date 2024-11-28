/* package com.mebae.diparitor.algorithm;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Map.Entry;

import com.mebae.diparitor.model.Player;
import com.mebae.diparitor.model.Power;
import com.mebae.diparitor.model.Round;
import com.mebae.diparitor.model.Variant;
import com.mebae.diparitor.utils.Validator;

public final class BruteForceAlgorithmLeo implements Algorithm {
    public Set<Round> run(int nbRounds, Set<Power> powers, Map<Player, Integer> playerGameCounts) {
        Validator.checkPositive(nbRounds, "A tournament must have at least one round");
        Objects.requireNonNull(powers);
        Objects.requireNonNull(playerGameCounts);
        Validator.checkCollectionElementsNotNull(powers, null);
        Validator.checkCollectionElementsNotNull(playerGameCounts.keySet(), null);
        Validator.checkCollectionElementsNotNull(playerGameCounts.values(), null);
        var allPossibleRounds = getAllRound(powers, playerGameCounts.keySet());
        System.out.println(allPossibleRounds);
        return Set.of();
    }

    private ArrayList<ArrayList<Entry<Player, Power>>> getAllRound(ArrayList<Entry<Player, Power>> allPossiblePairings, int nbPowers) {
        var listofRound = new ArrayList<ArrayList<Entry<Player, Power>>>();
        
        var knownPlayer = new ArrayList<Player>();
        var knownPower = new ArrayList<Power>();
        var player = allPossiblePairings.getFirst().getKey();
        knownPlayer.add(player);
        
        listofRound = getAllPossibilities(allPossiblePairings, knownPlayer, knownPower, null, listofRound);

        return listofRound;
    }

    private ArrayList<ArrayList<Entry<Player, Power>>> getAllPossibilities(
        ArrayList<Entry<Player, Power>> allPossiblePairings,
        ArrayList<Player> knownPlayer,
        ArrayList<Power> knownPower,
        ArrayList<Entry<Player, Power>> round,
        ArrayList<ArrayList<Entry<Player, Power>>> listofRound
    ) {
        Player player = null;
        for (var pairing : allPossiblePairings) {
            if (!knownPlayer.contains(pairing.getKey())) {
                player = pairing.getKey();
                break;
            }
        }
        
        if (player == null) {
            listofRound.add(round);
            return listofRound;
        }

        knownPlayer.add(player);
        
        for (var power : Variant.STANDARD_VARIANT.getPowers()) {
            if (knownPower.contains(power)) {
                continue;
            }

            knownPower.add(power);
            
            if ( round == null) {
                round = new ArrayList<Entry<Player, Power>>();
            }
            var pairing = Map.entry(player, power);
            round.add(pairing);

            listofRound = getAllPossibilities(allPossiblePairings, knownPlayer, knownPower, round, listofRound);

            knownPower.remove(power);
        }
            
        
        knownPlayer.remove(player);

        return listofRound;
    }

    // Génère un flux de toutes les paires possibles entre joueurs et puissances
    private Set<Entry<Player, Power>> runAllPossiblePairings(Set<Power> powerList, Set<Player> playerList) {
        return playerList.stream()
            .flatMap(player -> createPairingsForPlayer(player, powerList))
            .collect(Collectors.toSet());
    }

    // Crée un flux de paires pour un joueur donné avec toutes les puissances
    private Stream<Entry<Player, Power>> createPairingsForPlayer(Player player, Set<Power> powerList) {
        return powerList.stream()
                .map(power -> Map.entry(player, power));
    }
}
 */