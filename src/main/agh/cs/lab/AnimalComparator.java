package agh.cs.lab;

import java.util.Comparator;

public class AnimalComparator implements Comparator<Animal> {

    @Override
    public int compare(Animal o1, Animal o2) {
        int compare = Float.compare(o1.getEnergy(), o2.getEnergy());
        if(compare == 0){
            return Integer.compare(o1.hashCode(), o2.hashCode());
        }
        else return compare;
    }
}
