package ca.ulaval.glo4002.game.actions.domain;

import ca.ulaval.glo4002.game.breeding.domain.Breeder;
import ca.ulaval.glo4002.game.breeding.domain.BreedingFamilyInformation;
import ca.ulaval.glo4002.game.breeding.domain.BreedingResult;
import ca.ulaval.glo4002.game.dinosaurs.domain.Dinosaur;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurFactory;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurHerd;
import io.vavr.control.Option;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class BreedDinosaursAction implements Action {
  private final BreedingFamilyInformation breedingFamilyInformation;
  private final Breeder breeder;
  private final DinosaurHerd dinosaurHerd;
  private final DinosaurFactory dinosaurFactory;

  public BreedDinosaursAction(
      BreedingFamilyInformation breedingFamilyInformation,
      Breeder breeder,
      DinosaurHerd dinosaurHerd,
      DinosaurFactory dinosaurFactory) {
    this.breedingFamilyInformation = breedingFamilyInformation;
    this.breeder = breeder;
    this.dinosaurHerd = dinosaurHerd;
    this.dinosaurFactory = dinosaurFactory;
  }

  @Override
  public void execute() {
    Option<BreedingResult> breedingResult = breeder.breedDinosaurs(this.breedingFamilyInformation);
    if (breedingResult.isDefined()) {
      Dinosaur babyDinosaur =
          this.dinosaurFactory.createFromBreeding(
              breedingResult.get(), this.breedingFamilyInformation);
      this.dinosaurHerd.addDinosaur(babyDinosaur);
    }
  }
}
