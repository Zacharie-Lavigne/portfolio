package ca.ulaval.glo4002.game.breeding.domain;

import ca.ulaval.glo4002.game.dinosaurs.domain.Dinosaur;

public class BreedingFamilyInformation {
  private final String babyName;
  private final Dinosaur father;
  private final Dinosaur mother;

  public BreedingFamilyInformation(String babyName, Dinosaur father, Dinosaur mother) {
    this.babyName = babyName;
    this.father = father;
    this.mother = mother;
  }

  public Dinosaur getFather() {
    return this.father;
  }

  public Dinosaur getMother() {
    return mother;
  }

  public String getBabyName() {
    return babyName;
  }
}
