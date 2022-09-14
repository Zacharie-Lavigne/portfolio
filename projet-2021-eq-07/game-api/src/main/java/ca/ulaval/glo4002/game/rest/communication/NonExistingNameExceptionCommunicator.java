package ca.ulaval.glo4002.game.rest.communication;

import ca.ulaval.glo4002.game.dinosaurs.useCases.exceptions.NonExistingNameException;
import io.vavr.collection.HashMap;
import org.springframework.stereotype.Component;

@Component
public class NonExistingNameExceptionCommunicator extends ExceptionCommunicator {
  public NonExistingNameExceptionCommunicator() {
    this.titles = HashMap.of(NonExistingNameException.class.getSimpleName(), "NON_EXISTENT_NAME");
    this.descriptions =
        HashMap.of(
            NonExistingNameException.class.getSimpleName(), "The specified name does not exist.");
  }
}
