package ca.ulaval.glo4002.game.resources.rest;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class FoodResourcesReportResponse {

  public final FoodResourcesQuantitiesResponse fresh;
  public final FoodResourcesQuantitiesResponse expired;
  public final FoodResourcesQuantitiesResponse consumed;

  public FoodResourcesReportResponse(
      FoodResourcesQuantitiesResponse fresh,
      FoodResourcesQuantitiesResponse expired,
      FoodResourcesQuantitiesResponse consumed) {
    this.fresh = fresh;
    this.expired = expired;
    this.consumed = consumed;
  }
}
