package agh.cs.lab;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class TorusMap extends AbstractWorldMap {

    private final int width;

    private final int height;

    private final Map<Vector2d,AbstractWorldElement> steppeGrass = new HashMap<>();

    private final Map<Vector2d,AbstractWorldElement> jungleGrass = new HashMap<>();

    private final int maxAmountOfJungleGrass;

    private final int maxAmountOfSteppeGrass;

    private final int jungleWidth;

    private final int jungleHeight;

    private final Random generator = new Random();

    /*
    Jungla jest usytuowana w lewym dolnym rogu dla wygody obsługi,
    ale ze względu na "zawijanie" się naszej mapy mozemy sprawić żeby w wizualizacji znalazła się na środku
     */

    public TorusMap(int width, int height, int jungleWidth, int jungleHeight) {
        super();
        this.width = width;
        this.height = height;
        listOfElements.add(steppeGrass);
        listOfElements.add(jungleGrass);
        this.jungleWidth = jungleWidth;
        this.jungleHeight = jungleHeight;
        maxAmountOfJungleGrass = jungleHeight * jungleWidth;
        maxAmountOfSteppeGrass = width * height - maxAmountOfJungleGrass;
    }

    @Override
    public Vector2d getUpperRight() {
        return new Vector2d(width - 1, height - 1);
    }

    @Override
    public Vector2d getLowerLeft() {
        return new Vector2d(0, 0);

    }

    public void addGrass() {
        if (jungleGrass.size() < maxAmountOfJungleGrass) {
            Grass grass = generateGrass(jungleWidth, jungleHeight);
            while (jungleGrass.get(grass.position) != null) {
                grass = generateGrass(jungleWidth, jungleHeight);
            }
            jungleGrass.put(grass.position, grass);
        }

        if (steppeGrass.size() < maxAmountOfSteppeGrass) {
            Grass grass = generateGrass(width, height);
            while (steppeGrass.get(grass.position) != null ||
                    (grass.getPosition().x < jungleWidth && grass.getPosition().y < jungleWidth)) {
                grass = generateGrass(width, height);
            }
            steppeGrass.put(grass.getPosition(), grass);
        }

    }

    private Grass generateGrass(int xAxisBound, int yAxisBound){
        int x = generator.nextInt(xAxisBound);
        int y = generator.nextInt(yAxisBound);

        return new Grass(new Vector2d(x, y));
    }


    // TO REMOVE
    @Override
    public boolean canMoveTo(Vector2d position) {
        return true;
    }

    @Override
    public Vector2d checkPosition(Vector2d newPosition) {
        int x = newPosition.x;
        int y = newPosition.y;

        if (x > width - 1) {
            x = 0;
        }
        if (x < 0) {
            x = width-1;
        }
        if (y > height - 1) {
            y = 0;
        }
        if (y < 0) {
            y = height-1;
        }

        return new Vector2d(x, y);
    }

}
