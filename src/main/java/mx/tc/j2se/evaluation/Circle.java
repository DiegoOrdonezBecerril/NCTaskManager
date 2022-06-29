package mx.tc.j2se.evaluation;

/**
 * The Circle class is a circle representation
 */
public class Circle {
    private int radius;

    /**
     * Constructs a circle setting the radius as 1
     */
    public Circle() {
        this.radius = 1;
    }

    /**
     * Constructs a circle with the radius given
     *
     * @param radius the argument who will be set as radius
     *
     * @throws IllegalArgumentException when radius is equals or less than zero
     */
    public Circle(int radius) throws IllegalArgumentException {
        if (radius <= 0) {
            throw new IllegalArgumentException("Radius can not be equals or less than zero");
        }

        this.radius = radius;
    }

    /**
     * Sets the circle radius with the radius given
     *
     * @param radius the argument who will be set as radius
     *
     * @throws IllegalArgumentException when radius is equals or less than zero
     */
    public void setRadius(int radius) throws IllegalArgumentException {
        if (radius <= 0) {
            throw new IllegalArgumentException("Radius can not be equals or less than zero");
        }

        this.radius = radius;
    }

    /**
     * Returns the circle radius
     *
     * @return the radius
     */
    public int getRadius() {
        return this.radius;
    }

    /**
     * Returns the circle area as a double
     * @return the circle area
     */
    public double getArea() {
        return Math.PI * Math.pow(this.radius, 2);
    }
}
