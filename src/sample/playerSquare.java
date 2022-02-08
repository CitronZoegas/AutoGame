package sample;



import javafx.fxml.FXML;
import javafx.scene.shape.Rectangle;



public class playerSquare extends Rectangle {

    private double health;
    private double speed;
    private double x,y;

    private double velX = 0;
    private double velY = 0;


    public playerSquare(Controller controller,double health, double speed, double x,double y) {
        this.health = health;
        this.speed = speed;
        this.x = x;
        this.y = y;
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

    public double checkSquare(double x, double y) {
        x += velX;
        y += velY;
        if(x <= 0){
            x=0;
            System.out.println(x);
        }
        if(x >= 892){
            x=892;
            System.out.println(x);
        }
        if(y <=0){
            y=0;
            System.out.println(y);
        }
        if(y >= 735){
            y=735;
            System.out.println(y);
        }
        return 0;
    }
}
