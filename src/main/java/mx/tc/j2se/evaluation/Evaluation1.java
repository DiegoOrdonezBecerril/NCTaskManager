package mx.tc.j2se.evaluation;

public class Evaluation1 {
    public static void main(String[] args) {
        try {
            Circle circle = new Circle(-1);
        } catch (IllegalArgumentException e) {
            System.out.printf("Error: %s \n", e.getMessage());
        }

        Circle[] circles = { new Circle(5), new Circle(10), new Circle(9) };
        int biggestCircleIndex = new Evaluation1().biggestCircle(circles);
        System.out.printf("Biggest circle radius: %s \n", circles[biggestCircleIndex].getRadius());
    }

    public int biggestCircle(Circle[] array) {
        int biggest = -1;
        for (int i = 0; i < array.length; i++) {
            biggest = i == 0 ? i : (array[i].getRadius() > array[biggest].getRadius() ? i : biggest);
        }
        return biggest;
    }
}
