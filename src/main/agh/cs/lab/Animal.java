package agh.cs.lab;

import javafx.util.Pair;

import java.util.*;

public class Animal extends AbstractWorldElement{

    private final List<IAnimalChangeObserver> moveObservers = new ArrayList<>();
    private final List<IAnimalDeadObserver> deadObservers = new ArrayList<>();

    private final static Random generator = new Random();

    private Pair<Animal,Animal> parents;

    protected final Genotype genotype;

    private MapDirection direction;

    private long childrenAmount;

    private final TorusMap map;

    private int lifeLength;

    private float energy;

    private final long id;


    public Animal(TorusMap map, Vector2d initialPosition, float startEnergy, Genotype genotype, long id){
        super(initialPosition);
        this.map = map;
        this.id = id;
        energy = startEnergy;
        direction = MapDirection.values()[generator.nextInt(8)];
        childrenAmount = 0;
        lifeLength = 0;
        this.genotype = Objects.requireNonNullElseGet(genotype, Genotype::new);
    }

    public Animal(TorusMap map, Animal parent1, Animal parent2, long id){
        this(map,childPosition(map,parent1), 0, new Genotype(parent1.getGenotype(), parent2.getGenotype()), id);
        this.parents = new Pair<>(parent1,parent2);
        transferEnergy(parent2, this);
        transferEnergy(parent1, this);
        parent2.newChild();
        parent1.newChild();
    }

    private static Vector2d childPosition(TorusMap map, Animal parent1) {
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

    public int getLifeLength() {
        return lifeLength;
    }

    public long getChildrenAmount() {
        return childrenAmount;
    }


    public void subtractEnergy(float amount){
        energy -= amount;
        energy = Math.max(energy, 0);
    }

    public void addEnergy(float amount){
        energy += amount;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return id == animal.id &&
                Objects.equals(genotype, animal.genotype);
    }

    @Override
    public int hashCode() {
        return Objects.hash(genotype, id);
    }
}
