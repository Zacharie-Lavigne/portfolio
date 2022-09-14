package ca.ulaval.glo4002.game.resources.infrastructure.inMemory;

import ca.ulaval.glo4002.game.resources.WaterTanks;
import ca.ulaval.glo4002.game.resources.domain.PantryRepository;
import ca.ulaval.glo4002.game.resources.domain.food.FreshFoodResources;
import ca.ulaval.glo4002.game.resources.domain.food.Pantry;
import ca.ulaval.glo4002.game.resources.domain.food.WaterDistribution;
import ca.ulaval.glo4002.game.resources.domain.food.WaterTank;
import org.springframework.stereotype.Component;

@Component
public class InMemoryPantryRepository implements PantryRepository {
  private Pantry pantry =
      new Pantry(
          new FreshFoodResources(
              new WaterDistribution(new WaterTanks(new WaterTank(), new WaterTank()))));

  @Override
  public Pantry getPantry() {
    return this.pantry;
  }

  @Override
  public void update(Pantry pantry) {
    this.pantry = pantry;
  }
}
