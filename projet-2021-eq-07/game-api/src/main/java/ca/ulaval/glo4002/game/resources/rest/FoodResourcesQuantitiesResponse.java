package ca.ulaval.glo4002.game.resources.rest;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class FoodResourcesQuantitiesResponse {

  public final int qtyBurger;
  public final int qtySalad;
  public final int qtyWater;

  public FoodResourcesQuantitiesResponse(int qtyBurger, int qtySalad, int qtyWater) {
    this.qtyBurger = qtyBurger;
    this.qtySalad = qtySalad;
    this.qtyWater = qtyWater;
  }
}
