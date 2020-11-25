package agh.cs.lab;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SimulationEngine implements IEngine{

    private final List<Animal> animals = new LinkedList<>();

    private final AbstractWorldMap map;
    public SimulationEngine(TorusMap map, int amountOfAnimals){
        this.map = map;
        for(int i=0; i<amountOfAnimals; i++){
            Animal animal = new Animal(map);
            map.place(animal);
            animals.add(animal);
        }
    }

    @Override
    public void run() {
        while (true) {

            //usuwanie martwych zwierzat

            //aniaml move
            for (Animal animal : animals) {
                animal.move();
            }
            // jedzenie

            // rozmanżanie

            //dodanie nowych roślin
            map.addGrass();
            System.out.println(map);
        }
    }
}
