package ca.ulaval.glo4002.game.dinosaurs.domain;

import org.springframework.stereotype.Component;

@Component
public class DinosaurFamilyHandler {

  public void getRidOfOrphans(DinosaurHerd dinosaurHerd) {
    dinosaurHerd
        .getAliveDinosaurs()
        .forEach(dinosaur -> killDinosaurIfBothParentsAreDead(dinosaurHerd, dinosaur));
  }

  private void killDinosaurIfBothParentsAreDead(DinosaurHerd dinosaurHerd, Dinosaur dinosaur) {
    if (dinosaur.hasParents()) {
      boolean fatherPresent = dinosaurHerd.containsName(dinosaur.getFatherName());
      boolean motherPresent = dinosaurHerd.containsName(dinosaur.getMotherName());

      if (!fatherPresent && !motherPresent) {
        dinosaur.die();
      }
    }
  }
}
