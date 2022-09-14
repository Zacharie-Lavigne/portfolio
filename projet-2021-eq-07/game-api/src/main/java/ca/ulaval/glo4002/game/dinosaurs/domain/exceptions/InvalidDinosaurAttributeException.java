package ca.ulaval.glo4002.game.dinosaurs.domain.exceptions;

import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurException;

public class InvalidDinosaurAttributeException extends DinosaurException {

  public InvalidDinosaurAttributeException(String message) {
    super(message);
  }
}
