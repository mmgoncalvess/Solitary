package com.example.view;

import com.example.controller.Controller;
import com.example.enums.Rank;
import com.example.enums.StackName;
import com.example.model.Card;
import com.example.model.Movement;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import java.util.ArrayList;

public class GraphicGame {
    private final ArrayList<ArrayList<GraphicCard>> stacks = new ArrayList<>();
    private final ArrayList<StackRectangle> stackRectangles = new ArrayList<>();
    private final GraphicCardFactory graphicCardFactory;
    private final Duration duration = new Duration(50);
    private final Controller controller;
    private final Button completeButton;
    private final Button newGameButton;
    private boolean enableAction = false;

    public GraphicGame(Pane pane, Controller controller, Button completeButton, Button newGameButton) {
        this.graphicCardFactory = new GraphicCardFactory(pane, this);
        this.controller = controller;
        this.completeButton = completeButton;
        this.newGameButton = newGameButton;
        init();
    }

    private void init() {
        for (int i = 0; i < 12; i++) stacks.add(new ArrayList<>());
        for (int i = 0; i < 11; i++) {
            double x1 = StackName.values()[i].getX();
            double x2 = StackName.values()[i].getX() + 144;
            double y1 = StackName.values()[i].getY();
            double y2 = StackName.values()[i].getY() + 202;
            stackRectangles.add(new StackRectangle(x1, x2, y1, y2));
        }
    }

    public GraphicCard findGraphicCard(Card card) {
        GraphicCard graphicCard = null;
        for (ArrayList<GraphicCard> stack : stacks) {
            for (GraphicCard item : stack) {
                if (item.getCard().getSuit().equals(card.getSuit()) &&
                        item.getCard().getRank().equals(card.getRank())) {
                    graphicCard = item;
                }
            }
        }
        if (graphicCard == null) {
            graphicCard = graphicCardFactory.makeGraphicCard(card, StackName.DECK);
            stacks.get(11).add(graphicCard);
        }
        return graphicCard;
    }

    public void moveCards(Runnable runnable) {
        enableAction = false;
        newGameButton.setDisable(true);

        Movement movement = controller.getMovement();
        if (movement != null) System.out.println(movement);
        if (movement == null) {
            enableAction = true;
            if (controller.isSolved()) completeButton.setDisable(false);
            newGameButton.setDisable(false);
            try {
                runnable.run();
            } catch (NullPointerException e) {
                //e.printStackTrace();
            }
            if (checkFinal()) startAnimation();
            return;
        }
        Card card = movement.getCard();
        StackName targetStackName = movement.getStackName();
        int index = movement.getIndex();
        GraphicCard graphicCard = findGraphicCard(card);
        StackName sourceStackName = graphicCard.getStackName();
        stacks.get(graphicCard.getStackName().ordinal()).remove(graphicCard);
        if (index < 0) {
            stacks.get(targetStackName.ordinal()).add(graphicCard);
        } else {
            stacks.get(targetStackName.ordinal()).add(index, graphicCard);
        }
        graphicCard.setStackName(targetStackName);
        graphicCard.setCoordinateX(targetStackName.getX());
        int indexLocation = getIndexLocation(targetStackName, index);
        graphicCard.setCoordinateY(targetStackName.getY() + indexLocation * 30);
        TranslateTransition transition = getTransition(graphicCard, runnable);
        transition.play();
        checkStack(sourceStackName);
        checkStack(targetStackName);
    }

    public int getIndexLocation(StackName newStackName, int index) {
        int indexLocation = stacks.get(newStackName.ordinal()).size() - 1;
        if (index >= 0) indexLocation = index;
        if (newStackName.equals(StackName.DECK) || newStackName.equals(StackName.FINAL_ONE) ||
                newStackName.equals(StackName.FINAL_TWO) || newStackName.equals(StackName.FINAL_THREE) ||
                newStackName.equals(StackName.FINAL_FOUR)) {
            indexLocation = 0;
        }
        return indexLocation;
    }

    private TranslateTransition getTransition(GraphicCard graphicCard, Runnable runnable) {
        double deltaX = graphicCard.getCoordinateX() - graphicCard.getImage().getTranslateX();
        double deltaY = graphicCard.getCoordinateY() - graphicCard.getImage().getTranslateY();
        Duration time = graphicCard.getStackName().equals(StackName.DECK)? new Duration(1) : duration;
        TranslateTransition transition = new TranslateTransition(time, graphicCard.getImage());
        transition.setByX(deltaX);
        transition.setByY(deltaY);
        transition.setOnFinished(event -> {
            checkDeck();
            moveCards(runnable);
        });
        return transition;
    }

    void deckMoveLeft(GraphicCard graphicCard) {
        graphicCard.getImage().setTranslateX(60);
        graphicCard.getImage().setTranslateY(52);
    }

    private void deckMoveRight(GraphicCard graphicCard) {
        graphicCard.getImage().setTranslateX(263);
        graphicCard.getImage().setTranslateY(52);
    }

    public void deckNext() {
        controller.next();
        moveCards(this::checkDeck);
    }

    private void checkDeck() {
        for (GraphicCard graphicCard : stacks.get(11)) {
            if (graphicCard.getCard().isCovered()) {
                deckMoveLeft(graphicCard);
            } else {
                deckMoveRight(graphicCard);
            }
        }
    }

    private void updateRectangles() {
        for (int i = 0; i < 7; i++) {
            double y2 = (stacks.get(i).size() - 1) * 30 + 202 + stackRectangles.get(i).getY1();
            stackRectangles.get(i).setY2(y2);
        }
    }

    public StackName getStackTarget(GraphicCard graphicCard) {
        updateRectangles();
        int index = 0;
        double maxArea = 0;
        for (int i = 0; i < 11; i++) {
            double area = getArea(stackRectangles.get(i), graphicCard);
            System.out.println("Stack number:" + i + "  Area:" + area);
            if (area > maxArea) {
                maxArea = area;
                index = i;
            }
        }
        if (maxArea > 0) {
            return StackName.values()[index];
        } else {
            return null;
        }

    }

    public double getArea(StackRectangle stackRectangle, GraphicCard graphicCard) {
        double tx = graphicCard.getImage().getTranslateX();
        double ty = graphicCard.getImage().getTranslateY();
        double x1 = stackRectangle.getX1();
        double x2 = stackRectangle.getX2();
        double y1 = stackRectangle.getY1();
        double y2 = stackRectangle.getY2();
        double height = 0;
        double width = 0;
        if (ty < y2 && ty > y1) height = Math.min(y2 - ty, 202.0);
        if (ty <= y1) height = Math.max(ty + 202 - y1, 0);
        if (tx < x1) width = Math.max(tx + 144 - x1, 0);
        if (tx >= x1) width = Math.max(x2 - tx, 0);
        return height * width;
    }

    public StackName askToMoveCard(GraphicCard graphicCard) {
        StackName stackTarget = getStackTarget(graphicCard);
        if (stackTarget == null) stackTarget = graphicCard.getStackName();
        int index = stacks.get(graphicCard.getStackName().ordinal()).indexOf(graphicCard);
        Card card = graphicCard.getCard();
        controller.moveCard(card, stackTarget, index);
        return stackTarget;
    }

    public void askToMoveCard(GraphicCard graphicCard, StackName stackTarget) {
        int index = stacks.get(graphicCard.getStackName().ordinal()).indexOf(graphicCard);
        Card card = graphicCard.getCard();
        controller.moveCard(card, stackTarget, index);
    }

    private void checkStack(StackName stackName) {
        if (stackName.equals(StackName.DECK)) {
            checkDeck();
        } else {
            for (GraphicCard graphicCard : stacks.get(stackName.ordinal())) {
                graphicCard.getImage().toFront();
            }
        }
    }

    public void executeDoubleClick(GraphicCard graphicCard) {
        Card card = graphicCard.getCard();
        if (card.getRank().equals(Rank.ACE)) {
            for (int stackIndex = 7; stackIndex < 11; stackIndex++) {
                if (stacks.get(stackIndex).size() == 0) {
                    StackName stackName = StackName.values()[stackIndex];
                    askToMoveCard(graphicCard, stackName);
                    return;
                }
            }
        } else {
            for (int stackIndex = 7; stackIndex < 11; stackIndex++) {
                //noinspection ComparatorResultComparison
                if (stacks.get(stackIndex).size() > 0 &&
                        stacks.get(stackIndex).get(0).getCard().getSuit().equals(card.getSuit()) &&
                        stacks.get(stackIndex).get(stacks.get(stackIndex).size() -1).getCard().getRank().compareTo(card.getRank()) == -1) {
                    StackName stackName = StackName.values()[stackIndex];
                    askToMoveCard(graphicCard, stackName);
                    return;
                }
            }
        }
    }

    public ArrayList<ArrayList<GraphicCard>> getStacks() {
        return stacks;
    }

    public void startAnimation() {
        try {
            Thread.sleep(1200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Animation animation = new Animation(stacks);
        animation.start();
    }

    public boolean isEnableAction() {
        return enableAction;
    }

    public boolean checkFinal() {
        for (int stackIndex = 7; stackIndex < 11; stackIndex++) {
            if (stacks.get(stackIndex).size() < 13) return false;
        }
        return true;
    }
}
