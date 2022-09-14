package ca.ulaval.glo4002.game.flow.useCases;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4002.game.combat.domain.SumoCombatOrganizer;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurHerd;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurHerdRepository;
import ca.ulaval.glo4002.game.flow.domain.Game;
import ca.ulaval.glo4002.game.flow.domain.GameRepository;
import ca.ulaval.glo4002.game.resources.domain.PantryRepository;
import ca.ulaval.glo4002.game.resources.domain.food.FreshFoodResourcesFixture;
import ca.ulaval.glo4002.game.resources.domain.food.Pantry;
import io.vavr.collection.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ResetUseCaseTest {
  private final GameRepository gameRepository = mock(GameRepository.class);
  private final DinosaurHerdRepository dinosaurHerdRepository = mock(DinosaurHerdRepository.class);
  private final PantryRepository pantryRepository = mock(PantryRepository.class);
  private final SumoCombatOrganizer sumoCombatOrganizer = mock(SumoCombatOrganizer.class);

  private final ResetUseCase useCase =
      new ResetUseCase(
          gameRepository, dinosaurHerdRepository, pantryRepository, sumoCombatOrganizer);
  private final Game A_GAME = spy(new Game());
  private final DinosaurHerd A_DINOSAUR_HERD = spy(new DinosaurHerd(List.empty()));
  private final Pantry A_PANTRY = spy(new Pantry(FreshFoodResourcesFixture.createDefault()));

  @BeforeEach
  public void setUp() {
    when(gameRepository.getGame()).thenReturn(A_GAME);
    when(dinosaurHerdRepository.getDinosaurHerd()).thenReturn(A_DINOSAUR_HERD);
    when(pantryRepository.getPantry()).thenReturn(A_PANTRY);
  }

  @Test
  public void givenAGame_whenReset_thenShouldResetGame() {
    useCase.reset();

    verify(A_GAME).resetGame();
  }

  @Test
  public void givenAPantry_whenReset_thenShouldDeleteAllFoodResources() {
    useCase.reset();

    verify(A_PANTRY).deleteAll();
  }

  @Test
  public void givenADinosaurHerd_whenReset_thenShouldDeleteAllDinosaurs() {
    useCase.reset();

    verify(A_DINOSAUR_HERD).deleteAll();
  }

  @Test
  public void givenAGame_whenReset_thenShouldUpdateGameRepository() {
    useCase.reset();

    verify(gameRepository).update(A_GAME);
  }

  @Test
  public void givenAPantry_whenReset_thenShouldUpdatePantryRepository() {
    useCase.reset();

    verify(pantryRepository).update(A_PANTRY);
  }

  @Test
  public void givenADinosaurHerd_whenReset_thenShouldUpdateDinosaurHerdRepository() {
    useCase.reset();

    verify(dinosaurHerdRepository).update(A_DINOSAUR_HERD);
  }

  @Test
  public void whenReset_thenShouldClearSumoCombatOrganizerCombatRegistry() {
    useCase.reset();

    verify(sumoCombatOrganizer).clearCombatRegistry();
  }
}
