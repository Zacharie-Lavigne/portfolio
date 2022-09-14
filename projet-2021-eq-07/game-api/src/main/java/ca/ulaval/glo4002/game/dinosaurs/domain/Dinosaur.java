package ca.ulaval.glo4002.game.dinosaurs.domain;

import ca.ulaval.glo4002.game.dinosaurs.domain.exceptions.InvalidAdultWeightException;
import ca.ulaval.glo4002.game.dinosaurs.domain.exceptions.InvalidBabyChangeWeightException;
import ca.ulaval.glo4002.game.dinosaurs.domain.exceptions.InvalidDinosaurWeightChangeException;
import ca.ulaval.glo4002.game.dinosaurs.domain.needs.NeedsCalculationStrategy;
import ca.ulaval.glo4002.game.dinosaurs.domain.needs.WaterConsumptionStrategy;
import ca.ulaval.glo4002.game.resources.domain.FoodResourcesQuantities;
import ca.ulaval.glo4002.game.resources.domain.food.FoodType;
import ca.ulaval.glo4002.game.resources.domain.food.Pantry;
import ca.ulaval.glo4002.game.resources.domain.food.WaterTank;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Dinosaur {

  private static final int BABY_GROWTH_INCREMENT = 33;
  private static final int ADULT_WEIGHT_THRESHOLD = 100;
  private final Gender gender;
  private final String name;
  private final DinosaurSpecies species;
  private final NeedsCalculationStrategy needsCalculationStrategy;
  private Weight weight;
  private Boolean isAlive;
  private Boolean isStarving;
  private String fatherName;
  private String motherName;
  private final WaterConsumptionStrategy waterConsumptionStrategy;

  public Dinosaur(
      Gender gender,
      String name,
      DinosaurSpecies species,
      Weight weight,
      NeedsCalculationStrategy needsCalculationStrategy,
      WaterConsumptionStrategy waterConsumptionStrategy,
      Boolean isAlive,
      Boolean isStarving,
      String fatherName,
      String motherName) {
    this.gender = gender;
    this.name = name;
    this.species = species;
    this.weight = weight;
    this.needsCalculationStrategy = needsCalculationStrategy;
    this.waterConsumptionStrategy = waterConsumptionStrategy;
    this.isAlive = isAlive;
    this.isStarving = isStarving;
    this.fatherName = fatherName;
    this.motherName = motherName;
  }

  public Dinosaur(
      Gender gender,
      String name,
      DinosaurSpecies species,
      NeedsCalculationStrategy needsCalculationStrategy,
      WaterConsumptionStrategy waterConsumptionStrategy,
      Weight weight) {
    this(
        gender,
        name,
        species,
        needsCalculationStrategy,
        waterConsumptionStrategy,
        weight,
        null,
        null);
    if (weight.getIntValue() < ADULT_WEIGHT_THRESHOLD) {
      throw new InvalidAdultWeightException();
    }
  }

  public Dinosaur(
      Gender gender,
      String name,
      DinosaurSpecies species,
      NeedsCalculationStrategy needsCalculationStrategy,
      WaterConsumptionStrategy waterConsumptionStrategy,
      Weight weight,
      String fatherName,
      String motherName) {
    this(
        gender,
        name,
        species,
        weight,
        needsCalculationStrategy,
        waterConsumptionStrategy,
        true,
        true,
        fatherName,
        motherName);
  }

  public void die() {
    this.isAlive = false;
  }

  public void consumes(Pantry pantry, FoodType foodType, WaterTank waterTank) {
    FoodResourcesQuantities needs = this.needsCalculationStrategy.calculateNeedsFor(this);
    this.consumeFood(pantry, foodType, needs.getQuantityFor(foodType));
    this.consumeWater(pantry, needs.getQuantityFor(FoodType.WATER), waterTank);
  }

  private void consumeFood(Pantry pantry, FoodType foodType, int foodNeeds) {
    int consumedFood = pantry.consumeFreshFoodResources(foodType, foodNeeds);
    if (consumedFood < foodNeeds) {
      this.die();
    }
  }

  private void consumeWater(Pantry pantry, int waterNeeds, WaterTank waterTank) {
    int consumed = this.waterConsumptionStrategy.consumeWater(pantry, waterNeeds, waterTank);
    if (!this.waterConsumptionStrategy.isWaterNeedsSatisfied(waterNeeds, consumed)) {
      this.die();
    }
  }

  public int getStrength() {
    return Double.valueOf(
            Math.ceil(
                this.weight.getIntValue()
                    * this.species.getStrengthFactor()
                    * this.gender.getStrengthFactor()))
        .intValue();
  }

  public int getWeightInKilograms() {
    return this.weight.getIntValue();
  }

  public DinosaurSpecies getSpecies() {
    return this.species;
  }

  public ConsumptionPreference getConsumptionPreference() {
    return this.species.getConsumptionPreference();
  }

  public Gender getGender() {
    return this.gender;
  }

  public String getName() {
    return this.name;
  }

  public boolean isAlive() {
    return this.isAlive;
  }

  public String getFatherName() {
    return this.fatherName;
  }

  public String getMotherName() {
    return this.motherName;
  }

  public int getStarvingFactor() {
    if (this.isStarving) {
      return 2;
    }
    return 1;
  }

  public boolean hasParents() {
    return this.fatherName != null && this.motherName != null;
  }

  private void becomeAdult() {
    this.motherName = null;
    this.fatherName = null;
  }

  public void starve() {
    this.isStarving = true;
  }

  public void changeWeight(DeltaWeight deltaWeight) {
    this.weight = this.weight.changeWeight(deltaWeight);
  }

  public void removeStarving() {
    this.isStarving = false;
  }

  public void grow() {
    if (this.hasParents()) {
      this.weight = this.weight.changeWeight(BABY_GROWTH_INCREMENT);
      if (this.weight.getIntValue() >= ADULT_WEIGHT_THRESHOLD) {
        this.becomeAdult();
      }
    }
  }

  public void validateWeightChange(DeltaWeight deltaWeight) {
    if (this.hasParents()) {
      throw new InvalidBabyChangeWeightException();
    }
    Weight newWeight = this.weight.changeWeight(deltaWeight);
    if (newWeight.getIntValue() < ADULT_WEIGHT_THRESHOLD) {
      throw new InvalidDinosaurWeightChangeException();
    }
  }
}
