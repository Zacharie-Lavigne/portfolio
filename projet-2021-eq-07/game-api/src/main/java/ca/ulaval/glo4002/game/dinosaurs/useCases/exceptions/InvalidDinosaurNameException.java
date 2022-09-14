package ca.ulaval.glo4002.game.dinosaurs.useCases.exceptions;

import ca.ulaval.glo4002.game.dinosaurs.domain.exceptions.InvalidDinosaurAttributeException;

public class InvalidDinosaurNameException extends InvalidDinosaurAttributeException {

  public InvalidDinosaurNameException() {
    super(InvalidDinosaurNameException.class.getSimpleName());
  }
}
