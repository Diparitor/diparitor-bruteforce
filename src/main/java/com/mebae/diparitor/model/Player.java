package com.mebae.diparitor.model;

import com.mebae.diparitor.utils.StringUtils;

public record Player(String firstName, String lastName) {
    public Player {
        if (StringUtils.isNullOrEmpty(firstName) && StringUtils.isNullOrEmpty(lastName)) {
            throw new IllegalArgumentException("A player must have a first name or a last name");
        }
    }
}
