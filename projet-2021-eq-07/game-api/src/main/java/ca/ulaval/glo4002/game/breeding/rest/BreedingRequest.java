package ca.ulaval.glo4002.game.breeding.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BreedingRequest {

  public final String name;
  public final String fatherName;
  public final String motherName;

  public BreedingRequest(
      @JsonProperty(value = "name", required = true) String name,
      @JsonProperty(value = "fatherName", required = true) String fatherName,
      @JsonProperty(value = "motherName", required = true) String motherName) {

    this.name = name;
    this.fatherName = fatherName;
    this.motherName = motherName;
  }
}
