package agh.cs.lab;

import org.junit.Assert;
import org.junit.Test;

public class AnimalDeadTest {

    @Test
    public void animalDeadTest(){
        Statistics statistics = new Statistics(1,new MutableInt());
        TorusMap map = new TorusMap(2, 2, (float) 0.5, statistics);
        Vector2d position = new Vector2d(1, 1);
        Animal animal = new Animal(map, position, 0);
        map.place(animal);
        Assert.assertEquals(1, map.animals.get(position).size());
        animal.checkIfDead();
        Assert.assertEquals(0, map.animals.get(position).size());

    }
}
