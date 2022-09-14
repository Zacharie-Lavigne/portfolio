package ca.ulaval.glo4002.game.dinosaurs.useCases;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4002.game.actions.domain.ActionFactory;
import ca.ulaval.glo4002.game.actions.domain.AddDinosaurAction;
import ca.ulaval.glo4002.game.actions.domain.ChangeDinosaurWeightAction;
import ca.ulaval.glo4002.game.dinosaurs.domain.DeltaWeight;
import ca.ulaval.glo4002.game.dinosaurs.domain.Dinosaur;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurBuilder;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurHerd;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurHerdRepository;
import ca.ulaval.glo4002.game.dinosaurs.domain.Weight;
import ca.ulaval.glo4002.game.dinosaurs.useCases.exceptions.InvalidDinosaurNameException;
import ca.ulaval.glo4002.game.flow.domain.Game;
import ca.ulaval.glo4002.game.flow.domain.GameRepository;
import io.vavr.collection.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DinosaurUseCaseTest {

  private final ActionFactory actionFactory = mock(ActionFactory.class);
  private final GameRepository gameRepository = mock(GameRepository.class);
  private final DinosaurHerdRepository dinosaurHerdRepository = mock(DinosaurHerdRepository.class);

  private final DinosaurUseCase dinosaurUseCase =
      new DinosaurUseCase(actionFactory, dinosaurHerdRepository, gameRepository);

  private final String A_DINOSAUR_NAME = "FrankyBoii";
  private static final DeltaWeight A_DELTA_WEIGHT = new DeltaWeight(20);
  private final Dinosaur A_DINOSAUR =
      spy(
          new DinosaurBuilder()
              .withName(A_DINOSAUR_NAME)
              .withWeight(Weight.fromKilograms(100))
              .build());
  private final DinosaurHerd AN_EMPTY_DINOSAUR_HERD = new DinosaurHerd(List.empty());
  private final DinosaurHerd A_DINOSAUR_HERD = spy(new DinosaurHerd(List.of(A_DINOSAUR)));
  private final AddDinosaurAction AN_ADD_DINOSAUR_ACTION =
      new AddDinosaurAction(A_DINOSAUR, AN_EMPTY_DINOSAUR_HERD);
  private final Game A_GAME = spy(new Game());

  @BeforeEach
  public void setUp() {
    when(actionFactory.createAddDinosaurAction(any(Dinosaur.class), any(DinosaurHerd.class)))
        .thenReturn(AN_ADD_DINOSAUR_ACTION);

    when(gameRepository.getGame()).thenReturn(A_GAME);
    when(dinosaurHerdRepository.getDinosaurHerd()).thenReturn(A_DINOSAUR_HERD);
  }

  @Test
  public void
      givenAnEmptyDinosaurHerd_whenCreatingAddDinosaurAction_thenShouldCreateAddDinosaurAction() {
    givenAnEmptyDinosaurHerd();

    dinosaurUseCase.createAddDinosaurAction(A_DINOSAUR);

    verify(actionFactory).createAddDinosaurAction(A_DINOSAUR, AN_EMPTY_DINOSAUR_HERD);
  }

  @Test
  public void
      givenADinosaurHerdWithDinosaurWithTheSameName_whenCreatingAddDinosaurAction_thenShouldThrowInvalidDinosaurName() {
    assertThrows(
        InvalidDinosaurNameException.class,
        () -> dinosaurUseCase.createAddDinosaurAction(A_DINOSAUR));
  }

  @Test
  public void givenAnEmptyDinosaurHerd_whenCreatingAddDinosaurAction_thenShouldAddActionToGame() {
    givenAnEmptyDinosaurHerd();

    dinosaurUseCase.createAddDinosaurAction(A_DINOSAUR);

    verify(A_GAME).addAction(AN_ADD_DINOSAUR_ACTION);
  }

  @Test
  public void
      givenAnEmptyDinosaurHerd_whenCreatingAddDinosaurAction_thenShouldUpdateGameRepository() {
    givenAnEmptyDinosaurHerd();

    dinosaurUseCase.createAddDinosaurAction(A_DINOSAUR);

    verify(gameRepository).update(A_GAME);
  }

  @Test
  public void givenADinosaurHerd_whenGettingAllDinosaurs_thenShouldGetDinosaursFromHerd() {
    dinosaurUseCase.getAllDinosaurs();

    verify(A_DINOSAUR_HERD).getAliveDinosaurs();
  }

  @Test
  public void givenADinosaurHerd_whenGettingDinosaurByName_thenShouldGetTheDinosaurFromHerd() {
    dinosaurUseCase.getDinosaurByName(A_DINOSAUR_NAME);

    verify(A_DINOSAUR_HERD).getByName(A_DINOSAUR_NAME);
  }

  @Test
  public void
      givenADinosaurNameAndADeltaWeight_whenCreateChangeDinosaurWeightAction_thenShouldGetTheDinosaurFromDinosaurHerd() {
    dinosaurUseCase.createChangeDinosaurWeightAction(A_DINOSAUR_NAME, A_DELTA_WEIGHT);

    verify(A_DINOSAUR_HERD).getByName(A_DINOSAUR_NAME);
  }

  @Test
  public void
      givenADinosaurNameAndADeltaWeight_whenCreateChangeDinosaurWeightAction_thenShouldGetGame() {
    dinosaurUseCase.createChangeDinosaurWeightAction(A_DINOSAUR_NAME, A_DELTA_WEIGHT);

    verify(gameRepository).getGame();
  }

  @Test
  public void
      givenADinosaurNameAndADeltaWeight_whenCreateChangeDinosaurWeightAction_thenShouldValidateWeightChange() {
    dinosaurUseCase.createChangeDinosaurWeightAction(A_DINOSAUR_NAME, A_DELTA_WEIGHT);

    verify(A_DINOSAUR).validateWeightChange(A_DELTA_WEIGHT);
  }

  @Test
  public void
      givenADinosaurNameAndADeltaWeight_whenCreateChangeDinosaurWeightAction_thenShouldAddChangeDinosaurWeightActionToGame() {
    ChangeDinosaurWeightAction aChangeDinosaurWeightAction =
        new ChangeDinosaurWeightAction(A_DINOSAUR, A_DELTA_WEIGHT);
    when(actionFactory.createChangeDinosaurWeightAction(A_DINOSAUR, A_DELTA_WEIGHT))
        .thenReturn(aChangeDinosaurWeightAction);

    dinosaurUseCase.createChangeDinosaurWeightAction(A_DINOSAUR_NAME, A_DELTA_WEIGHT);

    verify(A_GAME).addAction(aChangeDinosaurWeightAction);
  }

  @Test
  public void
      givenADinosaurNameAndADeltaWeight_whenCreateChangeDinosaurWeightAction_thenShouldUpdateGameRepository() {
    dinosaurUseCase.createChangeDinosaurWeightAction(A_DINOSAUR_NAME, A_DELTA_WEIGHT);

    verify(gameRepository).update(A_GAME);
  }

  @Test
  public void
      givenADinosaurNameAndADeltaWeight_whenCreateChangeDinosaurWeightAction_thenShouldCreateChangeDinosaurWeightAction() {
    dinosaurUseCase.createChangeDinosaurWeightAction(A_DINOSAUR_NAME, A_DELTA_WEIGHT);

    verify(actionFactory).createChangeDinosaurWeightAction(A_DINOSAUR, A_DELTA_WEIGHT);
  }

  private void givenAnEmptyDinosaurHerd() {
    when(dinosaurHerdRepository.getDinosaurHerd()).thenReturn(AN_EMPTY_DINOSAUR_HERD);
  }
}
