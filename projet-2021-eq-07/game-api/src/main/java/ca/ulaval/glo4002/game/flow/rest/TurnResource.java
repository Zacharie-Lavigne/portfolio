package ca.ulaval.glo4002.game.flow.rest;

import ca.ulaval.glo4002.game.flow.useCases.PlayTurnUseCase;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.stereotype.Component;

@Path("/turn")
@Produces(MediaType.APPLICATION_JSON)
@Component
public class TurnResource {

  private final PlayTurnUseCase playTurnUseCase;

  public TurnResource(PlayTurnUseCase playTurnUseCase) {
    this.playTurnUseCase = playTurnUseCase;
  }

  @POST
  public TurnResponse playTurn() {
    int turnNumber = this.playTurnUseCase.playTurn();
    return new TurnResponse(turnNumber);
  }
}
