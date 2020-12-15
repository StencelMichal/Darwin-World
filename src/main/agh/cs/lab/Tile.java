package agh.cs.lab;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Tile extends StackPane {
    private final int x;
    private final int y;
    private final float startEnergy;
    private Rectangle border;
    private Text text = new Text();

    public Tile(int x, int y, int tileSize, float startEnergy) {
        this.x = x;
        this.y = y;
        this.startEnergy = startEnergy;
        border = new Rectangle(tileSize, tileSize);


        border.setStroke(Color.LIGHTGRAY);

        text.setFont(Font.font(tileSize / 2));
        text.setVisible(false);
        text.setText("X");

        getChildren().addAll(border, text);

        setTranslateX(x * tileSize);
        setTranslateY(y * tileSize);

        //setOnMouseClicked(e -> open());
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


    // sledzenie statystyk
//        public void open() {
//            if (isOpen)
//                return;
//
////            if (hasBomb) {
////                System.out.println("Game Over");
////                scene.setRoot(createContent());
////                return;
////            }
//
//            isOpen = true;
//            text.setVisible(true);
//            border.setFill(null);
//
//            if (text.getText().isEmpty()) {
//                getNeighbors(this).forEach(Tile::open);
//            }
//        }
//    }

//    public void start(Stage stage) throws Exception {
//        scene = new Scene(createContent());
//
//        stage.setScene(scene);
//        stage.show();
//    }

}