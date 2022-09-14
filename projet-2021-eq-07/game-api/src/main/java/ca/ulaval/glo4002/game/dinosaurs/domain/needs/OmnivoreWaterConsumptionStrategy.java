package ca.ulaval.glo4002.game.dinosaurs.domain.needs;

import ca.ulaval.glo4002.game.resources.domain.food.Pantry;
import ca.ulaval.glo4002.game.resources.domain.food.WaterDistribution;
import ca.ulaval.glo4002.game.resources.domain.food.WaterTank;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class OmnivoreWaterConsumptionStrategy implements WaterConsumptionStrategy {

  @Override
  public int consumeWater(Pantry pantry, int waterNeeds, WaterTank waterTank) {
    int waterToConsume = this.getQuantityPerContainer(waterNeeds);
    return pantry.consumeWater(waterToConsume, waterTank);
  }

  @Override
  public boolean isWaterNeedsSatisfied(int waterNeeds, int consumed) {
    int quantityPerContainer = this.getQuantityPerContainer(waterNeeds);
    return consumed >= quantityPerContainer;
  }

  private int getQuantityPerContainer(int waterNeeds) {
    return Double.valueOf(
            Math.ceil(
                Integer.valueOf(waterNeeds).doubleValue() / WaterDistribution.NUMBER_OF_TANKS))
        .intValue();
  }
}
