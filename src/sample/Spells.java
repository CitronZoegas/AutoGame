package sample;

import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class Spells {

    private Controller controller;
    private Timer timer;
    private TimerTask timerTask;

    public Spells(Controller controller) {
        this.timer = new Timer();
    }

    @FXML
    public void circleSmash(Circle c, boolean reverse, int angle, int duration) {
        RotateTransition rt = new RotateTransition(Duration.seconds(duration),c);
        rt.setByAngle(angle);
        rt.setAutoReverse(reverse);
        rt.setDelay(Duration.seconds(0));
        rt.setRate(30);
        rt.setCycleCount(5);
        rt.play();
        }
}
