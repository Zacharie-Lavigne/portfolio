package ca.ulaval.glo4002.game.rest.factories;

import ca.ulaval.glo4002.game.dinosaurs.domain.exceptions.InvalidDinosaurWeightException;
import ca.ulaval.glo4002.game.rest.communication.InvalidDinosaurWeightExceptionCommunicator;
import ca.ulaval.glo4002.game.rest.exceptions.ExceptionResponse;
import org.springframework.stereotype.Component;

@Component
public class InvalidDinosaurWeightExceptionResponseFactory extends ExceptionResponseFactory {
  private final InvalidDinosaurWeightExceptionCommunicator
      invalidDinosaurWeightExceptionCommunicator;

  public InvalidDinosaurWeightExceptionResponseFactory(
      InvalidDinosaurWeightExceptionCommunicator invalidDinosaurWeightExceptionCommunicator) {
    this.invalidDinosaurWeightExceptionCommunicator = invalidDinosaurWeightExceptionCommunicator;
  }

  public ExceptionResponse fromInvalidDinosaurWeightException(
      InvalidDinosaurWeightException invalidDinosaurWeightException) {
    return fromRuntimeExceptionAndExceptionCommunicator(
        invalidDinosaurWeightException, invalidDinosaurWeightExceptionCommunicator);
  }
}
