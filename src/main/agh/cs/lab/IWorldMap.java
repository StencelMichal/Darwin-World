package agh.cs.lab;

public interface IWorldMap {
    

    void place(Animal animal);
    

    boolean isOccupied(Vector2d position);


    AbstractWorldElement objectAt(Vector2d position);

}

