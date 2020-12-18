package agh.cs.lab;

import javafx.util.Pair;

import java.util.HashSet;

public class AnimalTracker implements IAnimalDeadObserver {

    private final HashSet<Animal> Descendants = new HashSet<>();

    private int childrenBeforeTracking;

    private Animal trackedAnimal;

    private final MutableInt day;

    private int dayOfDeath;

    private boolean dead;

    public AnimalTracker(MutableInt days) {
        this.day = days;
    }

    public void track(Animal animal) {
        this.trackedAnimal = animal;
        childrenBeforeTracking = (int) animal.getChildrenAmount();
        Descendants.clear();
        Descendants.add(animal);
        dead = false;
    }

    public void checkIfDescendant(Animal animal) {
        Pair<Animal, Animal> parents = animal.getParents();
        Animal parent1 = parents.getKey();
        Animal parent2 = parents.getValue();
        if (parent1 != null) {
            if (Descendants.contains(parent1)) {
                Descendants.add(animal);
            }
        }
        if (parent2 != null) {
            if (Descendants.contains(parent2)) {
                Descendants.add(animal);
            }
        }
    }

    @Override
    public String toString() {
        String ret = "Tracking animal:" + "\n" +
                     "Amount of children: " + (trackedAnimal.getChildrenAmount() - childrenBeforeTracking) + "\n" +
                     "Amount of descendants: " + (Descendants.size() - 1) + "\n";
        if (dead) {
            ret += "Day of death: " + dayOfDeath + "\n";
        }

        return ret;
    }

    @Override
    public void animalDead(Animal animal) {
        if (animal == trackedAnimal) {
            dayOfDeath = day.getValue();
            dead = true;
        }
    }
}
