package ca.ulaval.glo4002.game.resources.rest;

import ca.ulaval.glo4002.game.resources.domain.FoodResourcesQuantities;
import ca.ulaval.glo4002.game.resources.domain.FoodResourcesReport;
import ca.ulaval.glo4002.game.resources.rest.assemblers.FoodResourcesQuantitiesAssembler;
import ca.ulaval.glo4002.game.resources.rest.assemblers.FoodResourcesReportAssembler;
import ca.ulaval.glo4002.game.resources.useCases.ResourcesUseCase;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.stereotype.Component;

@Path("/resources")
@Produces(MediaType.APPLICATION_JSON)
@Component
public class ResourcesResource {

  private final ResourcesUseCase resourcesUseCase;
  private final FoodResourcesReportAssembler foodResourcesReportAssembler;
  private final FoodResourcesQuantitiesAssembler foodResourcesQuantitiesAssembler;

  public ResourcesResource(
      ResourcesUseCase resourcesUseCase,
      FoodResourcesReportAssembler foodResourcesReportAssembler,
      FoodResourcesQuantitiesAssembler foodResourcesQuantitiesAssembler) {
    this.resourcesUseCase = resourcesUseCase;
    this.foodResourcesReportAssembler = foodResourcesReportAssembler;
    this.foodResourcesQuantitiesAssembler = foodResourcesQuantitiesAssembler;
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getResources() {
    FoodResourcesReport foodResourcesReport = this.resourcesUseCase.getResourcesReport();

    FoodResourcesReportResponse foodResourcesReportResponse =
        foodResourcesReportAssembler.toResponse(foodResourcesReport);

    return Response.ok().entity(foodResourcesReportResponse).build();
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response createAddResourcesAction(FoodResourcesQuantitiesRequest foodRequest) {
    FoodResourcesQuantities foodResourcesQuantities =
        this.foodResourcesQuantitiesAssembler.toDomain(foodRequest);

    this.resourcesUseCase.addResourcesAction(foodResourcesQuantities);

    return Response.ok().build();
  }
}
