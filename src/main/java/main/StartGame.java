package main;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;



public class StartGame extends Pane{
    public Obstacles obstacles;
    public Stage primaryStage;
    public Dino dino;
    public Score Score;
    private Clouds clouds;

    public StartGame (Pane pane, Stage primaryStage, Dino dino) {
        this.dino = dino;
        this.primaryStage = primaryStage;
        dino = new Dino(pane, primaryStage);
        new Ground(pane);
        Score = new Score(pane);
        obstacles = new Obstacles(pane, dino, Score);
        clouds = new Clouds(pane);
    }

}


class gameOver {
    private Pane pane;
    private Button button;
    private Stage primaryStage;
    private Score Score;
    private Dino dino;
    private Obstacles obstacles;
    private Score yscore;
    private Clouds clouds;


    gameOver(Pane pane, Dino dino, Score score) {
        this.Score = score;
        this.dino = dino;
        this.pane = pane;
        this.primaryStage = primaryStage;
        pane.getChildren().clear();
        Label yscore = new Label();
        yscore.setText("Your Score : " + Score.GetScore());
        yscore.setLayoutX(550);
        yscore.setLayoutY(280);
        button = new Button();
        button.setGraphic(new ImageView(new Image("game_restart.png")));
        button.setLayoutX(550);
        button.setLayoutY(300);
        button.setVisible(true);
        pane.getChildren().addAll(yscore);
        pane.getChildren().addAll(button);

        button.setOnAction(event -> {
            pane.getChildren().addAll(dino.DinoLView);
            pane.getChildren().remove(yscore);
            pane.getChildren().remove(button);
            new Ground(pane);
            Score = new Score(pane);
            obstacles = new Obstacles(pane, dino, score);
            clouds = new Clouds(pane);
        });
    }
}

