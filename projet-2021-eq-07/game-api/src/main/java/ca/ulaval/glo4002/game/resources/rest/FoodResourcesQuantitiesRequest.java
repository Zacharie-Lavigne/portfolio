package ca.ulaval.glo4002.game.resources.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FoodResourcesQuantitiesRequest {

  public final int qtyBurger;
  public final int qtySalad;
  public final int qtyWater;

  public FoodResourcesQuantitiesRequest(
      @JsonProperty("qtyBurger") int qtyBurger,
      @JsonProperty("qtySalad") int qtySalad,
      @JsonProperty("qtyWater") int qtyWater) {
    this.qtyBurger = qtyBurger;
    this.qtySalad = qtySalad;
    this.qtyWater = qtyWater;
  }
}
