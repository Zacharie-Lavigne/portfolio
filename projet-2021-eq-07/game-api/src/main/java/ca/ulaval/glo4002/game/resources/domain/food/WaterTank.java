package ca.ulaval.glo4002.game.resources.domain.food;

public class WaterTank {
  private int availableQuantity = 0;

  int consumeWater(int waterQuantity) {
    int consumedWaterQuantity = Math.min(waterQuantity, this.availableQuantity);

    this.availableQuantity -= consumedWaterQuantity;
    return consumedWaterQuantity;
  }

  public void updateWaterTankQuantity(int waterTanksQuantity) {
    this.availableQuantity = waterTanksQuantity;
  }
}
