package com.example.view;

import com.example.enums.StackName;
import com.example.model.Card;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.Objects;

public class GraphicCardFactory {
    private final Pane pane;
    private final GraphicGame graphicGame;

    public GraphicCardFactory(Pane pane, GraphicGame graphicGame) {
        this.pane = pane;
        this.graphicGame = graphicGame;
    }

    public GraphicCard makeGraphicCard(Card card, StackName stackName) {
        ImageView cover = makeCover();
        GraphicCard graphicCard = new GraphicCard(card, stackName, makeCardImage(card), cover, graphicGame);
        pane.getChildren().add(graphicCard.getImage());
        return graphicCard;
    }

    private ImageView makeCover() {
        String string = "cover.png";
        ClassLoader classLoader = getClass().getClassLoader();
        String fileURL = Objects.requireNonNull(classLoader.getResource(string)).toExternalForm();
        ImageView imageView = new ImageView(new Image(fileURL));
        imageView.setFitWidth(134);
        imageView.setFitHeight(192);
        return imageView;
    }

    private ImageView makeCardImage(Card card) {
        String string = card.getRank().getRankChar() + card.getSuit().getSuitChar() + ".png";
        ClassLoader classLoader = getClass().getClassLoader();
        String fileURL = Objects.requireNonNull(classLoader.getResource(string)).toExternalForm();
        ImageView imageView = new ImageView(new Image(fileURL));
        imageView.setFitWidth(144);
        imageView.setFitHeight(202);
        return imageView;
    }

}
