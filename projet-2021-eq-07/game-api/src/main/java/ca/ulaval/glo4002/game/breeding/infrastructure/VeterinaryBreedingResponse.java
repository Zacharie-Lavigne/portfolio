package ca.ulaval.glo4002.game.breeding.infrastructure;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VeterinaryBreedingResponse {
  public final String offspring;
  public final String gender;

  public VeterinaryBreedingResponse(
      @JsonProperty(value = "offspring", required = true) String offspring,
      @JsonProperty(value = "gender", required = true) String gender) {
    this.offspring = offspring;
    this.gender = gender;
  }
}
