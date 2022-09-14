package ca.ulaval.glo4002.game.resources.domain.food;

public class FoodResourceByTurn {
  private final int turn;
  private final FoodResource foodResource;

  public FoodResourceByTurn(int currentTurn, int quantity, FoodType foodType) {
    this.turn = currentTurn;
    this.foodResource = new FoodResource(foodType, quantity);
  }

  public void remove(int quantity) {
    this.foodResource.remove(quantity);
  }

  public boolean isType(FoodType foodType) {
    return this.foodResource.getFoodType() == foodType;
  }

  public int getTurn() {
    return this.turn;
  }

  public int getQuantity() {
    return this.foodResource.getQuantity();
  }
}
