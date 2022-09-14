package ca.ulaval.glo4002.game.combat.domain.exceptions;

public class DinosaurAlreadyParticipatingException extends SumoCombatException {

  public DinosaurAlreadyParticipatingException() {
    super(DinosaurAlreadyParticipatingException.class.getSimpleName());
  }
}
