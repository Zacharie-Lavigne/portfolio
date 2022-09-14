package ca.ulaval.glo4002.game.flow.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4002.game.flow.useCases.PlayTurnUseCase;
import org.junit.jupiter.api.Test;

class TurnResourceTest {
  private final PlayTurnUseCase playTurnUseCase = mock(PlayTurnUseCase.class);

  private final TurnResource turnResource = new TurnResource(playTurnUseCase);

  @Test
  public void whenPlayingTurn_thenShouldPlayTurn() {
    turnResource.playTurn();

    verify(playTurnUseCase).playTurn();
  }

  @Test
  public void whenPlayingTurn_thenShouldReturnTurnResponse() {
    int aTurnNumber = 4;

    when(playTurnUseCase.playTurn()).thenReturn(aTurnNumber);

    TurnResponse expectedResponse = new TurnResponse(aTurnNumber);
    TurnResponse actualResponse = turnResource.playTurn();

    assertEquals(expectedResponse, actualResponse);
  }
}
