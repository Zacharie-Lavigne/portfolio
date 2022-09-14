package ca.ulaval.glo4002.game.flow.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import ca.ulaval.glo4002.game.actions.domain.Action;
import org.junit.jupiter.api.Test;

class GameTest {
  private final Game A_GAME = spy(new Game());

  @Test
  public void whenInitializingGame_thenItShouldHaveTurnNumberAt1() {
    assertEquals(1, A_GAME.getTurnNumber());
  }

  @Test
  public void givenAGame_whenGoingToNextTurn_thenItShouldIncrementTurnNumber() {
    int initialTurnNumber = A_GAME.getTurnNumber();

    A_GAME.executeTurn();

    int updatedTurnNumber = A_GAME.getTurnNumber();

    assertEquals(initialTurnNumber + 1, updatedTurnNumber);
  }

  @Test
  public void givenAGameWithActions_whenExecutingActions_thenShouldExecuteThem() {
    Action anAction = mock(Action.class);
    Action anotherAction = mock(Action.class);

    A_GAME.addAction(anAction);
    A_GAME.addAction(anotherAction);
    A_GAME.executeActions();

    verify(anAction).execute();
    verify(anotherAction).execute();
  }

  @Test
  public void
      givenAGameWithActionsAndAResetOfTheGame_whenExecutingActions_thenShouldNotExecuteThem() {
    Action anAction = mock(Action.class);
    Action anotherAction = mock(Action.class);

    A_GAME.addAction(anAction);
    A_GAME.addAction(anotherAction);
    A_GAME.resetGame();

    A_GAME.executeActions();

    verify(anAction, never()).execute();
    verify(anotherAction, never()).execute();
  }

  @Test
  public void givenAGameWithTwoTurns_whenResetGame_thenShouldResetTurnNumberToOne() {
    A_GAME.executeTurn();
    A_GAME.executeTurn();

    A_GAME.resetGame();

    assertEquals(1, A_GAME.getTurnNumber());
  }
}
