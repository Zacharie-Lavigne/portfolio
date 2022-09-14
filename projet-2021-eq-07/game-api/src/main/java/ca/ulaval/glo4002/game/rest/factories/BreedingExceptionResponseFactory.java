package ca.ulaval.glo4002.game.rest.factories;

import ca.ulaval.glo4002.game.breeding.useCases.exceptions.BreedingException;
import ca.ulaval.glo4002.game.rest.communication.BreedingExceptionCommunicator;
import ca.ulaval.glo4002.game.rest.exceptions.ExceptionResponse;
import org.springframework.stereotype.Component;

@Component
public class BreedingExceptionResponseFactory extends ExceptionResponseFactory {
  private final BreedingExceptionCommunicator breedingExceptionCommunicator;

  public BreedingExceptionResponseFactory(
      BreedingExceptionCommunicator breedingExceptionCommunicator) {
    this.breedingExceptionCommunicator = breedingExceptionCommunicator;
  }

  public ExceptionResponse fromBreedingException(BreedingException breedingException) {
    return fromRuntimeExceptionAndExceptionCommunicator(
        breedingException, breedingExceptionCommunicator);
  }
}
