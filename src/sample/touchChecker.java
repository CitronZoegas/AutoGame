package sample;

import javafx.animation.AnimationTimer;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.stage.Stage;
import java.util.LinkedList;

public class touchChecker {

    private Rectangle rectangleCollided;
    private LinkedList<Rectangle> enemyRectangle;
    private Controller controller;

    @FXML
    private Circle c2;
    @FXML
    private Rectangle squareShape;
    @FXML
    private AnchorPane scene;

    private int score = 0;

    public touchChecker(Controller controller, Rectangle squareShape, Rectangle rectangleCollided, LinkedList<Rectangle> enemyRectangle,Circle c2) {
        this.controller = controller;
        this.squareShape = squareShape;
        this.rectangleCollided = rectangleCollided;
        this.enemyRectangle = enemyRectangle;
        this.c2 = c2;
    }

    public void CollidedOrNa(Rectangle squareShape, Rectangle rectangleCollided) {
        if(!(squareShape == null) && squareShape.getBoundsInParent().intersects(rectangleCollided.getBoundsInParent())){
            collision.stop();
            gameOver();
            System.out.println("ACTUALLY STOPPED");

        }
    }

    public void hitEnemyWithSpell(Rectangle rectangleCollided, Circle circle) {

        if(circle.getBoundsInParent().intersects(rectangleCollided.getBoundsInParent())){
            score++;
            controller.scoreAreaIncrementer(score);
        }

    }

    public void gameOver() {
        Stage stage = (Stage) squareShape.getScene().getWindow();

        Label label = new Label("Final score: "+score);
        Label labelESC = new Label("\n\n\nPress 'ESC' to remove the score text.");
        label.setFont(new Font("Arial",50));
        label.setTextFill(Color.RED);
        labelESC.setFont(new Font("Arial", 20));
        labelESC.setTextFill(Color.RED);


        Popup pop = new Popup();
        pop.getContent().add(label);
        pop.getContent().add(labelESC);
        if(!pop.isShowing()){
            pop.isHideOnEscape();
            pop.show(stage);
        }
    }

    public void startSpellHitChecker() {
        spellHit.start();
    }

    public void startCollisionChecker() {
        collision.start();
    }

    AnimationTimer spellHit = new AnimationTimer() {
        @Override
        public void handle(long l) {
            for(Rectangle rec: enemyRectangle){
                if(rec != null){
                    hitEnemyWithSpell(rec,c2);
                }
            }
        }
    };

    AnimationTimer collision = new AnimationTimer() {
        @Override
        public void handle(long timer){
            for(Rectangle rec: enemyRectangle){
                if(rec != null){
                    controller.setTimer();
                    CollidedOrNa(squareShape,rec);
                }
            }
        }
    };
}