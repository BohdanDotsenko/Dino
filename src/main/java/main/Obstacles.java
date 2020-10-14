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

public class Obstacles {
    public Stage primaryStage;
    protected Pane pane;
    protected Dino dino;
    protected ImageView cactus;
    protected Image cactusOne = new Image("Cactus-0.png");
    protected Image cactusTwo = new Image("Cactus-1.png");
    protected Image cactusTree = new Image("Cactus-2.png");
    protected Image cactusFore = new Image("Cactus-3.png");
    protected Image cactusFive = new Image("Cactus-4.png");
    protected boolean lose = true;
    protected Score score;
    protected ArrayList<Image> listImage = new ArrayList<Image>();
    protected ArrayList<ImageView> listImageView = new ArrayList<ImageView>();

    public Obstacles(Pane pane, Dino dino, Score score) {
        this.score = score;
        this.dino = dino;
        this.primaryStage = primaryStage;
        this.pane = pane;
        this.setListImage();
        this.setPositionImg();
        animation().start();
        collision();

    }
    protected void setListImage() {
        listImage.add(cactusOne);
        listImage.add(cactusTwo);
        listImage.add(cactusTree);
        listImage.add(cactusFore);
        listImage.add(cactusFive);
    }
    protected void setPositionImg() {
        for (int i = 0; i < listImage.size(); i++) {
            cactus = new ImageView();
            cactus.setImage(listImage.get(i));
            cactus.setLayoutY(720);
            cactus.setLayoutX(random());
            listImageView.add(cactus);
        }
        pane.getChildren().addAll(listImageView);
    }
    private double random() {
        double result = 1200 + (int) (Math.random() * 15) * 100;

        for (ImageView y : listImageView) {
            if (Math.abs(result - y.getLayoutX()) < 250)
                result = -50;
        }
        return result;
    }

    public AnimationTimer animation() {
        AnimationTimer animation = new AnimationTimer() {
            @Override
            public void handle(long now) {
                for (ImageView i : listImageView) {
                    i.setLayoutX(i.getLayoutX() - 3.35);
                    if (i.getLayoutX() < -50) {
                        i.setLayoutX(random());
                    }
                }
            }
        };
        return animation;
    }
    public boolean collision() {
        int i = 0;
        Timeline t = new Timeline(
                new KeyFrame(Duration.millis(1), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent t) {
                        for (ImageView i : listImageView) {
                            if (dino.DinoLView.getBoundsInParent().intersects(i.getLayoutX() + 0, i.getLayoutY() + 0,
                                    i.getBoundsInParent().getWidth() - 16, i.getBoundsInParent().getHeight() - 16) && lose) {
                                lose = false;
                                new gameOver(pane, dino, score);
                            }
                        }
                    }
                })
        );
        t.setCycleCount(Timeline.INDEFINITE);
        t.play();
        return lose;
    }

}
