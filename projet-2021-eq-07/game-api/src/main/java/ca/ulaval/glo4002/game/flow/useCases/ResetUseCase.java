package ca.ulaval.glo4002.game.flow.useCases;

import ca.ulaval.glo4002.game.combat.domain.SumoCombatOrganizer;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurHerd;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurHerdRepository;
import ca.ulaval.glo4002.game.flow.domain.Game;
import ca.ulaval.glo4002.game.flow.domain.GameRepository;
import ca.ulaval.glo4002.game.resources.domain.PantryRepository;
import ca.ulaval.glo4002.game.resources.domain.food.Pantry;
import org.springframework.stereotype.Component;

@Component
public class ResetUseCase {
  private final GameRepository gameRepository;
  private final DinosaurHerdRepository dinosaurHerdRepository;
  private final PantryRepository pantryRepository;
  private final SumoCombatOrganizer sumoCombatOrganizer;

  public ResetUseCase(
      GameRepository gameRepository,
      DinosaurHerdRepository dinosaurHerdRepository,
      PantryRepository pantryRepository,
      SumoCombatOrganizer sumoCombatOrganizer) {
    this.gameRepository = gameRepository;
    this.dinosaurHerdRepository = dinosaurHerdRepository;
    this.pantryRepository = pantryRepository;
    this.sumoCombatOrganizer = sumoCombatOrganizer;
  }

  public void reset() {
    Game game = this.gameRepository.getGame();
    Pantry pantry = this.pantryRepository.getPantry();
    DinosaurHerd dinosaurHerd = this.dinosaurHerdRepository.getDinosaurHerd();

    game.resetGame();
    pantry.deleteAll();
    dinosaurHerd.deleteAll();
    this.sumoCombatOrganizer.clearCombatRegistry();

    this.gameRepository.update(game);
    this.pantryRepository.update(pantry);
    this.dinosaurHerdRepository.update(dinosaurHerd);
  }
}
