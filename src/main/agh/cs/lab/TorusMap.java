package agh.cs.lab;

import java.util.List;
import java.util.NavigableSet;

public class TorusMap extends AbstractWorldMap {

    private final int width;

    private final int height;

    private final GrassField grass;

    private final AnimalComparator comparator = new AnimalComparator();

    /*
    Jungla jest usytuowana w lewym dolnym rogu dla wygody obsługi,
    ale ze względu na "zawijanie" się naszej mapy mozemy sprawić żeby w wizualizacji znalazła się na środku
     */

    public TorusMap(int width, int height, int jungleWidth, int jungleHeight) {
        super();
        this.width = width;
        this.height = height;
        grass = new GrassField(width, height, jungleWidth, jungleHeight);
    }

//    @Override
//    public Vector2d getUpperRight() {
//        return new Vector2d(width - 1, height - 1);
//    }
//
//    @Override
//    public Vector2d getLowerLeft() {
//        return new Vector2d(0, 0);
//
//    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void eatGrass(float energyFromGrass){
        for(Vector2d position : animals.keys()){
            Grass grass = this.grass.grassAtPosition(position);
            if (grass != null) {
                NavigableSet<Animal> animalsOnField = this.animals.get(position);
                float maxEnergy = animalsOnField.last().getEnergy();
                long animalsWithSameEnergy = animalsOnField
                        .stream()
                        .filter(animal -> animal.getEnergy() == maxEnergy)
                        .count();

                float energyToAdd = energyFromGrass / animalsWithSameEnergy;
                animalsOnField
                        .stream()
                        .filter(animal -> animal.getEnergy() == maxEnergy)
                        .forEach(animal -> animal.addEnergy(energyToAdd));

                this.grass.removeGrass(grass);
            }
        }
    }

    public void addGrass(){
        grass.addGrass();
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

    // TO REMOVE
    @Override
    public Object objectAt(Vector2d position) {
        return null;
    }
}
