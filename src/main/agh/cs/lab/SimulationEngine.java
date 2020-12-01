package agh.cs.lab;

import java.util.*;

public class SimulationEngine implements IEngine{

    private final List<Animal> animals = new LinkedList<>();

    private final float energyToSubtract;

    private final float energyFromGrass;

    private final float startEnergy;

    private final TorusMap map;

    public SimulationEngine(TorusMap map, int amountOfAnimals, float energyToSubtract, float startEnergy, float energyFromGrass){
        this.energyToSubtract = energyToSubtract;
        this.startEnergy = startEnergy;
        this.energyFromGrass = energyFromGrass;
        this.map = map;
        Random generator = new Random();
        for(int i=0; i<amountOfAnimals; i++){
            int x = generator.nextInt(map.getWidth());
            int y = generator.nextInt(map.getHeight());
            Vector2d initialPosition = new Vector2d(x, y);
            Animal animal = new Animal(map, initialPosition, startEnergy);
            map.place(animal);
            animals.add(animal);
        }
    }

    @Override
    public void run() {
        while (true) {

            //test
            for (Animal animal : animals) {
                System.out.println(animal.getEnergy());
            }
            System.out.println();
            //

            //usuwanie martwych zwierzat
//            Iterator<Animal> iterator = animals.iterator();
//            while(iterator.hasNext()){
//
//            }
            //animal.removeObserver(map);
            //usuwanie z hashmapy
            System.out.println("usuwanie");
            animals.removeIf(animal -> animal.getEnergy() == 0);

            //aniaml move
            System.out.println("ruch");
            for (Animal animal : animals) {
                animal.move();
            }
            // jedzenie
            System.out.println("jedzenie");
            map.eatGrass(energyFromGrass);

            // rozmanżanie
            System.out.println("rozmnażanie");
            ArrayList<Animal> newAnimals = map.copulate(startEnergy / 2);
            for (Animal newAnimal : newAnimals) {
                map.place(newAnimal);
            }
            animals.addAll(newAnimals);

            //dodanie nowych roślin
            System.out.println("dodwanie trawy");
            map.addGrass();

            //utrata energii przez zwierzeta
            System.out.println("utrata energii");
            for (Animal animal : animals) {
                animal.subtractEnergy(energyToSubtract);
            }

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
