package agh.cs.lab;

public class World {

    public static void main(String[] args) {

    int amountOfAnimals = 1;
    TorusMap map = new TorusMap(10,10,3,3);
    SimulationEngine engine = new SimulationEngine(map, amountOfAnimals);
    System.out.println(map);
    engine.run();
    System.out.println(map);



    }
}
