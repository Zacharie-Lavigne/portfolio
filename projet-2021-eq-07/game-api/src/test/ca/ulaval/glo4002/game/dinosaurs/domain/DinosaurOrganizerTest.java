package ca.ulaval.glo4002.game.dinosaurs.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import ca.ulaval.glo4002.game.dinosaurs.domain.needs.CarnivoreNeedsCalculationStrategy;
import ca.ulaval.glo4002.game.dinosaurs.domain.needs.HerbivoreNeedsCalculationStrategy;
import ca.ulaval.glo4002.game.dinosaurs.domain.needs.NeedsCalculationStrategy;
import ca.ulaval.glo4002.game.dinosaurs.domain.needs.OmnivoreNeedsCalculationStrategy;
import ca.ulaval.glo4002.game.dinosaurs.domain.needs.WaterConsumptionStrategy;
import io.vavr.collection.List;
import org.junit.jupiter.api.Test;

class DinosaurOrganizerTest {
  private final WaterConsumptionStrategy waterConsumptionStrategy = mock(WaterConsumptionStrategy.class);

  private final DinosaurOrganizer dinosaurOrganizer = new DinosaurOrganizer();

  private final String FIRST_DINOSAUR_NAME = "Pauly";
  private final String SECOND_DINOSAUR_NAME = "Paula";

  private final NeedsCalculationStrategy A_CARNIVORE_NEEDS_CALCULATION_STRATEGY =
      new CarnivoreNeedsCalculationStrategy();
  private final NeedsCalculationStrategy AN_OMNIVORE_NEEDS_CALCULATION_STRATEGY =
      new OmnivoreNeedsCalculationStrategy();
  private final NeedsCalculationStrategy AN_HERBIVORE_NEEDS_CALCULATION_STRATEGY =
      new HerbivoreNeedsCalculationStrategy();


  private final Weight A_WEIGHT = Weight.fromKilograms(1200);
  private final Weight A_HEAVIER_WEIGHT = Weight.fromKilograms(2800);

  private final Dinosaur FIRST_CARNIVOROUS_DINOSAUR = new DinosaurBuilder()
      .withGender(Gender.MALE)
      .withName(FIRST_DINOSAUR_NAME)
      .withSpecies(DinosaurSpecies.VELOCIRAPTOR)
      .withNeedsCalculationStrategy(A_CARNIVORE_NEEDS_CALCULATION_STRATEGY)
      .withWaterConsumptionStrategy(waterConsumptionStrategy)
      .withWeight(A_WEIGHT)
      .build();

  private final Dinosaur SECOND_CARNIVOROUS_DINOSAUR = new DinosaurBuilder()
      .withGender(Gender.MALE)
      .withName(SECOND_DINOSAUR_NAME)
      .withSpecies(DinosaurSpecies.VELOCIRAPTOR)
      .withNeedsCalculationStrategy(A_CARNIVORE_NEEDS_CALCULATION_STRATEGY)
      .withWaterConsumptionStrategy(waterConsumptionStrategy)
      .withWeight(A_WEIGHT)
      .build();

  private final Dinosaur A_STRONGER_OMNIVOROUS_DINOSAUR = new DinosaurBuilder()
      .withGender(Gender.FEMALE)
      .withName(FIRST_DINOSAUR_NAME)
      .withSpecies(DinosaurSpecies.ORNITHOMIMUS)
      .withNeedsCalculationStrategy(AN_OMNIVORE_NEEDS_CALCULATION_STRATEGY)
      .withWaterConsumptionStrategy(waterConsumptionStrategy)
      .withWeight(A_HEAVIER_WEIGHT)
      .build();

  private final Dinosaur FIRST_HERBIVORE_DINOSAUR = new DinosaurBuilder()
      .withGender(Gender.MALE)
      .withName(FIRST_DINOSAUR_NAME)
      .withSpecies(DinosaurSpecies.ANKYLOSAURUS)
      .withNeedsCalculationStrategy(AN_HERBIVORE_NEEDS_CALCULATION_STRATEGY)
      .withWaterConsumptionStrategy(waterConsumptionStrategy)
      .withWeight(A_WEIGHT)
      .build();

  private final Dinosaur SECOND_HERBIVORE_DINOSAUR = new DinosaurBuilder()
      .withGender(Gender.MALE)
      .withName(SECOND_DINOSAUR_NAME)
      .withSpecies(DinosaurSpecies.ANKYLOSAURUS)
      .withNeedsCalculationStrategy(AN_HERBIVORE_NEEDS_CALCULATION_STRATEGY)
      .withWaterConsumptionStrategy(waterConsumptionStrategy)
      .withWeight(A_WEIGHT)
      .build();

  @Test
  public void
  givenTwoCarnivorousDinosaursWithSameStrength_whenSortingCarnivoreAndOmnivoreDinosaurs_thenShouldOrderByAlphabeticalOrder() {
    List<Dinosaur> actual =
        dinosaurOrganizer.sortBurgerEatersByStrongestToWeakest(
            List.of(FIRST_CARNIVOROUS_DINOSAUR, SECOND_CARNIVOROUS_DINOSAUR));

    assertEquals(List.of(SECOND_CARNIVOROUS_DINOSAUR, FIRST_CARNIVOROUS_DINOSAUR), actual);
  }

  @Test
  public void
      givenTwoCarnivorousAndOneOmnivorousDinosaursWithDifferentStrength_whenSortingCarnivoreAndOmnivoreDinosaurs_thenShouldOrderByStrongestToWeakest() {
    List<Dinosaur> actual =
        dinosaurOrganizer.sortBurgerEatersByStrongestToWeakest(
            List.of(
                FIRST_CARNIVOROUS_DINOSAUR,
                SECOND_CARNIVOROUS_DINOSAUR,
                A_STRONGER_OMNIVOROUS_DINOSAUR));

    assertEquals(
        List.of(
            A_STRONGER_OMNIVOROUS_DINOSAUR,
            SECOND_CARNIVOROUS_DINOSAUR,
            FIRST_CARNIVOROUS_DINOSAUR),
        actual);
  }

  @Test
  public void
      givenTwoHerbivorousAndOneOmnivorousDinosaursWithDifferentStrength_whenSortingHerbivoreAndOmnivoreDinosaurs_thenShouldOrderByWeakestToStrongest() {
    List<Dinosaur> actual =
        dinosaurOrganizer.sortSaladEatersByWeakestToStrongest(
            List.of(
                A_STRONGER_OMNIVOROUS_DINOSAUR,
                FIRST_HERBIVORE_DINOSAUR,
                SECOND_HERBIVORE_DINOSAUR));

    assertEquals(
        List.of(
            FIRST_HERBIVORE_DINOSAUR, SECOND_HERBIVORE_DINOSAUR, A_STRONGER_OMNIVOROUS_DINOSAUR),
        actual);
  }

  @Test
  public void
      givenTwoHerbivorousWithSameStrength_whenSortingHerbivoreAndOmnivoreDinosaurs_thenShouldOrderByReverseAlphabeticalOrder() {
    List<Dinosaur> actual =
        dinosaurOrganizer.sortSaladEatersByWeakestToStrongest(
            List.of(FIRST_HERBIVORE_DINOSAUR, SECOND_HERBIVORE_DINOSAUR));

    assertEquals(List.of(FIRST_HERBIVORE_DINOSAUR, SECOND_HERBIVORE_DINOSAUR), actual);
  }
}
