package ca.ulaval.glo4002.game.dinosaurs.domain.needs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.ulaval.glo4002.game.dinosaurs.domain.Dinosaur;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurBuilder;
import ca.ulaval.glo4002.game.dinosaurs.domain.Weight;
import ca.ulaval.glo4002.game.resources.domain.FoodResourcesQuantities;
import ca.ulaval.glo4002.game.resources.domain.food.FoodType;
import org.junit.jupiter.api.Test;

class OmnivoreNeedsCalculationStrategyTest {
  private final OmnivoreNeedsCalculationStrategy omnivoreNeedsCalculationStrategy =
      new OmnivoreNeedsCalculationStrategy();

  private final Weight A_WEIGHT = Weight.fromKilograms(1000);
  private final Dinosaur AN_HUNGRIER_DINOSAUR = new DinosaurBuilder().withWeight(A_WEIGHT).isStarving(true).build();
  private final Dinosaur A_NORMAL_HUNGRY_DINOSAUR = new DinosaurBuilder().withWeight(A_WEIGHT).isStarving(false).build();

  @Test
  public void
      givenAnHungrierDinosaurWithWeightOf1_whenCalculateNeeds_thenShouldRoundUpNeededAmountOfWater() {
    Dinosaur dinosaur = new DinosaurBuilder().withWeight(Weight.fromKilograms(1)).isStarving(true).build();
    FoodResourcesQuantities needs = omnivoreNeedsCalculationStrategy.calculateNeedsFor(dinosaur);

    int expectedWater = 2;

    assertEquals(expectedWater, needs.getQuantityFor(FoodType.WATER));
  }

  @Test
  public void
      givenAnHungrierDinosaurWithWeightOf1_whenCalculateNeeds_thenShouldRoundUpNeededAmountOfSalads() {
    Dinosaur dinosaur = new DinosaurBuilder().withWeight(Weight.fromKilograms(1)).isStarving(true).build();
    FoodResourcesQuantities needs = omnivoreNeedsCalculationStrategy.calculateNeedsFor(dinosaur);

    int expectedSalads = 1;

    assertEquals(expectedSalads, needs.getQuantityFor(FoodType.SALAD));
  }

  @Test
  public void
      givenAnHungrierDinosaurWithAndWeightOf1_whenCalculateNeeds_thenShouldRoundUpNeededAmountOfBurgers() {
    Dinosaur dinosaur = new DinosaurBuilder().withWeight(Weight.fromKilograms(1)).isStarving(true).build();
    FoodResourcesQuantities needs = omnivoreNeedsCalculationStrategy.calculateNeedsFor(dinosaur);

    int expectedBurgers = 1;

    assertEquals(expectedBurgers, needs.getQuantityFor(FoodType.BURGER));
  }

  @Test
  public void givenHungrierAndAWeight_whenComputingNeeds_thenShouldGetTheCorrectAmountOfWater() {
    FoodResourcesQuantities needs =
        omnivoreNeedsCalculationStrategy.calculateNeedsFor(AN_HUNGRIER_DINOSAUR);

    int expectedWater = 1200;

    assertEquals(needs.getQuantityFor(FoodType.WATER), expectedWater);
  }

  @Test
  public void givenAndAWeight_whenComputingNeeds_thenShouldGetTheCorrectAmountOfWater() {
    FoodResourcesQuantities needs =
        omnivoreNeedsCalculationStrategy.calculateNeedsFor(A_NORMAL_HUNGRY_DINOSAUR);

    int expectedWater = 600;

    assertEquals(needs.getQuantityFor(FoodType.WATER), expectedWater);
  }

  @Test
  public void
      givenAnHungrierAndAWeight_whenComputingNeeds_thenShouldGetTheCorrectAmountOfBurgers() {
    FoodResourcesQuantities needs =
        omnivoreNeedsCalculationStrategy.calculateNeedsFor(AN_HUNGRIER_DINOSAUR);

    int expectedBurgers = 1;

    assertEquals(needs.getQuantityFor(FoodType.BURGER), expectedBurgers);
  }

  @Test
  public void givenAWeight_whenComputingNeeds_thenShouldGetTheCorrectAmountOfBurgers() {
    FoodResourcesQuantities needs =
        omnivoreNeedsCalculationStrategy.calculateNeedsFor(A_NORMAL_HUNGRY_DINOSAUR);

    int expectedBurgers = 1;

    assertEquals(needs.getQuantityFor(FoodType.BURGER), expectedBurgers);
  }

  @Test
  public void
      givenAnHungrierAndAWeight_whenComputingNeeds_thenShouldReturnTheCorrectAmountOfSalads() {
    FoodResourcesQuantities needs =
        omnivoreNeedsCalculationStrategy.calculateNeedsFor(AN_HUNGRIER_DINOSAUR);

    int expectedSalads = 3;

    assertEquals(expectedSalads, needs.getQuantityFor(FoodType.SALAD));
  }

  @Test
  public void givenAWeight_whenComputingNeeds_thenShouldReturnTheCorrectAmountOfSalads() {
    FoodResourcesQuantities needs =
        omnivoreNeedsCalculationStrategy.calculateNeedsFor(A_NORMAL_HUNGRY_DINOSAUR);

    int expectedSalads = 2;

    assertEquals(expectedSalads, needs.getQuantityFor(FoodType.SALAD));
  }
}
