package agh.cs.lab;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class Main extends Application {

    private final int sizeX=40;

    private final int sizeY=20;

    private final int amountOfAnimals=3;

    public static void main(String[] args) {
        System.out.println("dupa");
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Pane root = new Pane();

        primaryStage.setTitle("World");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        for(int i =0; i<10; i++){
            for(int j =0; j<10; j++){
                Label label = new Label();
                label.setMinSize(20,20);
                label.setStyle("-fx-border-color:red; -fx-background-color: blue;");
                label.setTextFill(Color.GREEN);
                grid.add(label,i,j);
            }
        }


//        BorderPane pane = new BorderPane();
//        HBox world = new HBox();
//        pane.setCenter(world);
//
//        Rectangle r1 = new Rectangle(20,20);
//        r1.setFill(Color.AQUA);
////        r1.setX(-100);
////        r1.setY(300);
//        r1.relocate(100,100);
//        Rectangle r2 = new Rectangle(20,20);
//        r2.setFill(Color.BLUE);
//        Rectangle r3 = new Rectangle(20,20);
//        r3.setFill(Color.RED);
//
//
//
//        world.getChildren().add(r1);
//        world.getChildren().add(r2);
//        world.getChildren().add(r3);
//
//
//        Circle circle = new Circle(50,Color.BLUE);
//        circle.relocate(20, 20);
//        Rectangle rectangle = new Rectangle(100,100,Color.RED);
//        rectangle.relocate(300,70);
//        pane.getChildren().addAll(circle,rectangle);

        grid.setAlignment(Pos.CENTER);


        Scene scene = new Scene(root, 700,450);
        primaryStage.setScene(scene);


        GridPane g2 = new GridPane();
        grid.setAlignment(Pos.CENTER);
        for(int i =0; i<10; i++){
            for(int j =0; j<10; j++){
                Label label = new Label();
                label.setMinSize(20,20);
                label.setStyle("-fx-border-color:red; -fx-background-color: orange;");
                label.setTextFill(Color.GREEN);
                g2.add(label,i,j);
            }
        }

        root.getChildren().add(grid);
        Thread.sleep(2000);
        primaryStage.toFront();

        root.getChildren().removeAll();
        root.getChildren().add(g2);
        System.out.println("lol");
        primaryStage.show();

//        StackPane layout = new StackPane();
//        Scene scene = new Scene(layout, 800, 450);
//        primaryStage.setScene(scene);
//        primaryStage.show();
//        public class Demo extends Application {
//            public static void main(String[] args) {
//                Application.launch(args);
//            }
//
//            @Override
//            public void start(final Stage primaryStage) {
//
//                Pane root = new Pane();
//
//                GridPane gridPane = new GridPane();
//                gridPane.setGridLinesVisible(true);
//
//                for (int i = 0; i < 5; i++) {
//                    for (int j = 0; j < 5; j++) {
//
//                        Label label = new Label("Label " + i + "/" + j);
//                        label.setMouseTransparent(true);
//                        GridPane.setRowIndex(label, i);
//                        GridPane.setColumnIndex(label, j);
//
//                        gridPane.getChildren().add(label);
//                    }
//                }
//
//                root.getChildren().add( gridPane);
//
//                Scene scene = new Scene(root, 400, 300, Color.WHITE);
//                primaryStage.setScene(scene);
//
//                primaryStage.show();
//
//                gridPane.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
//                    @Override
//                    public void handle(MouseEvent e) {
//
//                        for( Node node: gridPane.getChildren()) {
//
//                            if( node instanceof Label) {
//                                if( node.getBoundsInParent().contains(e.getSceneX(),  e.getSceneY())) {
//                                    System.out.println( "Node: " + node + " at " + GridPane.getRowIndex( node) + "/" + GridPane.getColumnIndex( node));
//                                }
//                            }
//                        }
//                    }
//                });
//
//            }
//
//        }
    }
}
