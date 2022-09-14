package ca.ulaval.glo4002.game.dinosaurs.domain;

import ca.ulaval.glo4002.game.resources.domain.food.FoodType;
import ca.ulaval.glo4002.game.resources.domain.food.Pantry;
import ca.ulaval.glo4002.game.resources.domain.food.WaterTank;
import io.vavr.collection.List;
import org.springframework.stereotype.Component;

@Component
public class DinosaurFeeder {
  private final DinosaurOrganizer dinosaurOrganizer;

  public DinosaurFeeder(DinosaurOrganizer dinosaurOrganizer) {
    this.dinosaurOrganizer = dinosaurOrganizer;
  }

  public void feed(DinosaurHerd dinosaurHerd, Pantry pantry) {
    List<Dinosaur> dinosaurs = dinosaurHerd.getDinosaurs();

    List<Dinosaur> sortedCarnivorousAndOmnivorousDinosaurs =
        this.dinosaurOrganizer.sortBurgerEatersByStrongestToWeakest(dinosaurs);

    List<Dinosaur> sortedHerbivorousAndOmnivorousDinosaurs =
        this.dinosaurOrganizer.sortSaladEatersByWeakestToStrongest(dinosaurs);

    pantry.separateWaterDistribution();

    WaterTank burgerEatersWaterTank = pantry.getBurgerEatersWaterTank();
    WaterTank saladEatersWaterTank = pantry.getSaladEatersWaterTank();

    sortedCarnivorousAndOmnivorousDinosaurs.forEach(
        dinosaur -> dinosaur.consumes(pantry, FoodType.BURGER, burgerEatersWaterTank));

    sortedHerbivorousAndOmnivorousDinosaurs.forEach(
        dinosaur -> dinosaur.consumes(pantry, FoodType.SALAD, saladEatersWaterTank));

    dinosaurs.forEach(Dinosaur::removeStarving);
  }
}
