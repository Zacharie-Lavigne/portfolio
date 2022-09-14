package ca.ulaval.glo4002.game.actions.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4002.game.breeding.domain.Breeder;
import ca.ulaval.glo4002.game.breeding.domain.BreedingFamilyInformation;
import ca.ulaval.glo4002.game.breeding.domain.BreedingResult;
import ca.ulaval.glo4002.game.dinosaurs.domain.Dinosaur;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurBuilder;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurFactory;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurHerd;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurSpecies;
import ca.ulaval.glo4002.game.dinosaurs.domain.Gender;
import io.vavr.collection.List;
import io.vavr.control.Option;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BreedDinosaursActionTest {
  private final Breeder breeder = mock(Breeder.class);
  private final DinosaurFactory dinosaurFactory = mock(DinosaurFactory.class);

  private final String A_BABY_NAME = "ti cuir";
  private final Dinosaur FATHER_DINOSAUR = new DinosaurBuilder().withName("dad").isAlive(true).build();
  private final Dinosaur MOTHER_DINOSAUR = new DinosaurBuilder().withName("mom").isAlive(true).build();
  private final BreedingFamilyInformation A_BREEDING_INFORMATION =
      new BreedingFamilyInformation(A_BABY_NAME, FATHER_DINOSAUR, MOTHER_DINOSAUR);
  private final BreedingResult BREEDING_RESULT =
      new BreedingResult(Gender.FEMALE, DinosaurSpecies.ALLOSAURUS, A_BABY_NAME);
  private final Dinosaur BABY_DINOSAUR = new DinosaurBuilder().withName(A_BABY_NAME).isAlive(true).build();

  private final DinosaurHerd DINOSAUR_HERD = new DinosaurHerd(List.empty());

  @BeforeEach
  public void setUp() {
    when(dinosaurFactory.createFromBreeding(BREEDING_RESULT, A_BREEDING_INFORMATION))
        .thenReturn(BABY_DINOSAUR);
  }

  @Test
  public void
      givenABreedingFamilyInformationADinosaurHerdAndASuccessfulBreeding_whenExecuting_thenShouldCreateBabyDinosaur() {
    when(breeder.breedDinosaurs(A_BREEDING_INFORMATION)).thenReturn(Option.of(BREEDING_RESULT));

    BreedDinosaursAction breedDinosaursAction =
        new BreedDinosaursAction(A_BREEDING_INFORMATION, breeder, DINOSAUR_HERD, dinosaurFactory);

    breedDinosaursAction.execute();

    verify(dinosaurFactory).createFromBreeding(BREEDING_RESULT, A_BREEDING_INFORMATION);
  }

  @Test
  public void
      givenABreedingFamilyInformationADinosaurHerdAndASuccessfulBreeding_whenExecuting_thenShouldAddBabyDinosaurToDinosaurHerd() {
    when(breeder.breedDinosaurs(A_BREEDING_INFORMATION)).thenReturn(Option.of(BREEDING_RESULT));

    BreedDinosaursAction breedDinosaursAction =
        new BreedDinosaursAction(A_BREEDING_INFORMATION, breeder, DINOSAUR_HERD, dinosaurFactory);

    breedDinosaursAction.execute();

    assertEquals(List.of(BABY_DINOSAUR), DINOSAUR_HERD.getAliveDinosaurs());
  }

  @Test
  public void
      givenABreedingFamilyInformationADinosaurHerdAndANonSuccessfulBreeding_whenExecuting_thenShouldNotCreateBabyDinosaur() {
    when(breeder.breedDinosaurs(A_BREEDING_INFORMATION)).thenReturn(Option.none());

    BreedDinosaursAction breedDinosaursAction =
        new BreedDinosaursAction(A_BREEDING_INFORMATION, breeder, DINOSAUR_HERD, dinosaurFactory);

    breedDinosaursAction.execute();

    verify(dinosaurFactory, never()).createFromBreeding(BREEDING_RESULT, A_BREEDING_INFORMATION);
  }

  @Test
  public void
      givenABreedingFamilyInformationADinosaurHerdAndANonSuccessfulBreeding_whenExecuting_thenShouldNotAddBabyDinosaurToDinosaurHerd() {
    when(breeder.breedDinosaurs(A_BREEDING_INFORMATION)).thenReturn(Option.none());

    BreedDinosaursAction breedDinosaursAction =
        new BreedDinosaursAction(A_BREEDING_INFORMATION, breeder, DINOSAUR_HERD, dinosaurFactory);

    breedDinosaursAction.execute();

    assertEquals(List.empty(), DINOSAUR_HERD.getAliveDinosaurs());
  }
}
