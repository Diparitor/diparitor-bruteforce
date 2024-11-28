package com.mebae.diparitor.algorithm;

import java.util.Map;
import java.util.Set;

import com.mebae.diparitor.model.Player;
import com.mebae.diparitor.model.Power;
import com.mebae.diparitor.model.Round;

public interface Algorithm {
    Set<Round> run(int nbRounds, Set<Power> powers, Map<Player, Integer> playerGameCounts);
}
