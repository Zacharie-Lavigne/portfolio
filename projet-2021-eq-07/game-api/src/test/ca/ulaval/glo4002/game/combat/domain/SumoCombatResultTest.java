package ca.ulaval.glo4002.game.combat.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ca.ulaval.glo4002.game.dinosaurs.domain.Dinosaur;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurBuilder;
import org.junit.jupiter.api.Test;

class SumoCombatResultTest {
  private final DinosaurBuilder dinosaurBuilder = new DinosaurBuilder();
  private final Dinosaur A_DINOSAUR = dinosaurBuilder.build();

  @Test
  public void givenTwoDinosaurs_whenIsTie_thenShouldReturnTrue() {
    Dinosaur anotherDinosaur = dinosaurBuilder.build();
    SumoCombatResult sumoCombatResult = new SumoCombatResult(A_DINOSAUR, anotherDinosaur);

    assertTrue(sumoCombatResult.isTie());
  }

  @Test
  public void givenOneDinosaur_whenIsTie_thenShouldReturnFalse() {
    SumoCombatResult sumoCombatResult = new SumoCombatResult(A_DINOSAUR);

    assertFalse(sumoCombatResult.isTie());
  }

  @Test
  public void givenTwoDinosaursWithDifferentStrength_whenGettingWinner_thenShouldReturnTheWinner() {
    SumoCombatResult sumoCombatResult = new SumoCombatResult(A_DINOSAUR);

    Dinosaur winner = sumoCombatResult.getWinner();

    assertEquals(A_DINOSAUR, winner);
  }
}
