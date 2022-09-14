package ca.ulaval.glo4002.game.breeding.infrastructure.assemblers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.ulaval.glo4002.game.breeding.domain.BreedingResult;
import ca.ulaval.glo4002.game.breeding.infrastructure.VeterinaryBreedingResponse;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurSpecies;
import ca.ulaval.glo4002.game.dinosaurs.domain.Gender;
import org.junit.jupiter.api.Test;

class BreedingResultAssemblerTest {
  private final BreedingResultAssembler breedingResultAssembler = new BreedingResultAssembler();

  @Test
  public void
      givenABabyNameAndAVeterinaryBreedingResponse_whenAssemblingToDomain_thenShouldAssembleWithCorrespondingParameters() {
    String babyName = "Celine";
    String dinosaursSpecies = "Brachiosaurus";
    String genderAbbreviation = "m";
    VeterinaryBreedingResponse veterinaryBreedingResponse =
        new VeterinaryBreedingResponse(dinosaursSpecies, genderAbbreviation);

    BreedingResult expected =
        new BreedingResult(Gender.MALE, DinosaurSpecies.BRACHIOSAURUS, babyName);

    BreedingResult actual = breedingResultAssembler.toDomain(babyName, veterinaryBreedingResponse);

    assertEquals(expected, actual);
  }
}
