package main;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

class Dino extends Pane {
    public Pane pane;
    public Image DinoLeft = new Image("Dino-left-up.png");
    public ImageView DinoLView = new ImageView(this.DinoLeft);
    private Timeline RunTime;
    public AnimationTimer JumpTimer;
    public boolean readyJump = true;
    public int gravity = 0;

    public Dino(Pane pane, Stage stage) {
        DinoLView.setLayoutY(720);
        DinoLView.setLayoutX(30);
        this.pane = pane;
        this.animation(RunTime);
        this.pane.getChildren().add(DinoLView);
        this.jump(stage);
    }
    public Timeline animation( Timeline runTime) {
        RunTime = new Timeline(
                new KeyFrame(Duration.millis(250), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        DinoLView.setImage(new Image("Dino-left-up.png"));
                    }
                }),
                new KeyFrame(Duration.millis(500), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        DinoLView.setImage(new Image("Dino-right-up.png"));
                    }
                })
        );
        RunTime.setCycleCount(Timeline.INDEFINITE);
        RunTime.play();
        return runTime;
    }

    public void jump(Stage stage) {
        stage.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            public void handle(KeyEvent key) {
                if (key.getCode() == KeyCode.SPACE && gravity == 0) {
                    RunTime.pause();
                    double groundPos = DinoLView.getTranslateY();
                    JumpTimer = new AnimationTimer() {
                        @Override
                        public void handle(long now) {
                            gravity += 1;
                            DinoLView.setTranslateY(DinoLView.getTranslateY() - 18 + gravity);
                            if (groundPos <= DinoLView.getTranslateY()) {
                                JumpTimer.stop();
                                RunTime.play();
                                gravity = 0;
                            }
                        }

                    };
                    JumpTimer.start();
                }
            }
        });

    }

}
