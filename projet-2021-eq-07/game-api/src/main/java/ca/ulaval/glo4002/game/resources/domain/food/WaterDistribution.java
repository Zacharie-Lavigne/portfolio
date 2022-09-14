package ca.ulaval.glo4002.game.resources.domain.food;

import ca.ulaval.glo4002.game.resources.WaterTanks;

public class WaterDistribution {
  private final WaterTanks waterTanks;
  public static final int NUMBER_OF_TANKS = 2;

  public WaterDistribution(WaterTanks waterTanks) {
    this.waterTanks = waterTanks;
  }

  public void separateWaterDistribution(int totalWaterQuantity) {
    int quantityOfWaterForEachTank =
        Double.valueOf(
                Math.floor(Integer.valueOf(totalWaterQuantity / NUMBER_OF_TANKS).doubleValue()))
            .intValue();

    this.waterTanks.updateWaterTanksQuantity(quantityOfWaterForEachTank);
  }

  public WaterTank getBurgerEaterWaterTank() {
    return this.waterTanks.getBurgerEatersTank();
  }

  public WaterTank getSaladEaterWaterTank() {
    return this.waterTanks.getSaladEatersTank();
  }
}
