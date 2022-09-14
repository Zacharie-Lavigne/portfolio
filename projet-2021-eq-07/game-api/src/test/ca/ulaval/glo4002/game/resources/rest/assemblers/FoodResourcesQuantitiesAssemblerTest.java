package ca.ulaval.glo4002.game.resources.rest.assemblers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import ca.ulaval.glo4002.game.resources.domain.FoodResourcesQuantities;
import ca.ulaval.glo4002.game.resources.rest.FoodResourcesQuantitiesRequest;
import ca.ulaval.glo4002.game.resources.rest.FoodResourcesQuantitiesResponse;
import ca.ulaval.glo4002.game.resources.rest.exceptions.InvalidResourceQuantityException;
import org.junit.jupiter.api.Test;

class FoodResourcesQuantitiesAssemblerTest {
  private final FoodResourcesQuantitiesAssembler foodResourcesQuantitiesAssembler =
      new FoodResourcesQuantitiesAssembler();

  private final int A_BURGER_QUANTITY = 3;
  private final int A_SALAD_QUANTITY = 2;
  private final int A_WATER_QUANTITY = 5;

  @Test
  public void
      givenAFoodResourcesQuantities_whenAssemblingToResponse_thenShouldAssembleWithCorrespondingParameters() {
    FoodResourcesQuantities foodResourcesQuantities =
        new FoodResourcesQuantities(A_BURGER_QUANTITY, A_SALAD_QUANTITY, A_WATER_QUANTITY);

    FoodResourcesQuantitiesResponse expected =
        new FoodResourcesQuantitiesResponse(A_BURGER_QUANTITY, A_SALAD_QUANTITY, A_WATER_QUANTITY);

    FoodResourcesQuantitiesResponse actual =
        foodResourcesQuantitiesAssembler.toResponse(foodResourcesQuantities);

    assertEquals(expected, actual);
  }

  @Test
  public void
      givenAFoodResourcesQuantitiesRequest_whenAssemblingToDomain_thenShouldAssembleWithCorrespondingParameters() {
    FoodResourcesQuantitiesRequest foodResourcesQuantitiesRequest =
        new FoodResourcesQuantitiesRequest(A_BURGER_QUANTITY, A_SALAD_QUANTITY, A_WATER_QUANTITY);

    FoodResourcesQuantities expected =
        new FoodResourcesQuantities(A_BURGER_QUANTITY, A_SALAD_QUANTITY, A_WATER_QUANTITY);

    FoodResourcesQuantities actual =
        foodResourcesQuantitiesAssembler.toDomain(foodResourcesQuantitiesRequest);

    assertEquals(expected, actual);
  }

  @Test
  public void
  givenAFoodResourcesQuantitiesRequestWithNegativeValues_whenCreating_thenShouldThrowInvalidQuantityException() {
    int A_NEGATIVE_BURGER_QUANTITY = -3;

    FoodResourcesQuantitiesRequest request =
        new FoodResourcesQuantitiesRequest(A_NEGATIVE_BURGER_QUANTITY, A_SALAD_QUANTITY,
            A_WATER_QUANTITY);

    assertThrows(
        InvalidResourceQuantityException.class,
        () ->
            foodResourcesQuantitiesAssembler.toDomain(request));
  }

}
