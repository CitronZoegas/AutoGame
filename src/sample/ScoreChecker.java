package sample;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class ScoreChecker {

    @FXML
    private Circle c2;
    @FXML
    private Rectangle squareShape;

    public ScoreChecker() {

    }

    public void startTimer() {
        timerCounter.start();
    }

    AnimationTimer timerCounter = new AnimationTimer() {
        @Override
        public void handle(long timer){

        }
    };
}