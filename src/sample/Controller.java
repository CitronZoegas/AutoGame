package sample;

import javafx.animation.AnimationTimer;
import javafx.animation.RotateTransition;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.geometry.Point3D;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private BooleanProperty wPressed = new SimpleBooleanProperty();
    private BooleanProperty aPressed = new SimpleBooleanProperty();
    private BooleanProperty sPressed = new SimpleBooleanProperty();
    private BooleanProperty dPressed = new SimpleBooleanProperty();
    private BooleanProperty lPressed = new SimpleBooleanProperty();

    private BooleanBinding anyKeyPressed = wPressed.or(aPressed).or(sPressed).or(dPressed).or(lPressed);
    private double movementSpeed = 2;
    private double health = 100;

    Spells spells = new Spells();

    playerSquare PS = new playerSquare(health,movementSpeed);

    @FXML
    private Rectangle squareShape;
    @FXML
    private Button resetBtn;
    @FXML
    private Circle c1;
    @FXML
    private Circle c2;
    @FXML
    private AnchorPane scene;

    //@FXML
    //private Bounds boundsInScene = squareShape.localToScene(squareShape.getBoundsInLocal());

    @FXML
    void reset(ActionEvent event) {

        squareShape.setLayoutY(400);
        squareShape.setLayoutX(450);
        c1.setLayoutY(400);
        c1.setLayoutX(450);
        c2.setLayoutY(400);
        c2.setLayoutX(450);
    }

    AnimationTimer AT = new AnimationTimer() {
        @Override
        public void handle(long time) {
            if(wPressed.get()){

                squareShape.setLayoutY(squareShape.getLayoutY() - PS.getSpeed());
                c1.setLayoutY(c1.getLayoutY() - PS.getSpeed());
                c2.setLayoutY(c2.getLayoutY() - PS.getSpeed());
            }
            if(sPressed.get()){

                squareShape.setLayoutY(squareShape.getLayoutY() + PS.getSpeed());
                c1.setLayoutY(c1.getLayoutY() + PS.getSpeed());
                c2.setLayoutY(c2.getLayoutY() + PS.getSpeed());
            }
            if(aPressed.get()){

                squareShape.setLayoutX(squareShape.getLayoutX() - PS.getSpeed());
                c1.setLayoutX(c1.getLayoutX() - PS.getSpeed());
                c2.setLayoutX(c2.getLayoutX() - PS.getSpeed());
            }
            if(dPressed.get()){

                squareShape.setLayoutX(squareShape.getLayoutX() + PS.getSpeed());
                c1.setLayoutX(c1.getLayoutX() + PS.getSpeed());
                c2.setLayoutX(c2.getLayoutX() + PS.getSpeed());
            }
            if(lPressed.get()){

                setAttack(c1,true,720,3);
                setAttack(c2,true,180,3);
            }
        }
    };

    @FXML
    public void setAttack(Circle c, boolean reverse,int angle,int duration){

        RotateTransition rt = new RotateTransition(Duration.seconds(duration),c);

        rt.setByAngle(angle);
        rt.setAutoReverse(reverse);
        rt.setDelay(Duration.seconds(0));
        rt.setRate(12);

        rt.setCycleCount(25);
        rt.play();
    }


    public void movementInitialize() {

        /**
         * ON KEY PRESSED
         */
        scene.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.W){
                wPressed.set(true);
            }
            if(e.getCode() == KeyCode.A){
                aPressed.set(true);
            }
            if(e.getCode() == KeyCode.S){
                sPressed.set(true);
            }
            if(e.getCode() == KeyCode.D){
                dPressed.set(true);
            }
            if(e.getCode() == KeyCode.L){
                findCircles();
                lPressed.set(true);
            }

        });

        /**
         * ON KEY RELEASED
         */

        scene.setOnKeyReleased(e -> {
            if(e.getCode() == KeyCode.W){
                wPressed.set(false);
            }
            if(e.getCode() == KeyCode.A){
                aPressed.set(false);
            }
            if(e.getCode() == KeyCode.S){
                sPressed.set(false);
            }
            if(e.getCode() == KeyCode.D){
                dPressed.set(false);
            }
            if(e.getCode() == KeyCode.L){
                removeCircles();
                lPressed.set(false);
            }
        });



    }
    public void removeCircles() {
        c1.setOpacity(0);
        c2.setOpacity(0);
    }
    public void findCircles() {
        c1.setOpacity(1);
        c2.setOpacity(1);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        removeCircles();
        movementInitialize();

        anyKeyPressed.addListener((((observableValue, aBoolean, t1) -> {
            if(!aBoolean){
                AT.start();
            }else{
                AT.stop();
            }
        })));
    }

    /**
     *
     * @bound unknown.
     *
     * Game over checker instead of windowchecker.
     */
    /*public void windowCheck() {
        if(squareShape.getX() >= 800){
            squareShape.setX(800);
        }
        if(squareShape.getX() <= 0){
            squareShape.setX(0);
        }
        if(squareShape.getY() >= 900){
            squareShape.setY(900);
        }
        if(squareShape.getY() <= 0){
            squareShape.setY(0);
        }

        if(squareShape.intersects(bounds)){
            squareShape.setX(900);
        }
    }*/
    private void checkBoundaries() {
        boolean detected = false;

        if(squareShape.getLayoutBounds().intersects()){
            detected = true;
        }

    }
}
