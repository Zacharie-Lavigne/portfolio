package ca.ulaval.glo4002.game.resources.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4002.game.resources.domain.FoodResourcesQuantities;
import ca.ulaval.glo4002.game.resources.domain.FoodResourcesReport;
import ca.ulaval.glo4002.game.resources.rest.assemblers.FoodResourcesQuantitiesAssembler;
import ca.ulaval.glo4002.game.resources.rest.assemblers.FoodResourcesReportAssembler;
import ca.ulaval.glo4002.game.resources.useCases.ResourcesUseCase;
import javax.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ResourcesResourceTest {
  private final ResourcesUseCase resourcesUseCase = mock(ResourcesUseCase.class);
  private final FoodResourcesReportAssembler foodResourcesReportAssembler =
      mock(FoodResourcesReportAssembler.class);
  private final FoodResourcesQuantitiesAssembler foodResourcesQuantitiesAssembler =
      mock(FoodResourcesQuantitiesAssembler.class);

  private final ResourcesResource resourcesResource =
      new ResourcesResource(
          resourcesUseCase, foodResourcesReportAssembler, foodResourcesQuantitiesAssembler);

  private final int A_BURGER_QTY = 0;
  private final int A_SALAD_QTY = 0;
  private final int A_WATER_QTY = 0;

  private final FoodResourcesQuantities A_FOOD_RESOURCES_QUANTITIES =
      new FoodResourcesQuantities(A_BURGER_QTY, A_SALAD_QTY, A_WATER_QTY);
  private final FoodResourcesReport A_FOOD_RESOURCES_REPORT =
      new FoodResourcesReport(
          A_FOOD_RESOURCES_QUANTITIES, A_FOOD_RESOURCES_QUANTITIES, A_FOOD_RESOURCES_QUANTITIES);

  private final FoodResourcesQuantitiesResponse A_FOOD_RESOURCES_QUANTITIES_RESPONSE =
      new FoodResourcesQuantitiesResponse(A_BURGER_QTY, A_SALAD_QTY, A_WATER_QTY);
  private final FoodResourcesReportResponse A_FOOD_RESOURCES_REPORT_RESPONSE =
      new FoodResourcesReportResponse(
          A_FOOD_RESOURCES_QUANTITIES_RESPONSE,
          A_FOOD_RESOURCES_QUANTITIES_RESPONSE,
          A_FOOD_RESOURCES_QUANTITIES_RESPONSE);

  private final FoodResourcesQuantitiesRequest A_FOOD_RESOURCES_QUANTITIES_REQUEST =
      new FoodResourcesQuantitiesRequest(A_BURGER_QTY, A_SALAD_QTY, A_WATER_QTY);

  @BeforeEach
  public void setUp() {
    when(resourcesUseCase.getResourcesReport()).thenReturn(A_FOOD_RESOURCES_REPORT);
    when(foodResourcesReportAssembler.toResponse(A_FOOD_RESOURCES_REPORT))
        .thenReturn(A_FOOD_RESOURCES_REPORT_RESPONSE);
    when(foodResourcesQuantitiesAssembler.toDomain(A_FOOD_RESOURCES_QUANTITIES_REQUEST))
        .thenReturn(A_FOOD_RESOURCES_QUANTITIES);
  }

  @Test
  public void
      whenGettingFoodResourcesReport_thenShouldGetFoodResourcesReportFromResourcesUseCase() {
    resourcesResource.getResources();

    verify(resourcesUseCase).getResourcesReport();
  }

  @Test
  public void whenGettingFoodResourcesReport_thenShouldAssembleFoodResourcesReportToResponse() {
    resourcesResource.getResources();

    verify(foodResourcesReportAssembler).toResponse(A_FOOD_RESOURCES_REPORT);
  }

  @Test
  public void
      whenGettingFoodResourcesReport_thenShouldReturnFoodResourcesReportResponseWithEntity() {
    resourcesResource.getResources();

    Response expectedResponse = Response.ok().entity(A_FOOD_RESOURCES_REPORT_RESPONSE).build();
    Response actualResponse = resourcesResource.getResources();

    assertEquals(expectedResponse.getEntity(), actualResponse.getEntity());
  }

  @Test
  public void
      whenGettingFoodResourcesReport_thenShouldReturnFoodResourcesReportResponseWithCode200() {
    resourcesResource.getResources();

    Response expectedResponse = Response.ok().entity(A_FOOD_RESOURCES_REPORT_RESPONSE).build();
    Response actualResponse = resourcesResource.getResources();

    assertEquals(expectedResponse.getStatus(), actualResponse.getStatus());
  }

  @Test
  public void
      givenAValidFoodResourcesQuantitiesRequest_whenCreateAddResourcesAction_thenShouldAssembleFoodResourcesQuantitiesRequestToDomain() {
    resourcesResource.createAddResourcesAction(A_FOOD_RESOURCES_QUANTITIES_REQUEST);

    verify(foodResourcesQuantitiesAssembler).toDomain(A_FOOD_RESOURCES_QUANTITIES_REQUEST);
  }

  @Test
  public void
      givenAValidFoodResourcesQuantitiesRequest_whenCreateAddResourcesAction_thenShouldAddFoodResourcesAction() {
    resourcesResource.createAddResourcesAction(A_FOOD_RESOURCES_QUANTITIES_REQUEST);

    verify(resourcesUseCase).addResourcesAction(A_FOOD_RESOURCES_QUANTITIES);
  }

  @Test
  public void
      givenAValidFoodResourcesQuantitiesRequest_whenCreateAddResourcesAction_thenShouldReturnResponseWithCode200() {
    Response expectedResponse = Response.ok().build();
    Response actualResponse =
        resourcesResource.createAddResourcesAction(A_FOOD_RESOURCES_QUANTITIES_REQUEST);

    assertEquals(expectedResponse.getStatus(), actualResponse.getStatus());
  }
}
