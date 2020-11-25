package agh.cs.lab;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Animal extends AbstractWorldElement{

    private MapDirection direction = MapDirection.NORTH;

    private final AbstractWorldMap map;

    Random generator = new Random();

    private final List<IPositionChangeObserver> observers = new ArrayList<>();

    private final int[] genes = new int[32];

    public Animal(AbstractWorldMap map, Vector2d initialPosition){
        super(initialPosition);
        this.map = map;
        for(int i=0; i<32 ; i++){
            genes[i] = generator.nextInt(8);
        }
    }

    public Animal(AbstractWorldMap map){
        this(map , new Vector2d(2,2));
    }

    public MapDirection getDirection() {
        return direction;
    }

    public Vector2d getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return direction.toString();
    }

    public void move(){

        for( int i=0; i<genes[generator.nextInt(32)]; i++){
            direction = direction.next();
        }

        Vector2d newPosition = position.add(direction.toUnitVector());
        Vector2d positionBeforeMove = position;
        position = map.checkPosition(newPosition);
        positionChanged(positionBeforeMove);

//        switch (direction){
//            case LEFT:
//                this.direction = this.direction.previous();
//                break;
//            case RIGHT:
//                this.direction = this.direction.next();
//                break;
//            case FORWARD:
//                Vector2d forwardMove = position.add(this.direction.toUnitVector());
//                if(map.canMoveTo(forwardMove)){
//                    Vector2d positionBeforeMove = position;
//                    position = forwardMove;
//                    positionChanged(positionBeforeMove);
//                }
//                break;
//            case BACKWARD:
//                Vector2d backwardMove = position.subtract(this.direction.toUnitVector());
//                if(map.canMoveTo(backwardMove)){
//                    Vector2d positionBeforeMove = position;
//                    position = backwardMove;
//                    positionChanged(positionBeforeMove);
//                }
//                break;
//        }
    }

    public void addObserver(IPositionChangeObserver observer){
        observers.add(observer);
    }

    void removeObserver(IPositionChangeObserver observer){
        observers.remove(observer);
    }

    private void positionChanged(Vector2d oldPosition){
        for (IPositionChangeObserver observer : observers) {
            observer.positionChanged(oldPosition, position);
        }
    }


}
