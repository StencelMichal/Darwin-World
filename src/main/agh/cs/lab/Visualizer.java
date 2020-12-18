package agh.cs.lab;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Visualizer {

    private static final int W = 1200;
    private static final int H = 600;

    private static int xTiles;
    private static int yTiles;

    private final IWorldMap map;

    private boolean tracked = false;

    private final Tile[][] grid;

    private final Text statisticsText;

    private final Text trackerText;

    private final MapStatistics statistics;

    private BooleanHolder stopped;

    private final AnimalTracker tracker;


    public Visualizer(IWorldMap map, Stage stage, int width, int height, float startEnergy,
                      MapStatistics statistics, BooleanHolder stopped, MutableInt day, AnimalTracker tracker) {
        xTiles = width;
        yTiles = height;
        this.map = map;
        this.statistics = statistics;
        grid = new Tile[xTiles][yTiles];
        this.stopped = stopped;
        this.tracker = tracker;

        Pane root = new Pane();
        root.setPrefSize(W, H);
        int tileSize = Math.min(W / width, H / height);
        for (int x = 0; x < xTiles; x++) {
            for (int y = 0;  y< yTiles; y++) {
                Tile tile = new Tile(x, y, tileSize, startEnergy, this);
                grid[x][y] = tile;
                root.getChildren().add(tile);
            }
        }

        statisticsText = new Text();
        statisticsText.setFont(Font.font(20));
        statisticsText.setTranslateX(xTiles * tileSize + 10);
        statisticsText.setTranslateY(50);
        root.getChildren().add(statisticsText);

        trackerText = new Text();
        trackerText.setFont(Font.font(20));
        trackerText.setTranslateX(xTiles * tileSize + 10);
        trackerText.setTranslateY(400);
        root.getChildren().add(trackerText);



        root.autosize();
        stage.addEventFilter(KeyEvent.KEY_PRESSED, event->{
            if (event.getCode() == KeyCode.SPACE) {
                stopped.switchValue();
            }
        });
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Darwin World");
        stage.show();
    }


    public void update() {
        for (int i = 0; i < xTiles; i++) {
            for (int j = 0; j < yTiles; j++) {
                grid[i][j].updateTile(map.objectAt(new Vector2d(i, j)));
            }
        }

        statisticsText.setText(statistics.toString());
        if(tracked) {
            trackerText.setText(tracker.toString());
        }

    }


    public void trackOnPosition(Vector2d position){
        if(map.isOccupied(position)){
            AbstractWorldElement element = map.objectAt(position);
            if (element.getClass().equals(Animal.class)){
                tracker.track((Animal) element);
                tracked = true;
            }
        }
    }






}