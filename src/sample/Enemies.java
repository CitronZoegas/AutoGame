package sample;

public class Enemies {

    private double health;
    private double damage;
    private double speed;
    private int height;
    private int width;

    public Enemies(Controller controller,double health,double damage,double speed,int height,int width) {
        this.health = health;
        this.damage = damage;
        this.speed = speed;
        this.height = height;
        this.width = width;
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

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
