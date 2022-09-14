package ca.ulaval.glo4002.game.flow.infrastructure.inMemory;

import ca.ulaval.glo4002.game.flow.domain.Game;
import ca.ulaval.glo4002.game.flow.domain.GameRepository;
import org.springframework.stereotype.Component;

@Component
public class InMemoryGameRepository implements GameRepository {
  private Game game;

  public InMemoryGameRepository() {
    this.game = new Game();
  }

  @Override
  public Game getGame() {
    return this.game;
  }

  @Override
  public void update(Game game) {
    this.game = game;
  }
}
