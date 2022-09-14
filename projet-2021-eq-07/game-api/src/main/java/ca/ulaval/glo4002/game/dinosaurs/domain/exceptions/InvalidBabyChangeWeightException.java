package ca.ulaval.glo4002.game.dinosaurs.domain.exceptions;

public class InvalidBabyChangeWeightException extends InvalidDinosaurWeightException {

  public InvalidBabyChangeWeightException() {
    super(InvalidDinosaurWeightChangeException.class.getSimpleName());
  }
}
