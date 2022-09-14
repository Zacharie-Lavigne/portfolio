package ca.ulaval.glo4002.game.dinosaurs.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import ca.ulaval.glo4002.game.dinosaurs.useCases.exceptions.NonExistingNameException;
import io.vavr.collection.List;
import org.junit.jupiter.api.Test;

class DinosaurHerdTest {
  private final String FIRST_DINOSAUR_NAME = "diego";
  private final Dinosaur DINOSAUR =
      new DinosaurBuilder().withName(FIRST_DINOSAUR_NAME).isAlive(true).build();

  private final DinosaurHerd emptyDinosaurHerd = new DinosaurHerd(List.empty());
  private final DinosaurHerd dinosaurHerd = new DinosaurHerd(List.of(DINOSAUR));

  @Test
  public void givenAnEmptyDinosaurHerdAndADinosaur_whenAddingDinosaur_thenShouldAdd() {
    emptyDinosaurHerd.addDinosaur(DINOSAUR);

    assertEquals(List.of(DINOSAUR), emptyDinosaurHerd.getAliveDinosaurs());
  }

  @Test
  public void
      givenADinosaurHerdWithADinosaurAndADinosaurWithSameName_whenAddingDinosaur_thenShouldNotAdd() {
    Dinosaur aDinosaurWithSameName = new DinosaurBuilder().withName(FIRST_DINOSAUR_NAME).build();
    dinosaurHerd.addDinosaur(aDinosaurWithSameName);

    assertEquals(List.of(DINOSAUR), dinosaurHerd.getAliveDinosaurs());
  }

  @Test
  public void givenADinosaurHerd_whenGettingAliveDinosaurs_thenShouldGetAliveDinosaurs() {
    List<Dinosaur> actual = dinosaurHerd.getAliveDinosaurs();

    assertEquals(List.of(DINOSAUR), actual);
  }

  @Test
  public void
      givenADinosaurHerdWithOnlyDeadDinosaurs_whenGettingAliveDinosaurs_thenShouldNotGetAnyDinosaurs() {
    DINOSAUR.die();

    List<Dinosaur> actual = dinosaurHerd.getAliveDinosaurs();

    assertEquals(List.empty(), actual);
  }

  @Test
  public void givenAListOfDinosaurs_whenUpdatingDinosaurs_thenShouldUpdateDinosaurs() {
    Dinosaur aDinosaur = new DinosaurBuilder().isAlive(true).build();
    Dinosaur anotherDinosaur = new DinosaurBuilder().isAlive(true).build();

    List<Dinosaur> dinosaurs = List.of(aDinosaur, anotherDinosaur);

    dinosaurHerd.updateDinosaurs(dinosaurs);

    assertEquals(dinosaurs, dinosaurHerd.getAliveDinosaurs());
  }

  @Test
  public void givenADinosaurHerd_whenDeletingAll_thenShouldDeleteAllDinosaurs() {
    dinosaurHerd.deleteAll();

    assertEquals(List.empty(), dinosaurHerd.getAliveDinosaurs());
  }

  @Test
  public void givenADinosaurHerdWithADinosaur_whenGettingTheDinosaurByName_thenShouldReturnTheDinosaur() {
    Dinosaur actualDinosaur = dinosaurHerd.getByName(FIRST_DINOSAUR_NAME);

    assertEquals(DINOSAUR, actualDinosaur);
  }

  @Test
  public void givenADinosaurHerdWithDinosaurNotPresent_whenGettingDinosaurByName_thenShouldThrowNonExistingNameException() {
    dinosaurHerd.deleteAll();

    assertThrows(NonExistingNameException.class, () -> dinosaurHerd.getByName(FIRST_DINOSAUR_NAME));
  }
}
