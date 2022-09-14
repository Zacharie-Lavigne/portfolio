package ca.ulaval.glo4002.game.dinosaurs.domain;

public interface DinosaurHerdRepository {

  DinosaurHerd getDinosaurHerd();

  void update(DinosaurHerd dinosaurHerd);
}
