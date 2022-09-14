package ca.ulaval.glo4002.game.combat.rest.assemblers;

import ca.ulaval.glo4002.game.combat.domain.SumoCombatAttempt;
import ca.ulaval.glo4002.game.combat.domain.SumoCombatResult;
import ca.ulaval.glo4002.game.combat.rest.SumoCombatRequest;
import ca.ulaval.glo4002.game.combat.rest.SumoCombatResponse;
import org.springframework.stereotype.Component;

@Component
public class SumoCombatAssembler {

  public SumoCombatAttempt toDomain(SumoCombatRequest sumoCombatRequest) {
    return new SumoCombatAttempt(
        sumoCombatRequest.challengerName, sumoCombatRequest.challengeeName);
  }

  public SumoCombatResponse toResponse(SumoCombatResult prediction) {
    String responseContent;
    if (prediction.isTie()) {
      responseContent = "tie";
    } else {
      responseContent = prediction.getWinner().getName();
    }

    return new SumoCombatResponse(responseContent);
  }
}
