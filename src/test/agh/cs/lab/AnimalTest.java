package agh.cs.lab;

import org.junit.Assert;
import org.junit.Test;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class AnimalTest {

//    private final RectangularMap map = new RectangularMap(4,4);
//    private final Animal animal = new Animal(map);
//
//
//    @Test
//    public void directionLeftTest(){
//        animal.move(MoveDirection.LEFT);
//        assertEquals( animal.getDirection(), MapDirection.WEST);
//        animal.move(MoveDirection.LEFT);
//        assertEquals( animal.getDirection(), MapDirection.SOUTH);
//        animal.move(MoveDirection.LEFT);
//        assertEquals( animal.getDirection(), MapDirection.EAST);
//        animal.move(MoveDirection.LEFT);
//        assertEquals( animal.getDirection(), MapDirection.NORTH);
//    }
//
//    @Test
//    public void directionRightTest(){
//        animal.move(MoveDirection.RIGHT);
//        assertEquals( animal.getDirection(), MapDirection.EAST);
//        animal.move(MoveDirection.RIGHT);
//        assertEquals( animal.getDirection(), MapDirection.SOUTH);
//        animal.move(MoveDirection.RIGHT);
//        assertEquals( animal.getDirection(), MapDirection.WEST);
//        animal.move(MoveDirection.RIGHT);
//        assertEquals( animal.getDirection(), MapDirection.NORTH);
//    }
//
//    @Test
//    public void upperBoundBackwardTest(){
//        animal.move(MoveDirection.BACKWARD);
//        animal.move(MoveDirection.BACKWARD);
//        animal.move(MoveDirection.BACKWARD);
//        assertEquals(animal.getPosition() , new Vector2d(2,0));
//    }
//
//    @Test
//    public void downBoundBackwardTest(){
//        animal.move(MoveDirection.LEFT);
//        animal.move(MoveDirection.LEFT);
//        animal.move(MoveDirection.BACKWARD);
//        animal.move(MoveDirection.BACKWARD);
//        animal.move(MoveDirection.BACKWARD);
//        assertEquals(animal.getPosition() , new Vector2d(2,3));
//    }
//
//    @Test
//    public void leftBoundBackwardTest(){
//        animal.move(MoveDirection.LEFT);
//        animal.move(MoveDirection.BACKWARD);
//        animal.move(MoveDirection.BACKWARD);
//        animal.move(MoveDirection.BACKWARD);
//        assertEquals(new Vector2d(3, 2), animal.getPosition());
//    }
//
//    @Test
//    public void rightBoundBackwardTest(){
//        animal.move(MoveDirection.RIGHT);
//        animal.move(MoveDirection.BACKWARD);
//        animal.move(MoveDirection.BACKWARD);
//        animal.move(MoveDirection.BACKWARD);
//        assertEquals(animal.getPosition(), new Vector2d(0, 2));
//    }
//
//    @Test
//    public void upperBoundForwardTest(){
//        animal.move(MoveDirection.FORWARD);
//        animal.move(MoveDirection.FORWARD);
//        animal.move(MoveDirection.FORWARD);
//        assertEquals(animal.getPosition() , new Vector2d(2,3));
//    }
//
//    @Test
//    public void downBoundForwardTest(){
//        animal.move(MoveDirection.LEFT);
//        animal.move(MoveDirection.LEFT);
//        animal.move(MoveDirection.FORWARD);
//        animal.move(MoveDirection.FORWARD);
//        animal.move(MoveDirection.FORWARD);
//        assertEquals(animal.getPosition() , new Vector2d(2,0));
//    }
//
//    @Test
//    public void leftBoundForwardTest(){
//        animal.move(MoveDirection.LEFT);
//        animal.move(MoveDirection.FORWARD);
//        animal.move(MoveDirection.FORWARD);
//        animal.move(MoveDirection.FORWARD);
//        assertEquals(animal.getPosition(), new Vector2d(0, 2));
//    }
//
//    @Test
//    public void rightBoundForwardTest(){
//        animal.move(MoveDirection.RIGHT);
//        animal.move(MoveDirection.FORWARD);
//        animal.move(MoveDirection.FORWARD);
//        animal.move(MoveDirection.FORWARD);
//        assertEquals(animal.getPosition(), new Vector2d(3, 2));
//    }
//
//    @Test
//    public void illegalArgumentTest(){
//        String[] directions = {"forward", "error"};
//        Assert.assertThrows(IllegalArgumentException.class ,
//            () -> {
//                MoveDirection[] moveDirections = OptionsParser.parse(directions);
//                animal.move(moveDirections[0]);
//                assertEquals(animal.getPosition() , new Vector2d(2,3));
//                assertEquals(animal.getDirection() , MapDirection.NORTH);
//                animal.move(moveDirections[1]);
//                assertEquals(animal.getPosition() , new Vector2d(2,3));
//                assertEquals(animal.getDirection() , MapDirection.NORTH);
//            });
//    }
}
