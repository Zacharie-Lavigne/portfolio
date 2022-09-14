package ca.ulaval.glo4002.game.dinosaurs.domain.needs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.ulaval.glo4002.game.dinosaurs.domain.Dinosaur;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurBuilder;
import ca.ulaval.glo4002.game.dinosaurs.domain.Weight;
import ca.ulaval.glo4002.game.resources.domain.FoodResourcesQuantities;
import ca.ulaval.glo4002.game.resources.domain.food.FoodType;
import org.junit.jupiter.api.Test;

class CarnivoreNeedsCalculationStrategyTest {
  private final CarnivoreNeedsCalculationStrategy carnivoreNeedsCalculationStrategy =
      new CarnivoreNeedsCalculationStrategy();

  private final Weight A_WEIGHT = Weight.fromKilograms(1000);
  private final Dinosaur AN_HUNGRIER_DINOSAUR = new DinosaurBuilder().withWeight(A_WEIGHT).isStarving(true).build();
  private final Dinosaur A_NORMAL_HUNGRY_DINOSAUR = new DinosaurBuilder().withWeight(A_WEIGHT).isStarving(false).build();

  @Test
  public void
      givenAnHungrierDinosaurWeightOf1_whenCalculateNeeds_thenShouldRoundUpNeededAmountOfWater() {
    Dinosaur dinosaur = new DinosaurBuilder().withWeight(Weight.fromKilograms(1)).isStarving(true).build();
    FoodResourcesQuantities needs = carnivoreNeedsCalculationStrategy.calculateNeedsFor(dinosaur);

    int expectedWater = 2;

    assertEquals(expectedWater, needs.getQuantityFor(FoodType.WATER));
  }

  @Test
  public void
      givenAnHungrierDinosaurWithWeightOf1_whenCalculateNeeds_thenShouldRoundUpNeededAmountOfBurgers() {
    Dinosaur dinosaur = new DinosaurBuilder().withWeight(Weight.fromKilograms(1)).isStarving(true).build();
    FoodResourcesQuantities needs = carnivoreNeedsCalculationStrategy.calculateNeedsFor(dinosaur);

    int expectedBurgers = 1;

    assertEquals(expectedBurgers, needs.getQuantityFor(FoodType.BURGER));
  }

  @Test
  public void
      givenAnHungrierDinosaurWithAWeightOf1000_whenCalculateNeeds_thenShouldGetTheCorrectAmountOfWater() {
    FoodResourcesQuantities needs =
        carnivoreNeedsCalculationStrategy.calculateNeedsFor(AN_HUNGRIER_DINOSAUR);

    int expectedWater = 1200;

    assertEquals(expectedWater, needs.getQuantityFor(FoodType.WATER));
  }

  @Test
  public void givenADinosaurWithAWeight_whenCalculateNeeds_thenShouldGetTheCorrectAmountOfWater() {
    FoodResourcesQuantities needs =
        carnivoreNeedsCalculationStrategy.calculateNeedsFor(A_NORMAL_HUNGRY_DINOSAUR);

    int expectedWater = 600;

    assertEquals(expectedWater, needs.getQuantityFor(FoodType.WATER));
  }

  @Test
  public void
      givenAnHungrierDinosaurWithAWeight_whenCalculateNeeds_thenShouldGetTheCorrectAmountOfBurgers() {
    FoodResourcesQuantities needs =
        carnivoreNeedsCalculationStrategy.calculateNeedsFor(AN_HUNGRIER_DINOSAUR);

    int expectedBurgers = 2;

    assertEquals(expectedBurgers, needs.getQuantityFor(FoodType.BURGER));
  }

  @Test
  public void
      givenADinosaurWithAWeight_whenCalculateNeeds_thenShouldGetTheCorrectAmountOfBurgers() {
    FoodResourcesQuantities needs =
        carnivoreNeedsCalculationStrategy.calculateNeedsFor(A_NORMAL_HUNGRY_DINOSAUR);

    int expectedBurgers = 1;

    assertEquals(expectedBurgers, needs.getQuantityFor(FoodType.BURGER));
  }

  @Test
  public void givenADinosaurWith0Weight_whenCalculateNeeds_thenShouldNotGetAnySalad() {
    Dinosaur aDinosaur = new DinosaurBuilder().withWeight(Weight.fromKilograms(0)).isStarving(false).build();

    FoodResourcesQuantities needs = carnivoreNeedsCalculationStrategy.calculateNeedsFor(aDinosaur);

    assertEquals(0, needs.getQuantityFor(FoodType.SALAD));
  }
}
