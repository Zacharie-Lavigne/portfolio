package ca.ulaval.glo4002.game.rest.communication;

import ca.ulaval.glo4002.game.dinosaurs.domain.exceptions.InvalidGenderException;
import ca.ulaval.glo4002.game.dinosaurs.domain.exceptions.InvalidSpeciesException;
import ca.ulaval.glo4002.game.dinosaurs.useCases.exceptions.InvalidDinosaurNameException;
import io.vavr.collection.HashMap;
import org.springframework.stereotype.Component;

@Component
public class InvalidDinosaurAttributeExceptionCommunicator extends ExceptionCommunicator {

  public InvalidDinosaurAttributeExceptionCommunicator() {
    this.titles =
        HashMap.of(
            InvalidGenderException.class.getSimpleName(), "INVALID_GENDER",
            InvalidSpeciesException.class.getSimpleName(), "INVALID_SPECIES",
            InvalidDinosaurNameException.class.getSimpleName(), "DUPLICATE_NAME");
    this.descriptions =
        HashMap.of(
            InvalidGenderException.class.getSimpleName(),
            "The specified gender must be \"m\" or \"f\".",
            InvalidSpeciesException.class.getSimpleName(),
            "The specified species is not supported.",
            InvalidDinosaurNameException.class.getSimpleName(),
            "The specified name already exists and must be unique.");
  }
}
