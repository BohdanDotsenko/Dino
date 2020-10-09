package main;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.input.KeyEvent;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;

import java.util.ArrayList;


public class StartGame {
    private final Object Obstacles;
    public Stage primaryStage;
    public Pane pane;
    public Object Dino;
    public Object Score;
    public int score;
    public StartGame (Pane pane, Stage primaryStage) {
        this.primaryStage = primaryStage;
        new Ground(pane);
        Score = new Score(pane);
        Dino = new Dino(pane, primaryStage);
        Obstacles = new Obstacles((main.Dino) Dino, pane, primaryStage);
        this.colision();
    }
    private void colision() {

    }
}

class Obstacles {
    private ArrayList<ImageView> imageCactus;
    private ArrayList<ImageView> imageClouds;

    public Obstacles(Dino dino, Pane pane, Stage primaryStage) {
        int rand = 0;
//        imageCactus = new ArrayList<ImageView>();
//        ImageView Cloud = new ImageView(new Image("cloud.png"));
//
//        for (int i = 0; i < 5; i++) {
//            ImageView Cactus = new ImageView("Cactus-" +i + ".png");
//            rand = 1000 + (int)(Math.random() * 10)*300;
//            imageCactus[i].setLayoutX(750 - new Image("/images/Cactus-" + i +".png").getHeight() + 10);
//            Cactus.setLayoutY(50);
//            for (ImageView y : imageCactus) {
//                if (Math.abs(rand - y.getLayoutX()) < 300)
//                    rand = -50;
//            }
//        }
//        pane.getChildren().addAll(Cloud);
//        pane.getChildren().addAll(imageCactus);
    }

    protected AnimationTimer animation() {
        AnimationTimer animation = new AnimationTimer() {
            @Override
            public void handle(long now) {
                for (ImageView i : listImageView) {
                    i.setLayoutX(i.getLayoutX() - 2.2);
                    if (i.getLayoutX() < -50) {
                        i.setLayoutX(random());
                    }
                }
            }
        };
        return animation;
    }
}

