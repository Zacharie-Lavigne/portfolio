package ca.ulaval.glo4002.game.breeding.domain;

import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurSpecies;
import ca.ulaval.glo4002.game.dinosaurs.domain.Gender;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class BreedingResult {

  public final DinosaurSpecies dinosaurSpecies;
  public final Gender gender;
  public final String name;

  public BreedingResult(Gender gender, DinosaurSpecies dinosaurSpecies, String name) {
    this.dinosaurSpecies = dinosaurSpecies;
    this.gender = gender;
    this.name = name;
  }
}
