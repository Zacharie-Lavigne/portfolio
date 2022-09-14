package ca.ulaval.glo4002.game.combat.domain.exceptions;

public class MaximumCombatsReachedException extends SumoCombatException {
  public MaximumCombatsReachedException() {
    super(MaximumCombatsReachedException.class.getSimpleName());
  }
}
