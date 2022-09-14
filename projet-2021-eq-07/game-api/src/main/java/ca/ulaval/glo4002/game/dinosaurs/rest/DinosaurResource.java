package ca.ulaval.glo4002.game.dinosaurs.rest;

import ca.ulaval.glo4002.game.dinosaurs.domain.DeltaWeight;
import ca.ulaval.glo4002.game.dinosaurs.domain.Dinosaur;
import ca.ulaval.glo4002.game.dinosaurs.rest.assemblers.DeltaWeightAssembler;
import ca.ulaval.glo4002.game.dinosaurs.rest.assemblers.DinosaurAssembler;
import ca.ulaval.glo4002.game.dinosaurs.useCases.DinosaurUseCase;
import io.vavr.collection.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.stereotype.Component;

@Path("/dinosaurs")
@Produces(MediaType.APPLICATION_JSON)
@Component
public class DinosaurResource {

  private final DinosaurAssembler dinosaurAssembler;
  private final DinosaurUseCase dinosaurUseCase;
  private final DeltaWeightAssembler deltaWeightAssembler;

  public DinosaurResource(
      DinosaurAssembler dinosaurAssembler,
      DinosaurUseCase dinosaurUseCase,
      DeltaWeightAssembler deltaWeightAssembler) {
    this.dinosaurAssembler = dinosaurAssembler;
    this.dinosaurUseCase = dinosaurUseCase;
    this.deltaWeightAssembler = deltaWeightAssembler;
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response createAddDinosaurAction(DinosaurRequest dinosaurRequest) {
    Dinosaur dinosaur = this.dinosaurAssembler.toDomain(dinosaurRequest);

    this.dinosaurUseCase.createAddDinosaurAction(dinosaur);

    return Response.ok().build();
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getDinosaurs() {
    List<Dinosaur> dinosaurs = this.dinosaurUseCase.getAllDinosaurs();

    List<DinosaurResponse> dinosaursResponse = dinosaurs.map(dinosaurAssembler::toResponse);

    return Response.ok().entity(dinosaursResponse.asJava()).build();
  }

  @GET
  @Path("/{DinosaurName}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getDinosaurByName(@PathParam("DinosaurName") String name) {
    Dinosaur dinosaur = this.dinosaurUseCase.getDinosaurByName(name);

    DinosaurResponse dinosaurResponse = this.dinosaurAssembler.toResponse(dinosaur);

    return Response.ok().entity(dinosaurResponse).build();
  }

  @PATCH
  @Path("/{DinosaurName}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response createChangeDinosaurWeightAction(
      DinosaurChangeWeightRequest weightRequest, @PathParam("DinosaurName") String name) {
    DeltaWeight deltaWeight = this.deltaWeightAssembler.toDomain(weightRequest);

    this.dinosaurUseCase.createChangeDinosaurWeightAction(name, deltaWeight);

    return Response.ok().build();
  }
}
