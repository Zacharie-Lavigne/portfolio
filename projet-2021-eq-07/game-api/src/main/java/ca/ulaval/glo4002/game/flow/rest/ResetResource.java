package ca.ulaval.glo4002.game.flow.rest;

import ca.ulaval.glo4002.game.flow.useCases.ResetUseCase;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.stereotype.Component;

@Path("/reset")
@Produces(MediaType.APPLICATION_JSON)
@Component
public class ResetResource {

  private final ResetUseCase resetUseCase;

  public ResetResource(ResetUseCase resetUseCase) {
    this.resetUseCase = resetUseCase;
  }

  @POST
  public Response resetGame() {
    this.resetUseCase.reset();
    return Response.ok().build();
  }
}
