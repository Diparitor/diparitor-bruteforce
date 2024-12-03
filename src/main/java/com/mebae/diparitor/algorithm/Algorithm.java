package com.mebae.diparitor.algorithm;

import java.util.Map;
import java.util.Set;

import com.mebae.diparitor.model.Game;
import com.mebae.diparitor.model.Player;
import com.mebae.diparitor.model.Power;

public interface Algorithm {
  Set<Game> computeBestTournamentGames(Set<Power> powers, Map<Player, Integer> playerGameCounts);
}
