package ca.ulaval.glo4002.game.actions.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.ulaval.glo4002.game.dinosaurs.domain.Dinosaur;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurBuilder;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurHerd;
import io.vavr.collection.List;
import org.junit.jupiter.api.Test;

class AddDinosaurActionTest {

  private final Dinosaur A_DINOSAUR = new DinosaurBuilder().withName("pablo").build();

  @Test
  public void
      givenADinosaurToAddAndAnEmptyDinosaurHerd_whenExecuting_thenShouldAddDinosaurToDinosaurHerd() {
    DinosaurHerd dinosaurHerd = new DinosaurHerd(List.empty());
    AddDinosaurAction addDinosaurAction = new AddDinosaurAction(A_DINOSAUR, dinosaurHerd);

    addDinosaurAction.execute();

    assertEquals(List.of(A_DINOSAUR), dinosaurHerd.getAliveDinosaurs());
  }

  @Test
  public void
      givenADinosaurToAddAndADinosaurHerdWithWithDinosaurWithSameName_whenExecuting_thenShouldNotAddDinosaurToDinosaurHerd() {
    DinosaurHerd dinosaurHerd = new DinosaurHerd(List.of(A_DINOSAUR));
    AddDinosaurAction addDinosaurAction = new AddDinosaurAction(A_DINOSAUR, dinosaurHerd);

    addDinosaurAction.execute();

    assertEquals(List.of(A_DINOSAUR), dinosaurHerd.getAliveDinosaurs());
  }
}
