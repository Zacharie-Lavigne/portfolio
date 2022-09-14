package ca.ulaval.glo4002.game.rest.mappers;

import ca.ulaval.glo4002.game.dinosaurs.domain.exceptions.InvalidDinosaurAttributeException;
import ca.ulaval.glo4002.game.rest.factories.InvalidDinosaurAttributeExceptionResponseFactory;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import org.springframework.stereotype.Component;

@Provider
@Component
public class InvalidDinosaurAttributeExceptionMapper
    implements ExceptionMapper<InvalidDinosaurAttributeException> {
  private final InvalidDinosaurAttributeExceptionResponseFactory
      invalidDinosaurAttributeExceptionResponseFactory;

  public InvalidDinosaurAttributeExceptionMapper(
      InvalidDinosaurAttributeExceptionResponseFactory exceptionResponseFactory) {
    this.invalidDinosaurAttributeExceptionResponseFactory = exceptionResponseFactory;
  }

  @Override
  public Response toResponse(InvalidDinosaurAttributeException exception) {
    return Response.status(400)
        .entity(
            invalidDinosaurAttributeExceptionResponseFactory.fromInvalidDinosaurAttributeException(
                exception))
        .build();
  }
}
