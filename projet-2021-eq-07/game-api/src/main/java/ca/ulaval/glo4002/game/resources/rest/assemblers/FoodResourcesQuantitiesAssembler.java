package ca.ulaval.glo4002.game.resources.rest.assemblers;

import ca.ulaval.glo4002.game.resources.domain.FoodResourcesQuantities;
import ca.ulaval.glo4002.game.resources.domain.food.FoodType;
import ca.ulaval.glo4002.game.resources.rest.FoodResourcesQuantitiesRequest;
import ca.ulaval.glo4002.game.resources.rest.FoodResourcesQuantitiesResponse;
import ca.ulaval.glo4002.game.resources.rest.exceptions.InvalidResourceQuantityException;
import org.springframework.stereotype.Component;

@Component
public class FoodResourcesQuantitiesAssembler {

  public void validateFoodQuantitiesRequest(FoodResourcesQuantitiesRequest request) {
    if (request.qtyBurger < 0 || request.qtySalad < 0 || request.qtyWater < 0) {
      throw new InvalidResourceQuantityException();
    }
  }

  public FoodResourcesQuantitiesResponse toResponse(
      FoodResourcesQuantities foodResourcesQuantities) {
    return new FoodResourcesQuantitiesResponse(
        foodResourcesQuantities.getQuantityFor(FoodType.BURGER),
        foodResourcesQuantities.getQuantityFor(FoodType.SALAD),
        foodResourcesQuantities.getQuantityFor(FoodType.WATER));
  }

  public FoodResourcesQuantities toDomain(
      FoodResourcesQuantitiesRequest foodResourcesQuantitiesRequest) {
    validateFoodQuantitiesRequest(foodResourcesQuantitiesRequest);
    return new FoodResourcesQuantities(
        foodResourcesQuantitiesRequest.qtyBurger,
        foodResourcesQuantitiesRequest.qtySalad,
        foodResourcesQuantitiesRequest.qtyWater);
  }
}
