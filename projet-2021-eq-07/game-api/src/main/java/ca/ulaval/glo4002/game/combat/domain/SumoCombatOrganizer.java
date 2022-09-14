package ca.ulaval.glo4002.game.combat.domain;

import ca.ulaval.glo4002.game.actions.domain.ActionFactory;
import ca.ulaval.glo4002.game.actions.domain.SumoCombatAction;
import ca.ulaval.glo4002.game.combat.domain.exceptions.DinosaurAlreadyParticipatingException;
import ca.ulaval.glo4002.game.combat.domain.exceptions.MaximumCombatsReachedException;
import ca.ulaval.glo4002.game.combat.domain.exceptions.UnallowedSumoSpeciesException;
import ca.ulaval.glo4002.game.dinosaurs.domain.Dinosaur;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurSpecies;
import io.vavr.collection.List;
import org.springframework.stereotype.Component;

@Component
public class SumoCombatOrganizer {
  private static final int MAX_COMBATS_PER_TURN = 2;
  private final ActionFactory actionFactory;
  private final List<DinosaurSpecies> unallowedSpecies = List.of(DinosaurSpecies.TYRANNOSAURUS_REX);
  private List<SumoCombatAction> currentTurnSumoCombatActions = List.empty();
  private List<Dinosaur> currentTurnFighters = List.empty();

  public SumoCombatOrganizer(ActionFactory actionFactory) {
    this.actionFactory = actionFactory;
  }

  public SumoCombatAction createSumoCombat(Dinosaur challenger, Dinosaur challengee) {
    this.canDinosaursCombat(challenger, challengee);

    SumoCombatAction sumoCombatAction =
        this.actionFactory.createSumoCombatAction(challenger, challengee);

    this.currentTurnSumoCombatActions = this.currentTurnSumoCombatActions.append(sumoCombatAction);
    this.currentTurnFighters = this.currentTurnFighters.appendAll(List.of(challengee, challenger));

    return sumoCombatAction;
  }

  public void clearCombatRegistry() {
    this.currentTurnSumoCombatActions = List.empty();
    this.currentTurnFighters = List.empty();
  }

  private void canDinosaursCombat(Dinosaur challenger, Dinosaur challengee) {
    if (this.currentTurnFighters.contains(challenger)
        || this.currentTurnFighters.contains(challengee)) {
      throw new DinosaurAlreadyParticipatingException();
    }

    if (this.currentTurnSumoCombatActions.length() >= MAX_COMBATS_PER_TURN) {
      throw new MaximumCombatsReachedException();
    }

    if (this.unallowedSpecies.contains(challenger.getSpecies())
        || this.unallowedSpecies.contains(challengee.getSpecies())) {
      throw new UnallowedSumoSpeciesException();
    }
  }
}
