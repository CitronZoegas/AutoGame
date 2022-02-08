package sample;

import javafx.animation.*;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.net.URL;
import java.util.LinkedList;
import java.util.Random;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private BooleanProperty wPressed = new SimpleBooleanProperty();
    private BooleanProperty aPressed = new SimpleBooleanProperty();
    private BooleanProperty sPressed = new SimpleBooleanProperty();
    private BooleanProperty dPressed = new SimpleBooleanProperty();
    private BooleanProperty lPressed = new SimpleBooleanProperty();
    private BooleanBinding anyKeyPressed = wPressed.or(aPressed).or(sPressed).or(dPressed).or(lPressed);

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
    @FXML
    private TextField scoreTextArea;
    @FXML
    private Rectangle redEnemy;
    //@FXML
    //private final Bounds bound = outerSquare.getBoundsInLocal();


    private double movementSpeed = 5;
    private double health = 100;
    private double maxX = 892;
    private double maxY = 735;

    private double EnHealth = 4;
    private double EnDamage = 2;
    private double EnSpeed = 1;
    private int EnHeight = 100;
    private int EnWidth = 100;
    private int enemyCount = 1;
    private final Random r = new Random();
    private TranslateTransition trans;
    private LinkedList<Rectangle> eList = new LinkedList<>();

    playerSquare PS = new playerSquare(this,health,movementSpeed,2,2);
    Spells spells = new Spells(this);
    RedSlowEnemies enemies = new RedSlowEnemies(EnHealth,EnDamage,EnSpeed,EnHeight,EnWidth,1,1, PS, this);
    LinkedList<RedSlowEnemies> eRedList = new LinkedList<>();
    touchChecker touchCheck = new touchChecker(squareShape,enemyRectangle(),eList );



    @FXML
    void start(ActionEvent event){
        if(timeline.getStatus() == Animation.Status.RUNNING){
            timeline.stop();
        }
        else{
            timeline.play();
        }
    }
    @FXML
    void reset(ActionEvent event) {
        double centerX = (maxX/2);
        double centerY = (maxY/2);
        squareShape.setLayoutY(centerY);
        squareShape.setLayoutX(centerX);
        c1.setLayoutY(centerY);
        c1.setLayoutX(centerX);
        c2.setLayoutY(centerY);
        c2.setLayoutX(centerX);
    }
    public Rectangle enemyRectangle() {
        int rectangleHeightX = r.nextInt(150);
        //minimum of 40
        int rectangleHeight = 40 + r.nextInt(150);
        int rectangleWidth = 40 + r.nextInt(150);
        Rectangle rectangle = new Rectangle(600,rectangleHeightX,rectangleHeight,rectangleWidth);
        rectangle.setFill(Color.BLACK);
        trans = new TranslateTransition();
        trans.setNode(rectangle);
        trans.setDuration(Duration.seconds(4));
        trans.setToX(-800);
        trans.setOnFinished(e->{
            scene.getChildren().remove(rectangle);
            eList.remove(rectangle);

        });
        return rectangle;
    }
    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e->{
        Rectangle rectangle = enemyRectangle();
        scene.getChildren().add(rectangle);
        eList.add(rectangle);
        trans.play();
    }));

    AnimationTimer AT = new AnimationTimer() {
        @Override
        public void handle(long time) {
            enemies.eMovement();
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
    public void scoreAreaInitialize() {

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
        timeline.setCycleCount(Animation.INDEFINITE);
        enemyRectangle();
        removeCircles();
        movementInitialize();
        scoreAreaInitialize();
        touchCheck.CollidedOrNa(squareShape,enemyRectangle());
        touchCheck.startChecker();
        anyKeyPressed.addListener((((observableValue, aBoolean, t1) -> {
            if(!aBoolean){
                AT.start();
            }else{
                AT.stop();
            }
        })));
    }
}