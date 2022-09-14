package ca.ulaval.glo4002.game.breeding.infrastructure.assemblers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.ulaval.glo4002.game.breeding.domain.BreedingFamilyInformation;
import ca.ulaval.glo4002.game.breeding.infrastructure.VeterinaryBreedingDto;
import ca.ulaval.glo4002.game.dinosaurs.domain.Dinosaur;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurBuilder;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurSpecies;
import org.junit.jupiter.api.Test;

class VeterinaryBreedingAssemblerTest {
  private final VeterinaryBreedingAssembler veterinaryBreedingAssembler =
      new VeterinaryBreedingAssembler();

  @Test
  public void
      givenABreedingFamilyInformation_whenAssemblingToDto_thenShouldAssembleWithCorrespondingParameters() {
    String aBabyName = "Maggie";
    String aFatherName = "pablo";
    String aMotherName = "Ally";
    Dinosaur father =
        new DinosaurBuilder().withName(aFatherName).build();
    Dinosaur mother =
        new DinosaurBuilder().withName(aMotherName).build();
    BreedingFamilyInformation aBreedingFamilyInformation =
        new BreedingFamilyInformation(aBabyName, father, mother);

    VeterinaryBreedingDto expected =
        new VeterinaryBreedingDto(father.getSpecies().getLabel(), mother.getSpecies().getLabel());

    VeterinaryBreedingDto actual = veterinaryBreedingAssembler.toDto(aBreedingFamilyInformation);

    assertEquals(expected, actual);
  }
}
