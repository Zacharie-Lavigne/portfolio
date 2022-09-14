package ca.ulaval.glo4002.game.combat.rest;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class SumoCombatResponse {

  public final String predictedWinner;

  public SumoCombatResponse(String predictedWinner) {
    this.predictedWinner = predictedWinner;
  }
}
