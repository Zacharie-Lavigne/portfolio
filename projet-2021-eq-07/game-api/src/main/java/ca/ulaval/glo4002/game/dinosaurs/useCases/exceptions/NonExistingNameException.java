package ca.ulaval.glo4002.game.dinosaurs.useCases.exceptions;

import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurException;

public class NonExistingNameException extends DinosaurException {

  public NonExistingNameException() {
    super(NonExistingNameException.class.getSimpleName());
  }
}
