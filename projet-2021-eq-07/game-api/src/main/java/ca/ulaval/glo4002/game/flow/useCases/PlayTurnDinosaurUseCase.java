package ca.ulaval.glo4002.game.flow.useCases;

import ca.ulaval.glo4002.game.dinosaurs.domain.BabyDinosaurGrower;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurFamilyHandler;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurFeeder;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurHerd;
import ca.ulaval.glo4002.game.resources.domain.food.Pantry;
import org.springframework.stereotype.Component;

@Component
public class PlayTurnDinosaurUseCase {
  private final DinosaurFeeder dinosaurFeeder;
  private final DinosaurFamilyHandler dinosaurFamilyHandler;
  private final BabyDinosaurGrower babyDinosaurGrower;

  public PlayTurnDinosaurUseCase(
      DinosaurFeeder dinosaurFeeder,
      DinosaurFamilyHandler dinosaurFamilyHandler,
      BabyDinosaurGrower babyDinosaurGrower) {
    this.dinosaurFeeder = dinosaurFeeder;
    this.dinosaurFamilyHandler = dinosaurFamilyHandler;
    this.babyDinosaurGrower = babyDinosaurGrower;
  }

  public void playTurn(DinosaurHerd dinosaurHerd, Pantry pantry) {
    this.dinosaurFeeder.feed(dinosaurHerd, pantry);
    dinosaurHerd.removeDead();
    this.dinosaurFamilyHandler.getRidOfOrphans(dinosaurHerd);
    dinosaurHerd.removeDead();
    this.babyDinosaurGrower.growDinosaurs(dinosaurHerd);
  }
}
