package ca.ulaval.glo4002.game.resources;

import ca.ulaval.glo4002.game.resources.domain.food.WaterTank;

public class WaterTanks {
  private final WaterTank burgerEatersTank;
  private final WaterTank saladEatersTank;

  public WaterTanks(WaterTank burgerEatersTank, WaterTank saladEatersTank) {
    this.burgerEatersTank = burgerEatersTank;
    this.saladEatersTank = saladEatersTank;
  }

  public WaterTank getBurgerEatersTank() {
    return this.burgerEatersTank;
  }

  public WaterTank getSaladEatersTank() {
    return this.saladEatersTank;
  }

  public void updateWaterTanksQuantity(int waterTanksQuantity) {
    this.burgerEatersTank.updateWaterTankQuantity(waterTanksQuantity);
    this.saladEatersTank.updateWaterTankQuantity(waterTanksQuantity);
  }
}
