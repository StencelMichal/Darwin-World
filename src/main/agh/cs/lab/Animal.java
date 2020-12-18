package agh.cs.lab;

import javafx.util.Pair;

import java.util.*;

public class Animal extends AbstractWorldElement{

    private final List<IAnimalChangeObserver> moveObservers = new ArrayList<>();

    private final List<IAnimalDeadObserver> deadObservers = new ArrayList<>();

    private Pair<Animal,Animal> parents;

    private final static Random generator = new Random();

    private final AbstractWorldMap map;

    protected final Genotype genotype;

    private MapDirection direction;

    private long childrenAmount = 0;

    private int lifeLength = 0;

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
        this.parents = new Pair<>(parent1,parent2);
        this.map = map;
        this.direction = MapDirection.values()[generator.nextInt(8)];
        this.genotype = new Genotype(parent1.getGenotype(), parent2.getGenotype());
        this.energy = 0;
        this.childrenAmount = 0;
        lifeLength = 0;
        transferEnergy(parent1, this);
        transferEnergy(parent2, this);
        parent1.newChild();
        parent2.newChild();
    }

    private static Vector2d childPosition(AbstractWorldMap map, Animal parent1) {
        Vector2d parentPosition = parent1.getPosition();
        Vector2d childPosition = null;
        for(MapDirection direction : MapDirection.randomizedDirections()){
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

    public Pair<Animal, Animal> getParents() {
        return parents;
    }



    public void subtractEnergy(float amount){
        energy -= amount;
        energy = Math.max(energy, 0);
    }

    public void addEnergy(float amount){
        energy += amount;
    }

    public int getLifeLength() {
        return lifeLength;
    }

    public long getChildrenAmount() {
        return childrenAmount;
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

    public void newChild(){
        childrenAmount++;
    }

    public void nextDay(){
        lifeLength++;
    }

    public void addMoveObserver(IAnimalChangeObserver observer){
        moveObservers.add(observer);
    }

    public void addDeadObserver(IAnimalDeadObserver observer){
        deadObservers.add(observer);
    }

    void removeObserver(IAnimalChangeObserver observer){
        moveObservers.remove(observer);
    }

    private void positionChanged(Vector2d oldPosition){
        for (IAnimalChangeObserver observer : moveObservers) {
            observer.positionChanged(oldPosition, this);
        }
    }

    public boolean checkIfDead(){
        if(energy == 0){
            for (IAnimalDeadObserver observer : deadObservers) {
                observer.animalDead(this);
            }
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return direction.toString();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return Objects.equals(parents, animal.parents) &&
                Objects.equals(genotype, animal.genotype);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parents, genotype);
    }
}
