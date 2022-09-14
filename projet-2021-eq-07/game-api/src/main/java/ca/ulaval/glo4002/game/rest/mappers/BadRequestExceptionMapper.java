package ca.ulaval.glo4002.game.rest.mappers;

import ca.ulaval.glo4002.game.rest.exceptions.BadRequestException;
import ca.ulaval.glo4002.game.rest.factories.BadRequestExceptionResponseFactory;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import org.springframework.stereotype.Component;

@Provider
@Component
public class BadRequestExceptionMapper implements ExceptionMapper<BadRequestException> {
  private final BadRequestExceptionResponseFactory badRequestExceptionResponseFactory;

  public BadRequestExceptionMapper(BadRequestExceptionResponseFactory exceptionResponseFactory) {
    this.badRequestExceptionResponseFactory = exceptionResponseFactory;
  }

  @Override
  public Response toResponse(BadRequestException exception) {
    return Response.status(400)
        .entity(badRequestExceptionResponseFactory.fromBadRequestException(exception))
        .build();
  }
}
