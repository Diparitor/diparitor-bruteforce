package com.mebae.diparitor.model;

import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

public final class Game {
    Set<Entry<Player, Power>> pairings;

    public Game(Set<Entry<Player, Power>> pairings) {
        // TODO faire les vérifs & copie
        this.pairings = pairings;
    }
}
