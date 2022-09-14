package ca.ulaval.glo4002.game.combat.domain;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class SumoCombatAttempt {
  private final String challengerName;
  private final String challengeeName;

  public SumoCombatAttempt(String challengerName, String challengeeName) {
    this.challengerName = challengerName;
    this.challengeeName = challengeeName;
  }

  public String getChallengerName() {
    return this.challengerName;
  }

  public String getChallengeeName() {
    return this.challengeeName;
  }
}
