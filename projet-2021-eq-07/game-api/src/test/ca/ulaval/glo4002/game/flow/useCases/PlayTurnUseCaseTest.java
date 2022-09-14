package ca.ulaval.glo4002.game.flow.useCases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4002.game.combat.domain.SumoCombatOrganizer;
import ca.ulaval.glo4002.game.dinosaurs.domain.Dinosaur;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurBuilder;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurHerd;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurHerdRepository;
import ca.ulaval.glo4002.game.flow.domain.Game;
import ca.ulaval.glo4002.game.flow.domain.GameRepository;
import ca.ulaval.glo4002.game.resources.domain.PantryRepository;
import ca.ulaval.glo4002.game.resources.domain.food.FreshFoodResources;
import ca.ulaval.glo4002.game.resources.domain.food.FreshFoodResourcesFixture;
import ca.ulaval.glo4002.game.resources.domain.food.Pantry;
import io.vavr.collection.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlayTurnUseCaseTest {
  private final GameRepository gameRepository = mock(GameRepository.class);
  private final DinosaurHerdRepository dinosaurHerdRepository = mock(DinosaurHerdRepository.class);
  private final PantryRepository pantryRepository = mock(PantryRepository.class);
  private final PlayTurnDinosaurUseCase playTurnDinosaurUseCase =
      mock(PlayTurnDinosaurUseCase.class);
  private final PlayTurnResourcesUseCase playTurnResourcesUseCase =
      mock(PlayTurnResourcesUseCase.class);
  private final PlayTurnActionUseCase playTurnActionUseCase = mock(PlayTurnActionUseCase.class);
  private final SumoCombatOrganizer sumoCombatOrganizer = mock(SumoCombatOrganizer.class);

  private final PlayTurnUseCase useCase =
      new PlayTurnUseCase(
          dinosaurHerdRepository,
          gameRepository,
          pantryRepository,
          playTurnDinosaurUseCase,
          playTurnResourcesUseCase,
          playTurnActionUseCase,
          sumoCombatOrganizer);

  private final FreshFoodResources FRESH_FOOD_RESOURCES = FreshFoodResourcesFixture.createDefault();
  private final Pantry A_PANTRY = new Pantry(FRESH_FOOD_RESOURCES);

  private final List<Dinosaur> DINOSAURS = List.of(new DinosaurBuilder().build());
  private final DinosaurHerd DINOSAUR_HERD = new DinosaurHerd(DINOSAURS);
  private final Game A_GAME = new Game();

  @BeforeEach
  public void setUp() {
    when(gameRepository.getGame()).thenReturn(A_GAME);
    when(dinosaurHerdRepository.getDinosaurHerd()).thenReturn(DINOSAUR_HERD);
    when(pantryRepository.getPantry()).thenReturn(A_PANTRY);
  }

  @Test
  public void givenANewGame_whenPlayingTurn_thenShouldReturnTheExecutedTurnNumber() {
    int initialTurnNumber = A_GAME.getTurnNumber();

    int executedTurnNUmber = useCase.playTurn();

    assertEquals(initialTurnNumber, executedTurnNUmber);
  }

  @Test
  public void
      givenAGameAPantryAndDinosaurHerd_whenPlayingTurn_thenShouldExecuteTurnConsequencesForDinosaurs() {
    useCase.playTurn();

    verify(playTurnDinosaurUseCase).playTurn(DINOSAUR_HERD, A_PANTRY);
  }

  @Test
  public void givenAGameAPantry_whenPlayingTurn_thenShouldExecuteTurnConsequencesForResources() {
    int A_TURN_NUMBER = 1;

    useCase.playTurn();

    verify(playTurnResourcesUseCase).playTurn(A_PANTRY, A_TURN_NUMBER);
  }

  @Test
  public void givenAGame_whenPlayingTurn_thenShouldExecuteTurnConsequencesForActions() {
    useCase.playTurn();

    verify(playTurnActionUseCase).playTurn(A_GAME);
  }

  @Test
  public void givenADinosaurHerd_whenPlayingTurn_thenShouldUpdateDinosaurHerdRepository() {
    useCase.playTurn();

    verify(dinosaurHerdRepository).update(DINOSAUR_HERD);
  }

  @Test
  public void givenAPantry_whenPlayingTurn_thenShouldUpdatePantryRepository() {
    useCase.playTurn();

    verify(pantryRepository).update(A_PANTRY);
  }

  @Test
  public void givenAGame_whenPlayingTurn_thenShouldUpdateGameRepository() {
    useCase.playTurn();

    verify(gameRepository).update(A_GAME);
  }

  @Test
  public void whenPlayingTurn_thenShouldClearSumoCombatOrganizerCombatRegistry() {
    useCase.playTurn();

    verify(sumoCombatOrganizer).clearCombatRegistry();
  }
}
