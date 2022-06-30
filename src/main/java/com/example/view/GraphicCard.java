package com.example.view;

import com.example.enums.StackName;
import com.example.model.Card;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class GraphicCard {
    private final Card card;
    private StackName stackName;
    private final ImageView cardImage;
    private final ImageView cover;
    private final StackPane image;
    private int coordinateX;
    private int coordinateY;
    private final GraphicGame graphicGame;
    private double startX;
    private double startY;

    public GraphicCard(Card card, StackName stackName, ImageView cardImage, ImageView cover, GraphicGame graphicGame) {
        this.card = card;
        this.stackName = stackName;
        this.cardImage = cardImage;
        this.cover = cover;
        this.graphicGame = graphicGame;
        this.image = new StackPane();
        this.image.setPrefSize(144, 202);
        this.image.setStyle("-fx-background-color: white; -fx-background-radius: 8 8 8 8;");
        this.image.setTranslateX(StackName.DECK.getX());
        this.image.setTranslateY(StackName.DECK.getY());
        setUpCard();
    }

    private void setUpCard() {
        cover.setOnMouseClicked(event -> {
            if (this.stackName.equals(StackName.DECK) && card.isCovered()) {
                this.graphicGame.deckNext();
            }
        });
        AtomicBoolean releaseFlag = new AtomicBoolean(false);
        cardImage.setOnMousePressed(event -> {
            if (graphicGame.isEnableAction()) {
                if (event.isSecondaryButtonDown()) {
                    graphicGame.executeDoubleClick(this);
                    graphicGame.moveCards(null);
                    // secondary button press
                } else {
                    releaseFlag.set(true);
                    ArrayList<GraphicCard> subGraphicCards = getGraphicCards();
                    startX = event.getSceneX() - image.getTranslateX();
                    startY = event.getSceneY() - image.getTranslateY();
                    image.toFront();
                    for (GraphicCard graphicCard : subGraphicCards) {
                        graphicCard.startGroupMovement(event);
                    }
                    // primary button press
                }
            }
        });

        cardImage.setOnMouseDragged(event -> {
            if (graphicGame.isEnableAction()) {
                if (releaseFlag.get()) {
                    ArrayList<GraphicCard> subGraphicCards = getGraphicCards();
                    image.setTranslateX(event.getSceneX() - startX);
                    image.setTranslateY(event.getSceneY() - startY);
                    for (GraphicCard graphicCard : subGraphicCards) {
                        graphicCard.setGroupMovement(event);
                    }
                }
            }
        });

        cardImage.setOnMouseReleased(event -> {
            if (graphicGame.isEnableAction()) {
                if (releaseFlag.get()) {
                    ArrayList<GraphicCard> subGraphicCards = getGraphicCards();
                    StackName stackTarget = graphicGame.askToMoveCard(this);
                    for (GraphicCard graphicCard : subGraphicCards) {
                        graphicGame.askToMoveCard(graphicCard, stackTarget);
                    }
                    graphicGame.moveCards(null);
                    releaseFlag.set(false);
                }
            }
        });
    }

    public Card getCard() {
        return card;
    }

    public StackPane getImage() {
        image.getChildren().clear();
        if (card.isCovered()) image.getChildren().add(cover);
        if (!card.isCovered()) image.getChildren().add(cardImage);
        return image;
    }

    public void setStackName(StackName stackName) {
        this.stackName = stackName;
    }

    public StackName getStackName() {
        return stackName;
    }

    public int getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(int coordinateX) {
        this.coordinateX = coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(int coordinateY) {
        this.coordinateY = coordinateY;
    }

    public void startGroupMovement(MouseEvent event) {
        startX = event.getSceneX() - image.getTranslateX();
        startY = event.getSceneY() - image.getTranslateY();
        image.toFront();
    }

    public void setGroupMovement(MouseEvent event) {
        image.setTranslateX(event.getSceneX() - startX);
        image.setTranslateY(event.getSceneY() - startY);
    }

    private ArrayList<GraphicCard> getGraphicCards() {
        ArrayList<GraphicCard> graphicCards = new ArrayList<>();
        ArrayList<GraphicCard> stack = graphicGame.getStacks().get(stackName.ordinal());
        int index = stack.indexOf(this);
        for (int i = index + 1; i < stack.size(); i++) {
            graphicCards.add(stack.get(i));
        }
        return graphicCards;
    }

    @Override
    public String toString() {
        return card.toString();
    }
}
