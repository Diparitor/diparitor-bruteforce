package com.mebae.diparitor.model;

import java.util.Map;

public final class Registrations {
    private Map<Player, Integer> playerGameCounts;

    public Registrations(Map<Player, Integer> playerGameCounts) {
        // TODO faire les vérifs & copie
        this.playerGameCounts = Map.copyOf(playerGameCounts);
    }

    public Map<Player, Integer> getPlayerGameCounts() {
        return playerGameCounts;
    }
}
