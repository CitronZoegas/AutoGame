package sample;

import javafx.animation.AnimationTimer;
import javafx.scene.shape.Rectangle;

import java.util.LinkedList;

public class touchChecker {
    private Rectangle rectangle;
    private Rectangle rectangleCollided;
    private LinkedList<Rectangle> enemyRectangle;

    public touchChecker(Rectangle rectangle, Rectangle rectangleCollided, LinkedList<Rectangle> enemyRectangle) {
        this.rectangle = rectangle;
        this.rectangleCollided = rectangleCollided;
        this.enemyRectangle = enemyRectangle;
    }
    public void CollidedOrNa(Rectangle rectangle, Rectangle rectangleCollided) {

        if(rectangle.getBoundsInParent().intersects(rectangleCollided.getBoundsInParent())){
            System.out.println("You lost");
            collision.stop();
        }
    }
    public void startChecker() {
        collision.start();
    }



    AnimationTimer collision = new AnimationTimer() {
        @Override
        public void handle(long timer){
            for(Rectangle rectangle: enemyRectangle){
                if(rectangle != null){
                    CollidedOrNa(rectangle,rectangleCollided);
                }
            }
        }
    };
}