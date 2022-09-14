package ca.ulaval.glo4002.game.dinosaurs.domain.exceptions;

public class InvalidAdultWeightException extends InvalidDinosaurAttributeException {

  public InvalidAdultWeightException() {
    super(InvalidAdultWeightException.class.getSimpleName());
  }
}
