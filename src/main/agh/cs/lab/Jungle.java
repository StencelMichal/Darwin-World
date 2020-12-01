package agh.cs.lab;

import java.util.HashMap;
import java.util.Map;

public class Jungle {

    private final Vector2d lowerLeft;

    private final Vector2d upperRight;

    private Map<Vector2d,AbstractWorldElement> jungleGrass = new HashMap<>();

    public Jungle(Vector2d lowerLeft, Vector2d upperRight, Map<Vector2d, AbstractWorldElement> jungleGrass) {
        this.lowerLeft = lowerLeft;
        this.upperRight = upperRight;
        this.jungleGrass = jungleGrass;
    }
}
