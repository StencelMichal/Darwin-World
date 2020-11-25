package agh.cs.lab;

import org.junit.Test;

import static junit.framework.TestCase.*;

public class Vector2dTest {

    @Test
    public void equalsTest(){
        Vector2d v1 = new Vector2d(1, 2);
        Vector2d v2 = new Vector2d(1, 2);
        assertEquals(v1, v2);
        assertEquals(v1, v1);
    }

    @Test
    public void equalsIllegalArgument(){
        Vector2d v1 = new Vector2d(0,0);
        assertFalse(v1.equals(null));
        assertFalse(v1.equals(new Object()));
    }

    @Test
    public void toStingTest(){
        Vector2d vector = new Vector2d(100, 100);
        assertEquals("(100,100)", vector.toString());
    }

    @Test
    public void precedesTest(){
        Vector2d v1 = new Vector2d(0, 0);
        Vector2d v2 = new Vector2d(100, 100);
        assertTrue(v1.precedes(v2));
        assertTrue(v1.precedes(v1));
    }

    @Test
    public void followsTest(){
        Vector2d v1 = new Vector2d(0, 0);
        Vector2d v2 = new Vector2d(100, 100);
        assertTrue(v2.follows(v1));
        assertTrue(v1.follows(v1));
    }

    @Test
    public void upperRightTest(){
        Vector2d v1 = new Vector2d(0, 0);
        Vector2d v2 = new Vector2d(100, 100);
        assertEquals(v1.upperRight(v2) , new Vector2d(100 , 100));

        Vector2d v3 = new Vector2d(0, 100);
        Vector2d v4 = new Vector2d(100, 0);
        assertEquals(v1.upperRight(v2) , new Vector2d(100 , 100));
    }

    @Test
    public void lowerLeftTest(){
        Vector2d v1 = new Vector2d(0, 0);
        Vector2d v2 = new Vector2d(100, 100);
        assertEquals(v1.lowerLeft(v2) , new Vector2d(0,0));

        Vector2d v3 = new Vector2d(0, 100);
        Vector2d v4 = new Vector2d(100, 0);
        assertEquals(v1.lowerLeft(v2) , new Vector2d(0, 0));
    }

    @Test
    public void addTest(){
        Vector2d v1 = new Vector2d(1, 1);
        Vector2d v2 = new Vector2d(100, 100);
        assertEquals(v1.add(v2) , new Vector2d(101 , 101));
    }

    @Test
    public void substractTest(){
        Vector2d v1 = new Vector2d(1, 1);
        Vector2d v2 = new Vector2d(100, 100);
        assertEquals(v1.subtract(v2) , new Vector2d(-99,-99));
    }

    @Test
    public void oppositeTest(){
        Vector2d v1 = new Vector2d(5, 5);
        assertEquals(v1.opposite() , new Vector2d(-5,-5));

        Vector2d v2 = new Vector2d(0, 0);
        assertEquals(v2.opposite() , v2);
    }



}
