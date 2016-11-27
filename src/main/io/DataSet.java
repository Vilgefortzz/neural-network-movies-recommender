package main.io;

public class DataSet {

    private double x;
    private double y;
    private double out;

    public DataSet(double a, double b) {

        x = a;
        y = b;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getOut() {
        return out;
    }

    public void setOut(double out) {
        this.out = out;
    }
}
