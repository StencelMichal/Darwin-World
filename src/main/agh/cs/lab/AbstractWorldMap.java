package agh.cs.lab;

//import com.google.common.collect.ArrayListMultimap;
//import com.google.common.collect.Multimap;

import java.util.*;

abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {

    private final MapVisualizer mapVisualizer = new MapVisualizer(this);

    private final Map<Vector2d,AbstractWorldElement> animalsMap = new HashMap<>();

    protected final List<Map<Vector2d, AbstractWorldElement>> listOfElements = new ArrayList<>();

    public AbstractWorldMap(){
        listOfElements.add(animalsMap);
    }

    abstract public Vector2d getUpperRight();

    abstract public Vector2d getLowerLeft();

    abstract public Vector2d checkPosition( Vector2d newPosition);

    abstract public void addGrass();

    public List<AbstractWorldElement> getAnimals() {
        List<AbstractWorldElement> animals = new ArrayList<>(animalsMap.values());
        return Collections.unmodifiableList(animals);
    }

    @Override
    public Object objectAt(Vector2d position) {
        for (Map<Vector2d, AbstractWorldElement> elements : listOfElements) {
            if(elements.get(position) != null){
                return elements.get(position);
            }
        }
        return null;
    }

    @Override
    public boolean place(Animal animal) {
        animalsMap.put(animal.getPosition() , animal);
        animal.addObserver(this);
        return true;

//        if(canMoveTo(animal.getPosition())){
//            animalsMap.put(animal.getPosition() , animal);
//            return true;
//        }
//        else{
//            throw new IllegalArgumentException("Cannot create animal on position: " + animal.getPosition());
//        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }


    @Override
    public String toString() {
        Vector2d lowerLeft = getLowerLeft();
        Vector2d upperRight = getUpperRight();
        return mapVisualizer.draw(lowerLeft , upperRight);
    }

    public void positionChanged(Vector2d oldPosition , Vector2d newPosition){
        animalsMap.put(newPosition , animalsMap.remove(oldPosition));
    }


}
