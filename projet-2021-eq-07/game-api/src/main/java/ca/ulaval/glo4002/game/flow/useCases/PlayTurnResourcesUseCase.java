package ca.ulaval.glo4002.game.flow.useCases;

import ca.ulaval.glo4002.game.resources.domain.FoodResourcesQuantities;
import ca.ulaval.glo4002.game.resources.domain.food.Pantry;
import org.springframework.stereotype.Component;

@Component
public class PlayTurnResourcesUseCase {
  private static final int DEFAULT_BURGER_QTY = 100;
  private static final int DEFAULT_SALAD_QTY = 250;
  private static final int DEFAULT_WATER_QTY = 10000;

  public void playTurn(Pantry pantry, int currentTurnNumber) {
    pantry.updateExpiredResources(currentTurnNumber);
    this.addTurnResources(pantry, currentTurnNumber);
  }

  private void addTurnResources(Pantry pantry, int currentTurnNumber) {
    FoodResourcesQuantities defaultFoodResourcesQuantities =
        new FoodResourcesQuantities(DEFAULT_BURGER_QTY, DEFAULT_SALAD_QTY, DEFAULT_WATER_QTY);

    pantry.addFoodResources(defaultFoodResourcesQuantities, currentTurnNumber);
  }
}
