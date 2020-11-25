//package agh.cs.lab;
//
//import org.junit.Assert;
//import org.junit.Test;
//
//import static junit.framework.TestCase.*;
//
//public class GrassFieldTest {
//
//    @Test
//    public void upperRightTest(){
//        GrassField map = new GrassField(0);
//        String[] moves = {"f","f","f","r","f","f","f",};
//        MoveDirection[] directions = OptionsParser.parse(moves);
//        SimulationEngine engine = new SimulationEngine(directions, map, new Vector2d[]{new Vector2d(0,0)});
//        engine.run();
//        assertEquals(new Vector2d(3,3), map.getUpperRight());
//    }
//
//    @Test
//    public void lowerLeftTest(){
//        GrassField map = new GrassField(0);
//        String[] moves = {"b","b","b","r","b","b","b",};
//        MoveDirection[] directions = OptionsParser.parse(moves);
//        SimulationEngine engine = new SimulationEngine(directions, map, new Vector2d[]{new Vector2d(0,0)});
//        engine.run();
//        assertEquals(new Vector2d(-3,-3), map.getUpperRight());
//    }
//
//    @Test
//    public void ObjectAtTest(){
//        GrassField map = new GrassField(1);
//        Vector2d position = map.getGrassList().get(0).getPosition();
//        MoveDirection[] directions = {};
//        SimulationEngine engine = new SimulationEngine(directions, map, new Vector2d[]{position});
//        Object o = map.objectAt(position);
//        assertEquals(o.getClass() , Animal.class);
//    }
//
//    @Test
//    public void canMovetoTest(){
//        Assert.assertThrows(IllegalArgumentException.class, () -> {
//            GrassField map = new GrassField(10);
//            Animal animal = new Animal(map);
//            MoveDirection[] directions = {};
//            new SimulationEngine(directions, map, new Vector2d[]{new Vector2d(2, 2), new Vector2d(2, 2)});
//                });
//
//    }
//
//    @Test
//    public void grassAmountTest(){
//        GrassField map = new GrassField(10);
//        assertEquals(map.getGrassList().size() , 10);
//    }
//
//    @Test
//    public void animalOverTheGrassTest(){
//        GrassField map = new GrassField(1);
//        Vector2d position = map.getGrassList().get(0).getPosition();
//        MoveDirection[] directions = {MoveDirection.FORWARD};
//        SimulationEngine engine = new SimulationEngine(directions, map, new Vector2d[]{new Vector2d(position.x, position.y - 1)});
//        engine.run();
//        assertEquals(map.objectAt(new Vector2d(position.x, position.y)).getClass(), Animal.class);
//    }
//}
