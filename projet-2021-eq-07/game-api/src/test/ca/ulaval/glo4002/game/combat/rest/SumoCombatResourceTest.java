package ca.ulaval.glo4002.game.combat.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4002.game.actions.domain.SumoCombatAction;
import ca.ulaval.glo4002.game.combat.domain.SumoCombatAttempt;
import ca.ulaval.glo4002.game.combat.domain.SumoCombatResult;
import ca.ulaval.glo4002.game.combat.rest.assemblers.SumoCombatAssembler;
import ca.ulaval.glo4002.game.combat.useCase.SumoCombatUseCase;
import ca.ulaval.glo4002.game.dinosaurs.domain.Dinosaur;
import ca.ulaval.glo4002.game.dinosaurs.domain.DinosaurBuilder;
import javax.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SumoCombatResourceTest {
  private final SumoCombatUseCase sumoCombatUseCase = mock(SumoCombatUseCase.class);
  private final SumoCombatAssembler sumoCombatAssembler = mock(SumoCombatAssembler.class);

  private final SumoCombatResource sumoCombatResource =
      new SumoCombatResource(sumoCombatUseCase, sumoCombatAssembler);

  private static final String A_CHALLENGER_NAME = "Pierre";
  private static final String A_CHALLENGEE_NAME = "Roger";

  private final DinosaurBuilder dinosaurBuilder = new DinosaurBuilder();
  private final Dinosaur A_DINOSAUR = dinosaurBuilder.build();

  private final SumoCombatRequest A_SUMO_COMBAT_REQUEST =
      new SumoCombatRequest(A_CHALLENGER_NAME, A_CHALLENGEE_NAME);

  private final SumoCombatAttempt A_SUMO_COMBAT_ATTEMPT =
      new SumoCombatAttempt(A_CHALLENGER_NAME, A_CHALLENGEE_NAME);

  private final SumoCombatAction A_SUMO_COMBAT_ACTION = mock(SumoCombatAction.class);
  private final SumoCombatResponse A_SUMO_COMBAT_RESPONSE =
      new SumoCombatResponse(A_CHALLENGER_NAME);

  private final SumoCombatResult A_SUMO_COMBAT_RESULT = new SumoCombatResult(A_DINOSAUR);

  @BeforeEach
  public void setUp() {
    when(sumoCombatAssembler.toDomain(A_SUMO_COMBAT_REQUEST)).thenReturn(A_SUMO_COMBAT_ATTEMPT);
    when(sumoCombatUseCase.createSumoCombatAction(A_SUMO_COMBAT_ATTEMPT))
        .thenReturn(A_SUMO_COMBAT_ACTION);
    when(sumoCombatAssembler.toResponse(A_SUMO_COMBAT_RESULT)).thenReturn(A_SUMO_COMBAT_RESPONSE);
  }

  @Test
  public void
      givenAValidSumoCombatRequest_whenCreateAndPredictSumoCombatAction_thenShouldAssembleToDomain() {
    sumoCombatResource.createAndPredictSumoCombatAction(A_SUMO_COMBAT_REQUEST);

    verify(sumoCombatAssembler).toDomain(A_SUMO_COMBAT_REQUEST);
  }

  @Test
  public void
      givenAValidSumoCombatRequest_whenCreateAndPredictSumoCombatAction_thenShouldCreateSumoCombatAction() {
    sumoCombatResource.createAndPredictSumoCombatAction(A_SUMO_COMBAT_REQUEST);

    verify(sumoCombatUseCase).createSumoCombatAction(A_SUMO_COMBAT_ATTEMPT);
  }

  @Test
  public void
      givenAValidSumoCombatRequest_whenCreateAndPredictSumoCombatAction_thenShouldPredictCombat() {
    sumoCombatResource.createAndPredictSumoCombatAction(A_SUMO_COMBAT_REQUEST);

    verify(A_SUMO_COMBAT_ACTION).getPrediction();
  }

  @Test
  public void
      givenAValidSumoCombatRequest_whenCreateAndPredictSumoCombatAction_thenShouldAssembleSumoCombatToResponse() {
    givenAPrediction();

    sumoCombatResource.createAndPredictSumoCombatAction(A_SUMO_COMBAT_REQUEST);

    verify(sumoCombatAssembler).toResponse(A_SUMO_COMBAT_RESULT);
  }

  @Test
  public void
      givenAValidSumoCombatRequest_whenCreateAndPredictSumoCombatAction_thenShouldReturnCombatResponseWithSameEntity() {
    givenAPrediction();

    Response expectedResponse = Response.ok().entity(A_SUMO_COMBAT_RESPONSE).build();

    Response actualResponse =
        sumoCombatResource.createAndPredictSumoCombatAction(A_SUMO_COMBAT_REQUEST);

    assertEquals(expectedResponse.getEntity(), actualResponse.getEntity());
  }

  @Test
  public void
      givenAValidSumoCombatRequest_whenCreateAndPredictSumoCombatAction_thenShouldReturnCombatResponseWithCode200() {
    Response expectedResponse = Response.ok().entity(A_SUMO_COMBAT_RESPONSE).build();

    Response actualResponse =
        sumoCombatResource.createAndPredictSumoCombatAction(A_SUMO_COMBAT_REQUEST);

    assertEquals(expectedResponse.getStatus(), actualResponse.getStatus());
  }

  private void givenAPrediction() {
    when(A_SUMO_COMBAT_ACTION.getPrediction()).thenReturn(A_SUMO_COMBAT_RESULT);
  }
}
