package ca.ulaval.glo4002.game.breeding.infrastructure.assemblers;

import ca.ulaval.glo4002.game.breeding.domain.BreedingResult;
import ca.ulaval.glo4002.game.breeding.infrastructure.VeterinaryBreedingResponse;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurSpecies;
import ca.ulaval.glo4002.game.dinosaurs.domain.Gender;
import org.springframework.stereotype.Component;

@Component
public class BreedingResultAssembler {
  public BreedingResult toDomain(
      String babyName, VeterinaryBreedingResponse veterinaryBreedingResponse) {
    return new BreedingResult(
        Gender.fromAbbreviation(veterinaryBreedingResponse.gender),
        DinosaurSpecies.fromLabel(veterinaryBreedingResponse.offspring),
        babyName);
  }
}
