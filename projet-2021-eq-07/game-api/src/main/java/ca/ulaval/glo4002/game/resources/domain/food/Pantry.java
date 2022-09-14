package ca.ulaval.glo4002.game.resources.domain.food;

import ca.ulaval.glo4002.game.resources.domain.FoodResourcesQuantities;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Pantry {
  private static final int BURGER_LIFESPAN = 4;
  private static final int SALAD_LIFESPAN = 3;
  private static final int WATER_LIFESPAN = 10;

  private FoodResourcesQuantities expiredFoodQuantities;
  private FoodResourcesQuantities consumedFoodQuantities;
  private final FreshFoodResources freshFoodResources;

  public Pantry(FreshFoodResources foodResources) {
    this.freshFoodResources = foodResources;
    this.consumedFoodQuantities = new FoodResourcesQuantities();
    this.expiredFoodQuantities = new FoodResourcesQuantities();
  }

  public void separateWaterDistribution() {
    this.freshFoodResources.separateWaterDistribution();
  }

  public int consumeFreshFoodResources(FoodType foodType, int foodQuantity) {
    int consumedFoodResourcesQuantity =
        this.freshFoodResources.removeOldestFirst(foodType, foodQuantity);
    this.consumedFoodQuantities.addFoodResource(foodType, consumedFoodResourcesQuantity);
    return consumedFoodResourcesQuantity;
  }

  public int consumeWater(int waterQuantity, WaterTank waterTank) {
    int consumedWaterQuantity = this.freshFoodResources.consumeWater(waterQuantity, waterTank);
    this.consumedFoodQuantities.addFoodResource(FoodType.WATER, consumedWaterQuantity);
    return consumedWaterQuantity;
  }

  public void addFoodResources(FoodResourcesQuantities foodResources, int currentTurn) {
    this.freshFoodResources.add(foodResources, currentTurn);
  }

  public void deleteAll() {
    this.freshFoodResources.deleteAll();
    this.expiredFoodQuantities = new FoodResourcesQuantities(0, 0, 0);
    this.consumedFoodQuantities = new FoodResourcesQuantities(0, 0, 0);
  }

  public void updateExpiredResources(int currentTurn) {
    int expiredBurger =
        this.freshFoodResources.removeFoodByTurnNumber(
            FoodType.BURGER, currentTurn - BURGER_LIFESPAN);
    int expiredSalad =
        this.freshFoodResources.removeFoodByTurnNumber(
            FoodType.SALAD, currentTurn - SALAD_LIFESPAN);
    int expiredWater =
        this.freshFoodResources.removeFoodByTurnNumber(
            FoodType.WATER, currentTurn - WATER_LIFESPAN);

    this.expiredFoodQuantities.addFoodResource(FoodType.BURGER, expiredBurger);
    this.expiredFoodQuantities.addFoodResource(FoodType.SALAD, expiredSalad);
    this.expiredFoodQuantities.addFoodResource(FoodType.WATER, expiredWater);
  }

  public FoodResourcesQuantities getFreshFoodsQuantities() {
    int burgerQuantity = this.freshFoodResources.getQuantityOf(FoodType.BURGER);
    int saladQuantity = this.freshFoodResources.getQuantityOf(FoodType.SALAD);
    int waterQuantity = this.freshFoodResources.getQuantityOf(FoodType.WATER);

    return new FoodResourcesQuantities(burgerQuantity, saladQuantity, waterQuantity);
  }

  public FoodResourcesQuantities getConsumedFoodsQuantities() {
    return this.consumedFoodQuantities;
  }

  public FoodResourcesQuantities getExpiredFoodQuantities() {
    return this.expiredFoodQuantities;
  }

  public WaterTank getBurgerEatersWaterTank() {
    return this.freshFoodResources.getBurgerEatersWaterTank();
  }

  public WaterTank getSaladEatersWaterTank() {
    return this.freshFoodResources.getSaladEatersWaterTank();
  }
}
