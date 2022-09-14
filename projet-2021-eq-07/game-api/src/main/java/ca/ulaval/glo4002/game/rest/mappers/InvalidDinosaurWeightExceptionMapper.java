package ca.ulaval.glo4002.game.rest.mappers;

import ca.ulaval.glo4002.game.dinosaurs.domain.exceptions.InvalidDinosaurWeightException;
import ca.ulaval.glo4002.game.rest.factories.InvalidDinosaurWeightExceptionResponseFactory;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import org.springframework.stereotype.Component;

@Provider
@Component
public class InvalidDinosaurWeightExceptionMapper
    implements ExceptionMapper<InvalidDinosaurWeightException> {
  private final InvalidDinosaurWeightExceptionResponseFactory
      invalidDinosaurWeightExceptionResponseFactory;

  public InvalidDinosaurWeightExceptionMapper(
      InvalidDinosaurWeightExceptionResponseFactory exceptionResponseFactory) {
    this.invalidDinosaurWeightExceptionResponseFactory = exceptionResponseFactory;
  }

  @Override
  public Response toResponse(InvalidDinosaurWeightException exception) {
    return Response.status(400)
        .entity(
            invalidDinosaurWeightExceptionResponseFactory.fromInvalidDinosaurWeightException(
                exception))
        .build();
  }
}
