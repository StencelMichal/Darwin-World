package agh.cs.lab;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class AverageStatistics {

    private final MutableInt day;

    private double animalsAmount = 0;

    private double grassAmount=0;

    private double averageEnergy=0;

    private double averageLifeLength=0;

    private double averageChildrenAmount=0;


    public AverageStatistics(MutableInt day){
        this.day = day;
    }

    public void update(Statistics statistics){
        animalsAmount *= (day.getValue() - 1);
        animalsAmount += statistics.getAnimalsAmount();
        animalsAmount /= day.getValue();

        grassAmount *= (day.getValue() - 1);
        grassAmount += statistics.getGrassAmount();
        grassAmount /= day.getValue();

        averageEnergy *= (day.getValue() - 1);
        averageEnergy += statistics.getAverageEnergy();
        averageEnergy /= day.getValue();

        averageLifeLength *= (day.getValue() - 1);
        averageLifeLength += statistics.getAverageLifeLength();
        averageLifeLength /= day.getValue();

        averageChildrenAmount *= (day.getValue() - 1);
        averageChildrenAmount += statistics.getAnimalsAmount();
        averageChildrenAmount /= day.getValue();
    }

    public void saveToFile(Statistics statistics) {
        File stats = new File("average_statistics.txt");
        FileWriter writer;
        try {
            writer = new FileWriter(stats);
            writer.write("----Average----" + "\n");
            writer.write("Number of animals: "  + round(animalsAmount) +  "\n");
            writer.write("Number of grass: " + round(grassAmount) + "\n");
            writer.write("Energy per animal: " + round(averageEnergy) + "\n");
            writer.write("Life length: " + round(averageLifeLength) + "\n");
            writer.write("Number of children: " + round(averageChildrenAmount) + "\n");
            writer.write("Dominant genotype: " + "\n" + statistics.getDominantGenotype() + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private double round(double value){
        return Math.round(value * 100.0) / 100.0;
    }

//    private void updateParameter(double oldValue, double new)



}
