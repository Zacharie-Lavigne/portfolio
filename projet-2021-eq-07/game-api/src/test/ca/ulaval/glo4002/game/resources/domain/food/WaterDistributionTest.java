package ca.ulaval.glo4002.game.resources.domain.food;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import ca.ulaval.glo4002.game.resources.WaterTanks;
import org.junit.jupiter.api.Test;

class WaterDistributionTest {
  private final WaterTank BURGER_EATERS_WATER_TANK = new WaterTank();
  private final WaterTank SALAD_EATERS_WATER_TANK = new WaterTank();
  private final WaterTanks WATER_TANKS =
      spy(new WaterTanks(BURGER_EATERS_WATER_TANK, SALAD_EATERS_WATER_TANK));

  private final WaterDistribution waterDistribution = new WaterDistribution(WATER_TANKS);

  @Test
  public void givenATotalWaterQuantity_whenSeparateWaterDistribution_thenShouldSplitWaterEvenly() {
    int aWaterTotalQuantity = 10;

    waterDistribution.separateWaterDistribution(aWaterTotalQuantity);

    int expectedQuantityOfWaterForEachTank = 5;

    verify(WATER_TANKS).updateWaterTanksQuantity(expectedQuantityOfWaterForEachTank);
  }

  @Test
  public void
      givenATotalWaterQuantity_whenSeparateWaterDistribution_thenShouldRoundDownWaterQuantity() {
    int aWaterTotalQuantity = 3;

    waterDistribution.separateWaterDistribution(aWaterTotalQuantity);

    int expectedQuantityOfWaterForEachTank = 1;

    verify(WATER_TANKS).updateWaterTanksQuantity(expectedQuantityOfWaterForEachTank);
  }

  @Test
  public void whenGettingBurgerEaterWaterTank_thenShouldGet() {
    WaterTank actual = waterDistribution.getBurgerEaterWaterTank();

    assertEquals(BURGER_EATERS_WATER_TANK, actual);
  }

  @Test
  public void whenGettingSaladEaterWaterTank_thenShouldGet() {
    WaterTank actual = waterDistribution.getSaladEaterWaterTank();

    assertEquals(SALAD_EATERS_WATER_TANK, actual);
  }
}
