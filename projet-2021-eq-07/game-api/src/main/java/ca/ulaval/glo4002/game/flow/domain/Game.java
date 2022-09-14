package ca.ulaval.glo4002.game.flow.domain;

import ca.ulaval.glo4002.game.actions.domain.Action;
import io.vavr.collection.List;

public class Game {
  private int turnNumber;
  private List<Action> actions = List.empty();

  public Game() {
    this.turnNumber = 1;
  }

  public int getTurnNumber() {
    return this.turnNumber;
  }

  public void addAction(Action action) {
    this.actions = this.actions.append(action);
  }

  public void resetActions() {
    this.actions = List.empty();
  }

  public void resetGame() {
    this.turnNumber = 1;
    this.resetActions();
  }

  public void executeActions() {
    this.actions.forEach(Action::execute);
  }

  public Game executeTurn() {
    this.turnNumber += 1;
    return this;
  }
}
