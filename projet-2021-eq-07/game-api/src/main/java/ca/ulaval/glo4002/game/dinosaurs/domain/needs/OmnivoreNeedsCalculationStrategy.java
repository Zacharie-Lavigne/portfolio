package ca.ulaval.glo4002.game.dinosaurs.domain.needs;

import ca.ulaval.glo4002.game.dinosaurs.domain.Dinosaur;
import ca.ulaval.glo4002.game.resources.domain.FoodResourcesQuantities;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class OmnivoreNeedsCalculationStrategy implements NeedsCalculationStrategy {
  private static final double WATER_FACTOR = 0.6;
  private static final int DAILY_FOOD_DIVIDER = 200;

  public FoodResourcesQuantities calculateNeedsFor(Dinosaur dinosaur) {
    return new FoodResourcesQuantities(
        getBurgerQuantity(dinosaur), getSaladQuantity(dinosaur), getWaterQuantity(dinosaur));
  }

  private int getSaladQuantity(Dinosaur dinosaur) {
    double baseCalculation =
        ((dinosaur.getWeightInKilograms()
                    * HerbivoreNeedsCalculationStrategy.SALAD_FACTOR_HERBIVORE)
                / DAILY_FOOD_DIVIDER)
            / 2;

    return Double.valueOf(Math.ceil(baseCalculation * dinosaur.getStarvingFactor())).intValue();
  }

  private int getBurgerQuantity(Dinosaur dinosaur) {
    double baseCalculation =
        ((dinosaur.getWeightInKilograms()
                    * CarnivoreNeedsCalculationStrategy.BURGER_FACTOR_CARNIVORE)
                / DAILY_FOOD_DIVIDER)
            / 2;

    return Double.valueOf(Math.ceil(baseCalculation * dinosaur.getStarvingFactor())).intValue();
  }

  private int getWaterQuantity(Dinosaur dinosaur) {
    return Double.valueOf(
            Math.ceil(
                dinosaur.getWeightInKilograms() * WATER_FACTOR * dinosaur.getStarvingFactor()))
        .intValue();
  }
}
