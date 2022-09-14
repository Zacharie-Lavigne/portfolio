package ca.ulaval.glo4002.game.dinosaurs.domain;

import ca.ulaval.glo4002.game.dinosaurs.domain.exceptions.InvalidSpeciesException;
import io.vavr.collection.Stream;
import java.util.Objects;

public enum DinosaurSpecies {
  ANKYLOSAURUS("Ankylosaurus", ConsumptionPreference.HERBIVOROUS),
  BRACHIOSAURUS("Brachiosaurus", ConsumptionPreference.HERBIVOROUS),
  DIPLODOCUS("Diplodocus", ConsumptionPreference.HERBIVOROUS),
  STEGOSAURUS("Stegosaurus", ConsumptionPreference.HERBIVOROUS),
  TRICERATOPS("Triceratops", ConsumptionPreference.HERBIVOROUS),
  ALLOSAURUS("Allosaurus", ConsumptionPreference.CARNIVOROUS),
  MEGALOSAURUS("Megalosaurus", ConsumptionPreference.CARNIVOROUS),
  SPINOSAURUS("Spinosaurus", ConsumptionPreference.CARNIVOROUS),
  TYRANNOSAURUS_REX("Tyrannosaurus Rex", ConsumptionPreference.CARNIVOROUS),
  VELOCIRAPTOR("Velociraptor", ConsumptionPreference.CARNIVOROUS),
  EORAPTOR("Eoraptor", ConsumptionPreference.OMNIVOROUS),
  GIGANTORAPTOR("Gigantoraptor", ConsumptionPreference.OMNIVOROUS),
  HETERODONTOSAURUS("Heterodontosaurus", ConsumptionPreference.OMNIVOROUS),
  ORNITHOMIMUS("Ornithomimus", ConsumptionPreference.OMNIVOROUS),
  STRUTHIOMIMUS("Struthiomimus", ConsumptionPreference.OMNIVOROUS);

  private final String label;
  private final ConsumptionPreference consumptionPreference;

  DinosaurSpecies(String label, ConsumptionPreference consumptionPreference) {
    this.label = label;
    this.consumptionPreference = consumptionPreference;
  }

  public String getLabel() {
    return this.label;
  }

  public ConsumptionPreference getConsumptionPreference() {
    return this.consumptionPreference;
  }

  public static DinosaurSpecies fromLabel(String label) {
    return Stream.of(DinosaurSpecies.values())
        .filter(dinosaurSpecie -> Objects.equals(dinosaurSpecie.label, label))
        .getOrElseThrow(InvalidSpeciesException::new);
  }

  public double getStrengthFactor() {
    return this.consumptionPreference.getStrengthFactor();
  }
}
