package ca.ulaval.glo4002.game.dinosaurs.domain.needs;

import ca.ulaval.glo4002.game.resources.domain.food.Pantry;
import ca.ulaval.glo4002.game.resources.domain.food.WaterTank;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class DefaultWaterConsumptionStrategy implements WaterConsumptionStrategy {

  @Override
  public int consumeWater(Pantry pantry, int waterNeeds, WaterTank waterTank) {
    return pantry.consumeWater(waterNeeds, waterTank);
  }

  @Override
  public boolean isWaterNeedsSatisfied(int waterNeeds, int consumed) {
    return consumed >= waterNeeds;
  }
}
