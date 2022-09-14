package ca.ulaval.glo4002.game.resources.domain.food;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4002.game.resources.WaterTanks;
import ca.ulaval.glo4002.game.resources.domain.FoodResourcesQuantities;
import org.junit.jupiter.api.Test;

class PantryTest {
  private static final int A_TURN_NUMBER = 1;

  private final FreshFoodResources FRESH_FOOD_RESOURCES = mock(FreshFoodResources.class);

  private final int A_FOOD_QUANTITY = 1;
  private final int A_WATER_QUANTITY = 3;

  private final WaterTank A_WATER_TANK = new WaterTank();
  private final Pantry pantry = new Pantry(FRESH_FOOD_RESOURCES);

  @Test
  public void
      givenFreshFoodResourcesASaladFoodTypeAndAQuantityToEat_whenConsumeFreshFoodResources_thenShouldReturnConsumedFood() {
    FoodType aFoodType = FoodType.SALAD;
    when(FRESH_FOOD_RESOURCES.removeOldestFirst(aFoodType, A_FOOD_QUANTITY))
        .thenReturn(A_FOOD_QUANTITY);

    int result = pantry.consumeFreshFoodResources(FoodType.SALAD, A_FOOD_QUANTITY);

    assertEquals(A_FOOD_QUANTITY, result);
  }

  @Test
  public void
      givenFreshFoodResourcesASaladFoodTypeAndAQuantityToEat_whenConsumeFreshFoodResources_thenShouldAddConsumedFoodToConsumedQuantities() {
    FoodType aFoodType = FoodType.SALAD;
    when(FRESH_FOOD_RESOURCES.removeOldestFirst(aFoodType, A_FOOD_QUANTITY))
        .thenReturn(A_FOOD_QUANTITY);

    pantry.consumeFreshFoodResources(aFoodType, A_FOOD_QUANTITY);

    int consumedSalads = pantry.getConsumedFoodsQuantities().getQuantityFor(FoodType.SALAD);

    assertEquals(A_FOOD_QUANTITY, consumedSalads);
  }

  @Test
  public void
      givenAWaterTankAndWaterQuantityToConsume_whenConsumingWater_thenShouldReturnConsumedWaterQuantity() {
    when(FRESH_FOOD_RESOURCES.consumeWater(A_WATER_QUANTITY, A_WATER_TANK))
        .thenReturn(A_WATER_QUANTITY);

    int consumedWater = pantry.consumeWater(A_WATER_QUANTITY, A_WATER_TANK);

    assertEquals(A_WATER_QUANTITY, consumedWater);
  }

  @Test
  public void
      givenAWaterTankAndWaterQuantityToConsume_whenConsumingWater_thenShouldAddConsumedWaterToConsumedQuantities() {
    when(FRESH_FOOD_RESOURCES.consumeWater(A_WATER_QUANTITY, A_WATER_TANK))
        .thenReturn(A_WATER_QUANTITY);

    pantry.consumeWater(A_WATER_QUANTITY, A_WATER_TANK);

    int consumedWater = pantry.getConsumedFoodsQuantities().getQuantityFor(FoodType.WATER);

    assertEquals(A_WATER_QUANTITY, consumedWater);
  }

  @Test
  public void givenNoResources_whenGetFreshFoodsQuantities_thenShouldBeEmpty() {
    Pantry aPantry =
        new Pantry(
            new FreshFoodResources(
                new WaterDistribution(new WaterTanks(A_WATER_TANK, A_WATER_TANK))));

    FoodResourcesQuantities quantities = aPantry.getFreshFoodsQuantities();

    assertTrue(quantities.areAllResourcesEmpty());
  }

  @Test
  public void givenResources_whenGetFreshFoodsQuantities_thenShouldReturnFreshFoodQuantities() {
    int burgerQuantity = 3;
    int waterQuantity = 2;
    int saladQuantity = 1;
    Pantry aPantry =
        new Pantry(FreshFoodResourcesFixture.create(burgerQuantity, saladQuantity, waterQuantity));

    FoodResourcesQuantities quantities = aPantry.getFreshFoodsQuantities();

    assertEquals(quantities.getQuantityFor(FoodType.WATER), waterQuantity);
    assertEquals(quantities.getQuantityFor(FoodType.BURGER), burgerQuantity);
    assertEquals(quantities.getQuantityFor(FoodType.SALAD), saladQuantity);
  }

  @Test
  public void
      givenResources_whenGetConsumedFoodsQuantities_thenShouldReturnConsumedFoodQuantities() {
    int burgerQuantity = 3;
    int waterQuantity = 2;
    int saladQuantity = 1;
    Pantry aPantry =
        new Pantry(FreshFoodResourcesFixture.create(burgerQuantity, saladQuantity, waterQuantity));

    aPantry.consumeFreshFoodResources(FoodType.BURGER, burgerQuantity);
    FoodResourcesQuantities quantities = aPantry.getConsumedFoodsQuantities();

    assertEquals(quantities.getQuantityFor(FoodType.WATER), 0);
    assertEquals(quantities.getQuantityFor(FoodType.BURGER), burgerQuantity);
    assertEquals(quantities.getQuantityFor(FoodType.SALAD), 0);
  }

  @Test
  public void
      givenResources_whenUpdatingExpiredBurgerResources_thenShouldReturnTheExpiredBurgerQuantity() {
    int numberOfExpiredBurger = 5;
    int burgerLifespan = 4;

    when(FRESH_FOOD_RESOURCES.removeFoodByTurnNumber(FoodType.BURGER, A_TURN_NUMBER))
        .thenReturn(numberOfExpiredBurger);

    pantry.updateExpiredResources(A_TURN_NUMBER + burgerLifespan);
    FoodResourcesQuantities expiredQuantities = pantry.getExpiredFoodQuantities();

    assertEquals(numberOfExpiredBurger, expiredQuantities.getQuantityFor(FoodType.BURGER));
  }

  @Test
  public void
      givenResources_whenUpdatingExpiredSaladResources_thenShouldReturnTheExpiredSaladQuantity() {
    int numberOfExpiredSalad = 5;
    int saladLifespan = 3;

    when(FRESH_FOOD_RESOURCES.removeFoodByTurnNumber(FoodType.SALAD, A_TURN_NUMBER))
        .thenReturn(numberOfExpiredSalad);

    pantry.updateExpiredResources(A_TURN_NUMBER + saladLifespan);
    FoodResourcesQuantities expiredQuantities = pantry.getExpiredFoodQuantities();

    assertEquals(numberOfExpiredSalad, expiredQuantities.getQuantityFor(FoodType.SALAD));
  }

  @Test
  public void
      givenResources_whenUpdatingExpiredWaterResources_thenShouldReturnTheExpiredWaterQuantity() {
    int numberOfExpiredWater = 5;
    int waterLifespan = 10;

    when(FRESH_FOOD_RESOURCES.removeFoodByTurnNumber(FoodType.WATER, A_TURN_NUMBER))
        .thenReturn(numberOfExpiredWater);

    pantry.updateExpiredResources(A_TURN_NUMBER + waterLifespan);
    FoodResourcesQuantities expiredQuantities = pantry.getExpiredFoodQuantities();

    assertEquals(numberOfExpiredWater, expiredQuantities.getQuantityFor(FoodType.WATER));
  }

  @Test
  public void whenSeparateWaterDistribution_thenShouldSeparateWaterDistribution() {
    pantry.separateWaterDistribution();

    verify(FRESH_FOOD_RESOURCES).separateWaterDistribution();
  }

  @Test
  public void whenGettingBurgerEatersWaterTank_thenShouldGet() {
    pantry.getBurgerEatersWaterTank();

    verify(FRESH_FOOD_RESOURCES).getBurgerEatersWaterTank();
  }

  @Test
  public void whenGettingSaladEatersWaterTank_thenShouldGet() {
    pantry.getSaladEatersWaterTank();

    verify(FRESH_FOOD_RESOURCES).getSaladEatersWaterTank();
  }

  @Test
  public void whenDeletingAll_thenShouldDeleteAllFreshFoodResources() {
    pantry.deleteAll();

    verify(FRESH_FOOD_RESOURCES).deleteAll();
  }

  @Test
  public void givenNonZeroExpiredFood_whenDeletingAll_thenShouldResetExpiredFoodQuantitiesToZero() {
    int aTurnNumber = 20;
    int anExpiredFoodQuantity = 10;
    when(FRESH_FOOD_RESOURCES.removeFoodByTurnNumber(any(FoodType.class), anyInt()))
        .thenReturn(anExpiredFoodQuantity);
    pantry.updateExpiredResources(aTurnNumber);

    pantry.deleteAll();

    FoodResourcesQuantities actual = pantry.getExpiredFoodQuantities();

    assertTrue(actual.areAllResourcesEmpty());
  }

  @Test
  public void
      givenANonZeroConsumedFood_whenDeletingAll_thenShouldResetConsumedFoodQuantitiesToZero() {
    int aFoodQuantityToConsume = 12;
    when(FRESH_FOOD_RESOURCES.removeOldestFirst(any(FoodType.class), anyInt()))
        .thenReturn(aFoodQuantityToConsume);
    pantry.consumeFreshFoodResources(FoodType.BURGER, aFoodQuantityToConsume);

    pantry.deleteAll();

    FoodResourcesQuantities actual = pantry.getConsumedFoodsQuantities();

    assertTrue(actual.areAllResourcesEmpty());
  }

  @Test
  public void givenAFoodResourcesQuantitiesAndATurnNumber_whenAddingFoodResources_thenShouldAddFoodResources() {
    FoodResourcesQuantities aFoodResourcesQuantities = new FoodResourcesQuantities(5, 6, 7);

    pantry.addFoodResources(aFoodResourcesQuantities, A_TURN_NUMBER);

    verify(FRESH_FOOD_RESOURCES).add(aFoodResourcesQuantities, A_TURN_NUMBER);
  }
}
