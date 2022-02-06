package sample;

import javafx.animation.AnimationTimer;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
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
    private double maxX = 892;
    private double maxY = 735;

    private double EnHealth = 4;
    private double EnDamage = 2;
    private double EnSpeed = 1;
    private int EnHeight = 100;
    private int EnWidth = 100;

    Spells spells = new Spells(this);
    Enemies enemies = new Enemies(this,EnHealth,EnDamage,EnSpeed,EnHeight,EnWidth);
    playerSquare PS = new playerSquare(this,health,movementSpeed);



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
    @FXML
    private Rectangle outerSquare;
    //@FXML
    //private final Bounds bound = outerSquare.getBoundsInLocal();

    @FXML
    void reset(ActionEvent event) {

        double centerX = (417);
        System.out.println(centerX);
        double centerY = (338);
        squareShape.setLayoutY(centerY);
        squareShape.setLayoutX(centerX);
        c1.setLayoutY(centerY);
        c1.setLayoutX(centerX);
        c2.setLayoutY(centerY);
        c2.setLayoutX(centerX);
    }

    AnimationTimer AT = new AnimationTimer() {
        @Override
        public void handle(long time) {
            double Y = squareShape.getLayoutY();
            double X = squareShape.getLayoutX();
            if(checkBoundariesX(X)){
                squareShape.setLayoutX(squareShape.getLayoutX());
            }
            if(checkBoundariesY(Y)){
                squareShape.setLayoutY(squareShape.getLayoutY());
            }
            if (wPressed.get()) {
                squareShape.setLayoutY(squareShape.getLayoutY() - PS.getSpeed());
                c1.setLayoutY(c1.getLayoutY() - PS.getSpeed());
                c2.setLayoutY(c2.getLayoutY() - PS.getSpeed());
            }
            if (sPressed.get()) {
                squareShape.setLayoutY(squareShape.getLayoutY() + PS.getSpeed());
                c1.setLayoutY(c1.getLayoutY() + PS.getSpeed());
                c2.setLayoutY(c2.getLayoutY() + PS.getSpeed());
            }
            if (aPressed.get()) {
                squareShape.setLayoutX(squareShape.getLayoutX() - PS.getSpeed());
                c1.setLayoutX(c1.getLayoutX() - PS.getSpeed());
                c2.setLayoutX(c2.getLayoutX() - PS.getSpeed());
            }
            if (dPressed.get()) {
                squareShape.setLayoutX(squareShape.getLayoutX() + PS.getSpeed());
                c1.setLayoutX(c1.getLayoutX() + PS.getSpeed());
                c2.setLayoutX(c2.getLayoutX() + PS.getSpeed());
            }
            if (lPressed.get()) {
                System.out.println("lol");
                spells.circleSmash(c1, true, 720, 3);
                spells.circleSmash(c2, true, 180, 3);
            }
            }
        };

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
            if(e.getCode() == KeyCode.L) {
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
            if(e.getCode() == KeyCode.L) {
                removeCircles();
                lPressed.set(false);
            }
        });
    }

    /**
     * It seems like AnimationTimer can only take care of one "handle" method at a time.
     * Meaning i cant separate the movement and spell keybind inputs.
     */
    /*public void spellsInitialize() {
        scene.setOnKeyPressed(e-> {
            if(e.getCode() == KeyCode.L){

                lPressed.set(true);
            }
        });
        scene.setOnKeyReleased(e-> {
            if(e.getCode() == KeyCode.L){
                lPressed.set(false);
            }
        });
    }*/

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
        //spellsInitialize();

        anyKeyPressed.addListener((((observableValue, aBoolean, t1) -> {
            if(!aBoolean){
                AT.start();
            }else{
                AT.stop();
            }
        })));
    }

    private boolean checkBoundariesX(double x) {
        Bounds bound = outerSquare.getBoundsInLocal();
        if(x >=bound.getMaxX() || x <=bound.getMinX()){
            System.out.println("OUTSIDE");
            return false;
        }
        //if(y >=outerBounds.getMaxY() || y<=outerBounds.getMinY()){
        //    System.out.println("OUTSIDE TOP OR BOT");
        //    return false;
        //}
        return true;
    }
    private boolean checkBoundariesY(double y) {
        Bounds bound = outerSquare.getBoundsInLocal();
        if(y >=bound.getMaxY() || y <=bound.getMinY()){
            System.out.println("OUTSIDE");
            return false;
        }
        return true;
    }
}