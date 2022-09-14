package ca.ulaval.glo4002.game.rest.factories;

import ca.ulaval.glo4002.game.rest.communication.ExceptionCommunicator;
import ca.ulaval.glo4002.game.rest.exceptions.ExceptionResponse;

public abstract class ExceptionResponseFactory {

  protected ExceptionResponse fromRuntimeExceptionAndExceptionCommunicator(
      RuntimeException runtimeException, ExceptionCommunicator exceptionCommunicator) {
    String exceptionName = runtimeException.getMessage();
    String error = exceptionCommunicator.getErrorTitleFromExceptionName(exceptionName);
    String description = exceptionCommunicator.getErrorDescriptionFromExceptionName(exceptionName);
    return new ExceptionResponse(error, description);
  }
}
