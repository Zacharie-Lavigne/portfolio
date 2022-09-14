package ca.ulaval.glo4002.game.dinosaurs.domain.exceptions;

public class InvalidSpeciesException extends InvalidDinosaurAttributeException {

  public InvalidSpeciesException() {
    super(InvalidSpeciesException.class.getSimpleName());
  }
}
