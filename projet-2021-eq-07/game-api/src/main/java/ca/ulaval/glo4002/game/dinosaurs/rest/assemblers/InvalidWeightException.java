package ca.ulaval.glo4002.game.dinosaurs.rest.assemblers;

import ca.ulaval.glo4002.game.rest.exceptions.BadRequestException;

public class InvalidWeightException extends BadRequestException {

  public InvalidWeightException() {
    super(InvalidWeightException.class.getSimpleName());
  }
}
