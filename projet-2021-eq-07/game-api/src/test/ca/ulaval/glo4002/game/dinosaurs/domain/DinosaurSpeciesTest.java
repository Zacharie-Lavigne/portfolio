package ca.ulaval.glo4002.game.dinosaurs.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import ca.ulaval.glo4002.game.dinosaurs.domain.exceptions.InvalidSpeciesException;
import org.junit.jupiter.api.Test;

class DinosaurSpeciesTest {

  @Test
  public void givenValidDinosaurSpecies_thenShouldCreateSpecifiedSpecies() {
    DinosaurSpecies expectedSpecies = DinosaurSpecies.BRACHIOSAURUS;
    String aValidDinosaurSpecies = "Brachiosaurus";

    DinosaurSpecies actualSpecies = DinosaurSpecies.fromLabel(aValidDinosaurSpecies);

    assertEquals(expectedSpecies, actualSpecies);
  }

  @Test
  public void givenInvalidDinosaurSpecies_thenShouldThrowException() {
    String anInvalidDinosaurSpecies = "invalid";

    assertThrows(
        InvalidSpeciesException.class, () -> DinosaurSpecies.fromLabel(anInvalidDinosaurSpecies));
  }
}
