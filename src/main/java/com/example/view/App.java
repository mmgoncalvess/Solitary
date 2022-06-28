package com.example.view;

import com.example.controller.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class App extends Application {
    Controller controller = new Controller();

    @Override
    public void start(Stage stage) {
        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: #20ff20;");
        Scene scene = new Scene(pane, 1482, 916);
        stage.setTitle("Solitary");
        stage.setScene(scene);
        stage.show();
        Button buttonOne = new Button("Init");
        Button buttonTwo = new Button("Initial Deal");
        Button buttonThree = new Button("Read movements");
        buttonOne.relocate(1302, 40);
        buttonTwo.relocate(1302, 90);
        buttonThree.relocate(1302, 140);
        buttonOne.setPrefWidth(140);
        buttonTwo.setPrefWidth(140);
        buttonThree.setPrefWidth(140);
        AtomicReference<GraphicGame> graphicGame = new AtomicReference<>(new GraphicGame(pane, controller));
        pane.getChildren().addAll(buttonOne, buttonTwo, buttonThree);

        buttonOne.setOnAction(event -> {
            controller.newGame();
            pane.getChildren().clear();
            pane.getChildren().addAll(buttonOne, buttonTwo, buttonThree);
            graphicGame.set(new GraphicGame(pane, controller));
            stage.getIcons().clear();
            stage.getIcons().add(getIcon());
            controller.initialDeal();
            graphicGame.get().moveCards(() -> {
                graphicGame.get().deckNext();
                graphicGame.get().deckNext();
            });
        });

        buttonTwo.setOnAction(event -> graphicGame.get().moveCards(null));

        buttonThree.setOnAction(event -> {
            controller.completeGame();
            graphicGame.get().moveCards(null);
        });
        buttonOne.fire();
    }

    public Image getIcon() {
        String[] strings = {"clubs.jpg", "spades.png", "hearts.png", "diamonds.png"};
        String string = strings[(int) (Math.random() * 4)];
        ClassLoader classLoader = getClass().getClassLoader();
        String fileURL = Objects.requireNonNull(classLoader.getResource(string)).toExternalForm();
        return new Image(fileURL);
    }
}
