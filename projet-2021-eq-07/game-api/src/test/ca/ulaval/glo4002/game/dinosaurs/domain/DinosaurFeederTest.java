package ca.ulaval.glo4002.game.dinosaurs.domain;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4002.game.dinosaurs.domain.needs.CarnivoreNeedsCalculationStrategy;
import ca.ulaval.glo4002.game.dinosaurs.domain.needs.HerbivoreNeedsCalculationStrategy;
import ca.ulaval.glo4002.game.dinosaurs.domain.needs.OmnivoreNeedsCalculationStrategy;
import ca.ulaval.glo4002.game.dinosaurs.domain.needs.OmnivoreWaterConsumptionStrategy;
import ca.ulaval.glo4002.game.resources.domain.food.FoodType;
import ca.ulaval.glo4002.game.resources.domain.food.Pantry;
import ca.ulaval.glo4002.game.resources.domain.food.WaterTank;
import io.vavr.collection.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DinosaurFeederTest {
  private final DinosaurOrganizer dinosaurOrganizer = mock(DinosaurOrganizer.class);
  private final DinosaurFeeder dinosaurFeeder = new DinosaurFeeder(dinosaurOrganizer);

  private final WaterTank BURGER_EATER_TANK = new WaterTank();
  private final WaterTank SALAD_EATER_TANK = new WaterTank();
  private final Pantry A_PANTRY = mock(Pantry.class);
  private final Weight A_WEIGHT = Weight.fromKilograms(10);
  private final Dinosaur A_CARNIVOROUS_DINOSAUR =
      spy(
          new DinosaurBuilder()
              .isAlive(true)
              .withSpecies(DinosaurSpecies.ALLOSAURUS)
              .withNeedsCalculationStrategy(new CarnivoreNeedsCalculationStrategy())
              .build());

  private final Dinosaur A_HERBIVOROUS_DINOSAUR =
      spy(
          new DinosaurBuilder()
              .isAlive(true)
              .withSpecies(DinosaurSpecies.STEGOSAURUS)
              .withNeedsCalculationStrategy(new HerbivoreNeedsCalculationStrategy())
              .build());

  private final Dinosaur AN_OMNIVORE_DINOSAUR =
      spy(
          new DinosaurBuilder()
              .isAlive(true)
              .withSpecies(DinosaurSpecies.GIGANTORAPTOR)
              .withNeedsCalculationStrategy(new OmnivoreNeedsCalculationStrategy())
              .withWaterConsumptionStrategy(new OmnivoreWaterConsumptionStrategy())
              .build());

  private final List<Dinosaur> DINOSAURS =
      List.of(A_CARNIVOROUS_DINOSAUR, A_HERBIVOROUS_DINOSAUR, AN_OMNIVORE_DINOSAUR);

  private final DinosaurHerd A_DINOSAUR_HERD = spy(new DinosaurHerd(DINOSAURS));

  @BeforeEach
  public void setUp() {
    when(dinosaurOrganizer.sortBurgerEatersByStrongestToWeakest(DINOSAURS))
        .thenReturn(List.of(A_CARNIVOROUS_DINOSAUR, AN_OMNIVORE_DINOSAUR));
    when(dinosaurOrganizer.sortSaladEatersByWeakestToStrongest(DINOSAURS))
        .thenReturn(List.of(A_HERBIVOROUS_DINOSAUR, AN_OMNIVORE_DINOSAUR));
    when(A_PANTRY.getBurgerEatersWaterTank()).thenReturn(BURGER_EATER_TANK);
    when(A_PANTRY.getSaladEatersWaterTank()).thenReturn(SALAD_EATER_TANK);
  }

  @Test
  public void givenADinosaurHerd_whenFeeding_thenShouldGetAllDinosaurs() {
    dinosaurFeeder.feed(A_DINOSAUR_HERD, A_PANTRY);

    verify(A_DINOSAUR_HERD).getDinosaurs();
  }

  @Test
  public void givenADinosaurHerd_whenFeeding_thenShouldSortBurgerEatersByStrongestToWeakest() {
    dinosaurFeeder.feed(A_DINOSAUR_HERD, A_PANTRY);

    verify(dinosaurOrganizer).sortBurgerEatersByStrongestToWeakest(DINOSAURS);
  }

  @Test
  public void givenADinosaurHerd_whenFeeding_thenShouldSortSaladEatersByWeakestToStrongest() {
    dinosaurFeeder.feed(A_DINOSAUR_HERD, A_PANTRY);

    verify(dinosaurOrganizer).sortSaladEatersByWeakestToStrongest(DINOSAURS);
  }

  @Test
  public void givenAPantry_whenFeeding_thenShouldSeparateWaterDistribution() {
    dinosaurFeeder.feed(A_DINOSAUR_HERD, A_PANTRY);

    verify(A_PANTRY).separateWaterDistribution();
  }

  @Test
  public void
      givenAPantryAndADinosaurHerd_whenFeeding_thenShouldFeedEveryCarnivorousDinosaursWithBurgersAndBurgerEaterTank() {
    dinosaurFeeder.feed(A_DINOSAUR_HERD, A_PANTRY);

    verify(A_CARNIVOROUS_DINOSAUR).consumes(A_PANTRY, FoodType.BURGER, BURGER_EATER_TANK);
  }

  @Test
  public void
      givenAPantryAndADinosaurHerd_whenFeeding_thenShouldFeedEveryOmnivorousDinosaursWithBurgersAndBurgerEaterTank() {
    dinosaurFeeder.feed(A_DINOSAUR_HERD, A_PANTRY);

    verify(AN_OMNIVORE_DINOSAUR).consumes(A_PANTRY, FoodType.BURGER, BURGER_EATER_TANK);
  }

  @Test
  public void
      givenAPantryAndADinosaurHerd_whenFeeding_thenShouldNotFeedEveryCarnivorousDinosaursWithSaladsAndSaladEaterTank() {
    dinosaurFeeder.feed(A_DINOSAUR_HERD, A_PANTRY);

    verify(A_CARNIVOROUS_DINOSAUR, never()).consumes(A_PANTRY, FoodType.SALAD, SALAD_EATER_TANK);
  }

  @Test
  public void
      givenAPantryAndADinosaurHerd_whenFeeding_thenShouldFeedEveryHerbivorousDinosaursWithSaladsAndSaladEaterTank() {
    dinosaurFeeder.feed(A_DINOSAUR_HERD, A_PANTRY);

    verify(A_HERBIVOROUS_DINOSAUR).consumes(A_PANTRY, FoodType.SALAD, SALAD_EATER_TANK);
  }

  @Test
  public void
      givenAPantryAndADinosaurHerd_whenFeeding_thenShouldFeedEveryOmnivorousDinosaursWithSaladsAndSaladEaterTank() {
    dinosaurFeeder.feed(A_DINOSAUR_HERD, A_PANTRY);

    verify(AN_OMNIVORE_DINOSAUR).consumes(A_PANTRY, FoodType.SALAD, SALAD_EATER_TANK);
  }

  @Test
  public void
      givenAPantryAndADinosaurHerd_whenFeeding_thenShouldNotFeedEveryHerbivorousDinosaursWithBurgersAndBurgerEaterTank() {
    dinosaurFeeder.feed(A_DINOSAUR_HERD, A_PANTRY);

    verify(A_HERBIVOROUS_DINOSAUR, never()).consumes(A_PANTRY, FoodType.BURGER, BURGER_EATER_TANK);
  }

  @Test
  public void givenAStarvingDinosaur_whenFeeding_thenShouldNotBeStarving() {
    A_CARNIVOROUS_DINOSAUR.starve();
    int starvingFactorBeforeFeeding = A_CARNIVOROUS_DINOSAUR.getStarvingFactor();

    dinosaurFeeder.feed(A_DINOSAUR_HERD, A_PANTRY);

    int starvingFactorAfterFeeding = A_CARNIVOROUS_DINOSAUR.getStarvingFactor();
    assertTrue(starvingFactorAfterFeeding < starvingFactorBeforeFeeding);
  }
}
