package agh.cs.lab;

import javafx.scene.effect.*;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Tile extends StackPane {

    private final float startEnergy;
    private final Rectangle border;
    private final Text text = new Text();

    public Tile(int x, int y, int tileSize, float startEnergy, Visualizer visualizer) {
        this.startEnergy = startEnergy;
        border = new Rectangle(tileSize-2, tileSize-2);
//        border.setStroke(Color.LIGHTGRAY);

//        border.setFill(Color.hsb(55, 0.65, 0.51));

        text.setFont(Font.font(tileSize / 2));
        text.setVisible(false);
        text.setText("X");

        getChildren().addAll(border, text);

        setTranslateX(x * tileSize);
        setTranslateY(y * tileSize);

        setOnMouseClicked(e -> visualizer.trackOnPosition(new Vector2d(x,y)));

    }

    public void updateTile(AbstractWorldElement element) {
        if (element == null) {
            border.setFill(Color.hsb(55, 0.65, 0.51));
            text.setVisible(false);
        } else if (element.getClass().equals(Grass.class)) {
            border.setFill(Color.hsb(154, 0.92, 1.0));
            text.setVisible(false);
        } else if (element.getClass().equals(Animal.class)) {
            text.setVisible(true);
            double hue = Math.min(((Animal) element).getEnergy() * 100 / (3 * startEnergy), 100);
            border.setFill(Color.hsb(hue, 1.0, 1.0));
        }
    }

    public void highlight(){
        border.setFill(Color.hsb(184, 1.0, 0.88));
    }

}