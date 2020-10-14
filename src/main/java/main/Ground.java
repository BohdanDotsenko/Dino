package main;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;


class Ground extends Pane {
    public Pane pane;
    protected Image groundImg = new Image("Ground.png");
    private Timeline t;
    protected ImageView groundOne = new ImageView(this.groundImg);
    protected ImageView groundTwo = new ImageView(this.groundImg);
    public Ground(Pane pane) {
        this.pane = pane;
        this.init();
    }

    protected void init() {
        groundOne.setLayoutY(750);
        groundOne.setLayoutX(0);
        groundTwo.setLayoutY(750);
        groundTwo.setLayoutX(1200);
        this.animation();
        pane.getChildren().addAll(groundOne);
        pane.getChildren().addAll(groundTwo);
    }

    protected void animation() {
        t = new Timeline(
                new KeyFrame(Duration.seconds(6), new KeyValue(groundOne.translateXProperty(), -1200)),
                new KeyFrame(Duration.seconds(6), new KeyValue(groundTwo.translateXProperty(), -1200))
        );
        t.setCycleCount(Timeline.INDEFINITE);
        t.play();
    }

}

