package ca.ulaval.glo4002.game.actions.domain;

import ca.ulaval.glo4002.game.resources.domain.FoodResourcesQuantities;
import ca.ulaval.glo4002.game.resources.domain.food.Pantry;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class AddFoodResourcesAction implements Action {
  private final FoodResourcesQuantities pendingResources;
  private final Pantry pantry;
  private final int turnNumber;

  public AddFoodResourcesAction(FoodResourcesQuantities resources, Pantry pantry, int turnNumber) {
    this.pendingResources = resources;
    this.pantry = pantry;
    this.turnNumber = turnNumber;
  }

  @Override
  public void execute() {
    this.pantry.addFoodResources(this.pendingResources, this.turnNumber);
  }
}
