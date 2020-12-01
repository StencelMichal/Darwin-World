package agh.cs.lab;

import org.junit.Test;
import static junit.framework.TestCase.assertEquals;

public class MapDirectionTest{
    

    @Test
    public void nextNorth(){
        MapDirection dir = MapDirection.NORTH;
        assertEquals(dir.next(), MapDirection.NORTH_EAST);
    }

    @Test
    public void nextNorthEast(){
        MapDirection dir = MapDirection.NORTH_EAST;
        assertEquals(dir.next(), MapDirection.EAST);
    }

    @Test
    public void nextEast(){
        MapDirection dir = MapDirection.EAST;
        assertEquals(dir.next(), MapDirection.SOUTH_EAST);
    }

    @Test
    public void nextSouthEast(){
        MapDirection dir = MapDirection.SOUTH_EAST;
        assertEquals(dir.next(), MapDirection.SOUTH);
    }

    @Test
    public void NextSouth(){
        MapDirection dir = MapDirection.SOUTH;
        assertEquals(dir.next(), MapDirection.SOUTH_WEST);
    }

    @Test
    public void nextSouthWest(){
        MapDirection dir = MapDirection.SOUTH_WEST;
        assertEquals(dir.next(), MapDirection.WEST);
    }

    @Test
    public void nextWest(){
        MapDirection dir = MapDirection.WEST;
        assertEquals(dir.next(), MapDirection.NORTH_WEST);
    }

    @Test
    public void nextNorthWest(){
        MapDirection dir = MapDirection.NORTH_WEST;
        assertEquals(dir.next(), MapDirection.NORTH);
    }

//    @Test
//    public void prevNorth(){
//        MapDirection dir = MapDirection.NORTH;
//        assertEquals(dir.previous(), MapDirection.WEST);
//    }
//
//    @Test
//    public void prevEast(){
//        MapDirection dir = MapDirection.EAST;
//        assertEquals(dir.previous(), MapDirection.NORTH);
//    }
//
//    @Test
//    public void prevSouth(){
//        MapDirection dir = MapDirection.SOUTH;
//        assertEquals(dir.previous(), MapDirection.EAST);
//    }
//
//    @Test
//    public void prevWest(){
//        MapDirection dir = MapDirection.WEST;
//        assertEquals(dir.previous(), MapDirection.SOUTH);
//    }

    @Test
    public void toUnitVectorNorth(){
        MapDirection dir = MapDirection.NORTH;
        assertEquals(dir.toUnitVector(), new Vector2d(0,1));
    }

    @Test
    public void toUnitVectorNorthEast(){
        MapDirection dir = MapDirection.NORTH_EAST;
        assertEquals(dir.toUnitVector(), new Vector2d(1,1));
    }

    @Test
    public void toUnitVectorNorthWest(){
        MapDirection dir = MapDirection.NORTH_WEST;
        assertEquals(dir.toUnitVector(), new Vector2d(-1,1));
    }

    @Test
    public void toUnitVectorEast(){
        MapDirection dir = MapDirection.EAST;
        assertEquals(dir.toUnitVector(), new Vector2d(1,0));
    }

    @Test
    public void toUnitVectorSouth(){
        MapDirection dir = MapDirection.SOUTH;
        assertEquals(dir.toUnitVector(), new Vector2d(0,-1));
    }

    @Test
    public void toUnitVectorSouthEast(){
        MapDirection dir = MapDirection.SOUTH_EAST;
        assertEquals(dir.toUnitVector(), new Vector2d(1,-1));
    }

    @Test
    public void toUnitVectorSouthWest(){
        MapDirection dir = MapDirection.SOUTH_WEST;
        assertEquals(dir.toUnitVector(), new Vector2d(-1,-1));
    }

    @Test
    public void toUnitVectorWest(){
        MapDirection dir = MapDirection.WEST;
        assertEquals(dir.toUnitVector(), new Vector2d(-1,0));
    }
}
