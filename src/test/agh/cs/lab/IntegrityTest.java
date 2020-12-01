//package agh.cs.lab;
//
//import org.junit.Assert;
//import org.junit.Test;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import static junit.framework.TestCase.*;
//
//public class IntegrityTest {
//
//    RectangularMap map = new RectangularMap(10,5);
//
//    @Test
//    public void sameInitialLocationTest(){
//        Assert.assertThrows(IllegalArgumentException.class , () -> {
//            String[] moves = {};
//            MoveDirection[] directions = OptionsParser.parse(moves);
//            Vector2d[] initialPositions = {new Vector2d(2,2), new Vector2d(2,2)};
//            SimulationEngine engine = new SimulationEngine(directions , map, initialPositions);
//        });
//    }
//
//    @Test
//    public void sameAnimalLocationTest(){
//        String[] moves = {"f"};
//        MoveDirection[] directions = OptionsParser.parse(moves);
//        Vector2d[] initialPositions = {new Vector2d(2,2), new Vector2d(2,3)};
//        SimulationEngine engine = new SimulationEngine(directions , map, initialPositions);
//        engine.run();
//        List<AbstractWorldElement> animals = map.getAnimals();
//        assertEquals(animals.get(0).getPosition() , new Vector2d(2,2));
//    }
//
//    @Test
//    public void directionsTest(){
//        String[] moves = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
//        MoveDirection[] directions = OptionsParser.parse(moves);
//        Vector2d[] initialPositions = {new Vector2d(2,2), new Vector2d(3,4)};
//        SimulationEngine engine = new SimulationEngine(directions , map, initialPositions);
//
//        engine.run();
//        List<AbstractWorldElement> animals = map.getAnimals();
//        Animal a1 = (Animal) animals.get(0);
//        Animal a2 = (Animal) animals.get(1);
//
//        assertEquals(a2.getPosition() , new Vector2d(2,0));
//        assertEquals(a2.getDirection() , MapDirection.SOUTH);
//        assertEquals(a1.getPosition() , new Vector2d(3,4));
//        assertEquals(a1.getDirection() , MapDirection.NORTH);
//    }
//
//    @Test
//    public void IllegalPositionTest() {
//        Assert.assertThrows(IllegalArgumentException.class,
//                () -> {
//                    Vector2d[] initialPositions = new Vector2d[]{new Vector2d(-1, -1)};
//                    MoveDirection[] directions = new MoveDirection[]{};
//                    SimulationEngine engine = new SimulationEngine(directions, map, initialPositions);
//                    engine.run();
//                });
//    }
//
//    @Test
//    public void noAnimalTest(){
//        String[] moves = new String[]{"f", "b"};
//        MoveDirection[] directions = OptionsParser.parse(moves);
//        SimulationEngine engine = new SimulationEngine(directions, map, new Vector2d[]{});
//        boolean thrown = false;
//        try {
//            engine.run();
//        }
//        catch (IndexOutOfBoundsException e){
//            thrown = true;
//        }
//
//        assertFalse(thrown);
//    }
//}
