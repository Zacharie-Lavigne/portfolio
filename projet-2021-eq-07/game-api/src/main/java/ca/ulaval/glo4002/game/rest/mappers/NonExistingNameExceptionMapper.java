package ca.ulaval.glo4002.game.rest.mappers;

import ca.ulaval.glo4002.game.dinosaurs.useCases.exceptions.NonExistingNameException;
import ca.ulaval.glo4002.game.rest.factories.NonExistingNameExceptionResponseFactory;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import org.springframework.stereotype.Component;

@Provider
@Component
public class NonExistingNameExceptionMapper implements ExceptionMapper<NonExistingNameException> {
  private final NonExistingNameExceptionResponseFactory nonExistingNameExceptionResponseFactory;

  public NonExistingNameExceptionMapper(
      NonExistingNameExceptionResponseFactory exceptionResponseFactory) {
    this.nonExistingNameExceptionResponseFactory = exceptionResponseFactory;
  }

  @Override
  public Response toResponse(NonExistingNameException exception) {
    return Response.status(404)
        .entity(nonExistingNameExceptionResponseFactory.fromNonExistingNameException(exception))
        .build();
  }
}
