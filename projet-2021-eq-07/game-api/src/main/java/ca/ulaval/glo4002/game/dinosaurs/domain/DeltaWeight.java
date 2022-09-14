package ca.ulaval.glo4002.game.dinosaurs.domain;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public final class DeltaWeight {
  private final int deltaWeightInKilogram;

  public DeltaWeight(int deltaWeightInKilogram) {
    this.deltaWeightInKilogram = deltaWeightInKilogram;
  }

  public int getValueInKilogram() {
    return this.deltaWeightInKilogram;
  }
}
