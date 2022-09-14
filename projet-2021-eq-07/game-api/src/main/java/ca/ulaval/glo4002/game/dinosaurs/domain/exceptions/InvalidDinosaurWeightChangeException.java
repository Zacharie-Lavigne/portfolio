package ca.ulaval.glo4002.game.dinosaurs.domain.exceptions;

public class InvalidDinosaurWeightChangeException extends InvalidDinosaurWeightException {

  public InvalidDinosaurWeightChangeException() {
    super(InvalidDinosaurWeightChangeException.class.getSimpleName());
  }
}
