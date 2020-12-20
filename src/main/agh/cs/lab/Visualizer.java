package agh.cs.lab;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Visualizer implements EventHandler<ActionEvent> {

    private static final int W = 1200;
    private static final int H = 600;

    private static int xTiles;
    private static int yTiles;

    private final IWorldMap map;

    private final Pane root;

    private final Button startStopButton;

    private final Button trackAnimalButton;

    private Animal selectedAnimal;

    private boolean tracked = false;

    private final Tile[][] grid;

    private final Text statisticsText;

    private final Text trackerText;

    private final Statistics statistics;

    private BooleanHolder stopped;

    private final AnimalTracker tracker;

    private final Button dominantGenotypeButton;


    public Visualizer(IWorldMap map, Stage stage, int width, int height, float startEnergy,
                      Statistics statistics, BooleanHolder stopped, MutableInt day, AnimalTracker tracker, MutableInt timeGap) {
        xTiles = width;
        yTiles = height;
        this.map = map;
        this.statistics = statistics;
        grid = new Tile[xTiles][yTiles];
        this.stopped = stopped;
        this.tracker = tracker;

        Pane root = new Pane();
        this.root = root;
        root.setPrefSize(W, H);
        int tileSize = Math.min(H / width, H / height);
        for (int x = 0; x < xTiles; x++) {
            for (int y = 0; y < yTiles; y++) {
                Tile tile = new Tile(x, y, tileSize, startEnergy, this);
                grid[x][y] = tile;
                root.getChildren().add(tile);
            }
        }


        this.startStopButton = addNewButton("Stop", xTiles * tileSize + 25, 20);

        this.dominantGenotypeButton = addNewButton("Dominant genotype", xTiles * tileSize + 190, 20);

        this.trackAnimalButton = addNewButton("Track animal", xTiles * tileSize + 355, 20);
        trackAnimalButton.setVisible(false);

        this.statisticsText = addText(xTiles * tileSize + 25, 140);

        this.trackerText = addText(xTiles * tileSize + 25, 400);

        Label timeGapLabel = new Label("Time gap: " + timeGap.getValue());
        timeGapLabel.setTranslateX(xTiles * tileSize + 245);
        timeGapLabel.setTranslateY(530);
        timeGapLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        root.getChildren().add(timeGapLabel);
        Slider timeGapSlider = new Slider();
        timeGapSlider.setTranslateX(xTiles * tileSize + 50);
        timeGapSlider.setTranslateY(550);
        timeGapSlider.setMax(2000);
        timeGapSlider.setMin(20);
        timeGapSlider.setValue(2000);
        timeGapSlider.setPrefSize(500,30);
        timeGapSlider.setBlockIncrement(10);
        timeGapSlider.setMajorTickUnit(200);
        timeGapSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            timeGap.change(newValue.intValue());
            timeGapLabel.setText("Time gap: " + timeGap.getValue());

        });
        root.getChildren().add(timeGapSlider);


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
        if (tracked) {
            trackerText.setText(tracker.toString());
        }

    }


    public void trackOnPosition(Vector2d position) {
        if (map.isOccupied(position)) {
            AbstractWorldElement element = map.objectAt(position);
            if (element.getClass().equals(Animal.class)) {
                trackAnimalButton.setVisible(true);
                selectedAnimal = (Animal) element;
            }
        }
    }


    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == dominantGenotypeButton) {
            for (int x = 0; x < xTiles; x++) {
                for (int y = 0; y < yTiles; y++) {
                    Vector2d position = new Vector2d(x, y);
                    if (map.isOccupied(position)) {
                        AbstractWorldElement element = map.objectAt(position);
                        if (element.getClass().equals(Animal.class)) {
                            if (((Animal) element).getGenotype().equals(statistics.getDominantGenotype())) {
                                grid[x][y].highlight();
                            }
                        }
                    }
                }
            }
        } else if (event.getSource() == startStopButton) {
            stopped.switchValue();
            if (startStopButton.getText().equals("Start")) {
                startStopButton.setText("Stop");
            } else
                startStopButton.setText("Start");
        } else if (event.getSource() == trackAnimalButton) {
            tracker.track(selectedAnimal);
            tracked = true;
        }
    }

    private Button addNewButton(String name, int x, int y) {
        Button button = new Button(name);
        button.setPrefSize(150, 60);
        button.setTranslateX(x);
        button.setTranslateY(y);
        button.setOnAction(this);
        button.setFont(Font.font(14));
        root.getChildren().add(button);

        return button;

    }

    private Text addText(int x, int y) {
        Text text = new Text();
        text.setTranslateX(x);
        text.setTranslateY(y);
        text.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, 20));
        root.getChildren().add(text);

        return text;


    }
}
