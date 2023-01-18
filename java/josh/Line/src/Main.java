import java.util.Scanner;

class LineCoeff {
    private double a, b, c;

    public LineCoeff(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }

    public double slope() {
        return -a / b;
    }

    @Override
    public String toString() {
        return String.format("%.1fx + %.1fy = %.1f", a, b, c);
    }
}

class LineType {
    private LineCoeff x, y;

    public LineType(LineCoeff x, LineCoeff y) {
        this.x = x;
        this.y = y;
    }


    /**
     * Displays the lines in standard form
     */
    public void display() {
        System.out.println(x);
        System.out.println(y);
    }

    public boolean isParallel() {
        // If the slopes are equal, the lines are parallel
        return x.slope() == y.slope();
    }

    public boolean isPerpendicular() {
        // x1 *  x2 + y1 * y2 = 0
        return x.getA() * y.getA() + x.getB() * y.getB() == 0;
    }

    public boolean isEqual() {
        // the lines are equal if they have the same slope and the same y-intercept
        return x.slope() == y.slope() && x.getC() / x.getB() == y.getC() / y.getB();
    }

    public void intersection() {
        // If the lines are parallel, they don't intersect
        if (isParallel()) {
            System.out.println("The lines are parallel and do not intersect.");
            return;
        }

        // If the lines are perpendicular, they intersect at a point
        // detX = (x1 * y2 - y1 * x2)/ (x1 * y2 - y1 * x2)
        // detX = (x1 * y2 - y1 * x2) / (x1 * y2 - y1 * x2)
        // get intersection point
        double detX = (x.getC() * y.getB() - y.getC() * x.getB()) / (x.getA() * y.getB() - y.getA() * x.getB());
        double detY = (x.getA() * y.getC() - y.getA() * x.getC()) / (x.getA() * y.getB() - y.getA() * x.getB());
        System.out.println("x = " + detX);
        System.out.println("y = " + detY);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("The equation of a line in standard form is ax + by = c");
        System.out.println("Enter the coefficients of the first line: ");
        double a1 = scanner.nextDouble();
        double b1 = scanner.nextDouble();
        double c1 = scanner.nextDouble();
        LineCoeff line1 = new LineCoeff(a1, b1, c1);
        System.out.println("Enter the coefficients of the second line: ");
        double a2 = scanner.nextDouble();
        double b2 = scanner.nextDouble();
        double c2 = scanner.nextDouble();
        LineCoeff line2 = new LineCoeff(a2, b2, c2);
        LineType line = new LineType(line1, line2);
        line.display();
        System.out.printf("The slope of the first line is %.2f%n", line1.slope());
        System.out.printf("The slope of the second line is %.2f%n", line2.slope());
        System.out.println("The lines are " + (line.isEqual() ? "" : "not ") + "equal!");
        System.out.println("The lines are " + (line.isParallel() ? "" : "not ") + "parallel!");
        System.out.println("The lines are " + (line.isPerpendicular() ? "" : "not ") + "perpendicular!");
        line.intersection();


    }
}