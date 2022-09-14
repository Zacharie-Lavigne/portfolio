package ca.ulaval.glo4002.game.dinosaurs.domain;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public final class Weight {
  private final int weightInKilograms;

  public Weight(int weightInKilograms) {
    this.weightInKilograms = weightInKilograms;
  }

  public static Weight fromKilograms(int weightInKilograms) {
    return new Weight(weightInKilograms);
  }

  public int getIntValue() {
    return this.weightInKilograms;
  }

  public Weight changeWeight(int value) {
    return new Weight(this.weightInKilograms + value);
  }

  public Weight changeWeight(DeltaWeight deltaWeight) {
    return new Weight(this.weightInKilograms + deltaWeight.getValueInKilogram());
  }
}
