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
public class PlayTurnUseCase {
  private final DinosaurHerdRepository dinosaurHerdRepository;
  private final GameRepository gameRepository;
  private final PantryRepository pantryRepository;
  private final PlayTurnDinosaurUseCase playTurnDinosaurUseCase;
  private final PlayTurnResourcesUseCase playTurnResourcesUseCase;
  private final PlayTurnActionUseCase playTurnActionUseCase;
  private final SumoCombatOrganizer sumoCombatOrganizer;

  public PlayTurnUseCase(
      DinosaurHerdRepository dinosaurHerdRepository,
      GameRepository gameRepository,
      PantryRepository pantryRepository,
      PlayTurnDinosaurUseCase playTurnDinosaurUseCase,
      PlayTurnResourcesUseCase playTurnResourcesUseCase,
      PlayTurnActionUseCase playTurnActionUseCase,
      SumoCombatOrganizer sumoCombatOrganizer) {
    this.dinosaurHerdRepository = dinosaurHerdRepository;
    this.gameRepository = gameRepository;
    this.pantryRepository = pantryRepository;
    this.playTurnDinosaurUseCase = playTurnDinosaurUseCase;
    this.playTurnResourcesUseCase = playTurnResourcesUseCase;
    this.playTurnActionUseCase = playTurnActionUseCase;
    this.sumoCombatOrganizer = sumoCombatOrganizer;
  }

  public int playTurn() {
    Game currentGame = this.gameRepository.getGame();
    int currentTurnNumber = currentGame.getTurnNumber();
    Pantry pantry = this.pantryRepository.getPantry();
    DinosaurHerd dinosaurHerd = this.dinosaurHerdRepository.getDinosaurHerd();

    this.playTurnResourcesUseCase.playTurn(pantry, currentTurnNumber);
    this.playTurnActionUseCase.playTurn(currentGame);
    this.playTurnDinosaurUseCase.playTurn(dinosaurHerd, pantry);
    this.sumoCombatOrganizer.clearCombatRegistry();

    this.dinosaurHerdRepository.update(dinosaurHerd);
    this.pantryRepository.update(pantry);
    this.gameRepository.update(currentGame.executeTurn());

    return currentTurnNumber;
  }
}
