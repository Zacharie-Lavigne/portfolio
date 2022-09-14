package ca.ulaval.glo4002.game.combat.domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4002.game.actions.domain.ActionFactory;
import ca.ulaval.glo4002.game.actions.domain.SumoCombatAction;
import ca.ulaval.glo4002.game.combat.domain.exceptions.DinosaurAlreadyParticipatingException;
import ca.ulaval.glo4002.game.combat.domain.exceptions.MaximumCombatsReachedException;
import ca.ulaval.glo4002.game.combat.domain.exceptions.UnallowedSumoSpeciesException;
import ca.ulaval.glo4002.game.dinosaurs.domain.Dinosaur;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurBuilder;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurSpecies;
import org.junit.jupiter.api.Test;

class SumoCombatOrganizerTest {
  private final ActionFactory actionFactory = mock(ActionFactory.class);

  private final SumoCombatOrganizer sumoCombatOrganizer = new SumoCombatOrganizer(actionFactory);

  private final DinosaurBuilder dinosaurBuilder = new DinosaurBuilder();
  private final Dinosaur A_FIRST_CHALLENGER = dinosaurBuilder.withName("pauly").build();
  private final Dinosaur A_FIRST_CHALLENGEE = dinosaurBuilder.withName("paula").build();
  private final Dinosaur A_SECOND_CHALLENGER = dinosaurBuilder.withName("cory").build();
  private final Dinosaur A_SECOND_CHALLENGEE = dinosaurBuilder.withName("cora").build();
  private final Dinosaur A_THIRD_CHALLENGER = dinosaurBuilder.withName("bixy").build();
  private final Dinosaur A_THIRD_CHALLENGEE = dinosaurBuilder.withName("bixu").build();
  private final Dinosaur UNALLOWED_FIGHTER =
      dinosaurBuilder.withSpecies(DinosaurSpecies.TYRANNOSAURUS_REX).build();

  @Test
  public void
      givenAChallengerAndAChallengeeThatAreNotParticipatingInOtherCombats_whenCreateSumoCombat_thenShouldCreateSumoCombatAction() {
    sumoCombatOrganizer.createSumoCombat(A_FIRST_CHALLENGER, A_FIRST_CHALLENGEE);

    verify(actionFactory).createSumoCombatAction(A_FIRST_CHALLENGER, A_FIRST_CHALLENGEE);
  }

  @Test
  public void
      givenAChallengerAndAChallengeeThatAreNotParticipatingInOtherCombats_whenCreateSumoCombat_thenShouldReturnSumoCombatAction() {
    SumoCombatAction aSumoCombatAction =
        new SumoCombatAction(A_FIRST_CHALLENGER, A_FIRST_CHALLENGEE);
    when(actionFactory.createSumoCombatAction(A_FIRST_CHALLENGER, A_FIRST_CHALLENGEE))
        .thenReturn(aSumoCombatAction);

    SumoCombatAction actual =
        sumoCombatOrganizer.createSumoCombat(A_FIRST_CHALLENGER, A_FIRST_CHALLENGEE);

    assertEquals(aSumoCombatAction, actual);
  }

  @Test
  public void
      givenAChallengerThatIsParticipatingInAnOtherCombatAndAChallengee_whenCreateSumoCombat_thenShouldThrowDinosaurAlreadyParticipatingException() {
    sumoCombatOrganizer.createSumoCombat(A_FIRST_CHALLENGER, A_FIRST_CHALLENGEE);

    assertThrows(
        DinosaurAlreadyParticipatingException.class,
        () -> sumoCombatOrganizer.createSumoCombat(A_FIRST_CHALLENGER, A_SECOND_CHALLENGEE));
  }

  @Test
  public void
      givenAChallengeeThatIsParticipatingInAnOtherCombatAndAChallenger_whenCreateSumoCombat_thenShouldThrowDinosaurAlreadyParticipatingException() {
    sumoCombatOrganizer.createSumoCombat(A_FIRST_CHALLENGER, A_FIRST_CHALLENGEE);

    assertThrows(
        DinosaurAlreadyParticipatingException.class,
        () -> sumoCombatOrganizer.createSumoCombat(A_SECOND_CHALLENGER, A_FIRST_CHALLENGEE));
  }

  @Test
  public void
      givenAChallengerAndAChallengeeAndAlreadyOneOtherPreparedCombat_whenCreateSumoCombat_thenShouldNotThrowMaximumCombatsReachedException() {
    sumoCombatOrganizer.createSumoCombat(A_FIRST_CHALLENGER, A_FIRST_CHALLENGEE);

    assertDoesNotThrow(
        () -> sumoCombatOrganizer.createSumoCombat(A_SECOND_CHALLENGER, A_SECOND_CHALLENGEE));
  }

  @Test
  public void
      givenAChallengerAndAChallengeeAndAlreadyTwoOtherPreparedCombat_whenCreateSumoCombat_thenShouldThrowMaximumCombatsReachedException() {
    sumoCombatOrganizer.createSumoCombat(A_FIRST_CHALLENGER, A_FIRST_CHALLENGEE);
    sumoCombatOrganizer.createSumoCombat(A_SECOND_CHALLENGER, A_SECOND_CHALLENGEE);

    assertThrows(
        MaximumCombatsReachedException.class,
        () -> sumoCombatOrganizer.createSumoCombat(A_THIRD_CHALLENGER, A_THIRD_CHALLENGEE));
  }

  @Test
  public void
      givenAlreadyTwoOtherPreparedCombat_whenClearingCombatRegistryAndCreatingNewSumoCombat_thenShouldNotThrowMaximumCombatsReachedException() {
    sumoCombatOrganizer.createSumoCombat(A_FIRST_CHALLENGER, A_FIRST_CHALLENGEE);
    sumoCombatOrganizer.createSumoCombat(A_SECOND_CHALLENGER, A_SECOND_CHALLENGEE);

    sumoCombatOrganizer.clearCombatRegistry();

    assertDoesNotThrow(
        () -> sumoCombatOrganizer.createSumoCombat(A_THIRD_CHALLENGER, A_THIRD_CHALLENGEE));
  }

  @Test
  public void
      givenAChallengerAndChallengeeAlreadyParticipatingInAnOtherCombat_whenClearingCombatRegistryAndCreatingNewSumoCombat_thenShouldNotThrowDinosaurAlreadyParticipatingException() {
    sumoCombatOrganizer.createSumoCombat(A_FIRST_CHALLENGER, A_FIRST_CHALLENGEE);

    sumoCombatOrganizer.clearCombatRegistry();

    assertDoesNotThrow(
        () -> sumoCombatOrganizer.createSumoCombat(A_FIRST_CHALLENGER, A_FIRST_CHALLENGEE));
  }

  @Test
  public void
      givenAnUnallowedChallengerSpecie_whenCreateSumoCombat_thenShouldThrowUnallowedSpecieException() {
    assertThrows(
        UnallowedSumoSpeciesException.class,
        () -> sumoCombatOrganizer.createSumoCombat(UNALLOWED_FIGHTER, A_FIRST_CHALLENGEE));
  }

  @Test
  public void
      givenAnUnallowedChallengeeSpecie_whenCreateSumoCombat_thenShouldThrowUnallowedSpecieException() {
    assertThrows(
        UnallowedSumoSpeciesException.class,
        () -> sumoCombatOrganizer.createSumoCombat(A_FIRST_CHALLENGER, UNALLOWED_FIGHTER));
  }
}
