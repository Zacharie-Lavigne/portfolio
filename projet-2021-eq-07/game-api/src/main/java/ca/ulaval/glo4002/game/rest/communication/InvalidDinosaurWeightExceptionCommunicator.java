package ca.ulaval.glo4002.game.rest.communication;

import ca.ulaval.glo4002.game.dinosaurs.domain.exceptions.InvalidBabyChangeWeightException;
import ca.ulaval.glo4002.game.dinosaurs.domain.exceptions.InvalidDinosaurWeightChangeException;
import io.vavr.collection.HashMap;
import org.springframework.stereotype.Component;

@Component
public class InvalidDinosaurWeightExceptionCommunicator extends ExceptionCommunicator {
  public InvalidDinosaurWeightExceptionCommunicator() {
    this.titles =
        HashMap.of(
            InvalidDinosaurWeightChangeException.class.getSimpleName(),
            "INVALID_WEIGHT_CHANGE",
            InvalidBabyChangeWeightException.class.getSimpleName(),
            "INVALID_BABY_WEIGHT_CHANGE");
    this.descriptions =
        HashMap.of(
            InvalidDinosaurWeightChangeException.class.getSimpleName(),
            "The specified weight loss must not make the dinosaur weight less than 100 kg.",
            InvalidBabyChangeWeightException.class.getSimpleName(),
            "The weight of a baby dinosaur can not be changed.");
  }
}
