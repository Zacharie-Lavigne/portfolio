package ca.ulaval.glo4002.game.dinosaurs.domain.needs;

import ca.ulaval.glo4002.game.resources.domain.food.Pantry;
import ca.ulaval.glo4002.game.resources.domain.food.WaterTank;

public interface WaterConsumptionStrategy {
  int consumeWater(Pantry pantry, int waterNeeds, WaterTank waterTank);

  boolean isWaterNeedsSatisfied(int waterNeeds, int consumed);
}
