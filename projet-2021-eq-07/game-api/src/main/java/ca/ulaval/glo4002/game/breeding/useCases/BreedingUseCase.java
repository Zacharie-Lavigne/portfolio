package ca.ulaval.glo4002.game.breeding.useCases;

import ca.ulaval.glo4002.game.actions.domain.ActionFactory;
import ca.ulaval.glo4002.game.breeding.domain.Breeder;
import ca.ulaval.glo4002.game.breeding.domain.BreedingAttempt;
import ca.ulaval.glo4002.game.breeding.domain.BreedingFamilyInformation;
import ca.ulaval.glo4002.game.breeding.useCases.exceptions.InvalidFatherException;
import ca.ulaval.glo4002.game.breeding.useCases.exceptions.InvalidMotherException;
import ca.ulaval.glo4002.game.dinosaurs.domain.Dinosaur;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurFactory;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurHerd;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurHerdRepository;
import ca.ulaval.glo4002.game.dinosaurs.domain.Gender;
import ca.ulaval.glo4002.game.dinosaurs.useCases.exceptions.InvalidDinosaurNameException;
import ca.ulaval.glo4002.game.flow.domain.Game;
import ca.ulaval.glo4002.game.flow.domain.GameRepository;
import org.springframework.stereotype.Component;

@Component
public class BreedingUseCase {
  private final ActionFactory actionFactory;
  private final DinosaurHerdRepository dinosaurHerdRepository;
  private final Breeder breeder;
  private final DinosaurFactory dinosaurFactory;
  private final GameRepository gameRepository;

  public BreedingUseCase(
      ActionFactory actionFactory,
      DinosaurHerdRepository dinosaurHerdRepository,
      Breeder breeder,
      DinosaurFactory dinosaurFactory,
      GameRepository gameRepository) {
    this.actionFactory = actionFactory;
    this.dinosaurHerdRepository = dinosaurHerdRepository;
    this.breeder = breeder;
    this.dinosaurFactory = dinosaurFactory;
    this.gameRepository = gameRepository;
  }

  public void createBreedDinosaurAction(BreedingAttempt breedingAttempt) {
    DinosaurHerd dinosaurHerd = this.dinosaurHerdRepository.getDinosaurHerd();
    Dinosaur father = dinosaurHerd.getByName(breedingAttempt.getFatherName());
    Dinosaur mother = dinosaurHerd.getByName(breedingAttempt.getMotherName());

    if (this.dinosaurHerdRepository.getDinosaurHerd().containsName(breedingAttempt.getName())) {
      throw new InvalidDinosaurNameException();
    }
    if (father.getGender().equals(Gender.FEMALE)) {
      throw new InvalidFatherException();
    }
    if (mother.getGender().equals(Gender.MALE)) {
      throw new InvalidMotherException();
    }

    BreedingFamilyInformation breedingFamilyInformation =
        new BreedingFamilyInformation(breedingAttempt.getName(), father, mother);

    Game game = this.gameRepository.getGame();

    game.addAction(
        this.actionFactory.createBreedDinosaursAction(
            breedingFamilyInformation, breeder, dinosaurHerd, dinosaurFactory));

    this.gameRepository.update(game);
  }
}
