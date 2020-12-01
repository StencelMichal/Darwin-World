package agh.cs.lab;

import java.util.Comparator;

public class Vector2dComparator implements Comparator<Vector2d> {

    @Override
    public int compare(Vector2d o1, Vector2d o2) {
        int compareByX = Float.compare(o1.x, o2.x);
        if(compareByX == 0){
            return Float.compare(o1.y, o2.y);
        }
        else{
            return compareByX;
        }
    }
}
