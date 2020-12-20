package agh.cs.lab;

import java.util.*;

public class SimulationEngine implements IEngine{

    private final List<Animal> animals = new LinkedList<>();

    private final float moveEnergy;

    private final float energyFromGrass;

    private final float copulateEnergy;

    private final TorusMap map;

    private final AnimalTracker tracker;

    public SimulationEngine(TorusMap map, int startAnimals, float moveEnergy,
                            float startEnergy, float plantEnergy, AnimalTracker tracker){
        this.tracker = tracker;
        this.moveEnergy = moveEnergy;
        this.energyFromGrass = plantEnergy;
        this.map = map;
        this.copulateEnergy = startEnergy / 2;

        if(map.getHeight() * map.getWidth() < startAnimals){
            throw new IllegalArgumentException("Map is to small");
        }

        Random generator = new Random();
        HashSet<Vector2d> positions = new HashSet<>();
        while(positions.size() < startAnimals){
            int x = generator.nextInt(map.getWidth());
            int y = generator.nextInt(map.getHeight());
            positions.add(new Vector2d(x, y));
        }

        for (Vector2d position : positions) {
            Animal animal = new Animal(map, position, startEnergy, null);
            animal.addDeadObserver(tracker);
            map.place(animal);
            animals.add(animal);
        }
    }

    @Override
    public void nextDay() {


        // Remove dead animals
        animals.removeIf(Animal::checkIfDead);

        //move
        for (Animal animal : animals) {
            animal.move();
        }

        // eat
        map.eatGrass(energyFromGrass);

        // copulate
        ArrayList<Animal> newAnimals = map.copulate(copulateEnergy);
        for (Animal newAnimal : newAnimals) {
            map.place(newAnimal);
            newAnimal.addDeadObserver(tracker);
            tracker.checkIfDescendant(newAnimal);
        }
        animals.addAll(newAnimals);

        // new plants
        map.addGrass();

        // subtract move energy
        for (Animal animal : animals) {
            animal.subtractEnergy(moveEnergy);
        }

        for (Animal animal : animals) {
            animal.nextDay();
        }

    }

    public List<Animal> getAnimals() {
        return Collections.unmodifiableList(animals);
    }

    public int getCurrentAmountOfGrass(){
        return map.getAmountOfGrass();
    }
}
