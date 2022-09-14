package ca.ulaval.glo4002.game.combat.rest.assemblers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.ulaval.glo4002.game.combat.domain.SumoCombatAttempt;
import ca.ulaval.glo4002.game.combat.domain.SumoCombatResult;
import ca.ulaval.glo4002.game.combat.rest.SumoCombatRequest;
import ca.ulaval.glo4002.game.combat.rest.SumoCombatResponse;
import ca.ulaval.glo4002.game.dinosaurs.domain.Dinosaur;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurBuilder;
import org.junit.jupiter.api.Test;

class SumoCombatAssemblerTest {

  private static final String CHALLENGER_NAME = "Roger";
  private static final String CHALLENGEE_NAME = "Pierre";
  private final DinosaurBuilder dinosaurBuilder = new DinosaurBuilder();
  private final Dinosaur A_DINOSAUR = dinosaurBuilder.withName(CHALLENGER_NAME).build();
  private final SumoCombatRequest sumoCombatRequest =
      new SumoCombatRequest(CHALLENGER_NAME, CHALLENGEE_NAME);
  private final SumoCombatAssembler sumoCombatAssembler = new SumoCombatAssembler();

  @Test
  public void
      givenASumoCombatRequest_whenAssemblingToDomain_thenShouldAssembleWithTheCorrespondingParameters() {
    SumoCombatAttempt expectedSumoCombatAttempt =
        new SumoCombatAttempt(CHALLENGER_NAME, CHALLENGEE_NAME);

    SumoCombatAttempt actualSumoCombatAttempt = sumoCombatAssembler.toDomain(sumoCombatRequest);

    assertEquals(expectedSumoCombatAttempt, actualSumoCombatAttempt);
  }

  @Test
  public void givenAPredictionWith2Winners_whenGettingResponse_thenReturnResponseWithTie() {
    Dinosaur aDinosaur = dinosaurBuilder.build();
    SumoCombatResult sumoCombatResult = new SumoCombatResult(A_DINOSAUR, aDinosaur);

    SumoCombatResponse actualResponse = sumoCombatAssembler.toResponse(sumoCombatResult);

    assertEquals("tie", actualResponse.predictedWinner);
  }

  @Test
  public void givenAPredictionWith1Winner_whenGettingResponse_thenReturnResponseWithWinnerName() {
    SumoCombatResult sumoCombatResult = new SumoCombatResult(A_DINOSAUR);

    SumoCombatResponse actualResponse = sumoCombatAssembler.toResponse(sumoCombatResult);

    assertEquals(CHALLENGER_NAME, actualResponse.predictedWinner);
  }
}
