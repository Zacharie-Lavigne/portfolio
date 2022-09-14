package ca.ulaval.glo4002.game.rest.communication;

import ca.ulaval.glo4002.game.combat.domain.exceptions.DinosaurAlreadyParticipatingException;
import ca.ulaval.glo4002.game.combat.domain.exceptions.MaximumCombatsReachedException;
import ca.ulaval.glo4002.game.combat.domain.exceptions.UnallowedSumoSpeciesException;
import io.vavr.collection.HashMap;
import org.springframework.stereotype.Component;

@Component
public class SumoCombatExceptionCommunicator extends ExceptionCommunicator {
  public SumoCombatExceptionCommunicator() {
    this.titles =
        HashMap.of(
            DinosaurAlreadyParticipatingException.class.getSimpleName(),
            "DINOSAUR_ALREADY_PARTICIPATING",
            MaximumCombatsReachedException.class.getSimpleName(),
            "MAX_COMBATS_REACHED",
            UnallowedSumoSpeciesException.class.getSimpleName(),
            "ARMS_TOO_SHORT");
    this.descriptions =
        HashMap.of(
            DinosaurAlreadyParticipatingException.class.getSimpleName(),
            "Dinosaur is already participating.",
            MaximumCombatsReachedException.class.getSimpleName(),
            "Max number of combats has been reached.",
            UnallowedSumoSpeciesException.class.getSimpleName(),
            "Tyrannosaurus Rex can't participate.");
  }
}
