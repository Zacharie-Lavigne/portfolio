package ca.ulaval.glo4002.game.rest.factories;

import ca.ulaval.glo4002.game.combat.domain.exceptions.SumoCombatException;
import ca.ulaval.glo4002.game.rest.communication.SumoCombatExceptionCommunicator;
import ca.ulaval.glo4002.game.rest.exceptions.ExceptionResponse;
import org.springframework.stereotype.Component;

@Component
public class SumoCombatExceptionResponseFactory extends ExceptionResponseFactory {
  private final SumoCombatExceptionCommunicator sumoCombatExceptionCommunicator;

  public SumoCombatExceptionResponseFactory(
      SumoCombatExceptionCommunicator sumoCombatExceptionCommunicator) {
    this.sumoCombatExceptionCommunicator = sumoCombatExceptionCommunicator;
  }

  public ExceptionResponse fromSumoCombatException(SumoCombatException sumoCombatException) {
    return fromRuntimeExceptionAndExceptionCommunicator(
        sumoCombatException, sumoCombatExceptionCommunicator);
  }
}
