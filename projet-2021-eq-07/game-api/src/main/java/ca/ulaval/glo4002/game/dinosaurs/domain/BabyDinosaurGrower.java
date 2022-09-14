package ca.ulaval.glo4002.game.dinosaurs.domain;

import org.springframework.stereotype.Component;

@Component
public class BabyDinosaurGrower {
  public void growDinosaurs(DinosaurHerd dinosaurHerd) {
    dinosaurHerd.getAliveDinosaurs().forEach(Dinosaur::grow);
  }
}
