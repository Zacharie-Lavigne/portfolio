package ca.ulaval.glo4002.game.resources.domain;

import ca.ulaval.glo4002.game.resources.domain.food.Pantry;

public interface PantryRepository {
  Pantry getPantry();

  void update(Pantry pantry);
}
