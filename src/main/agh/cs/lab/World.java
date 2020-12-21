package agh.cs.lab;

import javafx.application.Application;
import javafx.stage.Stage;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.awt.*;
import java.io.*;

public class World extends Application {

    private final BooleanHolder stopped = new BooleanHolder();

    private final MutableInt day = new MutableInt();

    private final MutableInt timeGap = new MutableInt(2000);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws ParseException, IOException {
        JSONParser parser = new JSONParser();
        String jsonPath = new File("").getAbsolutePath();
        Reader reader = new FileReader(jsonPath + "/src/main/agh/cs/lab/parameters.json");
        JSONObject jsonObject = (JSONObject) parser.parse(reader);

        int width = (int) (long) jsonObject.get("width");
        int height = (int) (long) jsonObject.get("height");
        float jungleRatio = (float) (double) jsonObject.get("jungleRatio");
        int startEnergy = (int) (long) jsonObject.get("startEnergy");
        int plantEnergy = (int) (long) jsonObject.get("plantEnergy");
        int startAnimals = (int) (long) jsonObject.get("startAnimals");
        float moveEnergy = (float) (double) jsonObject.get("moveEnergy");
        int numberOfDays = (int) (long) jsonObject.get("numberOfDays");

        Statistics statistics = new Statistics(startAnimals, day);
        AverageStatistics averageStatistics = new AverageStatistics(day);
        AnimalTracker tracker = new AnimalTracker(day);
        TorusMap map = new TorusMap(width, height, jungleRatio, statistics);
        SimulationEngine engine = new SimulationEngine(map, startAnimals, moveEnergy, startEnergy, plantEnergy, tracker);
        Visualizer visualizer = new Visualizer(map, stage, width, height, startEnergy, statistics, stopped, day, tracker, timeGap, jungleRatio);


        new Thread(() -> {
            while (day.getValue() < numberOfDays) {
                try {
                    Thread.sleep(timeGap.getValue());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                day.increment();
                engine.nextDay();
                statistics.update(engine.getAnimals(), engine.getCurrentAmountOfGrass());
                averageStatistics.update(statistics);
                visualizer.update();
                while (stopped.getValue()) {
                    Thread.onSpinWait();
                }
            }

            averageStatistics.saveToFile();
            if (Desktop.isDesktopSupported()) {
                try {
                    Desktop.getDesktop().edit(new File("average_statistics.txt"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }).start();
    }

}
