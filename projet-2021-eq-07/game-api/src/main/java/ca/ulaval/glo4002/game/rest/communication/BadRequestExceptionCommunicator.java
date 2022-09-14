package ca.ulaval.glo4002.game.rest.communication;

import ca.ulaval.glo4002.game.dinosaurs.rest.assemblers.InvalidWeightException;
import ca.ulaval.glo4002.game.resources.rest.exceptions.InvalidResourceQuantityException;
import io.vavr.collection.HashMap;
import org.springframework.stereotype.Component;

@Component
public class BadRequestExceptionCommunicator extends ExceptionCommunicator {
  public BadRequestExceptionCommunicator() {
    this.titles =
        HashMap.of(
            InvalidWeightException.class.getSimpleName(), "INVALID_WEIGHT",
            InvalidResourceQuantityException.class.getSimpleName(), "INVALID_RESOURCE_QUANTITY");
    this.descriptions =
        HashMap.of(
            InvalidWeightException.class.getSimpleName(),
            "The specified weight must be equal or greater than 100 kg.",
            InvalidResourceQuantityException.class.getSimpleName(),
            "Resource quantities must be positive.");
  }
}
