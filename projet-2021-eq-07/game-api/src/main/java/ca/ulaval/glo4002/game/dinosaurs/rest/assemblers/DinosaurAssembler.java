package ca.ulaval.glo4002.game.dinosaurs.rest.assemblers;

import ca.ulaval.glo4002.game.dinosaurs.domain.Dinosaur;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurFactory;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurSpecies;
import ca.ulaval.glo4002.game.dinosaurs.domain.Gender;
import ca.ulaval.glo4002.game.dinosaurs.domain.exceptions.InvalidAdultWeightException;
import ca.ulaval.glo4002.game.dinosaurs.rest.DinosaurRequest;
import ca.ulaval.glo4002.game.dinosaurs.rest.DinosaurResponse;
import org.springframework.stereotype.Component;

@Component
public class DinosaurAssembler {
  private static final int MIN_ACCEPTED_WEIGHT_IN_KILOGRAMS = 0;
  private final DinosaurFactory dinosaurFactory;

  public DinosaurAssembler(DinosaurFactory dinosaurFactory) {
    this.dinosaurFactory = dinosaurFactory;
  }

  public void validateDinosaurRequest(DinosaurRequest dinosaurRequest) {
    if (dinosaurRequest.weight <= MIN_ACCEPTED_WEIGHT_IN_KILOGRAMS) {
      throw new InvalidWeightException();
    }
  }

  public DinosaurResponse toResponse(Dinosaur dinosaur) {
    return new DinosaurResponse(
        dinosaur.getName(),
        dinosaur.getWeightInKilograms(),
        dinosaur.getGender().getAbbreviation(),
        dinosaur.getSpecies().getLabel());
  }

  public Dinosaur toDomain(DinosaurRequest dinosaurRequest) {
    this.validateDinosaurRequest(dinosaurRequest);
    try {
      return this.dinosaurFactory.create(
          Gender.fromAbbreviation(dinosaurRequest.gender),
          dinosaurRequest.name,
          DinosaurSpecies.fromLabel(dinosaurRequest.species),
          dinosaurRequest.weight);
    } catch (InvalidAdultWeightException e) {
      throw new InvalidWeightException();
    }
  }
}
