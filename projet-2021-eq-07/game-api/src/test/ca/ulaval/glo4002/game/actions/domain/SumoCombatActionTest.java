package ca.ulaval.glo4002.game.actions.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4002.game.combat.domain.SumoCombatResult;
import ca.ulaval.glo4002.game.dinosaurs.domain.Dinosaur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SumoCombatActionTest {

  private final Dinosaur STRONG_DINOSAUR = mock(Dinosaur.class);
  private final Dinosaur WEAK_DINOSAUR = mock(Dinosaur.class);
  private final Dinosaur CHALLENGER = mock(Dinosaur.class);
  private final Dinosaur CHALLENGEE = mock(Dinosaur.class);

  @BeforeEach
  public void setUp() {
    int highStrength = 1000;
    int lowStrength = 10;
    int aStrength = 1000;

    when(STRONG_DINOSAUR.getStrength()).thenReturn(highStrength);
    when(WEAK_DINOSAUR.getStrength()).thenReturn(lowStrength);
    when(CHALLENGER.getStrength()).thenReturn(aStrength);
    when(CHALLENGEE.getStrength()).thenReturn(aStrength);
  }

  @Test
  public void
      givenAStrongerDinosaur_whenGettingPrediction_thenShouldReturnCombatResultWithStrongestDinosaur() {
    SumoCombatAction sumoCombatAction = new SumoCombatAction(STRONG_DINOSAUR, WEAK_DINOSAUR);

    SumoCombatResult expectedSumoCombatResult = new SumoCombatResult(STRONG_DINOSAUR);
    SumoCombatResult actualSumoCombatResult = sumoCombatAction.getPrediction();

    assertEquals(expectedSumoCombatResult, actualSumoCombatResult);
  }

  @Test
  public void givenAStrongerDinosaur_whenExecuting_thenShouldStarveTheStrongest() {
    SumoCombatAction sumoCombatAction = new SumoCombatAction(STRONG_DINOSAUR, WEAK_DINOSAUR);

    sumoCombatAction.execute();

    verify(STRONG_DINOSAUR).starve();
  }

  @Test
  public void givenTwoDinosaurWithSameStrength_whenExecuting_thenShouldStarveBothDinosaurs() {
    SumoCombatAction sumoCombatAction = new SumoCombatAction(CHALLENGER, CHALLENGEE);

    sumoCombatAction.execute();

    verify(CHALLENGER).starve();
    verify(CHALLENGEE).starve();
  }

  @Test
  public void givenTwoDinosaurWithSameStrength_whenExecuting_thenShouldNotKillBoth() {
    SumoCombatAction sumoCombatAction = new SumoCombatAction(CHALLENGER, CHALLENGEE);

    sumoCombatAction.execute();

    verify(CHALLENGER, never()).die();
    verify(CHALLENGEE, never()).die();
  }

  @Test
  public void givenAStrongerAndWeakerDinosaurs_whenExecuting_thenShouldKillTheWeakerDinosaur() {
    SumoCombatAction sumoCombatAction = new SumoCombatAction(STRONG_DINOSAUR, WEAK_DINOSAUR);

    sumoCombatAction.execute();

    verify(WEAK_DINOSAUR).die();
  }

  @Test
  public void
      givenAStrongerAndWeakerDinosaurs_whenExecuting_thenShouldNotKillTheStrongerDinosaur() {
    SumoCombatAction sumoCombatAction = new SumoCombatAction(STRONG_DINOSAUR, WEAK_DINOSAUR);

    sumoCombatAction.execute();

    verify(STRONG_DINOSAUR, never()).die();
  }
}
