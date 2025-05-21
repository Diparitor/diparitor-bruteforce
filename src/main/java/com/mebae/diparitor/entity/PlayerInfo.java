package com.mebae.diparitor.entity;

import com.mebae.diparitor.utils.StringUtils;

public final class PlayerInfo {
  private String firstName;
  private String lastName;
  private String notes;

  public PlayerInfo(String firstName, String lastName) {
    if (StringUtils.isNullOrEmpty(firstName) && StringUtils.isNullOrEmpty(lastName)) {
      throw new IllegalArgumentException("A player must have a first name or a last name");
    }
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public PlayerInfo(String firstName, String lastName, String notes) {
    this(firstName, lastName);
    this.notes = notes;
  }

  public String firstName() {
    return firstName;
  }

  public String lastName() {
    return lastName;
  }

  @Override
  public String toString() {
    return firstName + " " + lastName;
  }
}
