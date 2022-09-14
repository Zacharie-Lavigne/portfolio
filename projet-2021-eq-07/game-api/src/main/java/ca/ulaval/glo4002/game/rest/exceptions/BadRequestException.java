package ca.ulaval.glo4002.game.rest.exceptions;

public abstract class BadRequestException extends RuntimeException {

  public BadRequestException(String message) {
    super(message);
  }
}
