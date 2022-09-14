package ca.ulaval.glo4002.game.actions.domain;

import ca.ulaval.glo4002.game.breeding.domain.Breeder;
import ca.ulaval.glo4002.game.breeding.domain.BreedingFamilyInformation;
import ca.ulaval.glo4002.game.dinosaurs.domain.DeltaWeight;
import ca.ulaval.glo4002.game.dinosaurs.domain.Dinosaur;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurFactory;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurHerd;
import ca.ulaval.glo4002.game.resources.domain.FoodResourcesQuantities;
import ca.ulaval.glo4002.game.resources.domain.food.Pantry;
import org.springframework.stereotype.Component;

@Component
public class ActionFactory {

  public AddFoodResourcesAction createAddResourcesAction(
      FoodResourcesQuantities quantities, int turnNumberCreation, Pantry pantry) {
    return new AddFoodResourcesAction(quantities, pantry, turnNumberCreation);
  }

  public AddDinosaurAction createAddDinosaurAction(Dinosaur dinosaur, DinosaurHerd dinosaurHerd) {
    return new AddDinosaurAction(dinosaur, dinosaurHerd);
  }

  public BreedDinosaursAction createBreedDinosaursAction(
      BreedingFamilyInformation breedingFamilyInformation,
      Breeder breeder,
      DinosaurHerd dinosaurHerd,
      DinosaurFactory dinosaurFactory) {
    return new BreedDinosaursAction(
        breedingFamilyInformation, breeder, dinosaurHerd, dinosaurFactory);
  }

  public SumoCombatAction createSumoCombatAction(Dinosaur challenger, Dinosaur challengee) {
    return new SumoCombatAction(challenger, challengee);
  }

  public ChangeDinosaurWeightAction createChangeDinosaurWeightAction(
      Dinosaur dinosaur, DeltaWeight deltaWeight) {
    return new ChangeDinosaurWeightAction(dinosaur, deltaWeight);
  }
}
