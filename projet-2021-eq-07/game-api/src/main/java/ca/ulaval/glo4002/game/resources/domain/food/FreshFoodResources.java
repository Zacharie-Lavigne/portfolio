package ca.ulaval.glo4002.game.resources.domain.food;

import ca.ulaval.glo4002.game.resources.domain.FoodResourcesQuantities;
import io.vavr.collection.List;

public class FreshFoodResources {
  private List<FoodResourceByTurn> foodResources;
  private final WaterDistribution waterDistribution;

  public FreshFoodResources(WaterDistribution waterDistribution) {
    this.foodResources = List.empty();
    this.waterDistribution = waterDistribution;
  }

  public void add(FoodResourcesQuantities foodQuantities, int currentTurn) {
    for (FoodType foodType : FoodType.values()) {
      int quantityForType = foodQuantities.getQuantityFor(foodType);
      this.addByType(foodType, quantityForType, currentTurn);
    }
  }

  public int removeOldestFirst(FoodType foodType, int foodQuantityToRemove) {
    List<FoodResourceByTurn> sortedByOldest =
        this.getFoodByType(foodType).sortBy(FoodResourceByTurn::getTurn);

    int foundQuantity = 0;

    while (!sortedByOldest.isEmpty() && foodQuantityToRemove != foundQuantity) {
      FoodResourceByTurn oldestFoodResourceByTurn = sortedByOldest.get();
      int availableQuantity = oldestFoodResourceByTurn.getQuantity();

      if (oldestFoodResourceByTurn.getQuantity() <= foodQuantityToRemove - foundQuantity) {
        foundQuantity += availableQuantity;
        sortedByOldest = sortedByOldest.remove(oldestFoodResourceByTurn);
      } else {
        oldestFoodResourceByTurn.remove(foodQuantityToRemove - foundQuantity);
        foundQuantity = foodQuantityToRemove;
      }
    }
    this.updateByFoodType(sortedByOldest, foodType);

    return foundQuantity;
  }

  public int consumeWater(int waterQuantity, WaterTank waterTank) {
    int quantityOfWaterToRemove = waterTank.consumeWater(waterQuantity);
    return this.removeOldestFirst(FoodType.WATER, quantityOfWaterToRemove);
  }

  public int removeFoodByTurnNumber(FoodType foodType, int turnNumber) {
    List<FoodResourceByTurn> foodResourcesToRemove =
        this.getFoodByType(foodType).filter(foodByType -> foodByType.getTurn() == turnNumber);

    this.foodResources = this.foodResources.removeAll(foodResourcesToRemove);

    return foodResourcesToRemove.map(FoodResourceByTurn::getQuantity).sum().intValue();
  }

  public void deleteAll() {
    this.foodResources = List.empty();
  }

  public int getQuantityOf(FoodType foodType) {
    return this.getFoodByType(foodType).map(FoodResourceByTurn::getQuantity).sum().intValue();
  }

  public void separateWaterDistribution() {
    this.waterDistribution.separateWaterDistribution(this.getQuantityOf(FoodType.WATER));
  }

  public WaterTank getBurgerEatersWaterTank() {
    return this.waterDistribution.getBurgerEaterWaterTank();
  }

  public WaterTank getSaladEatersWaterTank() {
    return this.waterDistribution.getSaladEaterWaterTank();
  }

  private void addByType(FoodType foodType, int quantity, int currentTurn) {
    this.foodResources =
        this.foodResources.append(new FoodResourceByTurn(currentTurn, quantity, foodType));
  }

  private List<FoodResourceByTurn> getFoodByType(FoodType foodType) {
    return this.foodResources.filter(food -> food.isType(foodType));
  }

  private void updateByFoodType(List<FoodResourceByTurn> updatedFoodResources, FoodType foodType) {
    this.foodResources =
        this.foodResources
            .removeAll(foodByTypeAndTurn -> foodByTypeAndTurn.isType(foodType))
            .appendAll(updatedFoodResources);
  }
}
