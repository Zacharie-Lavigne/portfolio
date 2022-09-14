package ca.ulaval.glo4002.game.resources.domain.food;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class FoodResourceTest {
  private final int FOOD_RESOURCE_QUANTITY = 5;
  private final FoodResource aFoodResource =
      new FoodResource(FoodType.BURGER, FOOD_RESOURCE_QUANTITY);

  @Test
  public void
      givenAFoodResource_whenRemovingLessThanQuantity_thenShouldReturnFoodResourceWithNewQuantity() {
    int quantityToRemove = 2;
    int expectedQuantity = FOOD_RESOURCE_QUANTITY - quantityToRemove;

    aFoodResource.remove(quantityToRemove);

    assertEquals(expectedQuantity, aFoodResource.getQuantity());
  }

  @Test
  public void
      givenAFoodResource_whenRemovingMoreThanQuantity_thenShouldReturnFoodResourceWithQuantityOfZero() {
    int quantityToRemove = 10;

    aFoodResource.remove(quantityToRemove);

    assertEquals(0, aFoodResource.getQuantity());
  }
}
