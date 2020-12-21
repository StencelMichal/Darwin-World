package agh.cs.lab;


import java.util.*;

public class GrassField {

    private final List<Map<Vector2d, Grass>> grass = new ArrayList<>();
    private final Map<Vector2d,Grass> steppeGrass = new HashMap<>();
    private final Map<Vector2d,Grass> jungleGrass = new HashMap<>();

    private final int jungleWidth;
    private final int jungleHeight;
    private final int width;
    private final int height;

    private final Random generator = new Random();

    private final TorusMap map;



    public GrassField(int width, int height, int jungleWidth, int jungleHeight, TorusMap map) {
        this.jungleWidth = jungleWidth;
        this.jungleHeight = jungleHeight;
        this.height = height;
        this.width = width;
        this.map = map;
        grass.add(steppeGrass);
        grass.add(jungleGrass);
    }

    public int getAmountOfGrass(){
        return jungleGrass.size() + steppeGrass.size();
    }


    public void addGrass(){
        ArrayList<Vector2d> jungleFields = unoccupiedJungleFields();
        ArrayList<Vector2d> steppeFields = unoccupiedSteppeFields();
        if(!jungleFields.isEmpty()){
            Grass grass = new Grass(jungleFields.get(generator.nextInt(jungleFields.size())));
            jungleGrass.put(grass.position, grass);
        }
        if(!steppeFields.isEmpty()){
            Grass grass = new Grass(steppeFields.get(generator.nextInt(steppeFields.size())));
            steppeGrass.put(grass.position, grass);
        }
    }


    public void removeGrass(Grass grass) {
        for (Map<Vector2d, Grass> grassMap : this.grass) {
            if (grass == grassMap.get(grass.position)){
                grassMap.remove(grass.getPosition());
                break;
            }
        }
    }


    public Grass grassAt(Vector2d position){
        for (Map<Vector2d, Grass> grassMap : grass) {
            if(grassMap.containsKey(position)){
                return grassMap.get(position);
            }
        }
        return null;
    }

    public ArrayList<Vector2d> unoccupiedJungleFields(){
        ArrayList<Vector2d> unoccupied = new ArrayList<>();
        getUnoccupiedInRectangle(unoccupied, jungleWidth, jungleHeight, new Vector2d(0,0));
        Collections.shuffle(unoccupied);
        return unoccupied;
    }

    public ArrayList<Vector2d> unoccupiedSteppeFields(){
        ArrayList<Vector2d> unoccupied = new ArrayList<>();
        getUnoccupiedInRectangle(unoccupied, width, height, new Vector2d(jungleWidth,0));
        getUnoccupiedInRectangle(unoccupied, width, height, new Vector2d(0,jungleHeight));
        Collections.shuffle(unoccupied);
        return unoccupied;
    }

    private void getUnoccupiedInRectangle(ArrayList<Vector2d> unoccupied, int width, int height, Vector2d startPosition) {
        for (int x = startPosition.x; x < width; x++)
            for (int y = startPosition.y; y < height; y++) {
                Vector2d position = new Vector2d(x, y);
                if (!map.isOccupied(position)) {
                    unoccupied.add(position);
                }
            }
    }
}



