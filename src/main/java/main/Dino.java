package main;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Dino extends Pane {
    public Pane pane;
    public Image DinoLeft = new Image("Dino-left-up.png");
    public ImageView DinoLView = new ImageView(this.DinoLeft);
    private Timeline RunTime;
    public AnimationTimer JumpTimer;
    public boolean readyJump = true;
    public int gravity = 0;
    private Obstacles obstacles;

    public Dino(Pane pane, Stage stage) {

        DinoLView.setLayoutY(720);
        DinoLView.setLayoutX(30);
        this.pane = pane;
        this.animation(RunTime);
        this.pane.getChildren().add(DinoLView);
        jump(stage);
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

    private void installEventHandler(Pane pane) {

        final EventHandler<KeyEvent> keyEventHandler =
                new EventHandler<KeyEvent>() {
                    public void handle(final KeyEvent keyEvent) {
                        if (keyEvent.getCode() == KeyCode.ENTER) {
                            setPressed(keyEvent.getEventType()
                                    == KeyEvent.KEY_PRESSED);
                            System.out.println("Enter");
                            keyEvent.consume();
                        }
                    }
                };

        pane.setOnKeyPressed(keyEventHandler);
        pane.setOnKeyReleased(keyEventHandler);
    }

    public void jump(Stage stage) {
        stage.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent key) {
                if (key.getCode() == KeyCode.SPACE && gravity == 0) {
                    RunTime.pause();
                    double groundPos = DinoLView.getTranslateY();
                    JumpTimer = new AnimationTimer() {
                        @Override
                        public void handle(long now) {
//                            if(!obstacles.collision()) {
//                                JumpTimer.stop();
//                                RunTime.pause();
//                            }
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
