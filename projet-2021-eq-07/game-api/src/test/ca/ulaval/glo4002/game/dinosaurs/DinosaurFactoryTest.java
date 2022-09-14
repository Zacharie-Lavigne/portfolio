package ca.ulaval.glo4002.game.dinosaurs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.ulaval.glo4002.game.breeding.domain.BreedingFamilyInformation;
import ca.ulaval.glo4002.game.breeding.domain.BreedingResult;
import ca.ulaval.glo4002.game.dinosaurs.domain.Dinosaur;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurBuilder;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurFactory;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurSpecies;
import ca.ulaval.glo4002.game.dinosaurs.domain.Gender;
import ca.ulaval.glo4002.game.dinosaurs.domain.Weight;
import ca.ulaval.glo4002.game.dinosaurs.domain.needs.CarnivoreNeedsCalculationStrategy;
import ca.ulaval.glo4002.game.dinosaurs.domain.needs.DefaultWaterConsumptionStrategy;
import ca.ulaval.glo4002.game.dinosaurs.domain.needs.HerbivoreNeedsCalculationStrategy;
import ca.ulaval.glo4002.game.dinosaurs.domain.needs.NeedsCalculationStrategy;
import ca.ulaval.glo4002.game.dinosaurs.domain.needs.OmnivoreNeedsCalculationStrategy;
import ca.ulaval.glo4002.game.dinosaurs.domain.needs.OmnivoreWaterConsumptionStrategy;
import ca.ulaval.glo4002.game.dinosaurs.domain.needs.WaterConsumptionStrategy;
import org.junit.jupiter.api.Test;

class DinosaurFactoryTest {
  private final DinosaurFactory dinosaurFactory = new DinosaurFactory();

  private final NeedsCalculationStrategy A_NEEDS_CALCULATION_STRATEGY =
      new CarnivoreNeedsCalculationStrategy();
  private final WaterConsumptionStrategy waterConsumptionStrategy = new DefaultWaterConsumptionStrategy();
  private final String A_NAME = "Eric lapoint";
  private final Gender A_GENDER = Gender.MALE;
  private final DinosaurSpecies A_DINOSAUR_SPECIES = DinosaurSpecies.ALLOSAURUS;
  private final int A_WEIGHT_IN_KILOGRAMS = 125;
  private final Weight A_WEIGHT = Weight.fromKilograms(A_WEIGHT_IN_KILOGRAMS);

  @Test
  public void
      givenAGenderANameACarnivorousSpeciesAndAWeight_whenCreating_thenShouldCreateDinosaurWithCorrespondingNeedsCalculationStrategy() {
    Dinosaur expected = new DinosaurBuilder()
        .withGender(A_GENDER)
        .withName(A_NAME)
        .withSpecies(A_DINOSAUR_SPECIES)
        .withNeedsCalculationStrategy(A_NEEDS_CALCULATION_STRATEGY)
        .withWaterConsumptionStrategy(waterConsumptionStrategy)
        .withWeight(A_WEIGHT)
        .build();

    Dinosaur actual = dinosaurFactory.create(A_GENDER, A_NAME, A_DINOSAUR_SPECIES, A_WEIGHT_IN_KILOGRAMS);

    assertEquals(expected, actual);
  }

  @Test
  public void
      givenAGenderANameAHerbivorousSpeciesAndAWeight_whenCreating_thenShouldCreateDinosaurWithCorrespondingNeedsCalculationStrategy() {
    NeedsCalculationStrategy needsCalculationStrategy = new HerbivoreNeedsCalculationStrategy();
    DinosaurSpecies herbivorousSpecies = DinosaurSpecies.DIPLODOCUS;

    Dinosaur expected = new DinosaurBuilder()
        .withGender(A_GENDER)
        .withName(A_NAME)
        .withSpecies(herbivorousSpecies)
        .withNeedsCalculationStrategy(needsCalculationStrategy)
        .withWaterConsumptionStrategy(waterConsumptionStrategy)
        .withWeight(A_WEIGHT)
        .build();

    Dinosaur actual = dinosaurFactory.create(A_GENDER, A_NAME, herbivorousSpecies, A_WEIGHT_IN_KILOGRAMS);

    assertEquals(expected, actual);
  }

  @Test
  public void
      givenAGenderANameAOmnivorousSpeciesAndAWeight_whenCreating_thenShouldCreateDinosaurWithCorrespondingNeedsCalculationStrategy() {
    NeedsCalculationStrategy needsCalculationStrategy = new OmnivoreNeedsCalculationStrategy();
    WaterConsumptionStrategy waterConsumptionStrategy = new OmnivoreWaterConsumptionStrategy();
    DinosaurSpecies omnivorousSpecies = DinosaurSpecies.GIGANTORAPTOR;

    Dinosaur expected = new DinosaurBuilder()
        .withGender(A_GENDER)
        .withName(A_NAME)
        .withSpecies(omnivorousSpecies)
        .withNeedsCalculationStrategy(needsCalculationStrategy)
        .withWaterConsumptionStrategy(waterConsumptionStrategy)
        .withWeight(A_WEIGHT)
        .build();

    Dinosaur actual = dinosaurFactory.create(A_GENDER, A_NAME, omnivorousSpecies, A_WEIGHT_IN_KILOGRAMS);

    assertEquals(expected, actual);
  }

  @Test
  public void
  givenAGenderANameAOmnivorousSpeciesAndAWeight_whenCreating_thenShouldCreateDinosaurWithCorrespondingWaterConsumptionStrategy() {
    NeedsCalculationStrategy needsCalculationStrategy = new OmnivoreNeedsCalculationStrategy();
    WaterConsumptionStrategy waterConsumptionStrategy = new OmnivoreWaterConsumptionStrategy();
    DinosaurSpecies omnivorousSpecies = DinosaurSpecies.GIGANTORAPTOR;

    Dinosaur expected = new DinosaurBuilder()
            .withGender(A_GENDER)
            .withName(A_NAME)
            .withSpecies(omnivorousSpecies)
            .withNeedsCalculationStrategy(needsCalculationStrategy)
            .withWaterConsumptionStrategy(waterConsumptionStrategy)
            .withWeight(A_WEIGHT)
            .build();

    Dinosaur actual = dinosaurFactory.create(A_GENDER, A_NAME, omnivorousSpecies, A_WEIGHT_IN_KILOGRAMS);

    assertEquals(expected, actual);
  }

  @Test
  public void givenABreedingResult_whenCreatingFromBreedingResult_thenShouldCreateDinosaur() {
    Weight babyWeight = Weight.fromKilograms(1);
    String fatherName = "ticou";
    String motherName = "salsa";

    Dinosaur father = new DinosaurBuilder().withName(fatherName).build();
    Dinosaur mother = new DinosaurBuilder().withName(motherName).build();

    BreedingResult breedingResult = new BreedingResult(A_GENDER, A_DINOSAUR_SPECIES, A_NAME);
    BreedingFamilyInformation breedingFamilyInformation =
        new BreedingFamilyInformation(A_NAME, father, mother);

    Dinosaur expected = new DinosaurBuilder()
        .withGender(A_GENDER)
        .withName(A_NAME)
        .withSpecies(A_DINOSAUR_SPECIES)
        .withNeedsCalculationStrategy(A_NEEDS_CALCULATION_STRATEGY)
        .withWaterConsumptionStrategy(waterConsumptionStrategy)
        .withWeight(babyWeight)
        .withParents(fatherName, motherName)
        .build();

    Dinosaur actual = dinosaurFactory.createFromBreeding(breedingResult, breedingFamilyInformation);

    assertEquals(expected, actual);
  }
}
