//package agh.cs.lab;
//
//import java.util.Iterator;
//import java.util.SortedSet;
//import java.util.TreeSet;
//
//public class MapBoundary implements IPositionChangeObserver {
//
//    private final SortedSet<AbstractWorldElement> setX = new TreeSet<>(new CompareXAxis());
//
//    private final SortedSet<AbstractWorldElement> setY = new TreeSet<>(new CompareYAxis());
//
//    @Override
//    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
//        replaceElement(newPosition, setX);
//        replaceElement(newPosition, setY);
//    }
//
//    private void replaceElement(Vector2d newPosition, SortedSet<AbstractWorldElement> set) {
//        Iterator<AbstractWorldElement> iterator = set.iterator();
//        while(iterator.hasNext()) {
//            AbstractWorldElement element = iterator.next();
//            if (element.getPosition().equals(newPosition) && element.getClass().equals(Animal.class)) {
//                iterator.remove();
//                set.add(element);
//                break; // to avoid ConcurrentModificationException
//            }
//        }
//    }
//
//    public void addElement(AbstractWorldElement element){
//        setX.add(element);
//        setY.add(element);
//    }
//
//    public Vector2d getlowerLeft(){
//        if (setX.isEmpty()){
//            return new Vector2d(0,0);
//        }
//        return new Vector2d(setX.first().getPosition().x, setY.first().getPosition().y);
//    }
//
//    public Vector2d getUpperRight(){
//        if (setY.isEmpty()){
//            return new Vector2d(0,0);
//        }
//        return new Vector2d(setX.last().getPosition().x, setY.last().getPosition().y);
//    }
//
//}
