package ca.ulaval.glo4002.game.breeding.rest;

import ca.ulaval.glo4002.game.breeding.domain.BreedingAttempt;
import ca.ulaval.glo4002.game.breeding.rest.assemblers.BreedingAssembler;
import ca.ulaval.glo4002.game.breeding.useCases.BreedingUseCase;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.stereotype.Component;

@Path("/breed")
@Produces(MediaType.APPLICATION_JSON)
@Component
public class BreedingResource {

  private final BreedingAssembler breedingAssembler;
  private final BreedingUseCase breedingUseCase;

  public BreedingResource(BreedingAssembler breedingAssembler, BreedingUseCase breedingUseCase) {
    this.breedingUseCase = breedingUseCase;
    this.breedingAssembler = breedingAssembler;
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response createBreedDinosaursAction(BreedingRequest breedingRequest) {
    BreedingAttempt breedingAttempt = this.breedingAssembler.toDomain(breedingRequest);

    this.breedingUseCase.createBreedDinosaurAction(breedingAttempt);

    return Response.ok().build();
  }
}
