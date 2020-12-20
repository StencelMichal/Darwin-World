package agh.cs.lab;

import java.util.*;

public class Statistics implements IAnimalDeadObserver{

    private final HashMap<Genotype, Integer> genotypes;

    private final MutableInt day;

    private double averageChildrenAmount;
    private double averageLifeLength;
    private long deadAnimalsAmount;
    private double averageEnergy;
    private long animalsAmount;
    private int grassAmount;


    public Statistics(int animalsAmount, MutableInt day) {
        this.day = day;
        this.animalsAmount = animalsAmount;
        this.grassAmount = 0;
        this.averageEnergy = 0;
        this.averageLifeLength = 0;
        this.averageChildrenAmount = 0;
        this.deadAnimalsAmount = 0;
        this.genotypes = new HashMap<>();
    }

    public long getAnimalsAmount() {
        return animalsAmount;
    }

    public int getGrassAmount() {
        return grassAmount;
    }

    public double getAverageEnergy() {
        return averageEnergy;
    }

    public double getAverageLifeLength() {
        return averageLifeLength;
    }

    public Genotype getDominantGenotype(){
        Genotype dominantGenotype = new Genotype();
        int maxAmount = 0;
        for (Map.Entry<Genotype, Integer> integerEntry : genotypes.entrySet()) {
            if(integerEntry.getValue() > maxAmount){
                maxAmount = integerEntry.getValue();
                dominantGenotype = integerEntry.getKey();
            }
        }

        return dominantGenotype;

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
//        addCurrentDominantGenotype(animals);
    }

//    public void addCurrentDominantGenotype(List<Animal> animals){
//        HashMap<Genotype, Integer> currentGenotypes = new HashMap<>();
//        for (Animal animal : animals) {
//            Genotype genotype = animal.getGenotype();
//            if(currentGenotypes.containsKey(genotype)){
//                currentGenotypes.put(genotype, currentGenotypes.get(genotype) + 1);
//            }
//            else{
//                currentGenotypes.put(genotype, 1);
//            }
//        }
//
//        Genotype dominantGenotype = new Genotype();
//        int maxValue = 0;
//        for (Map.Entry<Genotype, Integer> entry : currentGenotypes.entrySet()) {
//            if(entry.getValue() > maxValue){
//                maxValue = entry.getValue();
//                dominantGenotype = entry.getKey();
//            }
//        }
//
//        if(genotypes.containsKey(dominantGenotype)){
//            genotypes.put(dominantGenotype, genotypes.get(dominantGenotype) + 1);
//        }
//        else{
//            genotypes.put(dominantGenotype, 1);
//        }
//    }

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
                getDominantGenotype().toString() + "\n";

    }

    @Override
    public void animalDead(Animal animal) {
        averageLifeLength *= deadAnimalsAmount;
        averageLifeLength += animal.getLifeLength();
        deadAnimalsAmount++;
        averageLifeLength /= deadAnimalsAmount;
        averageLifeLength = Math.round(averageLifeLength * 100.0) / 100.0;
        removeGenotype(animal.getGenotype());
    }

    public void addGenotype(Genotype genotype) {
        if(genotypes.containsKey(genotype)){
            genotypes.put(genotype, genotypes.get(genotype) + 1);
        }
        else {
            genotypes.put(genotype, 1);
        }
    }

    private void removeGenotype(Genotype genotype){
        if(genotypes.get(genotype) == 1){
            genotypes.remove(genotype);
        }
        else {
            genotypes.put(genotype, genotypes.get(genotype) - 1);
        }
    }
}
