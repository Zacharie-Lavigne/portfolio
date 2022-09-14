package ca.ulaval.glo4002.game.breeding.rest.assemblers;

import ca.ulaval.glo4002.game.breeding.domain.BreedingAttempt;
import ca.ulaval.glo4002.game.breeding.rest.BreedingRequest;
import org.springframework.stereotype.Component;

@Component
public class BreedingAssembler {

  public BreedingAttempt toDomain(BreedingRequest breedingRequest) {
    return new BreedingAttempt(
        breedingRequest.name, breedingRequest.fatherName, breedingRequest.motherName);
  }
}
