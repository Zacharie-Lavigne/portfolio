package ca.ulaval.glo4002.game.actions.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import ca.ulaval.glo4002.game.breeding.domain.Breeder;
import ca.ulaval.glo4002.game.breeding.domain.BreedingFamilyInformation;
import ca.ulaval.glo4002.game.dinosaurs.domain.DeltaWeight;
import ca.ulaval.glo4002.game.dinosaurs.domain.Dinosaur;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurBuilder;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurFactory;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurHerd;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurSpecies;
import ca.ulaval.glo4002.game.resources.WaterTanks;
import ca.ulaval.glo4002.game.resources.domain.FoodResourcesQuantities;
import ca.ulaval.glo4002.game.resources.domain.food.FreshFoodResources;
import ca.ulaval.glo4002.game.resources.domain.food.Pantry;
import ca.ulaval.glo4002.game.resources.domain.food.WaterDistribution;
import ca.ulaval.glo4002.game.resources.domain.food.WaterTank;
import io.vavr.collection.List;
import org.junit.jupiter.api.Test;

class ActionFactoryTest {

  private final DinosaurFactory dinosaurFactory = mock(DinosaurFactory.class);
  private final Breeder breeder = mock(Breeder.class);
  private final ActionFactory actionFactory = new ActionFactory();

  @Test
  public void
      givenAFoodResourcesQuantitiesAndATurnNumber_whenCreatingAnAddFoodRequestAction_thenShouldCreateAction() {
    int aTurnNumber = 1;
    FoodResourcesQuantities aFoodRequest = new FoodResourcesQuantities(2, 1, 4);
    WaterTank aWaterTank = new WaterTank();
    Pantry aPantry =
        new Pantry(
            new FreshFoodResources(new WaterDistribution(new WaterTanks(aWaterTank, aWaterTank))));

    Action expected = new AddFoodResourcesAction(aFoodRequest, aPantry, aTurnNumber);
    Action actual = actionFactory.createAddResourcesAction(aFoodRequest, aTurnNumber, aPantry);

    assertEquals(expected, actual);
  }

  @Test
  public void
      givenADinosaurAndADinosaurHerd_whenCreatingAnAddDinosaurAction_thenShouldCreateAction() {
    Dinosaur aDinosaur = new DinosaurBuilder().build();
    DinosaurHerd dinosaurHerd = new DinosaurHerd(List.of(aDinosaur));

    Action expected = new AddDinosaurAction(aDinosaur, dinosaurHerd);
    Action actual = actionFactory.createAddDinosaurAction(aDinosaur, dinosaurHerd);

    assertEquals(expected, actual);
  }

  @Test
  public void
      givenABreedingFamilyInformationABreederAndDinosaurHerd_whenCreatingABreedDinosaursAction_thenShouldCreateAction() {
    String babyName = "Diego";
    Dinosaur father = new DinosaurBuilder().build();
    Dinosaur mother = new DinosaurBuilder().build();
    BreedingFamilyInformation breedingFamilyInformation =
        new BreedingFamilyInformation(babyName, father, mother);
    DinosaurHerd dinosaurHerd = new DinosaurHerd(List.empty());

    Action expected =
        new BreedDinosaursAction(breedingFamilyInformation, breeder, dinosaurHerd, dinosaurFactory);
    Action actual =
        actionFactory.createBreedDinosaursAction(
            breedingFamilyInformation, breeder, dinosaurHerd, dinosaurFactory);

    assertEquals(expected, actual);
  }

  @Test
  public void
      givenAChallengerAndAChallengee_whenCreatingASumoCombatAction_thenShouldCreateAction() {
    Dinosaur challenger = new DinosaurBuilder().build();
    Dinosaur challengee = new DinosaurBuilder().build();

    Action expected = new SumoCombatAction(challenger, challengee);
    Action actual = actionFactory.createSumoCombatAction(challenger, challengee);

    assertEquals(expected, actual);
  }

  @Test
  public void givenADinosaurAndADeltaWeight_whenCreatingChangeDinosaurWeightAction_thenShouldCreateAction() {
    Dinosaur aDinosaur = new DinosaurBuilder().build();
    int aWeightInKg = 20;
    DeltaWeight aDeltaWeight = new DeltaWeight(aWeightInKg);

    Action expected = new ChangeDinosaurWeightAction(aDinosaur, aDeltaWeight);
    Action actual = actionFactory.createChangeDinosaurWeightAction(aDinosaur, aDeltaWeight);

    assertEquals(expected, actual);
  }
}
