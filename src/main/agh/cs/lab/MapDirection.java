package agh.cs.lab;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public enum MapDirection {
    NORTH,
    NORTH_EAST,
    NORTH_WEST,
    SOUTH,
    SOUTH_EAST,
    SOUTH_WEST,
    WEST,
    EAST;

    public Vector2d toUnitVector(){
        Vector2d ret = null;
        switch(this){
            case NORTH:
                ret = new Vector2d(0,1);
                break;
            case NORTH_EAST:
                ret = new Vector2d(1,1);
                break;
            case NORTH_WEST:
                ret = new Vector2d(-1,1);
                break;
            case EAST:
                ret = new Vector2d(1,0);
                break;
            case SOUTH:
                ret = new Vector2d(0,-1);
                break;
            case SOUTH_EAST:
                ret = new Vector2d(1,-1);
                break;
            case SOUTH_WEST:
                ret = new Vector2d(-1,-1);
                break;
            case WEST:
                ret = new Vector2d(-1,0);
                break;
        }

        return ret;
    }

    public MapDirection next(){
        MapDirection ret = null;
        switch(this){
            case NORTH:
                ret = MapDirection.NORTH_EAST;
                break;
            case NORTH_EAST:
                ret = MapDirection.EAST;
                break;
            case EAST:
                ret = MapDirection.SOUTH_EAST;
                break;
            case SOUTH_EAST:
                ret = MapDirection.SOUTH;
                break;
            case SOUTH:
                ret = MapDirection.SOUTH_WEST;
                break;
            case SOUTH_WEST:
                ret = MapDirection.WEST;
                break;
            case WEST:
                ret = MapDirection.NORTH_WEST;
                break;
            case NORTH_WEST:
                ret = MapDirection.NORTH;
                break;
        }

        return ret;
    }

    public static ArrayList<MapDirection> randomizedDirections(){
        ArrayList<MapDirection> directions = new ArrayList<>(Arrays.asList(MapDirection.values()));
        Collections.shuffle(directions);
        return directions;
    }


//    public MapDirection previous(){
//        MapDirection ret = null;
//        switch (this){
//            case NORTH:
//                ret = MapDirection.WEST;
//                break;
//            case EAST:
//                ret = MapDirection.NORTH;
//                break;
//            case SOUTH:
//                ret = MapDirection.EAST;
//                break;
//            case WEST:
//                ret = MapDirection.SOUTH;
//                break;
//        }
//
//        return ret;
//    }


    @Override
    public String toString() {
        String ret = null;
        switch(this){
            case EAST:
                ret = ">";
                break;
            case WEST:
                ret = "<";
                break;
            case NORTH:
                ret = "^";
                break;
            case SOUTH:
                ret = "v";
                break;
        }

        return ret;
    }
}
