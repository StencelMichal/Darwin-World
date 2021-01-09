package agh.cs.lab; // przydałby się podział na pakiety

public class AbstractWorldElement {

    protected Vector2d position;

    AbstractWorldElement(Vector2d position){
        this.position = position;
    }

    public Vector2d getPosition() {
        return position;
    }
}
