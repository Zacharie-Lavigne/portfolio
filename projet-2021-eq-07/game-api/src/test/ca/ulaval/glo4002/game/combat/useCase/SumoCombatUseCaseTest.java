package ca.ulaval.glo4002.game.combat.useCase;

import ca.ulaval.glo4002.game.actions.domain.SumoCombatAction;
import ca.ulaval.glo4002.game.combat.domain.SumoCombatAttempt;
import ca.ulaval.glo4002.game.combat.domain.SumoCombatOrganizer;
import ca.ulaval.glo4002.game.dinosaurs.domain.Dinosaur;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurBuilder;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurHerd;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurHerdRepository;
import ca.ulaval.glo4002.game.flow.domain.Game;
import ca.ulaval.glo4002.game.flow.domain.GameRepository;
import io.vavr.collection.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SumoCombatUseCaseTest {

  private final GameRepository gameRepository = mock(GameRepository.class);
  private final DinosaurHerdRepository dinosaurHerdRepository = mock(DinosaurHerdRepository.class);
  private final SumoCombatOrganizer sumoCombatOrganizer = mock(SumoCombatOrganizer.class);

  private final SumoCombatUseCase sumoCombatUseCase =
      new SumoCombatUseCase(dinosaurHerdRepository, gameRepository, sumoCombatOrganizer);

  private final Game A_GAME = mock(Game.class);
  private final String A_CHALLENGER_NAME = "Roger";
  private final String A_CHALLENGEE_NAME = "Pierre";
  private final DinosaurBuilder dinosaurBuilder = new DinosaurBuilder();
  private final Dinosaur A_CHALLENGER = dinosaurBuilder.withName(A_CHALLENGER_NAME).build();
  private final Dinosaur A_CHALLENGEE = dinosaurBuilder.withName(A_CHALLENGEE_NAME).build();
  private final DinosaurHerd A_DINOSAUR_HERD =
      new DinosaurHerd(List.of(A_CHALLENGER, A_CHALLENGEE));
  private final SumoCombatAttempt A_SUMO_COMBAT_ATTEMPT =
      new SumoCombatAttempt(A_CHALLENGER_NAME, A_CHALLENGEE_NAME);
  private final SumoCombatAction A_SUMO_COMBAT_ACTION =
      new SumoCombatAction(A_CHALLENGER, A_CHALLENGEE);

  @BeforeEach
  public void setUp() {
    when(gameRepository.getGame()).thenReturn(A_GAME);
    when(dinosaurHerdRepository.getDinosaurHerd()).thenReturn(A_DINOSAUR_HERD);
    when(sumoCombatOrganizer.createSumoCombat(A_CHALLENGER, A_CHALLENGEE))
        .thenReturn(A_SUMO_COMBAT_ACTION);
  }

  @Test
  public void
      givenAValidSumoCombatAttempt_whenCreatingSumoCombatAction_thenShouldCreateSumoCombatAction() {
    sumoCombatUseCase.createSumoCombatAction(A_SUMO_COMBAT_ATTEMPT);

    verify(sumoCombatOrganizer).createSumoCombat(A_CHALLENGER, A_CHALLENGEE);
  }

  @Test
  public void
      givenAValidSumoCombatAttempt_whenCreatingSumoCombatAction_thenShouldAddSumoCombatActionToGame() {
    sumoCombatUseCase.createSumoCombatAction(A_SUMO_COMBAT_ATTEMPT);

    verify(A_GAME).addAction(A_SUMO_COMBAT_ACTION);
  }

  @Test
  public void
      givenAValidSumoCombatAttempt_whenCreatingSumoCombatAction_thenShouldReturnSumoCombatAction() {
    SumoCombatAction actualSumoCombatAction =
        sumoCombatUseCase.createSumoCombatAction(A_SUMO_COMBAT_ATTEMPT);

    assertEquals(A_SUMO_COMBAT_ACTION, actualSumoCombatAction);
  }

  @Test
  public void givenAValidSumoCombatAttempt_whenCreatingSumoCombatAction_thenShouldUpdateGame() {
    sumoCombatUseCase.createSumoCombatAction(A_SUMO_COMBAT_ATTEMPT);

    verify(gameRepository).update(A_GAME);
  }
}
