package ca.ulaval.glo4002.game.rest.communication;

import ca.ulaval.glo4002.game.breeding.useCases.exceptions.InvalidFatherException;
import ca.ulaval.glo4002.game.breeding.useCases.exceptions.InvalidMotherException;
import io.vavr.collection.HashMap;
import org.springframework.stereotype.Component;

@Component
public class BreedingExceptionCommunicator extends ExceptionCommunicator {
  public BreedingExceptionCommunicator() {
    this.titles =
        HashMap.of(
            InvalidFatherException.class.getSimpleName(), "INVALID_FATHER",
            InvalidMotherException.class.getSimpleName(), "INVALID_MOTHER");
    this.descriptions =
        HashMap.of(
            InvalidFatherException.class.getSimpleName(), "Father must be a male.",
            InvalidMotherException.class.getSimpleName(), "Mother must be a female.");
  }
}
