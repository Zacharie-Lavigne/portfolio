package ca.ulaval.glo4002.game.dinosaurs.domain.needs;

import ca.ulaval.glo4002.game.dinosaurs.domain.Dinosaur;
import ca.ulaval.glo4002.game.resources.domain.FoodResourcesQuantities;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class HerbivoreNeedsCalculationStrategy implements NeedsCalculationStrategy {
  public static final double SALAD_FACTOR_HERBIVORE = 0.5;
  private static final double WATER_FACTOR = 0.6;
  private static final int DAILY_FOOD_DIVIDER = 200;

  public FoodResourcesQuantities calculateNeedsFor(Dinosaur dinosaur) {
    return new FoodResourcesQuantities(0, getSaladQuantity(dinosaur), getWaterQuantity(dinosaur));
  }

  private int getSaladQuantity(Dinosaur dinosaur) {
    return Double.valueOf(
            Math.ceil(
                dinosaur.getWeightInKilograms()
                    * SALAD_FACTOR_HERBIVORE
                    / DAILY_FOOD_DIVIDER
                    * dinosaur.getStarvingFactor()))
        .intValue();
  }

  private int getWaterQuantity(Dinosaur dinosaur) {
    return Double.valueOf(
            (Math.ceil(
                dinosaur.getWeightInKilograms() * WATER_FACTOR * dinosaur.getStarvingFactor())))
        .intValue();
  }
}
