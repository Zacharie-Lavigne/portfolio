package ca.ulaval.glo4002.game.dinosaurs.domain.needs;

import ca.ulaval.glo4002.game.dinosaurs.domain.Dinosaur;
import ca.ulaval.glo4002.game.resources.domain.FoodResourcesQuantities;

public interface NeedsCalculationStrategy {

  FoodResourcesQuantities calculateNeedsFor(Dinosaur dinosaur);
}
