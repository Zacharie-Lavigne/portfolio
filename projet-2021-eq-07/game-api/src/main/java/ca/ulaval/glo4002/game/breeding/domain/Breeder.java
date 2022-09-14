package ca.ulaval.glo4002.game.breeding.domain;

import io.vavr.control.Option;

public interface Breeder {
  Option<BreedingResult> breedDinosaurs(BreedingFamilyInformation breedingFamilyInformation);
}
