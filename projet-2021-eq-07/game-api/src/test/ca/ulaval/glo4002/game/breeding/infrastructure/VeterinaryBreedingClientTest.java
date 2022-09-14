package ca.ulaval.glo4002.game.breeding.infrastructure;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4002.game.breeding.domain.Breeder;
import ca.ulaval.glo4002.game.breeding.domain.BreedingFamilyInformation;
import ca.ulaval.glo4002.game.breeding.domain.BreedingResult;
import ca.ulaval.glo4002.game.breeding.infrastructure.assemblers.BreedingResultAssembler;
import ca.ulaval.glo4002.game.breeding.infrastructure.assemblers.VeterinaryBreedingAssembler;
import ca.ulaval.glo4002.game.dinosaurs.domain.Dinosaur;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurBuilder;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurSpecies;
import ca.ulaval.glo4002.game.dinosaurs.domain.Gender;
import ca.ulaval.glo4002.game.dinosaurs.useCases.exceptions.InvalidDinosaurNameException;
import io.vavr.control.Option;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VeterinaryBreedingClientTest {
  private final BreedingResultAssembler breedingResultAssembler =
      mock(BreedingResultAssembler.class);
  private final VeterinaryBreedingAssembler veterinaryBreedingAssembler =
      mock(VeterinaryBreedingAssembler.class);
  private final VeterinaryHttpClient veterinaryHttpClient = mock(VeterinaryHttpClient.class);

  private final Breeder breeder =
      new VeterinaryBreedingClient(
          breedingResultAssembler, veterinaryBreedingAssembler, veterinaryHttpClient);

  private final String A_BABY_NAME = "diego";
  private final DinosaurSpecies A_DINOSAUR_SPECIES = DinosaurSpecies.ALLOSAURUS;
  private final Dinosaur A_DINOSAUR = new DinosaurBuilder().withSpecies(A_DINOSAUR_SPECIES).build();
  private final BreedingResult A_BREEDING_RESULT =
      new BreedingResult(Gender.MALE, A_DINOSAUR_SPECIES, A_BABY_NAME);
  private final VeterinaryBreedingDto A_VETERINARY_BREEDING_DTO =
      new VeterinaryBreedingDto(A_DINOSAUR_SPECIES.getLabel(), A_DINOSAUR_SPECIES.getLabel());
  private final BreedingFamilyInformation A_BREEDING_FAMILY_INFORMATION =
      new BreedingFamilyInformation(A_BABY_NAME, A_DINOSAUR, A_DINOSAUR);

  private final String AN_OFFSPRING = "Allosaurus";
  private final String A_GENDER = "f";
  private final VeterinaryBreedingResponse veterinaryBreedingResponse =
      new VeterinaryBreedingResponse(AN_OFFSPRING, A_GENDER);

  @BeforeEach
  public void setUp() {
    when(veterinaryBreedingAssembler.toDto(A_BREEDING_FAMILY_INFORMATION))
        .thenReturn(A_VETERINARY_BREEDING_DTO);
    when(veterinaryHttpClient.breedDinosaurs(A_VETERINARY_BREEDING_DTO))
        .thenReturn(Option.of(veterinaryBreedingResponse));
    when(breedingResultAssembler.toDomain(A_BABY_NAME, veterinaryBreedingResponse))
        .thenReturn(A_BREEDING_RESULT);
  }

  @Test
  public void givenABreedingFamilyInformation_whenBreeding_thenShouldBreedDinosaurs() {
    breeder.breedDinosaurs(A_BREEDING_FAMILY_INFORMATION);

    verify(veterinaryHttpClient).breedDinosaurs(A_VETERINARY_BREEDING_DTO);
  }

  @Test
  public void
      givenABreedingFamilyInformationAndASuccessfulBreeding_whenBreeding_thenShouldReturnBreedingResult() {
    Option<BreedingResult> breedingResult = breeder.breedDinosaurs(A_BREEDING_FAMILY_INFORMATION);

    assertEquals(A_BREEDING_RESULT, breedingResult.get());
  }

  @Test
  public void
      givenABreedingFamilyInformationAndANonSuccessfulBreeding_whenBreeding_thenShouldNotReturnBreedingResult() {
    when(veterinaryHttpClient.breedDinosaurs(A_VETERINARY_BREEDING_DTO)).thenReturn(Option.none());
    Option<BreedingResult> breedingResult = breeder.breedDinosaurs(A_BREEDING_FAMILY_INFORMATION);

    assertEquals(Option.none(), breedingResult);
  }

  @Test
  public void
      givenABreedingFamilyInformationAndANonSuccessfulBreeding_whenBreeding_thenShouldIgnoreThrownException() {
    when(veterinaryHttpClient.breedDinosaurs(A_VETERINARY_BREEDING_DTO))
        .thenReturn(Option.none())
        .thenThrow(new InvalidDinosaurNameException());

    assertDoesNotThrow(() -> breeder.breedDinosaurs(A_BREEDING_FAMILY_INFORMATION));
  }
}
