package ca.ulaval.glo4002.game.flow.useCases;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import ca.ulaval.glo4002.game.dinosaurs.domain.BabyDinosaurGrower;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurFamilyHandler;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurFeeder;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurHerd;
import ca.ulaval.glo4002.game.resources.domain.food.FreshFoodResourcesFixture;
import ca.ulaval.glo4002.game.resources.domain.food.Pantry;
import io.vavr.collection.List;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

class PlayTurnDinosaurUseCaseTest {
  private final DinosaurFeeder dinosaurFeeder = mock(DinosaurFeeder.class);
  private final DinosaurFamilyHandler dinosaurFamilyHandler = mock(DinosaurFamilyHandler.class);
  private final BabyDinosaurGrower babyDinosaurGrower = mock(BabyDinosaurGrower.class);

  private final PlayTurnDinosaurUseCase playTurnDinosaurUseCase =
      new PlayTurnDinosaurUseCase(dinosaurFeeder, dinosaurFamilyHandler, babyDinosaurGrower);

  private final DinosaurHerd DINOSAUR_HERD = new DinosaurHerd(List.empty());
  private final Pantry PANTRY = new Pantry(FreshFoodResourcesFixture.createDefault());

  @Test
  public void givenADinosaurHerdAPantryAndTurnNumber_whenPlayingTurn_thenShouldFeedDinosaurs() {
    playTurnDinosaurUseCase.playTurn(DINOSAUR_HERD, PANTRY);

    verify(dinosaurFeeder).feed(DINOSAUR_HERD, PANTRY);
  }

  @Test
  public void givenADinosaurHerd_whenPlayingTurn_thenShouldGetRidOfOrphans() {
    playTurnDinosaurUseCase.playTurn(DINOSAUR_HERD, PANTRY);

    verify(dinosaurFamilyHandler).getRidOfOrphans(DINOSAUR_HERD);
  }

  @Test
  public void givenADinosaurHerd_whenPlayingTurn_thenShouldGrowAliveDinosaurs() {
    playTurnDinosaurUseCase.playTurn(DINOSAUR_HERD, PANTRY);

    verify(babyDinosaurGrower).growDinosaurs(DINOSAUR_HERD);
  }

  @Test
  public void givenADinosaurHerd_whenPlayingTurn_thenShouldExecuteInOrder() {
    DinosaurHerd aDinosaurHerd = mock(DinosaurHerd.class);
    InOrder inOrder =
        inOrder(dinosaurFeeder, aDinosaurHerd, dinosaurFamilyHandler, babyDinosaurGrower);

    playTurnDinosaurUseCase.playTurn(aDinosaurHerd, PANTRY);

    inOrder.verify(dinosaurFeeder).feed(any(DinosaurHerd.class), any(Pantry.class));
    inOrder.verify(aDinosaurHerd).removeDead();
    inOrder.verify(dinosaurFamilyHandler).getRidOfOrphans(any(DinosaurHerd.class));
    inOrder.verify(aDinosaurHerd).removeDead();
    inOrder.verify(babyDinosaurGrower).growDinosaurs(aDinosaurHerd);
  }
}
