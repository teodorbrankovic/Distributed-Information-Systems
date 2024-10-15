package at.fhooe.sail.vis.jaxb.parser.xml;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class Wind extends DefaultHandler {
    private double speed;
    private int degree;

    public Wind(double speed, int degree) {
        this.speed = speed;
        this.degree = degree;
    }

    @Override
    public String toString() {
        return "Wind: " +
                "speed = " + speed +
                ", degree = " + degree;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getSpeed() {
        return speed;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public int getDegree() {
        return degree;
    }

}
