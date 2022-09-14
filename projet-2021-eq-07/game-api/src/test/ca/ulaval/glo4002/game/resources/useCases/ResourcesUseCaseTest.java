package ca.ulaval.glo4002.game.resources.useCases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4002.game.actions.domain.ActionFactory;
import ca.ulaval.glo4002.game.actions.domain.AddFoodResourcesAction;
import ca.ulaval.glo4002.game.flow.domain.Game;
import ca.ulaval.glo4002.game.flow.domain.GameRepository;
import ca.ulaval.glo4002.game.resources.domain.FoodResourcesQuantities;
import ca.ulaval.glo4002.game.resources.domain.FoodResourcesReport;
import ca.ulaval.glo4002.game.resources.domain.PantryRepository;
import ca.ulaval.glo4002.game.resources.domain.food.FoodType;
import ca.ulaval.glo4002.game.resources.domain.food.FreshFoodResourcesFixture;
import ca.ulaval.glo4002.game.resources.domain.food.Pantry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ResourcesUseCaseTest {
  private final GameRepository gameRepository = mock(GameRepository.class);
  private final ActionFactory actionFactory = mock(ActionFactory.class);
  private final PantryRepository pantryRepository = mock(PantryRepository.class);

  private final ResourcesUseCase resourcesUseCase =
      new ResourcesUseCase(actionFactory, gameRepository, pantryRepository);

  private final int A_QTY_BURGER = 1;
  private final int A_QTY_SALAD = 2;
  private final int A_QTY_WATER = 3;

  private Pantry A_PANTRY =
      new Pantry(FreshFoodResourcesFixture.create(A_QTY_BURGER, A_QTY_SALAD, A_QTY_WATER));
  private final Game A_GAME = new Game();
  private final FoodResourcesQuantities actionFoodResourcesQuantities =
      new FoodResourcesQuantities(1, 1, 1);
  private final AddFoodResourcesAction AN_ACTION =
      new AddFoodResourcesAction(actionFoodResourcesQuantities, A_PANTRY, 1);

  @BeforeEach
  public void setUp() {
    A_PANTRY = new Pantry(FreshFoodResourcesFixture.create(A_QTY_BURGER, A_QTY_SALAD, A_QTY_WATER));

    when(gameRepository.getGame()).thenReturn(A_GAME);
    when(actionFactory.createAddResourcesAction(
            actionFoodResourcesQuantities, A_GAME.getTurnNumber(), A_PANTRY))
        .thenReturn(AN_ACTION);
    when(pantryRepository.getPantry()).thenReturn(A_PANTRY);
  }

  @Test
  public void
      givenSomeNotEmptyResources_whenGettingResourcesReport_thenShouldHaveTheRightQuantitiesOfFreshFood() {
    FoodResourcesReport actual = resourcesUseCase.getResourcesReport();

    assertEquals(A_QTY_WATER, actual.fresh.getQuantityFor(FoodType.WATER));
    assertEquals(A_QTY_BURGER, actual.fresh.getQuantityFor(FoodType.BURGER));
    assertEquals(A_QTY_SALAD, actual.fresh.getQuantityFor(FoodType.SALAD));
  }

  @Test
  public void
      givenSomeNotEmptyResources_whenGettingResourcesReport_thenShouldGetTheRightQuantitiesOfExpired() {
    FoodResourcesReport actual = resourcesUseCase.getResourcesReport();

    assertEquals(0, actual.expired.getQuantityFor(FoodType.WATER));
    assertEquals(0, actual.expired.getQuantityFor(FoodType.BURGER));
    assertEquals(0, actual.expired.getQuantityFor(FoodType.SALAD));
  }

  @Test
  public void
      givenSomeNotEmptyResources_whenGettingResourcesReport_thenShouldGetTheRightQuantitiesOfConsumed() {
    FoodResourcesReport actual = resourcesUseCase.getResourcesReport();

    assertEquals(0, actual.consumed.getQuantityFor(FoodType.WATER));
    assertEquals(0, actual.consumed.getQuantityFor(FoodType.BURGER));
    assertEquals(0, actual.consumed.getQuantityFor(FoodType.SALAD));
  }

  @Test
  public void givenAFoodResourcesQuantities_whenAddingAddResourcesAction_thenShouldGetGame() {
    FoodResourcesQuantities foodResourcesQuantities = new FoodResourcesQuantities(1, 2, 3);

    resourcesUseCase.addResourcesAction(foodResourcesQuantities);

    verify(gameRepository).getGame();
  }

  @Test
  public void givenAFoodResourcesQuantities_whenAddingAddResourcesAction_thenShouldUpdateGame() {
    FoodResourcesQuantities foodResourcesQuantities =
        new FoodResourcesQuantities(A_QTY_BURGER, A_QTY_SALAD, A_QTY_WATER);

    resourcesUseCase.addResourcesAction(foodResourcesQuantities);

    verify(gameRepository).update(A_GAME);
  }

  @Test
  public void
      givenAFoodResourcesQuantities_whenAddingAddResourcesAction_thenShouldCreateAnAction() {
    resourcesUseCase.addResourcesAction(actionFoodResourcesQuantities);

    verify(actionFactory)
        .createAddResourcesAction(actionFoodResourcesQuantities, A_GAME.getTurnNumber(), A_PANTRY);
  }
}
