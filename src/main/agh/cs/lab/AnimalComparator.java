package agh.cs.lab;

import java.util.Comparator;

public class AnimalComparator implements Comparator<Animal> {

    @Override
    public int compare(Animal o1, Animal o2) {
        int compare = Float.compare(o1.getEnergy(), o2.getEnergy());
        if(compare == 0){
            // if both animals have same energy there is no difference which
            // one will be said to be greater, but they to be distinguishable
            return -1;
        }
        else return compare;
    }
}
