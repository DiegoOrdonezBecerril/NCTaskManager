package mx.tc.j2se.evaluation;

public class Circle {
    private int radius;

    public Circle() {
        this.radius = 1;
    }

    public Circle(int radius) throws IllegalArgumentException {
        if (radius <= 0) {
            throw new IllegalArgumentException("Radius can not be equals or less than zero");
        }

        this.radius = radius;
    }

    public void setRadius(int radius) throws IllegalArgumentException {
        if (radius <= 0) {
            throw new IllegalArgumentException("Radius can not be equals or less than zero");
        }

        this.radius = radius;
    }

    public int getRadius() {
        return this.radius;
    }

    public double getArea() {
        return Math.PI * Math.pow(this.radius, 2);
    }
}
