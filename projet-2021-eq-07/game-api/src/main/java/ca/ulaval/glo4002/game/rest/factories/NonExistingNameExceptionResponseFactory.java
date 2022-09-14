package ca.ulaval.glo4002.game.rest.factories;

import ca.ulaval.glo4002.game.dinosaurs.useCases.exceptions.NonExistingNameException;
import ca.ulaval.glo4002.game.rest.communication.NonExistingNameExceptionCommunicator;
import ca.ulaval.glo4002.game.rest.exceptions.ExceptionResponse;
import org.springframework.stereotype.Component;

@Component
public class NonExistingNameExceptionResponseFactory extends ExceptionResponseFactory {
  private final NonExistingNameExceptionCommunicator nonExistingNameExceptionCommunicator;

  public NonExistingNameExceptionResponseFactory(
      NonExistingNameExceptionCommunicator nonExistingNameExceptionCommunicator) {
    this.nonExistingNameExceptionCommunicator = nonExistingNameExceptionCommunicator;
  }

  public ExceptionResponse fromNonExistingNameException(
      NonExistingNameException nonExistingNameException) {
    return fromRuntimeExceptionAndExceptionCommunicator(
        nonExistingNameException, nonExistingNameExceptionCommunicator);
  }
}
