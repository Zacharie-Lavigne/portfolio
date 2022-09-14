package ca.ulaval.glo4002.game.dinosaurs.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import ca.ulaval.glo4002.game.dinosaurs.domain.exceptions.InvalidGenderException;
import org.junit.jupiter.api.Test;

class GenderTest {

  @Test
  public void givenValidGenderString_thenGender() {
    Gender expectedGender = Gender.FEMALE;
    String aValidGender = "f";

    Gender actualGender = Gender.fromAbbreviation(aValidGender);

    assertEquals(expectedGender, actualGender);
  }

  @Test
  public void givenInvalidGenderString_thenThrowInvalidGender() {
    String anInvalidGender = "r";

    assertThrows(InvalidGenderException.class, () -> Gender.fromAbbreviation(anInvalidGender));
  }
}
