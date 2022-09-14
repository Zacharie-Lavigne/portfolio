package ca.ulaval.glo4002.game.dinosaurs.infrastructure.inMemory;

import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurHerd;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurHerdRepository;
import io.vavr.collection.List;
import org.springframework.stereotype.Component;

@Component
public class InMemoryDinosaurHerdRepository implements DinosaurHerdRepository {
  private DinosaurHerd dinosaurHerd = new DinosaurHerd(List.empty());

  @Override
  public DinosaurHerd getDinosaurHerd() {
    return this.dinosaurHerd;
  }

  @Override
  public void update(DinosaurHerd dinosaurHerd) {
    this.dinosaurHerd = dinosaurHerd;
  }
}
