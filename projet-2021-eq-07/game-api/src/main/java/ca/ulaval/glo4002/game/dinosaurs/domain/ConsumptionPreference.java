package ca.ulaval.glo4002.game.dinosaurs.domain;

public enum ConsumptionPreference {
  HERBIVOROUS(1),
  CARNIVOROUS(1.5),
  OMNIVOROUS(1.5);

  private final double strengthFactor;

  ConsumptionPreference(double strengthFactor) {
    this.strengthFactor = strengthFactor;
  }

  public double getStrengthFactor() {
    return this.strengthFactor;
  }
}
