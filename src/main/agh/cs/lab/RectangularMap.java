//package agh.cs.lab;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//public class RectangularMap extends AbstractWorldMap {
//
//    private final Vector2d upperRight;
//
//    private final Vector2d lowerLeft = new Vector2d(0,0);
//
//    public RectangularMap(int width, int height) {
//        super();
//        upperRight = new Vector2d(width-1,height-1);
//    }
//
//    @Override
//    public Vector2d getUpperRight() {
//        return upperRight;
//    }
//
//    @Override
//    public Vector2d getLowerLeft() {
//        return lowerLeft;
//    }
//
//    @Override
//    public boolean canMoveTo(Vector2d position) {
//        return position.follows(lowerLeft) && position.precedes(upperRight)
//                && !isOccupied(position);
//    }
//
//    @Override
//    public Vector2d checkPosition(Vector2d newPosition) {
//        int x = newPosition.x;
//        int y = newPosition.y;
//
//        if(x > width - 1){
//            x = 0;
//        }
//        if(x < 0){
//            x = width;
//        }
//        if(y > height-1){
//            y = 0;
//        }
//        if(y < 0){
//            y = height;
//        }
//
//        return new Vector2d(x,y);
//    }
//
//}
