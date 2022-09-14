package ca.ulaval.glo4002.game.dinosaurs.domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4002.game.dinosaurs.domain.exceptions.InvalidBabyChangeWeightException;
import ca.ulaval.glo4002.game.dinosaurs.domain.exceptions.InvalidDinosaurWeightChangeException;
import ca.ulaval.glo4002.game.dinosaurs.domain.needs.NeedsCalculationStrategy;
import ca.ulaval.glo4002.game.dinosaurs.domain.needs.WaterConsumptionStrategy;
import ca.ulaval.glo4002.game.resources.domain.FoodResourcesQuantities;
import ca.ulaval.glo4002.game.resources.domain.food.FoodType;
import ca.ulaval.glo4002.game.resources.domain.food.Pantry;
import ca.ulaval.glo4002.game.resources.domain.food.WaterTank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DinosaurTest {

  public static final int ADULT_WEIGHT_THRESHOLD = 100;
  private final NeedsCalculationStrategy needsCalculationStrategy =
      mock(NeedsCalculationStrategy.class);
  private final WaterConsumptionStrategy A_WATER_CONSUMPTION_STRATEGY =
      mock(WaterConsumptionStrategy.class);
  private final WaterTank A_WATER_CONTAINER = mock(WaterTank.class);
  private final Pantry A_PANTRY = mock(Pantry.class);

  private final DinosaurSpecies A_CARNIVOROUS_DINOSAUR_SPECIES = DinosaurSpecies.ALLOSAURUS;
  private final DinosaurSpecies AN_HERBIVOROUS_DINOSAUR_SPECIES = DinosaurSpecies.ANKYLOSAURUS;
  private final Gender A_GENDER = Gender.FEMALE;
  private final String A_NAME = "Elvis";
  private final Weight A_WEIGHT = Weight.fromKilograms(1000);

  private final int A_BURGER_QTY = 2;
  private final int A_SALAD_QTY = 1;
  private final int A_WATER_QTY = 4;

  private final FoodResourcesQuantities SOME_NEEDS =
      new FoodResourcesQuantities(A_BURGER_QTY, A_SALAD_QTY, A_WATER_QTY);

  private final Dinosaur DINOSAUR = new DinosaurBuilder()
      .withGender(A_GENDER)
      .withName(A_NAME)
      .withSpecies(A_CARNIVOROUS_DINOSAUR_SPECIES)
      .withNeedsCalculationStrategy(needsCalculationStrategy)
      .withWaterConsumptionStrategy(A_WATER_CONSUMPTION_STRATEGY)
      .withWeight(A_WEIGHT)
      .build();

  private final Dinosaur AN_HERBIVOROUS_DINOSAUR = new DinosaurBuilder()
      .withGender(A_GENDER)
      .withName(A_NAME)
      .withSpecies(AN_HERBIVOROUS_DINOSAUR_SPECIES)
      .withNeedsCalculationStrategy(needsCalculationStrategy)
      .withWaterConsumptionStrategy(A_WATER_CONSUMPTION_STRATEGY)
      .withWeight(A_WEIGHT)
      .build();

  private final DeltaWeight A_DELTA_WEIGHT = new DeltaWeight(10);

  @BeforeEach
  public void setup() {
    when(needsCalculationStrategy.calculateNeedsFor(DINOSAUR)).thenReturn(SOME_NEEDS);
  }

  @Test
  public void givenADinosaur_whenGettingStrength_thenShouldCalculateStrength() {
    double expected =
        DINOSAUR.getWeightInKilograms()
            * DINOSAUR.getSpecies().getConsumptionPreference().getStrengthFactor()
            * DINOSAUR.getGender().getStrengthFactor();

    double actual = DINOSAUR.getStrength();

    assertEquals(expected, actual);
  }

  @Test
  public void givenAStarvingDinosaur_whenGetStarvingFactor_thenShouldReturn2() {
    int actualStarvingFactor = DINOSAUR.getStarvingFactor();

    assertEquals(2, actualStarvingFactor);
  }

  @Test
  public void givenANonStarvingDinosaur_whenGetStarvingFactor_thenShouldReturn1() {
    DINOSAUR.removeStarving();

    int actualStarvingFactor = DINOSAUR.getStarvingFactor();

    assertEquals(1, actualStarvingFactor);
  }

  @Test
  public void givenADinosaurAndAPantryWithNotEnoughFood_whenConsumes_thenDinosaurShouldDie() {
    when(A_PANTRY.consumeFreshFoodResources(any(FoodType.class), anyInt()))
        .thenReturn(A_BURGER_QTY - 1);

    DINOSAUR.consumes(A_PANTRY, FoodType.BURGER, A_WATER_CONTAINER);

    assertFalse(DINOSAUR.isAlive());
  }

  @Test
  public void givenADinosaurAndAPantryWithNotEnoughWater_whenConsumes_thenDinosaurShouldDie() {
    when(A_PANTRY.consumeFreshFoodResources(any(FoodType.class), anyInt()))
        .thenReturn(A_BURGER_QTY);
    when(A_WATER_CONSUMPTION_STRATEGY.isWaterNeedsSatisfied(anyInt(), anyInt())).thenReturn(false);

    DINOSAUR.consumes(A_PANTRY, FoodType.BURGER, A_WATER_CONTAINER);

    assertFalse(DINOSAUR.isAlive());
  }

  @Test
  public void
      givenADinosaurAndAPantryWithEnoughFoodAndWater_whenConsumes_thenDinosaurShouldNotDie() {
    when(A_PANTRY.consumeFreshFoodResources(any(FoodType.class), anyInt()))
        .thenReturn(A_BURGER_QTY);
    when(A_WATER_CONSUMPTION_STRATEGY.isWaterNeedsSatisfied(anyInt(), anyInt())).thenReturn(true);

    DINOSAUR.consumes(A_PANTRY, FoodType.BURGER, A_WATER_CONTAINER);

    assertTrue(DINOSAUR.isAlive());
  }

  @Test
  public void whenConsumes_thenDinosaurShouldConsumeWater() {
    DINOSAUR.consumes(A_PANTRY, FoodType.BURGER, A_WATER_CONTAINER);

    verify(A_WATER_CONSUMPTION_STRATEGY).consumeWater(A_PANTRY, A_WATER_QTY, A_WATER_CONTAINER);
  }

  @Test
  public void whenConsumesWater_thenShouldVerifyIfWaterNeedsSatisfy() {
    int waterNeeds = SOME_NEEDS.getQuantityFor(FoodType.WATER);
    when(needsCalculationStrategy.calculateNeedsFor(AN_HERBIVOROUS_DINOSAUR))
        .thenReturn(SOME_NEEDS);
    when(A_WATER_CONSUMPTION_STRATEGY.consumeWater(
            any(Pantry.class), any(Integer.class), any(WaterTank.class)))
        .thenReturn(waterNeeds);

    DINOSAUR.consumes(A_PANTRY, FoodType.BURGER, A_WATER_CONTAINER);

    verify(A_WATER_CONSUMPTION_STRATEGY)
        .isWaterNeedsSatisfied(SOME_NEEDS.getQuantityFor(FoodType.WATER), A_WATER_QTY);
  }

  @Test
  public void givenADinosaurWithNoParents_whenGettingIfDinosaurHasParents_thenShouldReturnFalse() {
    boolean hasParents = DINOSAUR.hasParents();

    assertFalse(hasParents);
  }

  @Test
  public void givenADinosaurWithParents_whenGettingIfDinosaurHasParents_thenShouldReturnTrue() {
    Dinosaur dinosaurWithParents = new DinosaurBuilder().withParents().build();

    boolean hasParents = dinosaurWithParents.hasParents();

    assertTrue(hasParents);
  }

  @Test
  public void givenADinosaurAndADeltaWeight_whenChangingWeight_thenShouldChangeWeight() {
    int aWeightInKg = 20;
    Weight aWeight = Weight.fromKilograms(aWeightInKg);
    DeltaWeight aDeltaWeight = new DeltaWeight(aWeightInKg);
    Dinosaur aDinosaur = new DinosaurBuilder().withWeight(aWeight).build();

    aDinosaur.changeWeight(aDeltaWeight);

    int expectedWeightInKg = aWeightInKg + aWeightInKg;
    int actualWeightInKg = aDinosaur.getWeightInKilograms();

    assertEquals(expectedWeightInKg, actualWeightInKg);
  }

  @Test
  public void
      givenAnAdultDinosaur_whenValidateWeightChange_thenShouldNotThrowInvalidBabyChangeWeightException() {
    assertDoesNotThrow(() -> DINOSAUR.validateWeightChange(A_DELTA_WEIGHT));
  }

  @Test
  public void
      givenABabyDinosaur_whenValidateWeightChange_thenShouldThrowInvalidBabyChangeWeightException() {
    Dinosaur aBabyDinosaur = new DinosaurBuilder().withParents().build();

    assertThrows(
        InvalidBabyChangeWeightException.class,
        () -> aBabyDinosaur.validateWeightChange(A_DELTA_WEIGHT));
  }

  @Test
  public void
      givenAnAdultDinosaurAndNegativeWeightChangeResultingWithWeightLowerThan100_whenValidateWeightChange_thenShouldThrowInvalidWeightChangeException() {
    int initialWeight = DINOSAUR.getWeightInKilograms();
    DeltaWeight deltaWeight = new DeltaWeight(ADULT_WEIGHT_THRESHOLD - 1 - initialWeight);

    assertThrows(
        InvalidDinosaurWeightChangeException.class,
        () -> DINOSAUR.validateWeightChange(deltaWeight));
  }

  @Test
  public void
      givenAnAdultDinosaurAndNegativeWeightChangeResultingWithWeightGreaterThan100_whenValidateWeightChange_thenShouldNotThrowInvalidWeightChangeException() {
    int initialWeight = DINOSAUR.getWeightInKilograms();

    DeltaWeight deltaWeight = new DeltaWeight(ADULT_WEIGHT_THRESHOLD - initialWeight);

    assertDoesNotThrow(() -> DINOSAUR.validateWeightChange(deltaWeight));
  }

  @Test
  public void givenABabyDinosaur_whenGrowing_thenShouldGrow() {
    int babyGrowthIncrement = 33;
    int anInitialWeightInKilogram = 50;
    Weight anInitialWeight = Weight.fromKilograms(anInitialWeightInKilogram);
    Dinosaur aBabyDinosaur =
        new DinosaurBuilder().withWeight(anInitialWeight).withParents().build();

    aBabyDinosaur.grow();

    int expectedNewWeight = anInitialWeightInKilogram + babyGrowthIncrement;

    assertEquals(expectedNewWeight, aBabyDinosaur.getWeightInKilograms());
  }

  @Test
  public void givenAnAdultDinosaur_whenGrowing_thenShouldNotGrow() {
    Weight anInitialWeight = Weight.fromKilograms(150);
    Dinosaur anAdultDinosaur = new DinosaurBuilder().withWeight(anInitialWeight).build();

    anAdultDinosaur.grow();

    assertEquals(anInitialWeight.getIntValue(), anAdultDinosaur.getWeightInKilograms());
  }

  @Test
  public void givenABabyDinosaurWithLowWeight_whenGrowing_thenShouldStillBeBaby() {
    Weight aLowInitialWeight = Weight.fromKilograms(3);
    Dinosaur aBabyDinosaur =
        new DinosaurBuilder().withWeight(aLowInitialWeight).withParents().build();

    aBabyDinosaur.grow();

    boolean isBaby = aBabyDinosaur.hasParents();

    assertTrue(isBaby);
  }

  @Test
  public void givenABabyDinosaurWithHighWeight_whenGrowing_thenShouldBecomeAnAdult() {
    Weight aHighInitialWeight = Weight.fromKilograms(99);
    Dinosaur aBabyDinosaur =
        new DinosaurBuilder().withWeight(aHighInitialWeight).withParents().build();

    aBabyDinosaur.grow();

    boolean isBaby = aBabyDinosaur.hasParents();

    assertFalse(isBaby);
  }
}
