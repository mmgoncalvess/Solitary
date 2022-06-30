package com.example.view;

import com.example.controller.Controller;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class App extends Application {
    Controller controller = new Controller();
    ColorPicker colorPicker;

    @Override
    public void start(Stage stage) {
        Pane pane = new Pane();
        Scene scene = new Scene(pane, 1482, 916);
        stage.setTitle("Solitary");
        stage.setScene(scene);
        stage.show();
        Button newGameButton = new Button("New game");
        Button completeButton = new Button("Complete");
        newGameButton.relocate(60, 750);
        completeButton.relocate(60, 800);
        newGameButton.setPrefWidth(140);
        completeButton.setPrefWidth(140);
        StackPane rectangleOne = new StackPane();
        StackPane rectangleTwo = new StackPane();
        StackPane rectangleThree = new StackPane();
        StackPane rectangleFour = new StackPane();
        rectangleOne.setPrefSize(144, 202);
        rectangleTwo.setPrefSize(144, 202);
        rectangleThree.setPrefSize(144, 202);
        rectangleFour.setPrefSize(144, 202);
        Border border = new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(11),
                new BorderWidths(0.5), Insets.EMPTY));
        rectangleOne.setBorder(border);
        rectangleTwo.setBorder(border);
        rectangleThree.setBorder(border);
        rectangleFour.setBorder(border);
        rectangleOne.relocate(669, 52);
        rectangleTwo.relocate(872, 52);
        rectangleThree.relocate(1075, 52);
        rectangleFour.relocate(1278, 52);

        colorPicker = new ColorPicker();
        colorPicker.relocate(60, 700);
        colorPicker.setOnAction(event -> setBackground(pane, colorPicker.getValue()));
        colorPicker.getStyleClass().add("button");
        colorPicker.setStyle("-fx-color-label-visible: false; -fx-color-rect-height: 27; -fx-color-rect-width: 87;");
        colorPicker.setPrefWidth(90);

        AtomicReference<GraphicGame> graphicGame = new AtomicReference<>(new GraphicGame(pane, controller, completeButton, newGameButton));
        newGameButton.setOnAction(event -> {
            controller.newGame();
            pane.getChildren().clear();
            pane.getChildren().addAll(newGameButton, completeButton, rectangleOne, rectangleTwo,
                    rectangleThree, rectangleFour, colorPicker);
            graphicGame.set(new GraphicGame(pane, controller, completeButton, newGameButton));
            stage.getIcons().clear();
            stage.getIcons().add(getIcon());
            controller.initialDeal();
            setBackground(pane, getRandomColor());
            graphicGame.get().moveCards(() -> {
                graphicGame.get().deckNext();
                graphicGame.get().deckNext();
            });
            completeButton.setDisable(true);
        });

        completeButton.setOnAction(event -> {
            controller.completeGame();
            graphicGame.get().moveCards(null);
        });
        completeButton.setDisable(true);

        newGameButton.fire();
    }

    private Image getIcon() {
        String[] strings = {"clubs.jpg", "spades.png", "hearts.png", "diamonds.png"};
        String string = strings[(int) (Math.random() * 4)];
        ClassLoader classLoader = getClass().getClassLoader();
        String fileURL = Objects.requireNonNull(classLoader.getResource(string)).toExternalForm();
        return new Image(fileURL);
    }

    private Color getRandomColor() {
        return Color.color(Math.random(), Math.random(), Math.random());
    }

    private void setBackground(Pane pane, Color color) {
        pane.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
        colorPicker.setValue(color);
    }
}
