package sample;

import javafx.scene.shape.Rectangle;



public class RedSlowEnemies extends Units  {

    private double health;
    private double damage;
    private double speed;
    private int height;
    private int width;
    private playerSquare pSquare;
    private double valX;
    private double valY;
    private final Controller controller;

    public RedSlowEnemies(double health, double damage, double speed, int height, int width, double valX, double valY, playerSquare pSquare, Controller controller) {
        this.pSquare = pSquare;
        this.controller = controller;

        this.health = health;
        this.damage = damage;
        this.speed = speed;
        this.height = height;
        this.width = width;
        this.valX = valX;
        this.valY = valY;
    }
    public void eMovement() {
        valX++;

    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void performAction() {

        /*double dX = controller.getPlayer().getX() - valX;
        double dY = controller.getPlayer().getX() - valY;

        double divider = Math.sqrt(dX * dX + dY * dY);

        dX /= divider;
        dY /= divider;

        dX *= speed;
        dY *= speed;

        valX += dX;
        valY += dY;*/
    }
}
