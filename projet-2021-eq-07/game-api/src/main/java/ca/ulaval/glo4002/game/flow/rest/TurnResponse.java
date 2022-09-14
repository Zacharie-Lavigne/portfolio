package ca.ulaval.glo4002.game.flow.rest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class TurnResponse {

  public final int turnNumber;

  @JsonCreator
  public TurnResponse(@JsonProperty(value = "turnNumber", required = true) int turnNumber) {
    this.turnNumber = turnNumber;
  }
}
