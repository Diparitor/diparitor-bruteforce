package com.mebae.diparitor.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerInfoTests {
  @Test
  void constructor_ShouldThrowException_WhenFirstNameAndLastNameAreEmpty() {
    assertThrows(IllegalArgumentException.class, () ->
                   new PlayerInfo(null, null),
                 "A player must have a first name or a last name"
    );

    assertThrows(IllegalArgumentException.class, () ->
                   new PlayerInfo("", ""),
                 "A player must have a first name or a last name"
    );
  }

  @Test
  void constructor_ShouldCreatePlayerInfo_WhenFirstNameIsProvided() {
    var player = new PlayerInfo("John", null);
    assertEquals("John", player.firstName());
    assertNull(player.lastName());
  }

  @Test
  void constructor_ShouldCreatePlayerInfo_WhenLastNameIsProvided() {
    var player = new PlayerInfo(null, "Doe");
    assertNull(player.firstName());
    assertEquals("Doe", player.lastName());
  }

  @Test
  void constructor_ShouldCreatePlayerInfo_WhenBothNamesAreProvided() {
    var player = new PlayerInfo("John", "Doe");
    assertEquals("John", player.firstName());
    assertEquals("Doe", player.lastName());
  }

  @Test
  void constructorWithNotes_ShouldIncludeNotes_WhenProvided() {
    var player = new PlayerInfo("John", "Doe", "Some notes");
    assertEquals("John", player.firstName());
    assertEquals("Doe", player.lastName());
  }
}
