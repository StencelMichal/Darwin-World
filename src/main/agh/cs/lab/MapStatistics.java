package agh.cs.lab;

import java.util.*;

public class MapStatistics implements IAnimalDeadObserver{

    private MutableInt day;

    private long animalsAmount;

    private long deadAnimalsAmount;

    private int grassAmount;

    private double averageEnergy;

    private double averageLifeLength;

    private double averageChildrenAmount;

    private final HashMap<Genotype, Integer> dominantGenotypes;

    public MapStatistics(int animalsAmount, MutableInt day) {
        this.day = day;
        this.animalsAmount = animalsAmount;
        this.grassAmount = 0;
        this.averageEnergy = 0;
        this.averageLifeLength = 0;
        this.averageChildrenAmount = 0;
        this.deadAnimalsAmount = 0;
        this.dominantGenotypes = new HashMap<>();
    }

    public void update(List<Animal> animals, int newGrassAmount) {
        double newAverageEnergy = 0;
        double newAverageChildrenAmount = 0;
        for (Animal animal : animals) {
            newAverageEnergy += animal.getEnergy();
            newAverageChildrenAmount += animal.getChildrenAmount();
        }
        newAverageEnergy /= animals.size();

        animalsAmount = animals.size();
        averageEnergy = Math.round(newAverageEnergy * 100.0) / 100.0;
        averageChildrenAmount = Math.round(newAverageChildrenAmount * 100.0 / animalsAmount) / 100.0;
        grassAmount = newGrassAmount;
        addCurrentDominantGenotype(animals);
    }

    public void addCurrentDominantGenotype(List<Animal> animals){
        Genotype dominantGenotype = new Genotype();
        HashMap<Genotype, Integer> currentGenotypes = new HashMap<>();
        for (Animal animal : animals) {
            Genotype genotype = animal.getGenotype();
            if(currentGenotypes.containsKey(genotype)){
                currentGenotypes.put(genotype, currentGenotypes.get(genotype) + 1);
            }
            else{
                currentGenotypes.put(genotype, 1);
            }
        }

        if(dominantGenotypes.containsKey(dominantGenotype)){
            dominantGenotypes.put(dominantGenotype, dominantGenotypes.get(dominantGenotype) + 1);
        }
        else{
            dominantGenotypes.put(dominantGenotype, 1);
        }
    }

    private String getDominantGenotype(){
        Genotype dominantGenotype = new Genotype();
        int maxAmount = 0;
        for (Map.Entry<Genotype, Integer> integerEntry : dominantGenotypes.entrySet()) {
            if(integerEntry.getValue() > maxAmount){
                maxAmount = integerEntry.getValue();
                dominantGenotype = integerEntry.getKey();
            }
        }
        StringBuilder ret = new StringBuilder(" ");
        for (int gene : dominantGenotype.getGenes()) {
            ret.append(gene).append(" ");
        }

        return ret.toString();

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
                "Average number of children: " + averageChildrenAmount + "\n" +
                "Dominant genotype: "+ "\n" +
                getDominantGenotype() + "\n";

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
