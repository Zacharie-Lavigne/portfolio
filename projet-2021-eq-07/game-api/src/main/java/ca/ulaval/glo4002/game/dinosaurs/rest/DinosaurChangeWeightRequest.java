package ca.ulaval.glo4002.game.dinosaurs.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DinosaurChangeWeightRequest {
  public final int deltaWeight;

  public DinosaurChangeWeightRequest(
      @JsonProperty(value = "weight", required = true) int deltaWeight) {
    this.deltaWeight = deltaWeight;
  }
}
