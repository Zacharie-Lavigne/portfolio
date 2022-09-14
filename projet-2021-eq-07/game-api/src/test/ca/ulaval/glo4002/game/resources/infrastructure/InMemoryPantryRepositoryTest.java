package ca.ulaval.glo4002.game.resources.infrastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.ulaval.glo4002.game.resources.domain.food.FreshFoodResourcesFixture;
import ca.ulaval.glo4002.game.resources.domain.food.Pantry;
import ca.ulaval.glo4002.game.resources.infrastructure.inMemory.InMemoryPantryRepository;
import org.junit.jupiter.api.Test;

class InMemoryPantryRepositoryTest {
  private final InMemoryPantryRepository inMemoryFoodResourceRepository =
      new InMemoryPantryRepository();

  @Test
  public void givenAPantry_whenUpdating_thenShouldUpdatePantry() {
    Pantry pantry = new Pantry(FreshFoodResourcesFixture.createDefault());

    inMemoryFoodResourceRepository.update(pantry);

    assertEquals(pantry, inMemoryFoodResourceRepository.getPantry());
  }
}
