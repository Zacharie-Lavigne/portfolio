package ca.ulaval.glo4002.game.dinosaurs.domain;

import io.vavr.collection.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DinosaurFamilyHandlerTest {
  private final DinosaurFamilyHandler dinosaurFamilyHandler = new DinosaurFamilyHandler();

  private final String FIRST_DINOSAUR_NAME = "pedro";
  private final Dinosaur FIRST_DINOSAUR_WITH_NO_PARENTS =
      new DinosaurBuilder()
          .withGender(Gender.MALE)
          .withName(FIRST_DINOSAUR_NAME)
          .withSpecies(DinosaurSpecies.ALLOSAURUS)
          .withWeight(Weight.fromKilograms(150))
          .build();
  private final String A_FATHER_NAME = "Franky";
  private final String A_MOTHER_NAME = "Frankette";
  private final Dinosaur A_FATHER_DINOSAUR =
      new DinosaurBuilder().isAlive(true).withName(A_FATHER_NAME).build();
  private final Dinosaur A_MOTHER_DINOSAUR =
      new DinosaurBuilder().isAlive(true).withName(A_MOTHER_NAME).build();
  private final Dinosaur SECOND_DINOSAUR_WITH_PARENTS =
      new DinosaurBuilder()
          .withGender(Gender.MALE)
          .withName(FIRST_DINOSAUR_NAME)
          .withSpecies(DinosaurSpecies.ALLOSAURUS)
          .withWeight(Weight.fromKilograms(1))
          .withParents(A_FATHER_NAME, A_MOTHER_NAME)
          .build();
  private final List<Dinosaur> DINOSAURS =
      List.of(
          FIRST_DINOSAUR_WITH_NO_PARENTS,
          SECOND_DINOSAUR_WITH_PARENTS,
          A_FATHER_DINOSAUR,
          A_MOTHER_DINOSAUR);

  private final DinosaurHerd DINOSAUR_HERD = new DinosaurHerd(DINOSAURS);

  @Test
  public void
      givenOneDinosaurWithNoParentsAndOneWithAliveParents_whenGettingRidOfOrphans_thenShouldNotKillDinosaurWithNoParents() {
    dinosaurFamilyHandler.getRidOfOrphans(DINOSAUR_HERD);

    assertTrue(FIRST_DINOSAUR_WITH_NO_PARENTS.isAlive());
  }

  @Test
  public void
      givenOneDinosaurWithNoParentsAndOneWithAliveParents_whenGettingRidOfOrphans_thenShouldNotKillDinosaurWithAliveParents() {
    dinosaurFamilyHandler.getRidOfOrphans(DINOSAUR_HERD);

    assertTrue(SECOND_DINOSAUR_WITH_PARENTS.isAlive());
  }

  @Test
  public void
      givenOneDinosaurWithNoParentsAndOneWithDeadParents_whenGettingRidOfOrphans_thenShouldKillDinosaurWithDeadParents() {
    A_FATHER_DINOSAUR.die();
    A_MOTHER_DINOSAUR.die();
    DINOSAUR_HERD.removeDead();

    dinosaurFamilyHandler.getRidOfOrphans(DINOSAUR_HERD);

    assertFalse(SECOND_DINOSAUR_WITH_PARENTS.isAlive());
  }

  @Test
  public void
      givenADinosaurWithADeadFatherAndAnAliveMother_whenGettingRidOfOrphans_thenShouldNotKillDinosaur() {
    A_FATHER_DINOSAUR.die();

    dinosaurFamilyHandler.getRidOfOrphans(DINOSAUR_HERD);

    assertTrue(SECOND_DINOSAUR_WITH_PARENTS.isAlive());
  }

  @Test
  public void
      givenADinosaurWithAnAliveFatherAndADeadMother_whenGettingRidOfOrphans_thenShouldNotKillDinosaur() {
    A_MOTHER_DINOSAUR.die();

    dinosaurFamilyHandler.getRidOfOrphans(DINOSAUR_HERD);

    assertTrue(SECOND_DINOSAUR_WITH_PARENTS.isAlive());
  }
}
