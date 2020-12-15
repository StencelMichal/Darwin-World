package agh.cs.lab;

import java.util.*;

public class SimulationEngine implements IEngine{

    private final List<Animal> animals = new LinkedList<>();

    private final float moveEnergy;

    private final float energyFromGrass;

    private final float startEnergy;

    private final TorusMap map;

    public SimulationEngine(TorusMap map, int amountOfAnimals, float moveEnergy, float startEnergy, float plantEnergy){
        this.moveEnergy = moveEnergy;
        this.startEnergy = startEnergy;
        this.energyFromGrass = plantEnergy;
        this.map = map;
        Random generator = new Random();
        for(int i=0; i<amountOfAnimals; i++){
            // tylko 1 zwierze na 1 pozycje
            int x = generator.nextInt(map.getWidth());
            int y = generator.nextInt(map.getHeight());
            Vector2d initialPosition = new Vector2d(x, y);
            Animal animal = new Animal(map, initialPosition, startEnergy);
            map.place(animal);
            animals.add(animal);
        }
    }

    @Override
    public void nextDay() {


        // Remove dead animals
//        System.out.println("usuwanie");
        animals.removeIf(Animal::checkIfDead);

        //animal move
//        System.out.println("ruch");
        for (Animal animal : animals) {
            animal.move();
        }

        // jedzenie
//        System.out.println("jedzenie");
        map.eatGrass(energyFromGrass);

        // rozmanżanie
//        System.out.println("rozmnażanie");
        ArrayList<Animal> newAnimals = map.copulate(startEnergy / 2);
        for (Animal newAnimal : newAnimals) {
            map.place(newAnimal);
        }
        animals.addAll(newAnimals);

        //dodanie nowych roślin
//        System.out.println("dodwanie trawy");
        map.addGrass();

        //utrata energii przez zwierzeta
//        System.out.println("utrata energii");
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
