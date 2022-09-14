package ca.ulaval.glo4002.game.dinosaurs.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class WeightTest {
  private static final int A_WEIGHT_IN_KILOGRAMS = 10;

  @Test
  public void givenANumber_whenCreatingWeightFromKilograms_shouldCreateWeightWithSameKilograms() {
    Weight weight = Weight.fromKilograms(A_WEIGHT_IN_KILOGRAMS);

    assertEquals(A_WEIGHT_IN_KILOGRAMS, weight.getIntValue());
  }
}
