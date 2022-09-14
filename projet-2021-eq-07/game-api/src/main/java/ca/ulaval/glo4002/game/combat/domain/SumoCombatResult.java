package ca.ulaval.glo4002.game.combat.domain;

import ca.ulaval.glo4002.game.dinosaurs.domain.Dinosaur;
import io.vavr.Tuple2;
import io.vavr.control.Either;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class SumoCombatResult {

  private final Either<Dinosaur, Tuple2<Dinosaur, Dinosaur>> winner;

  public SumoCombatResult(Dinosaur winner) {
    this.winner = Either.left(winner);
  }

  public SumoCombatResult(Dinosaur challenger, Dinosaur challengee) {
    this.winner = Either.right(new Tuple2<>(challenger, challengee));
  }

  public Dinosaur getWinner() {
    return this.winner.getLeft();
  }

  public boolean isTie() {
    return this.winner.isRight();
  }
}
