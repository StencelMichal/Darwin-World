package agh.cs.lab;

import javafx.application.Application;
import javafx.print.Collation;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class World extends Application{

    private final BooleanHolder stopped = new BooleanHolder();

    private final MutableInt day = new MutableInt();

    public static void main(String[] args)  {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws ParseException, IOException {
        JSONParser parser = new JSONParser();
        String jsonPath = new File("").getAbsolutePath();
        Reader reader = new FileReader(jsonPath + "/src/main/agh/cs/lab/parameters.json");
        JSONObject jsonObject = (JSONObject) parser.parse(reader);

        int width = (int) (long) jsonObject.get("width");
        int height= (int) (long) jsonObject.get("height");
        float jungleRatio = (float) (double) jsonObject.get("jungleRatio");
        int startEnergy = (int) (long) jsonObject.get("startEnergy");
        int plantEnergy = (int) (long) jsonObject.get("plantEnergy");
        int startAnimals = (int) (long) jsonObject.get("startAnimals");
        float moveEnergy = (float) (double) jsonObject.get("moveEnergy");
        int timeGap = (int) (long) jsonObject.get("timeGap");

        MapStatistics statistics = new MapStatistics(startAnimals, day);
        AnimalTracker tracker = new AnimalTracker(day);
        TorusMap map = new TorusMap(width,height, jungleRatio, statistics);
        SimulationEngine engine = new SimulationEngine(map,startAnimals,moveEnergy,startEnergy,plantEnergy, tracker);
        Visualizer visualizer = new Visualizer(map,stage,width,height,startEnergy,statistics, stopped, day, tracker);



        new Thread(()-> {
            while (true) {
                day.increment();
                engine.nextDay();
                statistics.update(engine.getAnimals(), engine.getCurrentAmountOfGrass());
                visualizer.update();
                try {
                    Thread.sleep(timeGap);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                while(stopped.getValue()){
                    System.out.print("");
                }
            }
        }).start();
    }
}
