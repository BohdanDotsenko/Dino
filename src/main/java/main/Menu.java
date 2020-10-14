package main;

import javafx.animation.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Menu {
    public Stage primaryStage;
    public Pane pane;
    private Dino dino;
    public Menu(Pane pane, Stage primaryStage, Dino dino) {
        this.dino = dino;
        this.pane = pane;
        this.primaryStage = primaryStage;
        MenuItem Start = new MenuItem("Start");

        MenuItem Not = new MenuItem("Please, don't click!");
        MenuItem Quit = new MenuItem("Quit");
        Quit.setOnMouseClicked(event -> { System.exit(0);});


        SubMenu mainMenu = new SubMenu(Start,Not, Quit);

        MenuBox menuBox = new MenuBox(mainMenu);
        Start.setOnMouseClicked(event -> {
            menuBox.setVisible(false);
            new StartGame(pane, primaryStage, dino);
        });
        pane.getChildren().addAll(menuBox);
    }

}

class MenuItem extends StackPane {
    public MenuItem(String name) {
        Rectangle rectangle = new Rectangle(200, 20, Color.LIGHTGRAY);

        Text text = new Text(name);
        text.setFill(Color.BLACK);
        text.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        this.setAlignment((Pos.CENTER));
        getChildren().addAll(rectangle, text);
        FillTransition anim = new FillTransition(Duration.seconds(0.5), rectangle);
        setOnMouseEntered( event -> {
            anim.setFromValue(Color.LIGHTGRAY);
            anim.setToValue(Color.DARKGOLDENROD);
            anim.setCycleCount(Animation.INDEFINITE);
            anim.setAutoReverse(true);
            anim.play();
        });
        setOnMouseExited(event -> {
            anim.stop();
            rectangle.setFill(Color.LIGHTGRAY);
        });
    }
}

class MenuBox extends Pane {
    static SubMenu subMenu;
    public MenuBox(SubMenu subMenu) {
        MenuBox.subMenu = subMenu;
        setVisible(true);
        Rectangle rectangle = new Rectangle(1200, 800, Color.WHITE);
        getChildren().addAll(rectangle, subMenu);
    }
    public void setSubMenu(SubMenu subMenu) {
        getChildren().remove(MenuBox.subMenu);
        MenuBox.subMenu = subMenu;
        getChildren().add(MenuBox.subMenu);
    }
}

class SubMenu extends VBox {
    public SubMenu(MenuItem...items) {
        setSpacing(15);
        setTranslateX(500);
        setTranslateY(300);
        for(MenuItem item : items) {
            getChildren().addAll(item);
        }
    }
}
