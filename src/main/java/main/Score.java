package main;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

class Score extends Pane {
    public Label score;
    public Stage primaryStage;
    public Pane pane;
    public int i;
    private Timeline RunTime;

    public Score(Pane pane) {
        this.primaryStage = primaryStage;
        i = 0;
        this.pane = pane;
        score = new Label();
        score.setTranslateX(1100);
        score.setTranslateY(10);
        RunTime = new Timeline(
                new KeyFrame(Duration.millis(250), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        i += 1;
                        score.setText("Score : " + i);
                    }
                })
        );
        RunTime.setCycleCount(Timeline.INDEFINITE);
        RunTime.play();
        pane.getChildren().addAll(score);
    }
    public int GetScore () {
        return i;
    }
}

