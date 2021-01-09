package agh.cs.lab;

import javafx.application.Application;
import javafx.stage.Stage;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;


public class Main extends Application{

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        JSONParser parser = new JSONParser();   // czy wczytywanie JSONa jest głównym zadaniem tego programu?
        String jsonPath = new File("").getAbsolutePath();
        Reader reader = new FileReader(jsonPath + "/src/main/agh/cs/lab/parameters.json");
        JSONObject jsonObject = (JSONObject) parser.parse(reader);
        int visualizations = (int) (long) jsonObject.get("numberOfSimulations");
        for(int i=0; i<visualizations; i++){
            Stage stage = new Stage();
            Application world = World.class.newInstance();
            world.start(stage);
        }
    }
}
