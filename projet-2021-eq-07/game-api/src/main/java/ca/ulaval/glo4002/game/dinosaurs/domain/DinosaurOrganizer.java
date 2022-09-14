package ca.ulaval.glo4002.game.dinosaurs.domain;

import io.vavr.collection.List;
import java.util.Comparator;
import org.springframework.stereotype.Component;

@Component
public class DinosaurOrganizer {

  public List<Dinosaur> sortBurgerEatersByStrongestToWeakest(List<Dinosaur> dinosaurs) {
    List<Dinosaur> dinosaursSortedByNameInAlphabeticalOrder =
        dinosaurs.filter(this::isDinosaurBurgerEater).sortBy(Dinosaur::getName);

    return dinosaursSortedByNameInAlphabeticalOrder.sortBy(
        Comparator.reverseOrder(), Dinosaur::getStrength);
  }

  public List<Dinosaur> sortSaladEatersByWeakestToStrongest(List<Dinosaur> dinosaurs) {
    List<Dinosaur> dinosaursSortedByNameInReverseAlphabeticalOrder =
        dinosaurs
            .filter(this::isDinosaurSaladEater)
            .sortBy(Comparator.reverseOrder(), Dinosaur::getName);

    return dinosaursSortedByNameInReverseAlphabeticalOrder.sortBy(Dinosaur::getStrength);
  }

  private boolean isDinosaurBurgerEater(Dinosaur dinosaur) {
    return dinosaur.getConsumptionPreference().equals(ConsumptionPreference.CARNIVOROUS)
        || dinosaur.getConsumptionPreference().equals(ConsumptionPreference.OMNIVOROUS);
  }

  private boolean isDinosaurSaladEater(Dinosaur dinosaur) {
    return dinosaur.getConsumptionPreference().equals(ConsumptionPreference.HERBIVOROUS)
        || dinosaur.getConsumptionPreference().equals(ConsumptionPreference.OMNIVOROUS);
  }
}
