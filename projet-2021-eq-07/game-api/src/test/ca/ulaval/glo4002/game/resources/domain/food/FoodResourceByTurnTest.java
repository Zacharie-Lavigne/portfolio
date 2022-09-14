package ca.ulaval.glo4002.game.resources.domain.food;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class FoodResourceByTurnTest {
  private final FoodType FOOD_TYPE = FoodType.BURGER;
  private final int TURN_NUMBER = 5;
  private final int FOOD_QUANTITY = 10;
  private final FoodResourceByTurn foodResourceByTurn =
      new FoodResourceByTurn(TURN_NUMBER, FOOD_QUANTITY, FoodType.BURGER);

  @Test
  public void givenAQuantityToRemove_whenRemoving_thenShouldRemoveTheQuantityFromTheFoodResource() {
    int aQuantityToRemove = 5;
    int expectedResultingQuantity = FOOD_QUANTITY - aQuantityToRemove;

    foodResourceByTurn.remove(aQuantityToRemove);

    assertEquals(expectedResultingQuantity, foodResourceByTurn.getQuantity());
  }

  @Test
  public void givenAFoodResourceOfAType_whenIsTypeOfSameType_thenShouldReturnTrue() {
    assertTrue(foodResourceByTurn.isType(FOOD_TYPE));
  }

  @Test
  public void givenAFoodResourceOfAType_whenIsTypeOfDifferentType_thenShouldReturnFalse() {
    FoodType anotherFoodType = FoodType.WATER;
    assertFalse(foodResourceByTurn.isType(anotherFoodType));
  }
}
