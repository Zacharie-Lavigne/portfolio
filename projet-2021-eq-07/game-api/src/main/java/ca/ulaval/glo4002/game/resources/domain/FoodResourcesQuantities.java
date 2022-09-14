package ca.ulaval.glo4002.game.resources.domain;

import ca.ulaval.glo4002.game.resources.domain.food.FoodType;
import io.vavr.collection.HashMap;
import io.vavr.collection.Map;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class FoodResourcesQuantities {
  private Map<FoodType, Integer> quantities;

  public FoodResourcesQuantities(int qtyBurger, int qtySalad, int qtyWater) {
    this.quantities =
        HashMap.of(FoodType.BURGER, qtyBurger, FoodType.SALAD, qtySalad, FoodType.WATER, qtyWater);
  }

  public FoodResourcesQuantities() {
    this.quantities = HashMap.of(FoodType.BURGER, 0, FoodType.SALAD, 0, FoodType.WATER, 0);
  }

  public void removeFoodResource(FoodType foodType, int quantity) {
    int newQuantity = Math.max(this.getQuantityFor(foodType) - quantity, 0);
    this.quantities = this.quantities.replaceValue(foodType, newQuantity);
  }

  public void addFoodResource(FoodType foodType, int quantity) {
    int newQuantity = this.getQuantityFor(foodType) + quantity;
    this.quantities = this.quantities.replaceValue(foodType, newQuantity);
  }

  public boolean areAllResourcesEmpty() {
    return this.quantities.find(quantity -> quantity._2 != 0).isEmpty();
  }

  public int getQuantityFor(FoodType foodType) {
    return this.quantities.get(foodType).getOrElse(0);
  }
}
