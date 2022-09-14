package ca.ulaval.glo4002.game.resources.useCases;

import ca.ulaval.glo4002.game.actions.domain.Action;
import ca.ulaval.glo4002.game.actions.domain.ActionFactory;
import ca.ulaval.glo4002.game.flow.domain.Game;
import ca.ulaval.glo4002.game.flow.domain.GameRepository;
import ca.ulaval.glo4002.game.resources.domain.FoodResourcesQuantities;
import ca.ulaval.glo4002.game.resources.domain.FoodResourcesReport;
import ca.ulaval.glo4002.game.resources.domain.PantryRepository;
import ca.ulaval.glo4002.game.resources.domain.food.Pantry;
import org.springframework.stereotype.Component;

@Component
public class ResourcesUseCase {
  private final ActionFactory actionFactory;
  private final GameRepository gameRepository;
  private final PantryRepository pantryRepository;

  public ResourcesUseCase(
      ActionFactory actionFactory,
      GameRepository gameRepository,
      PantryRepository pantryRepository) {
    this.actionFactory = actionFactory;
    this.gameRepository = gameRepository;
    this.pantryRepository = pantryRepository;
  }

  public FoodResourcesReport getResourcesReport() {
    Pantry pantry = this.pantryRepository.getPantry();

    FoodResourcesQuantities freshQuantities = pantry.getFreshFoodsQuantities();

    FoodResourcesQuantities expiredQuantities = pantry.getExpiredFoodQuantities();

    FoodResourcesQuantities consumedQuantities = pantry.getConsumedFoodsQuantities();

    return new FoodResourcesReport(freshQuantities, expiredQuantities, consumedQuantities);
  }

  public void addResourcesAction(FoodResourcesQuantities foodResourcesQuantities) {
    Game game = this.gameRepository.getGame();
    Pantry pantry = this.pantryRepository.getPantry();

    Action action =
        this.actionFactory.createAddResourcesAction(
            foodResourcesQuantities, game.getTurnNumber(), pantry);

    game.addAction(action);

    this.gameRepository.update(game);
  }
}
