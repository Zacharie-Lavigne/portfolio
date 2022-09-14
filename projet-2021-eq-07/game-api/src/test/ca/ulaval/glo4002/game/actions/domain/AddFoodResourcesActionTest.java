package ca.ulaval.glo4002.game.actions.domain;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import ca.ulaval.glo4002.game.resources.domain.FoodResourcesQuantities;
import ca.ulaval.glo4002.game.resources.domain.food.Pantry;
import org.junit.jupiter.api.Test;

class AddFoodResourcesActionTest {
  private final Pantry pantry = mock(Pantry.class);

  @Test
  public void givenFoodResourcesToAddAndAPantry_whenExecuting_thenShouldAddFoodResourcesToPantry() {
    int aTurnNumber = 2;
    FoodResourcesQuantities foodResourcesQuantities = new FoodResourcesQuantities(1,1,1);
    AddFoodResourcesAction addFoodResourcesAction =
        new AddFoodResourcesAction(foodResourcesQuantities, pantry, aTurnNumber);

    addFoodResourcesAction.execute();

    verify(pantry).addFoodResources(foodResourcesQuantities, aTurnNumber);
  }
}
