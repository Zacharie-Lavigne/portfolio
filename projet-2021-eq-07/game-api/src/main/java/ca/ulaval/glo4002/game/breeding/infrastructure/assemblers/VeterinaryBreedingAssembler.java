package ca.ulaval.glo4002.game.breeding.infrastructure.assemblers;

import ca.ulaval.glo4002.game.breeding.domain.BreedingFamilyInformation;
import ca.ulaval.glo4002.game.breeding.infrastructure.VeterinaryBreedingDto;
import org.springframework.stereotype.Component;

@Component
public class VeterinaryBreedingAssembler {
  public VeterinaryBreedingDto toDto(BreedingFamilyInformation breedingFamilyInformation) {
    return new VeterinaryBreedingDto(
        breedingFamilyInformation.getFather().getSpecies().getLabel(),
        breedingFamilyInformation.getMother().getSpecies().getLabel());
  }
}
