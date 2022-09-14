package ca.ulaval.glo4002.game.combat.rest;

import ca.ulaval.glo4002.game.actions.domain.SumoCombatAction;
import ca.ulaval.glo4002.game.combat.domain.SumoCombatAttempt;
import ca.ulaval.glo4002.game.combat.domain.SumoCombatResult;
import ca.ulaval.glo4002.game.combat.rest.assemblers.SumoCombatAssembler;
import ca.ulaval.glo4002.game.combat.useCase.SumoCombatUseCase;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.stereotype.Component;

@Path("/sumodino")
@Component
public class SumoCombatResource {
  private final SumoCombatUseCase sumoCombatUseCase;
  private final SumoCombatAssembler sumoCombatAssembler;

  public SumoCombatResource(
      SumoCombatUseCase sumoCombatUseCase, SumoCombatAssembler sumoCombatAssembler) {
    this.sumoCombatUseCase = sumoCombatUseCase;
    this.sumoCombatAssembler = sumoCombatAssembler;
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response createAndPredictSumoCombatAction(SumoCombatRequest sumoCombatRequest) {
    SumoCombatAttempt sumoCombatAttempt = this.sumoCombatAssembler.toDomain(sumoCombatRequest);
    SumoCombatAction sumoCombatAction =
        this.sumoCombatUseCase.createSumoCombatAction(sumoCombatAttempt);

    SumoCombatResult prediction = sumoCombatAction.getPrediction();
    SumoCombatResponse sumoCombatResponse = this.sumoCombatAssembler.toResponse(prediction);
    return Response.ok().entity(sumoCombatResponse).build();
  }
}
