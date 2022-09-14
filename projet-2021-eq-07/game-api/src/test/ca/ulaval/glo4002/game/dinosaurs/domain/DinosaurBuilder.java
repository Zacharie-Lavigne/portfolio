package ca.ulaval.glo4002.game.dinosaurs.domain;

import ca.ulaval.glo4002.game.dinosaurs.domain.needs.DefaultWaterConsumptionStrategy;
import ca.ulaval.glo4002.game.dinosaurs.domain.needs.HerbivoreNeedsCalculationStrategy;
import ca.ulaval.glo4002.game.dinosaurs.domain.needs.NeedsCalculationStrategy;
import ca.ulaval.glo4002.game.dinosaurs.domain.needs.WaterConsumptionStrategy;

public class DinosaurBuilder {
  private Gender gender;
  private String name;
  private DinosaurSpecies species;
  private Weight weight;
  private NeedsCalculationStrategy needsCalculationStrategy;
  private WaterConsumptionStrategy waterConsumptionStrategy;
  private Boolean isAlive;
  private Boolean isStarving;
  private String fatherName;
  private String motherName;

  public DinosaurBuilder() {
    this.gender = Gender.MALE;
    this.name = "Bob";
    this.species = DinosaurSpecies.ANKYLOSAURUS;
    this.weight = Weight.fromKilograms(21);
    this.needsCalculationStrategy = new HerbivoreNeedsCalculationStrategy();
    this.waterConsumptionStrategy = new DefaultWaterConsumptionStrategy();
    this.isAlive = true;
    this.isStarving = true;
    this.fatherName = null;
    this.motherName = null;
  }

  public DinosaurBuilder withSpecies(DinosaurSpecies specie) {
    this.species = specie;
    return this;
  }

  public DinosaurBuilder withParents() {
    this.fatherName = "papa";
    this.motherName = "maman";
    return this;
  }

  public DinosaurBuilder withParents(String fatherName, String motherName) {
    this.fatherName = fatherName;
    this.motherName = motherName;
    return this;
  }

  public DinosaurBuilder withName(String name) {
    this.name = name;
    return this;
  }

  public DinosaurBuilder withWeight(Weight weight) {
    this.weight = weight;
    return this;
  }

  public DinosaurBuilder isAlive(boolean isAlive) {
    this.isAlive = isAlive;
    return this;
  }

  public DinosaurBuilder withGender(Gender gender) {
    this.gender = gender;
    return this;
  }

  public DinosaurBuilder isStarving(boolean isHungrier) {
    this.isStarving = isHungrier;
    return this;
  }

  public DinosaurBuilder withNeedsCalculationStrategy(
      NeedsCalculationStrategy needsCalculationStrategy) {
    this.needsCalculationStrategy = needsCalculationStrategy;
    return this;
  }

  public DinosaurBuilder withWaterConsumptionStrategy(
      WaterConsumptionStrategy waterConsumptionStrategy) {
    this.waterConsumptionStrategy = waterConsumptionStrategy;
    return this;
  }

  public Dinosaur build() {
    return new Dinosaur(
        this.gender,
        this.name,
        this.species,
        this.weight,
        this.needsCalculationStrategy,
        this.waterConsumptionStrategy,
        this.isAlive,
        this.isStarving,
        this.fatherName,
        this.motherName);
  }
}
