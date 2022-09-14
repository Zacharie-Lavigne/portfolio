package ca.ulaval.glo4002.game.flow.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import ca.ulaval.glo4002.game.flow.useCases.ResetUseCase;
import javax.ws.rs.core.Response;
import org.junit.jupiter.api.Test;

class ResetResourceTest {
  private final ResetUseCase resetUseCase = mock(ResetUseCase.class);

  private final ResetResource resetResource = new ResetResource(resetUseCase);

  @Test
  public void whenResetGame_thenShouldResetGame() {
    resetResource.resetGame();

    verify(resetUseCase).reset();
  }

  @Test
  public void whenResetGame_thenShouldReturnResponseWithCode200() {
    Response expectedResponse = Response.ok().build();
    Response actualResponse = resetResource.resetGame();

    assertEquals(expectedResponse.getStatus(), actualResponse.getStatus());
  }
}
