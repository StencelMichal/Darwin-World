package agh.cs.lab;

import com.google.common.collect.TreeMultimap;

import java.util.*;

abstract class AbstractWorldMap implements IWorldMap, IAnimalChangeObserver, IAnimalDeadObserver {

    private final MapVisualizer mapVisualizer = new MapVisualizer(this);


    protected final TreeMultimap<Vector2d, Animal> animals =
            TreeMultimap.create(new Vector2dComparator(), new AnimalComparator());

    abstract public Vector2d checkPosition(Vector2d newPosition);

    public List<AbstractWorldElement> getAnimals() {
        List<AbstractWorldElement> animals = new ArrayList<>(this.animals.values());
        return Collections.unmodifiableList(animals);
    }

    public Animal animalAt(Vector2d position){
        if (animals.get(position).size() > 0)
            return animals.get(position).descendingIterator().next();
        else return null;
    }



    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    public ArrayList<Animal> copulate(float copulateEnergy){
        ArrayList<Animal> newAnimals = new ArrayList<>();
        for( Vector2d position : this.animals.keySet()){
            NavigableSet<Animal> animalsOnField = this.animals.get(position);
            if( animalsOnField.size() > 1){
                Iterator<Animal> iterator = animalsOnField.descendingIterator();
                Animal parent1 = iterator.next();
                Animal parent2 = iterator.next();
                if(parent1.getEnergy() >= copulateEnergy && parent2.getEnergy() >= copulateEnergy){
                    Animal newAnimal = new Animal(this, parent1, parent2);
                    newAnimals.add(newAnimal);
                }
            }
        }

        return newAnimals;
    }


    @Override
    public void positionChanged(Vector2d oldPosition, Animal animal){
        animals.remove(oldPosition, animal);
        animals.put(animal.getPosition(), animal);
    }

    @Override
    public void animalDead(Animal animal) {
        animals.get(animal.getPosition()).remove(animal);
    }
}
