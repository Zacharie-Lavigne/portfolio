package ca.ulaval.glo4002.game.combat.useCase;

import ca.ulaval.glo4002.game.actions.domain.SumoCombatAction;
import ca.ulaval.glo4002.game.combat.domain.SumoCombatAttempt;
import ca.ulaval.glo4002.game.combat.domain.SumoCombatOrganizer;
import ca.ulaval.glo4002.game.dinosaurs.domain.Dinosaur;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurHerd;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurHerdRepository;
import ca.ulaval.glo4002.game.flow.domain.Game;
import ca.ulaval.glo4002.game.flow.domain.GameRepository;
import org.springframework.stereotype.Component;

@Component
public class SumoCombatUseCase {
  private final DinosaurHerdRepository dinosaurHerdRepository;
  private final GameRepository gameRepository;
  private final SumoCombatOrganizer sumoCombatOrganizer;

  public SumoCombatUseCase(
      DinosaurHerdRepository dinosaurHerdRepository,
      GameRepository gameRepository,
      SumoCombatOrganizer sumoCombatOrganizer) {
    this.dinosaurHerdRepository = dinosaurHerdRepository;
    this.gameRepository = gameRepository;
    this.sumoCombatOrganizer = sumoCombatOrganizer;
  }

  public SumoCombatAction createSumoCombatAction(SumoCombatAttempt sumoCombatAttempt) {
    Game game = this.gameRepository.getGame();
    DinosaurHerd dinosaurHerd = this.dinosaurHerdRepository.getDinosaurHerd();

    Dinosaur challenger = dinosaurHerd.getByName(sumoCombatAttempt.getChallengerName());
    Dinosaur challengee = dinosaurHerd.getByName(sumoCombatAttempt.getChallengeeName());

    SumoCombatAction sumoCombatAction =
        this.sumoCombatOrganizer.createSumoCombat(challenger, challengee);

    game.addAction(sumoCombatAction);
    this.gameRepository.update(game);

    return sumoCombatAction;
  }
}
