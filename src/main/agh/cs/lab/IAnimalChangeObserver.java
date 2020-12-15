package agh.cs.lab;

public interface IAnimalChangeObserver {

    void positionChanged(Vector2d oldPosition, Animal o);

    void animalDead(Animal animal);

}
