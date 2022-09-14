package ca.ulaval.glo4002.game.resources.domain.food;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class FoodResource {
  private final FoodType foodType;
  private int quantity;

  protected FoodResource(FoodType foodType, int quantity) {
    this.foodType = foodType;
    this.quantity = quantity;
  }

  public void remove(int quantity) {
    this.quantity = Math.max(this.quantity - quantity, 0);
  }

  public FoodType getFoodType() {
    return this.foodType;
  }

  public int getQuantity() {
    return this.quantity;
  }
}
