package ca.ulaval.glo4002.game.dinosaurs.rest.assemblers;

import ca.ulaval.glo4002.game.dinosaurs.domain.DeltaWeight;
import ca.ulaval.glo4002.game.dinosaurs.rest.DinosaurChangeWeightRequest;
import org.springframework.stereotype.Component;

@Component
public class DeltaWeightAssembler {

  public DeltaWeight toDomain(DinosaurChangeWeightRequest weightRequest) {
    return new DeltaWeight(weightRequest.deltaWeight);
  }
}
