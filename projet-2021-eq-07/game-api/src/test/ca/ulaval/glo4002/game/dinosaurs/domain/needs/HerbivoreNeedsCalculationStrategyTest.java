package ca.ulaval.glo4002.game.dinosaurs.domain.needs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.ulaval.glo4002.game.dinosaurs.domain.Dinosaur;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurBuilder;
import ca.ulaval.glo4002.game.dinosaurs.domain.Weight;
import ca.ulaval.glo4002.game.resources.domain.FoodResourcesQuantities;
import ca.ulaval.glo4002.game.resources.domain.food.FoodType;
import org.junit.jupiter.api.Test;

class HerbivoreNeedsCalculationStrategyTest {
  private final HerbivoreNeedsCalculationStrategy herbivoreNeedsCalculationStrategy =
      new HerbivoreNeedsCalculationStrategy();

  private final Weight A_WEIGHT = Weight.fromKilograms(1000);
  private final Dinosaur A_HUNGRIER_DINOSAUR = new DinosaurBuilder().withWeight(A_WEIGHT).isStarving(true).build();
  private final Dinosaur A_NORMAL_HUNGRY_DINOSAUR = new DinosaurBuilder().withWeight(A_WEIGHT).isStarving(false).build();

  @Test
  public void
      givenAnHungrierDinosaurWithWeightOf1_whenCalculateNeeds_thenShouldRoundUpNeededAmountOfWater() {
    Dinosaur dinosaur = new DinosaurBuilder().withWeight(Weight.fromKilograms(1)).isStarving(true).build();
    FoodResourcesQuantities needs = herbivoreNeedsCalculationStrategy.calculateNeedsFor(dinosaur);

    int expectedWater = 2;

    assertEquals(expectedWater, needs.getQuantityFor(FoodType.WATER));
  }

  @Test
  public void
      givenAnHungrierDinosaurWithWeightOf1_whenCalculateNeeds_thenShouldRoundUpNeededAmountOfSalad() {
    Dinosaur dinosaur = new DinosaurBuilder().withWeight(Weight.fromKilograms(1)).isStarving(true).build();
    FoodResourcesQuantities needs = herbivoreNeedsCalculationStrategy.calculateNeedsFor(dinosaur);

    int expectedSalads = 1;

    assertEquals(expectedSalads, needs.getQuantityFor(FoodType.SALAD));
  }

  @Test
  public void
      givenAnHungrierDinosaurWithWeight_whenComputingNeeds_thenShouldGetTheCorrectAmountOfWater() {
    FoodResourcesQuantities needs =
        this.herbivoreNeedsCalculationStrategy.calculateNeedsFor(A_HUNGRIER_DINOSAUR);

    int expectedWater = 1200;

    assertEquals(expectedWater, needs.getQuantityFor(FoodType.WATER));
  }

  @Test
  public void
      givenADinosaurWithAgeOf3AndAWeight_whenComputingNeeds_thenShouldGetTheCorrectAmountOfWater() {
    FoodResourcesQuantities needs =
        this.herbivoreNeedsCalculationStrategy.calculateNeedsFor(A_NORMAL_HUNGRY_DINOSAUR);

    int expectedWater = 600;

    assertEquals(expectedWater, needs.getQuantityFor(FoodType.WATER));
  }

  @Test
  public void
      givenADinosaurWithAgeOf0AndAWeight_whenComputingNeeds_thenShouldGetTheCorrectAmountOfSalads() {
    FoodResourcesQuantities needs =
        this.herbivoreNeedsCalculationStrategy.calculateNeedsFor(A_HUNGRIER_DINOSAUR);

    int expectedSalads = 5;

    assertEquals(expectedSalads, needs.getQuantityFor(FoodType.SALAD));
  }

  @Test
  public void
      givenADinosaurWithAge3f0AndAWeight_whenComputingNeeds_thenShouldGetTheCorrectAmountOfSalads() {
    FoodResourcesQuantities needs =
        this.herbivoreNeedsCalculationStrategy.calculateNeedsFor(A_NORMAL_HUNGRY_DINOSAUR);

    int expectedSalads = 3;

    assertEquals(expectedSalads, needs.getQuantityFor(FoodType.SALAD));
  }

  @Test
  public void givenA0Weight_whenComputingNeeds_thenShouldNotGetAnyBurger() {
    Dinosaur aDinosaur = new DinosaurBuilder().withWeight(Weight.fromKilograms(0)).isStarving(false).build();

    FoodResourcesQuantities needs =
        this.herbivoreNeedsCalculationStrategy.calculateNeedsFor(aDinosaur);

    assertEquals(0, needs.getQuantityFor(FoodType.BURGER));
  }
}
