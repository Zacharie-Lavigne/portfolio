package ca.ulaval.glo4002.game.resources.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ca.ulaval.glo4002.game.resources.domain.food.FoodType;
import org.junit.jupiter.api.Test;

class FoodResourcesQuantitiesTest {
  private final FoodResourcesQuantities NOT_EMPTY_RESOURCE_QUANTITY =
      new FoodResourcesQuantities(3, 2, 1);
  private final FoodResourcesQuantities EMPTY_RESOURCE_QUANTITY =
      new FoodResourcesQuantities(0, 0, 0);

  @Test
  public void
      givenAFoodResourcesQuantitiesWithCompletelyEmptyFoodResources_whenGettingIfSomeResourcesAreEmpty_thenShouldBeEmpty() {
    assertTrue(EMPTY_RESOURCE_QUANTITY.areAllResourcesEmpty());
  }

  @Test
  public void
      givenAFoodResourcesQuantitiesWithOneEmptyFoodResources_whenCheckingIfSomeResourcesAreEmpty_thenShouldNotBeEmpty() {
    FoodResourcesQuantities quantities = new FoodResourcesQuantities(3, 2, 0);

    assertFalse(quantities.areAllResourcesEmpty());
  }

  @Test
  public void
      givenAFoodResourcesQuantitiesWithNoEmptyFoodResources_whenCheckingIfSomeResourcesAreEmpty_thenShouldNotBeEmpty() {
    assertFalse(NOT_EMPTY_RESOURCE_QUANTITY.areAllResourcesEmpty());
  }

  @Test
  public void givenAFoodResourceQuantityNotEmpty_whenRemovingAResource_thenShouldRemoveIt() {
    int burgerQty = NOT_EMPTY_RESOURCE_QUANTITY.getQuantityFor(FoodType.BURGER);
    int quantity = 2;

    NOT_EMPTY_RESOURCE_QUANTITY.removeFoodResource(FoodType.BURGER, quantity);

    assertEquals(NOT_EMPTY_RESOURCE_QUANTITY.getQuantityFor(FoodType.BURGER), burgerQty - quantity);
  }

  @Test
  public void givenAFoodResourceQuantityWithNoResources_whenRemovingAResource_thenShouldBeAtZero() {
    EMPTY_RESOURCE_QUANTITY.removeFoodResource(FoodType.BURGER, 2);

    assertEquals(EMPTY_RESOURCE_QUANTITY.getQuantityFor(FoodType.BURGER), 0);
  }

  @Test
  public void
      givenAFoodResourceQuantityNotEmpty_whenRemovingMoreResourcesThanAvailable_thenShouldBeAtZero() {
    int burgerQty = NOT_EMPTY_RESOURCE_QUANTITY.getQuantityFor(FoodType.BURGER);

    NOT_EMPTY_RESOURCE_QUANTITY.removeFoodResource(FoodType.BURGER, burgerQty + 2);

    assertEquals(NOT_EMPTY_RESOURCE_QUANTITY.getQuantityFor(FoodType.BURGER), 0);
  }

  @Test
  public void
  givenAFoodResourceQuantityNotEmpty_whenAddingFoodResources_thenShouldAddIt() {
    int burgerQty = NOT_EMPTY_RESOURCE_QUANTITY.getQuantityFor(FoodType.BURGER);
    int addedBurgerQuantity = 5;
    int expectedBurgerQuantity = burgerQty + addedBurgerQuantity;

    NOT_EMPTY_RESOURCE_QUANTITY.addFoodResource(FoodType.BURGER, addedBurgerQuantity);

    assertEquals(expectedBurgerQuantity, NOT_EMPTY_RESOURCE_QUANTITY.getQuantityFor(FoodType.BURGER));
  }
}
