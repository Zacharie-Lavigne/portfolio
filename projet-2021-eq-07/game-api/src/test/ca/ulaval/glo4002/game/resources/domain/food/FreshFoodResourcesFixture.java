package ca.ulaval.glo4002.game.resources.domain.food;

import ca.ulaval.glo4002.game.resources.WaterTanks;
import ca.ulaval.glo4002.game.resources.domain.FoodResourcesQuantities;

public class FreshFoodResourcesFixture {
  private static final int defaultCurrentTurn = 1;
  private static final int defaultQuantity = 2;
  private static final FoodResourcesQuantities defaultFoodResourcesQuantity =
      new FoodResourcesQuantities(defaultQuantity, defaultQuantity, defaultQuantity);
  private static final WaterDistribution WATER_DISTRIBUTER =
      new WaterDistribution(new WaterTanks(new WaterTank(), new WaterTank()));

  public static FreshFoodResources createDefault() {
    FreshFoodResources freshFoodResources = new FreshFoodResources(WATER_DISTRIBUTER);
    freshFoodResources.add(defaultFoodResourcesQuantity, defaultCurrentTurn);
    return freshFoodResources;
  }

  public static FreshFoodResources create(
      int burgerQuantity, int saladQuantity, int waterQuantity) {
    FoodResourcesQuantities foodResourcesQuantities =
        new FoodResourcesQuantities(burgerQuantity, saladQuantity, waterQuantity);
    FreshFoodResources freshFoodResources = new FreshFoodResources(WATER_DISTRIBUTER);
    freshFoodResources.add(foodResourcesQuantities, defaultCurrentTurn);

    return freshFoodResources;
  }
}
