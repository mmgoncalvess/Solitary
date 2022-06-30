package com.example.view;

import javafx.animation.TranslateTransition;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;

public class Animation {
    private final ArrayList<ArrayList<GraphicCard>> stacks;
    private final ArrayList<StackPane> images = new ArrayList<>();
    private final Random random = new Random();

    public Animation(ArrayList<ArrayList<GraphicCard>> stacks) {
        this.stacks = stacks;
    }

    public void start() {
        fillImages();
        moveImage();
    }

    private void fillImages() {
        for (ArrayList<GraphicCard> stack : stacks) {
            for (GraphicCard graphicCard : stack) {
                graphicCard.getCard().setCovered(false);
                images.add(graphicCard.getImage());
            }
        }
    }

    private void moveImage() {
        StackPane image = getNextImage();
        double x = random.nextDouble() * (1482 - 144);
        double y = random.nextDouble() * (916 - 202);
        double deltaX = x - image.getTranslateX();
        double deltaY = y - image.getTranslateY();
        Duration time = new Duration(100);
        TranslateTransition transition = new TranslateTransition(time, image);
        transition.setByX(deltaX);
        transition.setByY(deltaY);
        transition.setOnFinished(event -> moveImage());
        transition.play();
        image.toFront();
    }

    private StackPane getNextImage() {
        int index = random.nextInt(52);
        return images.get(index);
    }
}
