package ca.ulaval.glo4002.game.flow.useCases;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import ca.ulaval.glo4002.game.flow.domain.Game;
import org.junit.jupiter.api.Test;

class PlayTurnActionUseCaseTest {
  private final PlayTurnActionUseCase playTurnActionUseCase = new PlayTurnActionUseCase();

  private final Game A_GAME = spy(new Game());

  @Test
  public void givenAGame_whenPlayingTurn_thenShouldExecuteActions() {
    playTurnActionUseCase.playTurn(A_GAME);

    verify(A_GAME).executeActions();
  }

  @Test
  public void givenAGame_whenPlayingTurn_thenShouldResetActions() {
    playTurnActionUseCase.playTurn(A_GAME);

    verify(A_GAME).resetActions();
  }
}
