package agh.cs.lab;

import com.google.common.collect.TreeMultimap;

import java.io.IOException;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.Random;

public class World {

    public static void main(String[] args) throws IOException {

        int amountOfAnimals = 4;
        TorusMap map = new TorusMap(3,3,1,1);
        SimulationEngine engine = new SimulationEngine(map, amountOfAnimals, (float) 0.1,3,3);
        System.out.println(map);
//        //Visualizer visualizer = new Visualizer(10, 10);
//        //visualizer.change();
        engine.run();
//        System.out.println(map);
//        Random generator = new Random();
//        for(int i=0; i<50; i++){
//            int cut1 = generator.nextInt(30) + 1;
//            int cut2 = generator.nextInt(32 - cut1 - 1) + 1 + cut1;
//            System.out.println(cut1 +"  " + cut2);
//        }
//        TreeMultimap<Vector2d, Animal> animals =
//                TreeMultimap.create(new Vector2dComparator(), new AnimalComparator());
//        Animal a1 = new Animal(map, new Vector2d(2, 2), 10);
//        Animal a2 = new Animal(map, new Vector2d(2, 2), 20);
//        Animal a3 = new Animal(map, new Vector2d(2, 2), 5);
//        Animal a4 = new Animal(map, new Vector2d(2, 2), 50);
//        Animal a5 = new Animal(map, new Vector2d(2, 2), 20);
//
//        animals.put(a1.getPosition(),a1);
//        animals.put(a2.getPosition(),a2);
//        animals.put(a3.getPosition(),a3);
//        animals.put(a4.getPosition(),a4);
//        animals.put(a5.getPosition(),a5);
//
//        NavigableSet<Animal> animals1 = animals.get(new Vector2d(2, 2));
//        for (Animal animal : animals1) {
//            System.out.println(animal.getEnergy());
//        }
//        System.out.println();
//        Iterator<Animal> animalIterator = animals1.descendingIterator();
////        while(animalIterator.hasNext()){
////            Animal next = animalIterator.next();
////            System.out.println(next.getEnergy());
////        }
//        System.out.println(animalIterator.next().getEnergy());
//        System.out.println(animalIterator.next().getEnergy());
//        Animal b1 = animals1.last();
//        Animal b2 = animals1.lower(b1);
//        Animal b3 = animals1.lower(b2);
//        Animal b4 = animals1.lower(b3);
//        Animal b5 = animals1.lower(b4);
//        System.out.println(b1.getEnergy());
//        System.out.println(b2.getEnergy());
//        System.out.println(b3.getEnergy());
//        System.out.println(b4.getEnergy());
//        System.out.println(b5.getEnergy());
    }
}
