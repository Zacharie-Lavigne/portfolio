package ca.ulaval.glo4002.game.resources.rest.assemblers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4002.game.resources.domain.FoodResourcesQuantities;
import ca.ulaval.glo4002.game.resources.domain.FoodResourcesReport;
import ca.ulaval.glo4002.game.resources.rest.FoodResourcesQuantitiesResponse;
import ca.ulaval.glo4002.game.resources.rest.FoodResourcesReportResponse;
import org.junit.jupiter.api.Test;

class FoodResourcesReportAssemblerTest {
  private final FoodResourcesQuantitiesAssembler foodResourcesQuantitiesAssembler =
      mock(FoodResourcesQuantitiesAssembler.class);

  private final FoodResourcesReportAssembler foodResourcesReportAssembler =
      new FoodResourcesReportAssembler(foodResourcesQuantitiesAssembler);

  private final FoodResourcesQuantities A_FOOD_RESOURCES_QUANTITIES =
      new FoodResourcesQuantities(1, 2, 4);
  private final FoodResourcesQuantitiesResponse A_FOOD_RESOURCES_QUANTITIES_RESPONSE =
      new FoodResourcesQuantitiesResponse(1, 2, 4);

  @Test
  public void
      givenAFoodResourcesReport_whenAssemblingToResponse_thenShouldAssembleWithTheCorrespondingParameters() {
    when(foodResourcesQuantitiesAssembler.toResponse(A_FOOD_RESOURCES_QUANTITIES))
        .thenReturn(A_FOOD_RESOURCES_QUANTITIES_RESPONSE);

    FoodResourcesReport aFoodResourcesReport =
        new FoodResourcesReport(
            A_FOOD_RESOURCES_QUANTITIES, A_FOOD_RESOURCES_QUANTITIES, A_FOOD_RESOURCES_QUANTITIES);

    FoodResourcesReportResponse expected =
        new FoodResourcesReportResponse(
            A_FOOD_RESOURCES_QUANTITIES_RESPONSE,
            A_FOOD_RESOURCES_QUANTITIES_RESPONSE,
            A_FOOD_RESOURCES_QUANTITIES_RESPONSE);

    FoodResourcesReportResponse actual =
        foodResourcesReportAssembler.toResponse(aFoodResourcesReport);

    assertEquals(expected, actual);
  }
}
