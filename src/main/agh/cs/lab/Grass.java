package agh.cs.lab;

public class Grass extends AbstractWorldElement {

    public Grass(Vector2d position) {
        super(position);
    }

    @Override
    public String toString() {
        return "*";
    }
}