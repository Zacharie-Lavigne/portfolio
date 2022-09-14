package ca.ulaval.glo4002.game.actions.domain;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import ca.ulaval.glo4002.game.dinosaurs.domain.DeltaWeight;
import ca.ulaval.glo4002.game.dinosaurs.domain.Dinosaur;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurBuilder;
import org.junit.jupiter.api.Test;

class ChangeDinosaurWeightActionTest {

  @Test
  public void givenADinosaurAndADeltaWeight_whenExecuting_thenShouldChangeDinosaurWeight() {
    Dinosaur aDinosaur = spy(new DinosaurBuilder().build());
    int aWeight = 150;
    DeltaWeight aDeltaWeight = new DeltaWeight(aWeight);

    ChangeDinosaurWeightAction changeDinosaurWeightAction =
        new ChangeDinosaurWeightAction(aDinosaur, aDeltaWeight);

    changeDinosaurWeightAction.execute();

    verify(aDinosaur).changeWeight(aDeltaWeight);
  }
}
