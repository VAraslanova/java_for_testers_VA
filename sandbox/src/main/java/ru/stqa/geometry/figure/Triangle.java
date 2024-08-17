package ru.stqa.geometry.figure;

public class Triangle {

    private static double s1;
    private static double s2;
    private static double s3;

    public Triangle (double s1, double s2, double s3){
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;

        if (s1 < 0 || s2 < 0 || s3 < 0){
            throw new IllegalArgumentException("Triangle side should be non-negative");
        }

        if (s1 + s2 < s3 || s1 + s3 < s2 || s2 + s3 < s1) {
            throw new IllegalArgumentException("Side of triangle too long");
        }
    }

    public static void printPerimeterResult(Triangle triangle){
        System.out.println("Периметр треугольника = " + triangle.perimeter());
    }

    public static void printAreaResult(Triangle triangle){
        System.out.println("Площадь треугольника = " + triangle.area());
    }

    public double perimeter(){
        return this.s1 + this.s2 + this.s3;
    }

    public double area() {
        double p = perimeter()/2;
        double sqrt = Math.sqrt(p * (p - this.s1) * (p - this.s2) * (p - this.s3));
        return sqrt;
    }
}
