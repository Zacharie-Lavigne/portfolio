package ca.ulaval.glo4002.game.combat.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SumoCombatRequest {
  public final String challengerName;
  public final String challengeeName;

  public SumoCombatRequest(
      @JsonProperty(value = "challenger", required = true) String challengerName,
      @JsonProperty(value = "challengee", required = true) String challengeeName) {

    this.challengerName = challengerName;
    this.challengeeName = challengeeName;
  }
}
