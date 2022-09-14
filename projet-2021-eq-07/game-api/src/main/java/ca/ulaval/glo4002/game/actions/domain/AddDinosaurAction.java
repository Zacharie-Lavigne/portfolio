package ca.ulaval.glo4002.game.actions.domain;

import ca.ulaval.glo4002.game.dinosaurs.domain.Dinosaur;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurHerd;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class AddDinosaurAction implements Action {
  private final Dinosaur pendingDinosaur;
  private final DinosaurHerd dinosaurHerd;

  public AddDinosaurAction(Dinosaur dinosaur, DinosaurHerd dinosaurHerd) {
    this.pendingDinosaur = dinosaur;
    this.dinosaurHerd = dinosaurHerd;
  }

  @Override
  public void execute() {
    if (!this.dinosaurHerd.containsName(this.pendingDinosaur.getName())) {
      this.dinosaurHerd.addDinosaur(pendingDinosaur);
    }
  }
}
