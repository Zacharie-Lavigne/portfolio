package ca.ulaval.glo4002.game.resources.rest.assemblers;

import ca.ulaval.glo4002.game.resources.domain.FoodResourcesReport;
import ca.ulaval.glo4002.game.resources.rest.FoodResourcesReportResponse;
import org.springframework.stereotype.Component;

@Component
public class FoodResourcesReportAssembler {

  private final FoodResourcesQuantitiesAssembler foodResourcesQuantitiesAssembler;

  public FoodResourcesReportAssembler(
      FoodResourcesQuantitiesAssembler foodResourcesQuantitiesAssembler) {
    this.foodResourcesQuantitiesAssembler = foodResourcesQuantitiesAssembler;
  }

  public FoodResourcesReportResponse toResponse(FoodResourcesReport foodResourcesReport) {
    return new FoodResourcesReportResponse(
        this.foodResourcesQuantitiesAssembler.toResponse(foodResourcesReport.fresh),
        this.foodResourcesQuantitiesAssembler.toResponse(foodResourcesReport.expired),
        this.foodResourcesQuantitiesAssembler.toResponse(foodResourcesReport.consumed));
  }
}
