package ca.ulaval.glo4002.game.dinosaurs.domain.needs;

import ca.ulaval.glo4002.game.dinosaurs.domain.Dinosaur;
import ca.ulaval.glo4002.game.resources.domain.FoodResourcesQuantities;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class CarnivoreNeedsCalculationStrategy implements NeedsCalculationStrategy {

  public static final double BURGER_FACTOR_CARNIVORE = 0.2;
  private static final double WATER_FACTOR = 0.6;
  private static final int DAILY_FOOD_DIVIDER = 200;

  public FoodResourcesQuantities calculateNeedsFor(Dinosaur dinosaur) {
    return new FoodResourcesQuantities(
        calculateBurgerQuantity(dinosaur), 0, calculateWaterQuantity(dinosaur));
  }

  private int calculateBurgerQuantity(Dinosaur dinosaur) {
    return Double.valueOf(
            Math.ceil(
                (dinosaur.getWeightInKilograms() * BURGER_FACTOR_CARNIVORE)
                    / DAILY_FOOD_DIVIDER
                    * dinosaur.getStarvingFactor()))
        .intValue();
  }

  private int calculateWaterQuantity(Dinosaur dinosaur) {
    return Double.valueOf(
            (Math.ceil(
                dinosaur.getWeightInKilograms() * WATER_FACTOR * dinosaur.getStarvingFactor())))
        .intValue();
  }
}
