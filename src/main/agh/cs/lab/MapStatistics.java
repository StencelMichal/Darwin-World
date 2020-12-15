package agh.cs.lab;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

public class MapStatistics implements IAnimalChangeObserver {

    private long day;

    private long animalsAmount;

    private long deadAnimalsAmount;

    private int grassAmount;

    private int[] DominantGenes;

    private double averageEnergy;

    private double averageLifeLength;

    private double averageChildrenAmount;

    public MapStatistics(int animalsAmount) {
        this.animalsAmount = animalsAmount;
        this.grassAmount = 0;
        this.averageEnergy = 0;
        this.averageLifeLength = 0;
        this.averageChildrenAmount = 0;
        this.deadAnimalsAmount = 0;
    }

    public void update(List<Animal> animals, int newGrassAmount) {
        double newAverageEnergy = 0;
        double newAverageChildrenAmount = 0;
        for (Animal animal : animals) {
            newAverageEnergy += animal.getEnergy();
            newAverageChildrenAmount += animal.getChildrenAmount();
        }

        animalsAmount = animals.size();
        averageEnergy = Math.round(newAverageEnergy * 100.0) / 100.0;
        averageChildrenAmount = Math.round(newAverageChildrenAmount * 100.0 / animalsAmount) / 100.0;
        day++;
        grassAmount = newGrassAmount;
    }

    @Override
    public String toString() {
        // genes
        return "Day: " + day + "\n" +
                "Amount of animals: " + animalsAmount + "\n" +
                "Amount of dead animals: " + deadAnimalsAmount + "\n" +
                "Amount of grass: " + grassAmount + "\n" +
                "Average energy: " + averageEnergy + "\n" +
                "Average length of life: " + averageLifeLength + "\n" +
                "Average number of children: " + averageChildrenAmount + "\n";

    }

    @Override
    public void positionChanged(Vector2d oldPosition, Animal o) {
    }

    @Override
    public void animalDead(Animal animal) {
        averageLifeLength *= deadAnimalsAmount;
        averageLifeLength += animal.getLifeLength();
        deadAnimalsAmount++;
        averageLifeLength /= deadAnimalsAmount;
        averageLifeLength = Math.round(averageLifeLength * 100.0) / 100.0;
    }
}
