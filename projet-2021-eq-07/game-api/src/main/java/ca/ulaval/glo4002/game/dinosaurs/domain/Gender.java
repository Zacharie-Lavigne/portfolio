package ca.ulaval.glo4002.game.dinosaurs.domain;

import ca.ulaval.glo4002.game.dinosaurs.domain.exceptions.InvalidGenderException;
import io.vavr.collection.Stream;
import java.util.Objects;

public enum Gender {
  FEMALE("f", 1.5),
  MALE("m", 1);

  private final String abbreviation;
  private final double strengthFactor;

  Gender(String abbreviation, double strengthFactor) {
    this.abbreviation = abbreviation;
    this.strengthFactor = strengthFactor;
  }

  public String getAbbreviation() {
    return this.abbreviation;
  }

  public double getStrengthFactor() {
    return this.strengthFactor;
  }

  public static Gender fromAbbreviation(String abbreviation) {
    return Stream.of(Gender.values())
        .filter(gender -> Objects.equals(gender.abbreviation, abbreviation))
        .getOrElseThrow(InvalidGenderException::new);
  }
}
