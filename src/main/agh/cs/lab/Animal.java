package agh.cs.lab;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Animal extends AbstractWorldElement{

    private final List<IPositionChangeObserver> observers = new ArrayList<>();

    private MapDirection direction;

    private final static Random generator = new Random();

    private final AbstractWorldMap map;

    protected final Genotype genotype;

    private float energy;

    public Animal(AbstractWorldMap map, Vector2d initialPosition, float startEnergy){
        super(initialPosition);
        this.map = map;
        energy = startEnergy;
        genotype = new Genotype();
        direction = MapDirection.values()[generator.nextInt(8)];
    }

    public Animal(AbstractWorldMap map, Animal parent1, Animal parent2){
        super(childPosition(map, parent1));
        this.map = map;
        this.direction = MapDirection.values()[generator.nextInt(8)];
        this.genotype = new Genotype(parent1.getGenotype(), parent2.getGenotype());
        this.energy = 0;
        transferEnergy(parent1, this);
        transferEnergy(parent2, this);
    }

    private static Vector2d childPosition(AbstractWorldMap map, Animal parent1) {
        Vector2d parentPosition = parent1.getPosition();
        Vector2d childPosition = null;
        for(MapDirection direction : MapDirection.values()){
            Vector2d possibleDirection = parentPosition.add(direction.toUnitVector());
            if(!map.isOccupied(possibleDirection)){
                childPosition = possibleDirection;
                break;
            }
        }
        if(childPosition == null){
            Vector2d direction = MapDirection.values()[generator.nextInt(8)].toUnitVector();
            childPosition = parentPosition.add(direction);
        }
        return childPosition;
    }

    private void transferEnergy(Animal parent, Animal child){
        float energy = parent.getEnergy() / 4;
        parent.subtractEnergy(energy);
        child.addEnergy(energy);
    }

    public Genotype getGenotype() {
        // niemodyfikowalność ??
        return genotype;
    }


    public MapDirection getDirection() {
        return direction;
    }

    public Vector2d getPosition() {
        return position;
    }

    public float getEnergy() {
        return energy;
    }

    public void subtractEnergy(float amount){
        energy -= amount;
        energy = Math.max(energy, 0);
    }

    public void addEnergy(float amount){
        energy += amount;
    }

    @Override
    public String toString() {
        return direction.toString();
    }

    public void move(){

        for(int i=0; i<genotype.randomGene(); i++){
            direction = direction.next();
        }

        Vector2d newPosition = position.add(direction.toUnitVector());
        Vector2d positionBeforeMove = position;
        position = map.checkPosition(newPosition);
        positionChanged(positionBeforeMove);
    }

    public void addObserver(IPositionChangeObserver observer){
        observers.add(observer);
    }

    void removeObserver(IPositionChangeObserver observer){
        observers.remove(observer);
    }


    private void positionChanged(Vector2d oldPosition){
        for (IPositionChangeObserver observer : observers) {
            observer.positionChanged(oldPosition, this);
        }
    }


}
