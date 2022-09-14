package ca.ulaval.glo4002.game.breeding.infrastructure;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class VeterinaryBreedingDto {
  public String fatherSpecies;
  public String motherSpecies;

  public VeterinaryBreedingDto(String fatherSpecies, String motherSpecies) {
    this.fatherSpecies = fatherSpecies;
    this.motherSpecies = motherSpecies;
  }
}
