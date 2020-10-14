package main;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

public class Clouds {
    public Stage primaryStage;
    protected Pane pane;
    protected ImageView cactus;
    protected Image cactusOne = new Image("cloud.png");

    protected boolean lose = true;
    protected Score score;
    protected ArrayList<Image> listImage = new ArrayList<Image>();
    protected ArrayList<ImageView> listImageView = new ArrayList<ImageView>();

    public Clouds(Pane pane) {
        this.primaryStage = primaryStage;
        this.pane = pane;
        this.setListImage();
        this.setPositionImg();
        animation().start();

    }
    protected void setListImage() {
        for (int i = 0; i < 12; i++)
            listImage.add(cactusOne);

    }
    protected void setPositionImg() {
        for (int i = 0; i < listImage.size(); i++) {
            cactus = new ImageView();
            cactus.setImage(listImage.get(i));
            cactus.setLayoutY(300 + i * i);
            cactus.setLayoutX(randomX());
            listImageView.add(cactus);
        }
        pane.getChildren().addAll(listImageView);
    }
    private double randomX() {
        double result = 1200 + (int) (Math.random() * 15) * 100;

        for (ImageView y : listImageView) {
            if (Math.abs(result - y.getLayoutX()) < 50)
                result = -50;
        }
        return result;
    }

    private double randomY () {
        double result = 100 + (int) (Math.random() * 15) * 100;

        for (ImageView y : listImageView) {
            if (Math.abs(result) > 500)
                result += 50;
        }
        return result;
    }

    private AnimationTimer animation() {
        AnimationTimer animation = new AnimationTimer() {
            @Override
            public void handle(long now) {
                for (ImageView i : listImageView) {
                    i.setLayoutX(i.getLayoutX() - 3.35);
                    if (i.getLayoutX() < -50) {
                        i.setLayoutX(randomX());
                    }
                }
            }
        };
        return animation;
    }


}