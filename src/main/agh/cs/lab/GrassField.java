//package agh.cs.lab;
//
//
//import java.util.*;
//
//public class GrassField extends AbstractWorldMap {
//
//    private final List<Grass> grassList = new ArrayList<>();
//
//    private final Map<Vector2d,AbstractWorldElement> grassMap = new HashMap<>();
//
//    private final MapBoundary mapBoundary = new MapBoundary();
//
//    public GrassField(int grassAmount) {
//        listOfElements.add(grassMap);
//        int size = (int) Math.sqrt(10 * grassAmount);
//        Random generate = new Random();
//        while (grassAmount > 0){
//            int x = generate.nextInt(size);
//            int y = generate.nextInt(size);
//            Vector2d position = new Vector2d(x, y);
//            if(objectAt(position) == null){
//                Grass grass = new Grass(position);
//                grassMap.put(position, grass);
//                grassList.add(grass);
//                mapBoundary.addElement(grass);
//                grassAmount--;
//            }
//        }
//    }
//
//    public List<Grass> getGrassList() {
//        return Collections.unmodifiableList(grassList);
//    }
//
//    @Override
//    public boolean place(Animal animal) {
//        if(super.place(animal)){
//            mapBoundary.addElement(animal);
//            animal.addObserver(mapBoundary);
//            return true;
//        }
//        else{
//            return false;
//        }
//
//    }
//
//    @Override
//    public Vector2d getUpperRight() {
////        // nie wiedzialem co powinno sie stac gdy nie ma elementow na mapie dlatego
////        // zwracam mape o zerowym rozmiarze: lowerLeft = upperRight = (0,0)
////        if(isMapEmpty()){
////            return new Vector2d(0,0);
////        }
////
////        Vector2d upperRight = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);
////        for (Map<Vector2d, AbstractWorldElement> elements : listOfElements) {
////            for (AbstractWorldElement element : elements.values()) {
////                upperRight = element.getPosition().upperRight(upperRight);
////            }
////        }
//
//        return mapBoundary.getUpperRight();
//
//    }
//
//    @Override
//    public Vector2d getLowerLeft() {
////        // nie wiedzialem co powinno sie stac gdy nie ma elementow na mapie dlatego
////        // zwracam mape o zerowym rozmiarze: lowerLeft = upperRight = (0,0)
////        if(isMapEmpty()){
////            return new Vector2d(0,0);
////        }
////
////        Vector2d lowerLeft = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
////        for (Map<Vector2d , AbstractWorldElement> elements : listOfElements) {
////            for (AbstractWorldElement element : elements.values()) {
////                lowerLeft = element.getPosition().lowerLeft(lowerLeft);
////            }
////        }
//
//        return mapBoundary.getlowerLeft();
//    }
//
//    @Override
//    public boolean canMoveTo(Vector2d position) {
//        Object obj = objectAt(position);
//        return (obj == null || obj.getClass().equals(Grass.class));
//    }
//
//}
