package ca.ulaval.glo4002.game.dinosaurs.infrastructure.inMemory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.ulaval.glo4002.game.dinosaurs.domain.Dinosaur;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurBuilder;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurHerd;
import io.vavr.collection.List;
import org.junit.jupiter.api.Test;

class InMemoryDinosaurHerdRepositoryTest {
  private final InMemoryDinosaurHerdRepository inMemoryDinosaurRepository =
      new InMemoryDinosaurHerdRepository();

  @Test
  public void givenAnEmptyDinosaurHerd_whenUpdatingDinosaurHerd_thenShouldUpdate() {
    Dinosaur A_DINOSAUR = new DinosaurBuilder().build();
    List<Dinosaur> dinosaurs = List.of(A_DINOSAUR);
    DinosaurHerd dinosaurHerd = new DinosaurHerd(dinosaurs);

    inMemoryDinosaurRepository.update(dinosaurHerd);

    assertEquals(dinosaurHerd, inMemoryDinosaurRepository.getDinosaurHerd());
  }
}
