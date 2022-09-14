package ca.ulaval.glo4002.game.resources;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import ca.ulaval.glo4002.game.resources.domain.food.WaterTank;
import org.junit.jupiter.api.Test;

class WaterTanksTest {
  private final WaterTank BURGER_EATER_WATER_TANK = spy(new WaterTank());
  private final WaterTank SALAD_EATER_WATER_TANK = spy(new WaterTank());

  private final WaterTanks waterTanks =
      new WaterTanks(BURGER_EATER_WATER_TANK, SALAD_EATER_WATER_TANK);

  private final int A_WATER_TANK_QUANTITY = 10;

  @Test
  public void
      givenAWaterTanksQuantity_whenUpdateWaterTanksQuantity_thenShouldUpdateBurgerEatersWaterTank() {
    waterTanks.updateWaterTanksQuantity(A_WATER_TANK_QUANTITY);

    verify(BURGER_EATER_WATER_TANK).updateWaterTankQuantity(A_WATER_TANK_QUANTITY);
  }

  @Test
  public void
      givenAWaterTanksQuantity_whenUpdateWaterTanksQuantity_thenShouldUpdateSaladEatersWaterTank() {
    waterTanks.updateWaterTanksQuantity(A_WATER_TANK_QUANTITY);

    verify(SALAD_EATER_WATER_TANK).updateWaterTankQuantity(A_WATER_TANK_QUANTITY);
  }
}
