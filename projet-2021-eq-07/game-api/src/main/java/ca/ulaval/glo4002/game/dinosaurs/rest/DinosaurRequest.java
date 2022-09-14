package ca.ulaval.glo4002.game.dinosaurs.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class DinosaurRequest {

  public final String name;
  public final int weight;
  public final String gender;
  public final String species;

  public DinosaurRequest(
      @JsonProperty(value = "name", required = true) String name,
      @JsonProperty(value = "weight", required = true) int weight,
      @JsonProperty(value = "gender", required = true) String gender,
      @JsonProperty(value = "species", required = true) String species) {

    this.name = name;
    this.weight = weight;
    this.gender = gender;
    this.species = species;
  }
}
