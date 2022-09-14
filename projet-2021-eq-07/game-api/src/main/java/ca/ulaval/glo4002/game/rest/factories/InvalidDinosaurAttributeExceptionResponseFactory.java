package ca.ulaval.glo4002.game.rest.factories;

import ca.ulaval.glo4002.game.dinosaurs.domain.exceptions.InvalidDinosaurAttributeException;
import ca.ulaval.glo4002.game.rest.communication.InvalidDinosaurAttributeExceptionCommunicator;
import ca.ulaval.glo4002.game.rest.exceptions.ExceptionResponse;
import org.springframework.stereotype.Component;

@Component
public class InvalidDinosaurAttributeExceptionResponseFactory extends ExceptionResponseFactory {

  private final InvalidDinosaurAttributeExceptionCommunicator
      invalidDinosaurAttributeExceptionCommunicator;

  public InvalidDinosaurAttributeExceptionResponseFactory(
      InvalidDinosaurAttributeExceptionCommunicator invalidDinosaurAttributeExceptionCommunicator) {
    this.invalidDinosaurAttributeExceptionCommunicator =
        invalidDinosaurAttributeExceptionCommunicator;
  }

  public ExceptionResponse fromInvalidDinosaurAttributeException(
      InvalidDinosaurAttributeException invalidDinosaurAttributeException) {
    return fromRuntimeExceptionAndExceptionCommunicator(
        invalidDinosaurAttributeException, invalidDinosaurAttributeExceptionCommunicator);
  }
}
