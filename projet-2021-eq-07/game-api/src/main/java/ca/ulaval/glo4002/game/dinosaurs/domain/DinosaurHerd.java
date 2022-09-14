package ca.ulaval.glo4002.game.dinosaurs.domain;

import ca.ulaval.glo4002.game.dinosaurs.useCases.exceptions.NonExistingNameException;
import io.vavr.collection.List;
import java.util.NoSuchElementException;

public class DinosaurHerd {
  private List<Dinosaur> dinosaurs;

  public DinosaurHerd(List<Dinosaur> dinosaurs) {
    this.dinosaurs = dinosaurs;
  }

  public void addDinosaur(Dinosaur dinosaur) {
    if (!this.containsName(dinosaur.getName())) {
      this.dinosaurs = this.dinosaurs.append(dinosaur);
    }
  }

  public Dinosaur getByName(String name) {
    try {
      return this.dinosaurs.find(dinosaur -> dinosaur.getName().equals(name)).get();
    } catch (NoSuchElementException e) {
      throw new NonExistingNameException();
    }
  }

  public boolean containsName(String name) {
    return this.dinosaurs.find(dinosaur -> dinosaur.getName().equals(name)).isDefined();
  }

  public List<Dinosaur> getDinosaurs() {
    return this.dinosaurs;
  }

  public List<Dinosaur> getAliveDinosaurs() {
    return this.dinosaurs.filter(Dinosaur::isAlive);
  }

  public void updateDinosaurs(List<Dinosaur> dinosaurs) {
    this.dinosaurs = dinosaurs;
  }

  public void deleteAll() {
    this.dinosaurs = List.empty();
  }

  public void removeDead() {
    this.dinosaurs = this.getAliveDinosaurs();
  }
}
