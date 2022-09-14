package ca.ulaval.glo4002.game.resources.domain.food;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import ca.ulaval.glo4002.game.resources.domain.FoodResourcesQuantities;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FreshFoodResourcesTest {
  private final WaterDistribution A_WATER_DISTRIBUTION = mock(WaterDistribution.class);

  private final int A_WATER_QUANTITY = 2;
  private final int A_SALAD_QUANTITY = 0;
  private final int A_BURGER_QUANTITY = 4;

  private final FoodType BURGER_FOOD_TYPE = FoodType.BURGER;
  private final int A_TURN_NUMBER = 1;
  private final FoodResourcesQuantities A_FOOD_RESOURCES_QUANTITIES =
      new FoodResourcesQuantities(A_BURGER_QUANTITY, A_SALAD_QUANTITY, A_WATER_QUANTITY);
  private final WaterTank A_WATER_TANK = spy(new WaterTank());
  private final int AN_OVER_BURGER_QUANTITY = 12;

  private final FreshFoodResources A_FRESH_FOOD_RESOURCES =
      new FreshFoodResources(A_WATER_DISTRIBUTION);
  private final FreshFoodResources AN_EMPTY_FRESH_FOOD_RESOURCES =
      new FreshFoodResources(A_WATER_DISTRIBUTION);

  @BeforeEach
  public void setUp() {
    A_FRESH_FOOD_RESOURCES.add(A_FOOD_RESOURCES_QUANTITIES, A_TURN_NUMBER);
  }

  @Test
  public void
      givenFoodResourcesQuantitiesAndATurnNumber_whenAddingFoodByType_thenShouldAddTheRightQuantityToTheResources() {
    FoodResourcesQuantities foodResourcesQuantities =
        new FoodResourcesQuantities(A_BURGER_QUANTITY, A_SALAD_QUANTITY, A_WATER_QUANTITY);

    AN_EMPTY_FRESH_FOOD_RESOURCES.add(foodResourcesQuantities, A_TURN_NUMBER);

    assertEquals(A_WATER_QUANTITY, AN_EMPTY_FRESH_FOOD_RESOURCES.getQuantityOf(FoodType.WATER));
    assertEquals(A_SALAD_QUANTITY, AN_EMPTY_FRESH_FOOD_RESOURCES.getQuantityOf(FoodType.SALAD));
    assertEquals(A_BURGER_QUANTITY, AN_EMPTY_FRESH_FOOD_RESOURCES.getQuantityOf(FoodType.BURGER));
  }

  @Test
  public void
      givenEnoughFoodResourcesAndAFoodQuantityToRemove_whenRemoveOldestFirst_thenShouldReturnRemovedQuantity() {
    int consumedQuantity =
        A_FRESH_FOOD_RESOURCES.removeOldestFirst(BURGER_FOOD_TYPE, A_BURGER_QUANTITY);

    assertEquals(A_BURGER_QUANTITY, consumedQuantity);
  }

  @Test
  public void
      givenEnoughFoodResourcesAndAFoodQuantityToRemove_whenRemoveOldestFirst_thenShouldRemoveResourcesFromFreshFoodResources() {
    A_FRESH_FOOD_RESOURCES.removeOldestFirst(BURGER_FOOD_TYPE, A_BURGER_QUANTITY);

    assertEquals(0, A_FRESH_FOOD_RESOURCES.getQuantityOf(BURGER_FOOD_TYPE));
  }

  @Test
  public void givenNotEnoughResources_whenRemoveOldestFirst_thenShouldReturnQuantityRemoved() {
    int consumedQuantity =
        A_FRESH_FOOD_RESOURCES.removeOldestFirst(BURGER_FOOD_TYPE, AN_OVER_BURGER_QUANTITY);

    assertEquals(A_BURGER_QUANTITY, consumedQuantity);
  }

  @Test
  public void
      givenNotEnoughResources_whenRemoveOldestFirst_thenShouldRemoveAllResourcesFromFreshFoodResources() {
    A_FRESH_FOOD_RESOURCES.removeOldestFirst(BURGER_FOOD_TYPE, AN_OVER_BURGER_QUANTITY);

    assertEquals(0, A_FRESH_FOOD_RESOURCES.getQuantityOf(BURGER_FOOD_TYPE));
  }

  @Test
  public void
      givenOlderResourcesWithNotEnoughResourcesInOldestFoods_whenRemoveOldestFirst_thenShouldRemoveAllOldestFoodResources() {
    FoodResourcesQuantities aFoodResourceQuantities =
        new FoodResourcesQuantities(A_BURGER_QUANTITY, 0, 0);
    A_FRESH_FOOD_RESOURCES.add(aFoodResourceQuantities, A_TURN_NUMBER + 1);

    A_FRESH_FOOD_RESOURCES.removeOldestFirst(BURGER_FOOD_TYPE, AN_OVER_BURGER_QUANTITY);

    int quantityFromOldestLeft =
        A_FRESH_FOOD_RESOURCES.removeFoodByTurnNumber(BURGER_FOOD_TYPE, A_TURN_NUMBER);

    assertEquals(0, quantityFromOldestLeft);
  }

  @Test
  public void
      givenOlderResourcesWithNotEnoughResourcesInOldestFoods_whenRemoveOldestFirst_thenShouldRemoveResourceMissingResourcesFromYoungestFoodResources() {
    int quantityOver = 2;
    FoodResourcesQuantities aFoodResourceQuantities =
        new FoodResourcesQuantities(quantityOver, 0, 0);
    A_FRESH_FOOD_RESOURCES.add(aFoodResourceQuantities, A_TURN_NUMBER + 1);

    A_FRESH_FOOD_RESOURCES.removeOldestFirst(BURGER_FOOD_TYPE, A_BURGER_QUANTITY + 1);

    int quantityFromYoungest =
        A_FRESH_FOOD_RESOURCES.removeFoodByTurnNumber(BURGER_FOOD_TYPE, A_TURN_NUMBER + 1);

    assertEquals(quantityOver - 1, quantityFromYoungest);
  }

  @Test
  public void givenExpiredFoodAndAFoodType_whenRemoveFood_thenShouldRemoveItFromList() {
    A_FRESH_FOOD_RESOURCES.removeFoodByTurnNumber(BURGER_FOOD_TYPE, A_TURN_NUMBER);

    assertEquals(0, A_FRESH_FOOD_RESOURCES.getQuantityOf(BURGER_FOOD_TYPE));
  }

  @Test
  public void givenExpiredFoodAndAFoodType_whenRemoveFood_thenShouldReturnTheQuantityRemoved() {
    int quantityRemoved =
        A_FRESH_FOOD_RESOURCES.removeFoodByTurnNumber(BURGER_FOOD_TYPE, A_TURN_NUMBER);

    assertEquals(A_BURGER_QUANTITY, quantityRemoved);
  }

  @Test
  public void givenAFoodType_whenGetQuantityOf_thenShouldReturnTotalQuantityOfFoodType() {
    int quantity = A_FRESH_FOOD_RESOURCES.getQuantityOf(BURGER_FOOD_TYPE);

    assertEquals(A_BURGER_QUANTITY, quantity);
  }

  @Test
  public void whenDeleteAll_thenShouldDeleteAllResources() {
    A_FRESH_FOOD_RESOURCES.deleteAll();

    assertEquals(0, A_FRESH_FOOD_RESOURCES.getQuantityOf(FoodType.BURGER));
    assertEquals(0, A_FRESH_FOOD_RESOURCES.getQuantityOf(FoodType.WATER));
    assertEquals(0, A_FRESH_FOOD_RESOURCES.getQuantityOf(FoodType.SALAD));
  }

  @Test
  public void whenSeparateWaterDistribution_thenShouldSeparateWaterDistribution() {
    A_FRESH_FOOD_RESOURCES.separateWaterDistribution();

    verify(A_WATER_DISTRIBUTION).separateWaterDistribution(any(Integer.class));
  }

  @Test
  public void
      givenAWaterTankAndAWaterQuantity_whenConsumeWater_thenShouldConsumeWaterFromWaterTank() {
    A_FRESH_FOOD_RESOURCES.consumeWater(A_WATER_QUANTITY, A_WATER_TANK);

    verify(A_WATER_TANK).consumeWater(A_WATER_QUANTITY);
  }

  @Test
  public void
      givenAWaterTankAndAWaterQuantity_whenConsumeWater_thenShouldReturnQuantityOfWaterRemoved() {
    FoodResourcesQuantities aFoodResourcesQuantities =
        new FoodResourcesQuantities(0, 0, A_WATER_QUANTITY);

    A_FRESH_FOOD_RESOURCES.add(aFoodResourcesQuantities, A_TURN_NUMBER);
    A_WATER_TANK.updateWaterTankQuantity(A_WATER_QUANTITY);

    int consumed = A_FRESH_FOOD_RESOURCES.consumeWater(A_WATER_QUANTITY, A_WATER_TANK);

    assertEquals(A_WATER_QUANTITY, consumed);
  }

  @Test
  public void
      givenAWaterTankAndAWaterQuantity_whenConsumeWater_thenShouldRemoveConsumedWaterFromFreshResources() {
    A_WATER_TANK.updateWaterTankQuantity(A_WATER_QUANTITY);

    A_FRESH_FOOD_RESOURCES.consumeWater(A_WATER_QUANTITY, A_WATER_TANK);

    assertEquals(0, A_FRESH_FOOD_RESOURCES.getQuantityOf(FoodType.WATER));
  }

  @Test
  public void whenGettingBurgerEatersWaterTank_thenShouldGet() {
    A_FRESH_FOOD_RESOURCES.getBurgerEatersWaterTank();

    verify(A_WATER_DISTRIBUTION).getBurgerEaterWaterTank();
  }

  @Test
  public void whenGettingSaladEatersWaterTank_thenShouldGet() {
    A_FRESH_FOOD_RESOURCES.getSaladEatersWaterTank();

    verify(A_WATER_DISTRIBUTION).getSaladEaterWaterTank();
  }
}
