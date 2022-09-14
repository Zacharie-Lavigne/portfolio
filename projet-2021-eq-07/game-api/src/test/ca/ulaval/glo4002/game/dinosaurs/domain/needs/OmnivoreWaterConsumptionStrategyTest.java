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

class OmnivoreWaterConsumptionStrategyTest {

  private final Pantry A_PANTRY = mock(Pantry.class);
  private final WaterTank A_WATER_TANK = new WaterTank();
  private final int SOME_WATER_NEEDS = 100;
  private final int WATER_NEEDS_PER_TANK = 50;
  private final OmnivoreWaterConsumptionStrategy omnivoreWaterConsumptionStrategy =
      new OmnivoreWaterConsumptionStrategy();

  @Test
  public void
      givenAPantryAWaterNeedsAndAWaterTank_whenConsumeWater_thenShouldConsumeTheQuantityPerTankFromPantry() {
    omnivoreWaterConsumptionStrategy.consumeWater(A_PANTRY, SOME_WATER_NEEDS, A_WATER_TANK);

    verify(A_PANTRY).consumeWater(WATER_NEEDS_PER_TANK, A_WATER_TANK);
  }

  @Test
  public void
      givenAPantryAWaterNeedsAndAWaterTank_whenConsumeWater_thenShouldReturnConsumedWaterFromPantry() {
    when(A_PANTRY.consumeWater(WATER_NEEDS_PER_TANK, A_WATER_TANK))
        .thenReturn(WATER_NEEDS_PER_TANK);

    int consumedWater =
        omnivoreWaterConsumptionStrategy.consumeWater(A_PANTRY, SOME_WATER_NEEDS, A_WATER_TANK);

    assertEquals(WATER_NEEDS_PER_TANK, consumedWater);
  }

  @Test
  public void
      givenAPantryAnOddWaterNeedsAndAWaterTank_whenConsumeWater_thenShouldConsumeTheQuantityRoundedUpPerTankFromPantry() {
    int waterNeeds = 333;

    omnivoreWaterConsumptionStrategy.consumeWater(A_PANTRY, waterNeeds, A_WATER_TANK);

    int actualWaterToConsume = 167;

    verify(A_PANTRY).consumeWater(actualWaterToConsume, A_WATER_TANK);
  }

  @Test
  public void
      givenConsumedEqualsToWaterNeedsPerTank_whenVerifyIfWaterNeedsIsSatisfied_thenShouldReturnTrue() {
    assertTrue(
        omnivoreWaterConsumptionStrategy.isWaterNeedsSatisfied(
            SOME_WATER_NEEDS, WATER_NEEDS_PER_TANK));
  }

  @Test
  public void
      givenConsumedWaterLowerToWaterNeedsPerTank_whenVerifyIfNeedsSatisfy_shouldReturnFalse() {
    int consumedWater = 20;

    assertFalse(
        omnivoreWaterConsumptionStrategy.isWaterNeedsSatisfied(SOME_WATER_NEEDS, consumedWater));
  }
}
