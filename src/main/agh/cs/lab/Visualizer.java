package agh.cs.lab;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import com.sun.org.glassfish.external.statistics.Statistic;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Visualizer {

    private static final int W = 950;
    private static final int H = 600;

    private static int xTiles;
    private static int yTiles;

    private final IWorldMap map;

    private final Tile[][] grid;

    private Text statisticsText;

    private final MapStatistics statistics;

    private boolean stopped;

    public Visualizer(IWorldMap map, Stage stage, int width, int height, float startEnergy, MapStatistics statistics) {
        xTiles = width;
        yTiles = height;
        this.map = map;
        this.statistics = statistics;
        grid = new Tile[xTiles][yTiles];
        this.stopped = false;

        Pane root = new Pane();
        root.setPrefSize(W, H);
        int tileSize = Math.min(W / width, H / height);
        for (int x = 0; x < xTiles; x++) {
            for (int y = 0;  y< yTiles; y++) {
                Tile tile = new Tile(x, y, tileSize, startEnergy);
                grid[x][y] = tile;
                root.getChildren().add(tile);
            }
        }

        statisticsText = new Text();
        statisticsText.setFont(Font.font(20));
        statisticsText.setTranslateX(xTiles * tileSize + 10);
        statisticsText.setTranslateY(100);
        root.getChildren().add(statisticsText);

        root.autosize();
        stage.addEventFilter(KeyEvent.KEY_PRESSED, event->{
            if (event.getCode() == KeyCode.SPACE) {
               stopped = !stopped;
            }
        });
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Darwin World");
        stage.show();
    }

    public boolean isStopped() {
        return stopped;
    }

    //    private List<Tile> getNeighbors(Tile tile) {
//        List<Tile> neighbors = new ArrayList<>();
//
//        // ttt
//        // tXt
//        // ttt
//
//        int[] points = new int[]{
//                -1, -1,
//                -1, 0,
//                -1, 1,
//                0, -1,
//                0, 1,
//                1, -1,
//                1, 0,
//                1, 1
//        };
//
//        for (int i = 0; i < points.length; i++) {
//            int dx = points[i];
//            int dy = points[++i];
//
//            int newX = tile.x + dx;
//            int newY = tile.y + dy;
//
//            if (newX >= 0 && newX < xTiles
//                    && newY >= 0 && newY < yTiles) {
//                neighbors.add(grid[newX][newY]);
//            }
//        }
//
//        return neighbors;
//    }

    public void update() {
        for (int i = 0; i < xTiles; i++) {
            for (int j = 0; j < yTiles; j++) {
                grid[i][j].updateTile(map.objectAt(new Vector2d(i, j)));
            }
        }

        statisticsText.setText(statistics.toString());
    }



}