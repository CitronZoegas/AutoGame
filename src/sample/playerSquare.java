package sample;



import javafx.scene.shape.Rectangle;



public class playerSquare extends Rectangle {

    private double health;
    private double speed;

    public playerSquare(double health, double speed) {
        this.health = health;
        this.speed = speed;
    }


    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}
