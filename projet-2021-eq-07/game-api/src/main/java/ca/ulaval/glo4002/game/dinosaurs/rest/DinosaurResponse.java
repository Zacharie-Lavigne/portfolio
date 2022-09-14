package ca.ulaval.glo4002.game.dinosaurs.rest;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class DinosaurResponse {

  public final String name;
  public final int weight;
  public final String gender;
  public final String species;

  public DinosaurResponse(String name, int weight, String gender, String species) {
    this.name = name;
    this.weight = weight;
    this.gender = gender;
    this.species = species;
  }
}
