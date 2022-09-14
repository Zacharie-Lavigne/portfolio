package ca.ulaval.glo4002.game.flow.useCases;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import ca.ulaval.glo4002.game.resources.domain.FoodResourcesQuantities;
import ca.ulaval.glo4002.game.resources.domain.food.Pantry;
import org.junit.jupiter.api.Test;

class PlayTurnResourcesUseCaseTest {
  private final Pantry PANTRY = mock(Pantry.class);
  private final PlayTurnResourcesUseCase playTurnResourcesUseCase = new PlayTurnResourcesUseCase();
  private final int A_TURN_NUMBER = 2;

  @Test
  public void
      givenAPantryAndATurnNumber_whenPlayingTurn_thenShouldAddDefaultTurnFoodResourcesToPantry() {
    int defaultTurnBurgerQty = 100;
    int defaultTurnSaladQty = 250;
    int defaultTurnWaterQty = 10000;

    FoodResourcesQuantities defaultTurnFoodResources =
        new FoodResourcesQuantities(defaultTurnBurgerQty, defaultTurnSaladQty, defaultTurnWaterQty);

    playTurnResourcesUseCase.playTurn(PANTRY, A_TURN_NUMBER);

    verify(PANTRY).addFoodResources(defaultTurnFoodResources, A_TURN_NUMBER);
  }

  @Test
  public void givenAPantryAndATurnNUmber_whenPlayingTurn_thenShouldUpdateExpiredFoodResources() {
    playTurnResourcesUseCase.playTurn(PANTRY, A_TURN_NUMBER);

    verify(PANTRY).updateExpiredResources(A_TURN_NUMBER);
  }
}
