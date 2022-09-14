package ca.ulaval.glo4002.game.resources.domain;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class FoodResourcesReport {

  public final FoodResourcesQuantities fresh;
  public final FoodResourcesQuantities expired;
  public final FoodResourcesQuantities consumed;

  public FoodResourcesReport(
      FoodResourcesQuantities fresh,
      FoodResourcesQuantities expired,
      FoodResourcesQuantities consumed) {
    this.fresh = fresh;
    this.expired = expired;
    this.consumed = consumed;
  }
}
