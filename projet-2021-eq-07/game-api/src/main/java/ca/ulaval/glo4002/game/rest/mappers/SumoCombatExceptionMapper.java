package ca.ulaval.glo4002.game.rest.mappers;

import ca.ulaval.glo4002.game.combat.domain.exceptions.SumoCombatException;
import ca.ulaval.glo4002.game.rest.factories.SumoCombatExceptionResponseFactory;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import org.springframework.stereotype.Component;

@Component
public class SumoCombatExceptionMapper implements ExceptionMapper<SumoCombatException> {
  private static final int status = 400;
  private final SumoCombatExceptionResponseFactory sumoCombatExceptionResponseFactory;

  public SumoCombatExceptionMapper(
      SumoCombatExceptionResponseFactory sumoCombatExceptionResponseFactory) {
    this.sumoCombatExceptionResponseFactory = sumoCombatExceptionResponseFactory;
  }

  @Override
  public Response toResponse(SumoCombatException exception) {
    return Response.status(status)
        .entity(this.sumoCombatExceptionResponseFactory.fromSumoCombatException(exception))
        .build();
  }
}
