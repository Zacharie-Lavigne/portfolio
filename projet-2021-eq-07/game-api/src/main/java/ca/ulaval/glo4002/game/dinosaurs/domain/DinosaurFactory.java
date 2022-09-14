package ca.ulaval.glo4002.game.dinosaurs.domain;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;

import ca.ulaval.glo4002.game.breeding.domain.BreedingFamilyInformation;
import ca.ulaval.glo4002.game.breeding.domain.BreedingResult;
import ca.ulaval.glo4002.game.dinosaurs.domain.needs.CarnivoreNeedsCalculationStrategy;
import ca.ulaval.glo4002.game.dinosaurs.domain.needs.DefaultWaterConsumptionStrategy;
import ca.ulaval.glo4002.game.dinosaurs.domain.needs.HerbivoreNeedsCalculationStrategy;
import ca.ulaval.glo4002.game.dinosaurs.domain.needs.NeedsCalculationStrategy;
import ca.ulaval.glo4002.game.dinosaurs.domain.needs.OmnivoreNeedsCalculationStrategy;
import ca.ulaval.glo4002.game.dinosaurs.domain.needs.OmnivoreWaterConsumptionStrategy;
import ca.ulaval.glo4002.game.dinosaurs.domain.needs.WaterConsumptionStrategy;
import org.springframework.stereotype.Component;

@Component
public class DinosaurFactory {
  private static final Weight BABY_DINOSAUR_WEIGHT = Weight.fromKilograms(1);

  public Dinosaur create(Gender gender, String name, DinosaurSpecies species, int weight) {
    NeedsCalculationStrategy needsCalculationStrategy = this.getNeedsCalculationStrategy(species);
    WaterConsumptionStrategy waterConsumptionStrategy = this.getWaterConsumptionStrategy(species);
    return new Dinosaur(
        gender,
        name,
        species,
        needsCalculationStrategy,
        waterConsumptionStrategy,
        Weight.fromKilograms(weight));
  }

  public Dinosaur createFromBreeding(
      BreedingResult breedingResult, BreedingFamilyInformation breedingFamilyInformation) {

    NeedsCalculationStrategy needsCalculationStrategy =
        this.getNeedsCalculationStrategy(breedingResult.dinosaurSpecies);

    WaterConsumptionStrategy waterConsumptionStrategy =
        this.getWaterConsumptionStrategy(breedingResult.dinosaurSpecies);

    return new Dinosaur(
        breedingResult.gender,
        breedingResult.name,
        breedingResult.dinosaurSpecies,
        needsCalculationStrategy,
        waterConsumptionStrategy,
        BABY_DINOSAUR_WEIGHT,
        breedingFamilyInformation.getFather().getName(),
        breedingFamilyInformation.getMother().getName());
  }

  private NeedsCalculationStrategy getNeedsCalculationStrategy(DinosaurSpecies dinosaurSpecies) {
    return Match(dinosaurSpecies.getConsumptionPreference())
        .of(
            Case($(ConsumptionPreference.CARNIVOROUS), new CarnivoreNeedsCalculationStrategy()),
            Case($(ConsumptionPreference.HERBIVOROUS), new HerbivoreNeedsCalculationStrategy()),
            Case($(ConsumptionPreference.OMNIVOROUS), new OmnivoreNeedsCalculationStrategy()));
  }

  private WaterConsumptionStrategy getWaterConsumptionStrategy(DinosaurSpecies dinosaurSpecies) {
    return Match(dinosaurSpecies.getConsumptionPreference())
        .of(
            Case($(ConsumptionPreference.CARNIVOROUS), new DefaultWaterConsumptionStrategy()),
            Case($(ConsumptionPreference.HERBIVOROUS), new DefaultWaterConsumptionStrategy()),
            Case($(ConsumptionPreference.OMNIVOROUS), new OmnivoreWaterConsumptionStrategy()));
  }
}
