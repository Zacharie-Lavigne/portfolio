package ca.ulaval.glo4002.game.breeding.rest.assemblers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.ulaval.glo4002.game.breeding.domain.BreedingAttempt;
import ca.ulaval.glo4002.game.breeding.rest.BreedingRequest;
import org.junit.jupiter.api.Test;

class BreedingAssemblerTest {
  private final BreedingAssembler breedingAssembler = new BreedingAssembler();

  @Test
  public void
      givenABreedingRequest_whenAssemblingToDomain_thenShouldAssembleWithTheCorrespondingParameters() {
    String aBabyName = "Tiger";
    String aFatherName = "Jean-René";
    String aMotherName = "Ginette";
    BreedingRequest breedingRequest = new BreedingRequest(aBabyName, aFatherName, aMotherName);

    BreedingAttempt expected = new BreedingAttempt(aBabyName, aFatherName, aMotherName);
    BreedingAttempt actual = breedingAssembler.toDomain(breedingRequest);

    assertEquals(expected, actual);
  }
}
