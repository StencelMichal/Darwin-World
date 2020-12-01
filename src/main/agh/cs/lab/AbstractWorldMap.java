package agh.cs.lab;

import com.google.common.collect.TreeMultimap;

import java.util.*;

abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {

    private final MapVisualizer mapVisualizer = new MapVisualizer(this);

    protected final TreeMultimap<Vector2d, Animal> animals =
            TreeMultimap.create(new Vector2dComparator(), new AnimalComparator());

  //  protected final List<Map<Vector2d, AbstractWorldElement>> listOfElements = new ArrayList<>();

//    public AbstractWorldMap() {
//        listOfElements.add(animalsMap);
//    }

    abstract public Vector2d checkPosition(Vector2d newPosition);

    public List<AbstractWorldElement> getAnimals() {
        List<AbstractWorldElement> animals = new ArrayList<>(this.animals.values());
        return Collections.unmodifiableList(animals);
    }
//
//    @Override
//    public Object objectAt(Vector2d position) {
//        for (Map<Vector2d, AbstractWorldElement> elements : listOfElements) {
//            if (elements.get(position) != null) {
//                return elements.get(position);
//            }
//        }
//        return null;
//    }

    @Override
    public boolean place(Animal animal) {
        animals.put(animal.getPosition(), animal);
        animal.addObserver(this);
        return true;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    public ArrayList<Animal> copulate(float energyToCopulate){
        ArrayList<Animal> newAnimals = new ArrayList<>();
        for( Vector2d position : this.animals.keys()){
            NavigableSet<Animal> animalsOnField = this.animals.get(position);
            if( animalsOnField.size() > 1){
                Iterator<Animal> iterator = animalsOnField.descendingIterator();
                Animal parent1 = iterator.next();
                Animal parent2 = iterator.next();
                if(parent1.getEnergy() >= energyToCopulate && parent2.getEnergy() >= energyToCopulate){
                    Animal newAnimal = new Animal(this, parent1, parent2);
                    newAnimals.add(newAnimal);
                }
            }
        }

        return newAnimals;
    }


//    @Override
//    public String toString() {
//        Vector2d lowerLeft = getLowerLeft();
//        Vector2d upperRight = getUpperRight();
//        return mapVisualizer.draw(lowerLeft, upperRight);
//    }

    @Override
    public void positionChanged(Vector2d oldPosition, Animal animal){
        animals.remove(oldPosition, animal);
        animals.put(animal.getPosition(), animal);
    }


}
