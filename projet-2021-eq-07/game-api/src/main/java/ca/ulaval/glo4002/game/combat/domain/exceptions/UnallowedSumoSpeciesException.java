package ca.ulaval.glo4002.game.combat.domain.exceptions;

public class UnallowedSumoSpeciesException extends SumoCombatException {
  public UnallowedSumoSpeciesException() {
    super(UnallowedSumoSpeciesException.class.getSimpleName());
  }
}
