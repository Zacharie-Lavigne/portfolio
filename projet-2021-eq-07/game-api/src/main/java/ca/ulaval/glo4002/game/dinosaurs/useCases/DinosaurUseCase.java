package ca.ulaval.glo4002.game.dinosaurs.useCases;

import ca.ulaval.glo4002.game.actions.domain.ActionFactory;
import ca.ulaval.glo4002.game.dinosaurs.domain.DeltaWeight;
import ca.ulaval.glo4002.game.dinosaurs.domain.Dinosaur;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurHerd;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurHerdRepository;
import ca.ulaval.glo4002.game.dinosaurs.useCases.exceptions.InvalidDinosaurNameException;
import ca.ulaval.glo4002.game.flow.domain.Game;
import ca.ulaval.glo4002.game.flow.domain.GameRepository;
import io.vavr.collection.List;
import org.springframework.stereotype.Component;

@Component
public class DinosaurUseCase {
  private final ActionFactory actionFactory;
  private final DinosaurHerdRepository dinosaurHerdRepository;
  private final GameRepository gameRepository;

  public DinosaurUseCase(
      ActionFactory actionFactory,
      DinosaurHerdRepository dinosaurHerdRepository,
      GameRepository gameRepository) {
    this.actionFactory = actionFactory;
    this.dinosaurHerdRepository = dinosaurHerdRepository;
    this.gameRepository = gameRepository;
  }

  public void createAddDinosaurAction(Dinosaur dinosaur) {
    DinosaurHerd dinosaurHerd = this.dinosaurHerdRepository.getDinosaurHerd();
    Game game = this.gameRepository.getGame();

    if (dinosaurHerd.containsName(dinosaur.getName())) {
      throw new InvalidDinosaurNameException();
    } else {
      game.addAction(this.actionFactory.createAddDinosaurAction(dinosaur, dinosaurHerd));
      this.gameRepository.update(game);
    }
  }

  public List<Dinosaur> getAllDinosaurs() {
    DinosaurHerd dinosaurHerd = this.dinosaurHerdRepository.getDinosaurHerd();
    return dinosaurHerd.getAliveDinosaurs();
  }

  public Dinosaur getDinosaurByName(String dinoName) {
    DinosaurHerd dinosaurHerd = this.dinosaurHerdRepository.getDinosaurHerd();
    return dinosaurHerd.getByName(dinoName);
  }

  public void createChangeDinosaurWeightAction(String name, DeltaWeight deltaWeight) {
    DinosaurHerd dinosaurHerd = this.dinosaurHerdRepository.getDinosaurHerd();
    Game game = this.gameRepository.getGame();

    Dinosaur dinosaur = dinosaurHerd.getByName(name);
    dinosaur.validateWeightChange(deltaWeight);

    game.addAction(this.actionFactory.createChangeDinosaurWeightAction(dinosaur, deltaWeight));

    this.gameRepository.update(game);
  }
}
