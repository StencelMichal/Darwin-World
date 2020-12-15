package agh.cs.lab;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class World extends Application{

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
        MapStatistics statistics = new MapStatistics(startAnimals);
        TorusMap map = new TorusMap(width,height, jungleRatio, statistics);
        SimulationEngine engine = new SimulationEngine(map,startAnimals,moveEnergy,startEnergy,plantEnergy);
        Visualizer visualizer = new Visualizer(map,stage,width,height,startEnergy,statistics);
        new Thread(()-> {
            while (true) {
                engine.nextDay();
                statistics.update(engine.getAnimals(), engine.getCurrentAmountOfGrass());
                System.out.println(statistics);
                System.out.println();
                try {
                    Thread.sleep(timeGap);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                visualizer.update();
//                if(visualizer.isStopped()){
//                    Thread.currentThread().suspend();
//                    while(visualizer.isStopped()){
//                        System.out.println("dupa");
//                    }
//                    Thread.currentThread().resume();
//                }
                // stop
            }
        }).start();
    }
}
