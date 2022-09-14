package ca.ulaval.glo4002.game.breeding.domain;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class BreedingAttempt {
  private final String name;
  private final String fatherName;
  private final String motherName;

  public BreedingAttempt(String name, String fatherName, String motherName) {
    this.name = name;
    this.fatherName = fatherName;
    this.motherName = motherName;
  }

  public String getName() {
    return this.name;
  }

  public String getFatherName() {
    return this.fatherName;
  }

  public String getMotherName() {
    return this.motherName;
  }
}
