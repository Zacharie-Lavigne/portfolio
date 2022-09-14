package ca.ulaval.glo4002.game.breeding.useCases.exceptions;

public class InvalidFatherException extends BreedingException {

  public InvalidFatherException() {
    super(InvalidFatherException.class.getSimpleName());
  }
}
