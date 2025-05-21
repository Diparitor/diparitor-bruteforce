package com.mebae.diparitor.entity;

import com.mebae.diparitor.model.Power;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class VariantTests {
  @Test
  void constructor_ShouldCreatevar_WhenValidInput() {
    var powers = Set.of(new Power("Austria-Hungary", 2.78), new Power("France", 6.14));
    var variant = new Variant("Standard", powers, true);
    assertEquals("Standard", variant.name());
    assertEquals("Unknown", variant.source());
    assertEquals(2, variant.powerCount());
    assertTrue(variant.hasCoefficient());
    assertEquals(powers, variant.powers());
  }

  @Test
  void constructorWithSources_ShouldCreatevar_WhenValidInput() {
    var powers = Set.of(new Power("Austria-Hungary", 2.78), new Power("France", 6.14));
    var variant = new Variant("Standard", "2024", powers, true);
    assertEquals("Standard", variant.name());
    assertEquals("2024", variant.source());
    assertEquals(2, variant.powerCount());
    assertTrue(variant.hasCoefficient());
    assertEquals(powers, variant.powers());
  }

  @Test
  void constructor_ShouldThrowException_WhenNameIsNull() {
    var powers = Set.of(new Power("France", 6.14));
    assertThrows(NullPointerException.class, () -> new Variant(null, powers, true));
  }

  @Test
  void constructor_ShouldThrowException_WhenPowersContainNull() {
    var powers = new HashSet<>(Arrays.asList(new Power("France", 6.14), null));
    assertThrows(IllegalArgumentException.class, () -> new Variant("Standard", powers, true));
  }

  @Test
  void constructor_ShouldThrowException_WhenPowersAreEmpty() {
    assertThrows(IllegalArgumentException.class, () -> new Variant("Standard", Set.<Power>of(), true));
  }

  @Test
  void constructor_ShouldThrowException_WhenPowersContainsOnlyOnePower() {
    var powers = Set.of(new Power("France", 6.14));
    assertThrows(IllegalArgumentException.class, () -> new Variant("Standard", "Test Source", powers, true));
  }
}