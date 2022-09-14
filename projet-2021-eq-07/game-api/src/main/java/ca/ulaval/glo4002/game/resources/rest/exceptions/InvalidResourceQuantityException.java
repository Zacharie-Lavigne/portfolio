package ca.ulaval.glo4002.game.resources.rest.exceptions;

import ca.ulaval.glo4002.game.rest.exceptions.BadRequestException;

public class InvalidResourceQuantityException extends BadRequestException {

  public InvalidResourceQuantityException() {
    super(InvalidResourceQuantityException.class.getSimpleName());
  }
}
