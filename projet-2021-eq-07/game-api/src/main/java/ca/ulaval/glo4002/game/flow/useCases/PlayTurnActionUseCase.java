package ca.ulaval.glo4002.game.flow.useCases;

import ca.ulaval.glo4002.game.flow.domain.Game;
import org.springframework.stereotype.Component;

@Component
public class PlayTurnActionUseCase {
  public void playTurn(Game game) {
    game.executeActions();
    game.resetActions();
  }
}
