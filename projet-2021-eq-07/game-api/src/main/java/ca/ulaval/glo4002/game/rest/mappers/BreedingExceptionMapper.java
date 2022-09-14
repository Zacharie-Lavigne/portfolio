package ca.ulaval.glo4002.game.rest.mappers;

import ca.ulaval.glo4002.game.breeding.useCases.exceptions.BreedingException;
import ca.ulaval.glo4002.game.rest.factories.BreedingExceptionResponseFactory;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import org.springframework.stereotype.Component;

@Provider
@Component
public class BreedingExceptionMapper implements ExceptionMapper<BreedingException> {
  private static final int status = 400;
  private final BreedingExceptionResponseFactory breedingExceptionResponseFactory;

  public BreedingExceptionMapper(BreedingExceptionResponseFactory exceptionResponseFactory) {
    this.breedingExceptionResponseFactory = exceptionResponseFactory;
  }

  @Override
  public Response toResponse(BreedingException exception) {
    return Response.status(status)
        .entity(this.breedingExceptionResponseFactory.fromBreedingException(exception))
        .build();
  }
}
