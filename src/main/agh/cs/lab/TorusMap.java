package agh.cs.lab;

import com.google.common.collect.TreeMultimap;

import java.util.*;

public class TorusMap implements IWorldMap, IAnimalChangeObserver, IAnimalDeadObserver {

    private final TreeMultimap<Vector2d, Animal> animals =
            TreeMultimap.create(new Vector2dComparator(), new AnimalComparator());

    private final Statistics statistics;

    private final GrassField grass;

    private final int height;
    private final int width;

    public TorusMap(int width, int height, float jungleRatio, Statistics statistics) {
        this.width = width;
        this.height = height;
        this.statistics = statistics;
        int jungleWidth = (int) (width * jungleRatio);
        int jungleHeight = (int) (height * jungleRatio);
        grass = new GrassField(width, height, jungleWidth, jungleHeight, this);
    }


    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getAmountOfGrass(){
        return grass.getAmountOfGrass();
    }


    public void eatGrass(float energyFromGrass){
        for(Vector2d position : animals.keySet()){
            Grass grass = this.grass.grassAt(position);
            if (grass != null) {
                NavigableSet<Animal> animalsOnField = this.animals.get(position);
                float maxEnergy = animalsOnField.last().getEnergy();
                long animalsWithSameEnergy = animalsOnField
                        .stream()
                        .filter(animal -> animal.getEnergy() == maxEnergy)
                        .count();

                float energyToAdd = energyFromGrass / animalsWithSameEnergy;
                animalsOnField
                        .stream()
                        .filter(animal -> animal.getEnergy() == maxEnergy)
                        .forEach(animal -> animal.addEnergy(energyToAdd));

                this.grass.removeGrass(grass);
            }
        }
    }

    public void addGrass(){
        grass.addGrass();
    }

    public Vector2d checkPosition(Vector2d newPosition) {
        int x = newPosition.x;
        int y = newPosition.y;

        if (x > width - 1) {
            x = 0;
        }
        if (x < 0) {
            x = width-1;
        }
        if (y > height - 1) {
            y = 0;
        }
        if (y < 0) {
            y = height-1;
        }

        return new Vector2d(x, y);
    }

    public ArrayList<Animal> copulate(float copulateEnergy, MutableLong id){
        ArrayList<Animal> newAnimals = new ArrayList<>();
        for( Vector2d position : this.animals.keySet()){
            NavigableSet<Animal> animalsOnField = this.animals.get(position);
            if( animalsOnField.size() > 1){
                Iterator<Animal> iterator = animalsOnField.descendingIterator();
                Animal parent1 = iterator.next();
                Animal parent2 = iterator.next();
                if(parent1.getEnergy() >= copulateEnergy && parent2.getEnergy() >= copulateEnergy){
                    Animal newAnimal = new Animal(this, parent1, parent2, id.getValue());
                    id.increment();
                    newAnimals.add(newAnimal);
                }
            }
        }

        return newAnimals;
    }

    public Animal animalAt(Vector2d position){
        if (animals.get(position).size() > 0)
            return animals.get(position).descendingIterator().next();
        else return null;
    }

    @Override
    public void place(Animal animal) {
        animals.put(animal.getPosition(), animal);
        animal.addMoveObserver(this);
        animal.addDeadObserver(this);
        animal.addDeadObserver(statistics);
        statistics.addGenotype(animal.getGenotype());
    }

    @Override
    public void animalDead(Animal animal) {
        animals.remove(animal.position, animal);
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Animal animal){
        animals.remove(oldPosition, animal);
        animals.put(animal.getPosition(), animal);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    @Override
    public AbstractWorldElement objectAt(Vector2d position) {
        if(animalAt(position) != null){
            return animalAt(position);
        }
        else{
            return grass.grassAt(position);
        }
    }
}
