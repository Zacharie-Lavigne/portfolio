package ca.ulaval.glo4002.game.breeding.useCases.exceptions;

public class InvalidMotherException extends BreedingException {

  public InvalidMotherException() {
    super(InvalidMotherException.class.getSimpleName());
  }
}
