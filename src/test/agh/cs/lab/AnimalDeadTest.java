package agh.cs.lab;

import org.junit.Assert;
import org.junit.Test;

public class AnimalDeadTest {

    @Test
    public void animalDeadTest(){
        Statistics statistics = new Statistics(1,new MutableInt());
        TorusMap map = new TorusMap(2, 2, (float) 0.5, statistics);
        SimulationEngine simulationEngine = new SimulationEngine
                (map, 1, 1, 0, 1, new AnimalTracker(new MutableInt(1)));
        Assert.assertEquals(1, simulationEngine.getAnimals().size());
        simulationEngine.getAnimals().get(0).checkIfDead();
        System.out.println(simulationEngine.getAnimals().get(0).getEnergy());
        Assert.assertEquals(0, simulationEngine.getAnimals().size());

    }
}
