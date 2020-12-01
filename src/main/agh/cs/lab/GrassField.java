package agh.cs.lab;


import java.util.*;

public class GrassField {

    private final List<Map<Vector2d, Grass>> grass = new ArrayList<>();

    private final Map<Vector2d,Grass> steppeGrass = new HashMap<>();

    private final Map<Vector2d,Grass> jungleGrass = new HashMap<>();

    private final Random generator = new Random();

    private final int maxAmountOfJungleGrass;

    private final int maxAmountOfSteppeGrass;

    private final int jungleWidth;

    private final int jungleHeight;

    private final int width;

    private final int height;



    public GrassField(int width, int height, int jungleWidth, int jungleHeight) {
        this.jungleWidth = jungleWidth;
        this.jungleHeight = jungleHeight;
        this.height = height;
        this.width = width;
        grass.add(steppeGrass);
        grass.add(jungleGrass);
        maxAmountOfJungleGrass = jungleHeight * jungleWidth;
        maxAmountOfSteppeGrass = width * height - maxAmountOfJungleGrass;
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

    public void removeGrass(Grass grass) {
        for (Map<Vector2d, Grass> grassMap : this.grass) {
            if (grass.equals(grassMap.get(grass.position))){
                grassMap.remove(grass.getPosition());
                break;
            }
        }
    }

    private Grass generateGrass(int xAxisBound, int yAxisBound){
        int x = generator.nextInt(xAxisBound);
        int y = generator.nextInt(yAxisBound);

        return new Grass(new Vector2d(x, y));
    }

    public Grass grassAtPosition(Vector2d position){
        for (Map<Vector2d, Grass> grassMap : grass) {
            if(grassMap.containsKey(position)){
                return grassMap.get(position);
            }
        }
        return null;
    }



}
