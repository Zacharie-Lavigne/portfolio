package ca.ulaval.glo4002.game.flow.domain;

public interface GameRepository {

  Game getGame();

  void update(Game game);
}
