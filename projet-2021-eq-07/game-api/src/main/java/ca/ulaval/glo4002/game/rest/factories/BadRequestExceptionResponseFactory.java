package ca.ulaval.glo4002.game.rest.factories;

import ca.ulaval.glo4002.game.rest.communication.BadRequestExceptionCommunicator;
import ca.ulaval.glo4002.game.rest.exceptions.BadRequestException;
import ca.ulaval.glo4002.game.rest.exceptions.ExceptionResponse;
import org.springframework.stereotype.Component;

@Component
public class BadRequestExceptionResponseFactory extends ExceptionResponseFactory {
  private final BadRequestExceptionCommunicator badRequestExceptionCommunicator;

  public BadRequestExceptionResponseFactory(
      BadRequestExceptionCommunicator badRequestExceptionCommunicator) {
    this.badRequestExceptionCommunicator = badRequestExceptionCommunicator;
  }

  public ExceptionResponse fromBadRequestException(BadRequestException badRequestException) {
    return fromRuntimeExceptionAndExceptionCommunicator(
        badRequestException, badRequestExceptionCommunicator);
  }
}
