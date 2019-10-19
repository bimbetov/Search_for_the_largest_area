public class Triangle {
    public Point A, B, C;
    private double a, b, c;

    Triangle(Point A, Point B, Point C) {
        this.A = A;
        this.B = B;
        this.C = C;
        a = calculateLength(B, C);
        b = calculateLength(A, C);
        c = calculateLength(A, B);
    }

    public double area() {
        double area;
        double p = halfPerimeter();
        area = Math.sqrt(p * (p - a) * (p - b) * (p - c));
        return area;
    }

    public double halfPerimeter() {
        double halfPerimeter;
        halfPerimeter = (a + b + c) / 2;
        return halfPerimeter;
    }

    public double calculateLength(Point M, Point N) {
        double length;
        length = Math.sqrt(Math.pow(M.x - N.x, 2) + Math.pow(M.y - N.y, 2));
        return length;
    }
}
