package agh.cs.lab;

import java.util.Comparator;

public class CompareXAxis implements Comparator<AbstractWorldElement> {

    @Override
    public int compare(AbstractWorldElement o1, AbstractWorldElement o2) {
        int compareByX = Integer.compare(o1.getPosition().x, o2.getPosition().x);
        if(compareByX != 0){
            return compareByX;
        }
        else {
            if(o1.getClass().equals(Animal.class) && !(o2.getClass().equals(Animal.class))){
                return 1;
            }
            return Integer.compare(o1.getPosition().y, o2.getPosition().y);
        }
    }
}
