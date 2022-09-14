package ca.ulaval.glo4002.game.dinosaurs.domain.exceptions;

public class InvalidGenderException extends InvalidDinosaurAttributeException {

  public InvalidGenderException() {
    super(InvalidGenderException.class.getSimpleName());
  }
}
