package ca.ulaval.glo4002.game.dinosaurs.domain.exceptions;

import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurException;

public class InvalidDinosaurWeightException extends DinosaurException {

  public InvalidDinosaurWeightException(String name) {
    super(name);
  }
}
