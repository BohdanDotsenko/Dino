package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;



public class Main extends Application {
    public static int score;
    public Label scoreLabel = new Label("Score : " + score);
    public Object Dino;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Pane pane = new Pane();
        primaryStage.setScene(new Scene(pane, 1200, 800));
        primaryStage.setResizable(false);
        primaryStage.setTitle("Dino");
        new Menu(pane, primaryStage);


        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
