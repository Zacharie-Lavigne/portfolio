package ca.ulaval.glo4002.game.resources.domain.food;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class WaterTankTest {
  private final WaterTank waterTank = new WaterTank();
  private final int A_WATER_QUANTITY_TO_CONSUME = 5;

  @Test
  public void givenEnoughWater_whenConsumingWater_shouldReturnQuantityRequested() {
    int anEnoughWaterQuantity = 10;
    waterTank.updateWaterTankQuantity(anEnoughWaterQuantity);

    int consumedWaterQuantity = waterTank.consumeWater(A_WATER_QUANTITY_TO_CONSUME);

    assertEquals(A_WATER_QUANTITY_TO_CONSUME, consumedWaterQuantity);
  }

  @Test
  public void givenNotEnoughWater_whenConsumingWater_shouldReturnQuantityAvailable() {
    int aNotEnoughWaterQuantity = 3;
    waterTank.updateWaterTankQuantity(aNotEnoughWaterQuantity);

    int consumedWaterQuantity = waterTank.consumeWater(A_WATER_QUANTITY_TO_CONSUME);

    assertEquals(aNotEnoughWaterQuantity, consumedWaterQuantity);
  }

  @Test
  public void whenConsumingWater_shouldUpdateTheQuantityLeftInTheContainer() {
    int quantity = A_WATER_QUANTITY_TO_CONSUME;

    waterTank.consumeWater(quantity);

    int leftQuantity = waterTank.consumeWater(quantity);

    assertEquals(0, leftQuantity);
  }
}
