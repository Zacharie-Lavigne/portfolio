package ca.ulaval.glo4002.game.dinosaurs.domain.needs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4002.game.resources.domain.food.Pantry;
import ca.ulaval.glo4002.game.resources.domain.food.WaterTank;
import org.junit.jupiter.api.Test;

class DefaultWaterConsumptionStrategyTest {

  private final Pantry A_PANTRY = mock(Pantry.class);
  private final WaterTank A_WATER_TANK = new WaterTank();
  private final int SOME_WATER_NEEDS = 100;
  private final DefaultWaterConsumptionStrategy defaultWaterConsumptionStrategy =
      new DefaultWaterConsumptionStrategy();

  @Test
  public void
      givenAPantryAWaterNeedsAndAWaterTank_whenConsumeWater_thenShouldConsumeTheQuantityWaterFromPantry() {
    defaultWaterConsumptionStrategy.consumeWater(A_PANTRY, SOME_WATER_NEEDS, A_WATER_TANK);

    verify(A_PANTRY).consumeWater(SOME_WATER_NEEDS, A_WATER_TANK);
  }

  @Test
  public void
      givenAPantryAWaterNeedsAndAWaterTank_whenConsumeWater_thenShouldReturnConsumedWaterFromPantry() {
    when(A_PANTRY.consumeWater(SOME_WATER_NEEDS, A_WATER_TANK)).thenReturn(SOME_WATER_NEEDS);

    int consumedWater =
        defaultWaterConsumptionStrategy.consumeWater(A_PANTRY, SOME_WATER_NEEDS, A_WATER_TANK);

    assertEquals(SOME_WATER_NEEDS, consumedWater);
  }

  @Test
  public void givenConsumedEqualsToWaterNeeds_whenVerifyIfNeedsAreSatisfied_thenShouldReturnTrue() {
    assertTrue(
        defaultWaterConsumptionStrategy.isWaterNeedsSatisfied(SOME_WATER_NEEDS, SOME_WATER_NEEDS));
  }

  @Test
  public void givenConsumedWaterLowerToWaterNeeds_whenVerifyIfNeedsSatisfied_shouldReturnFalse() {
    int consumedWater = 99;

    assertFalse(
        defaultWaterConsumptionStrategy.isWaterNeedsSatisfied(SOME_WATER_NEEDS, consumedWater));
  }
}
