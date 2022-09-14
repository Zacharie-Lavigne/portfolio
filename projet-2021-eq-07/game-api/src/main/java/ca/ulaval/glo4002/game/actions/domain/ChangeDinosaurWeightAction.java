package ca.ulaval.glo4002.game.actions.domain;

import ca.ulaval.glo4002.game.dinosaurs.domain.DeltaWeight;
import ca.ulaval.glo4002.game.dinosaurs.domain.Dinosaur;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class ChangeDinosaurWeightAction implements Action {
  private final Dinosaur dinosaur;
  private final DeltaWeight deltaWeight;

  public ChangeDinosaurWeightAction(Dinosaur dinosaur, DeltaWeight deltaWeight) {
    this.dinosaur = dinosaur;
    this.deltaWeight = deltaWeight;
  }

  @Override
  public void execute() {
    this.dinosaur.changeWeight(this.deltaWeight);
  }
}
