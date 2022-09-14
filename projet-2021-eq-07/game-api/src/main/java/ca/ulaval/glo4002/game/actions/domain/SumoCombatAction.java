package ca.ulaval.glo4002.game.actions.domain;

import ca.ulaval.glo4002.game.combat.domain.SumoCombatResult;
import ca.ulaval.glo4002.game.dinosaurs.domain.Dinosaur;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class SumoCombatAction implements Action {
  private final Dinosaur challenger;
  private final Dinosaur challengee;

  public SumoCombatAction(Dinosaur challenger, Dinosaur challengee) {
    this.challenger = challenger;
    this.challengee = challengee;
  }

  @Override
  public void execute() {
    SumoCombatResult sumoCombatResult = this.getPrediction();

    if (sumoCombatResult.isTie()) {
      this.challenger.starve();
      this.challengee.starve();
    } else {
      Dinosaur winner = sumoCombatResult.getWinner();
      this.applyWinConsequences(winner);
    }
  }

  public SumoCombatResult getPrediction() {
    if (this.challenger.getStrength() == this.challengee.getStrength()) {
      return new SumoCombatResult(this.challenger, this.challengee);
    }
    if (this.challengee.getStrength() < this.challenger.getStrength()) {
      return new SumoCombatResult(this.challenger);
    }

    return new SumoCombatResult(this.challengee);
  }

  private void applyWinConsequences(Dinosaur winner) {
    winner.starve();
    if (this.challenger == winner) {
      this.challengee.die();
    } else if (this.challengee == winner) {
      this.challenger.die();
    }
  }
}
