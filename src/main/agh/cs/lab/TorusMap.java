package agh.cs.lab;

import java.util.NavigableSet;

public class TorusMap extends AbstractWorldMap {

    private final int width;

    private final int height;

    private final GrassField grass;

    private final Statistics statistics;


    public TorusMap(int width, int height, float jungleRatio, Statistics statistics) {
        super();
        this.width = width;
        this.height = height;
        this.statistics = statistics;
        int jungleWidth = (int) (width * jungleRatio);
        int jungleHeight = (int) (height * jungleRatio);
        grass = new GrassField(width, height, jungleWidth, jungleHeight, this);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public boolean place(Animal animal) {
        animals.put(animal.getPosition(), animal);
        animal.addMoveObserver(this);
        animal.addDeadObserver(this);
        animal.addDeadObserver(statistics);
        statistics.addGenotype(animal.getGenotype());
        return true;
    }

    public void eatGrass(float energyFromGrass){
        for(Vector2d position : animals.keySet()){
            Grass grass = this.grass.grassAt(position);
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

    public int getAmountOfGrass(){
        return grass.getAmountOfGrass();
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

    @Override
    public AbstractWorldElement objectAt(Vector2d position) {
        if(animalAt(position) != null){
            return animalAt(position);
        }
        else{
            return grass.grassAt(position);
        }
    }
}
