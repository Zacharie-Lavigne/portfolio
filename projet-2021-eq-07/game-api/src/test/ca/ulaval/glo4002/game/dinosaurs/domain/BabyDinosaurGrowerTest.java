package ca.ulaval.glo4002.game.dinosaurs.domain;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import io.vavr.collection.List;
import org.junit.jupiter.api.Test;

class BabyDinosaurGrowerTest {

  @Test
  public void givenADinosaurList_whenGrowingDinosaurs_thenShouldGrowAllDinosaurs() {
    BabyDinosaurGrower babyDinosaurGrower = new BabyDinosaurGrower();
    Dinosaur aDinosaur = spy(new DinosaurBuilder().build());
    Dinosaur aSecondDinosaur = spy(new DinosaurBuilder().build());
    List<Dinosaur> dinosaurs = List.of(aDinosaur, aSecondDinosaur);
    DinosaurHerd aDinosaurHerd = new DinosaurHerd(dinosaurs);

    babyDinosaurGrower.growDinosaurs(aDinosaurHerd);

    verify(aDinosaur).grow();
    verify(aSecondDinosaur).grow();
  }
}
