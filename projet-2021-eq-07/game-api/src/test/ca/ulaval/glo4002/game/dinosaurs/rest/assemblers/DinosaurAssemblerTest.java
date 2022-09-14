package ca.ulaval.glo4002.game.dinosaurs.rest.assemblers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4002.game.dinosaurs.domain.Dinosaur;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurBuilder;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurFactory;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurSpecies;
import ca.ulaval.glo4002.game.dinosaurs.domain.Gender;
import ca.ulaval.glo4002.game.dinosaurs.domain.Weight;
import ca.ulaval.glo4002.game.dinosaurs.domain.needs.CarnivoreNeedsCalculationStrategy;
import ca.ulaval.glo4002.game.dinosaurs.domain.needs.DefaultWaterConsumptionStrategy;
import ca.ulaval.glo4002.game.dinosaurs.rest.DinosaurRequest;
import ca.ulaval.glo4002.game.dinosaurs.rest.DinosaurResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DinosaurAssemblerTest {
  private final DinosaurFactory dinosaurFactory = mock(DinosaurFactory.class);

  private final DinosaurAssembler dinosaurAssembler = new DinosaurAssembler(dinosaurFactory);

  private final DinosaurSpecies DINOSAUR_SPECIE = DinosaurSpecies.ALLOSAURUS;
  private final Gender GENDER = Gender.FEMALE;
  private final String NAME = "Elvis";
  private final int WEIGHT_IN_KILOGRAMS = 1000;
  private final Weight WEIGHT = Weight.fromKilograms(WEIGHT_IN_KILOGRAMS);
  private final Dinosaur DINOSAUR = new DinosaurBuilder()
      .withGender(GENDER)
      .withName(NAME)
      .withSpecies(DINOSAUR_SPECIE)
      .withWeight(WEIGHT)
      .build();

  @BeforeEach
  public void setUp() {
    when(dinosaurFactory.create(GENDER, NAME, DINOSAUR_SPECIE, WEIGHT_IN_KILOGRAMS)).thenReturn(DINOSAUR);
  }

  @Test
  public void
      givenDinosaur_whenAssemblingToResponse_thenShouldAssembleWithCorrespondingParameters() {

    DinosaurResponse expected =
        new DinosaurResponse(NAME, WEIGHT_IN_KILOGRAMS, GENDER.getAbbreviation(), DINOSAUR_SPECIE.getLabel());

    DinosaurResponse actual = dinosaurAssembler.toResponse(DINOSAUR);

    assertEquals(expected, actual);
  }

  @Test
  public void givenInvalidWeight_whenAssemblingToDomain_thenShouldThrowException() {
    int INVALID_WEIGHT_IN_KILOGRAMS = 0;
    DinosaurRequest dinosaurRequest =
        new DinosaurRequest(NAME, INVALID_WEIGHT_IN_KILOGRAMS, GENDER.getAbbreviation(), DINOSAUR_SPECIE.getLabel());
    assertThrows(
        InvalidWeightException.class,
        () -> dinosaurAssembler.toDomain(dinosaurRequest));
  }

  @Test
  public void
      givenDinosaurRequest_whenAssemblingToDomain_thenShouldAssembleWithCorrespondingParameters() {
    DinosaurRequest dinosaurRequest =
        new DinosaurRequest(NAME, WEIGHT_IN_KILOGRAMS, GENDER.getAbbreviation(), DINOSAUR_SPECIE.getLabel());

    Dinosaur actual = dinosaurAssembler.toDomain(dinosaurRequest);

    assertEquals(DINOSAUR, actual);
  }
}
