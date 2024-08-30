package ru.stqa.geometry.figure;

public record Triangle (double s1, double s2, double s3)  {

    /* private static double s1;
    private static double s2;
    private static double s3; */

    public Triangle {
        if (s1 < 0 || s2 < 0 || s3 < 0){
            throw new IllegalArgumentException("Triangle side should be non-negative");
        }

        if (s1 + s2 < s3 || s1 + s3 < s2 || s2 + s3 < s1) {
            throw new IllegalArgumentException("Side of triangle too long");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || obj.getClass() != this.getClass())
            return false;

        Triangle triangle = (Triangle) obj;
        if ((Double.compare(triangle.s1, this.s1) == 0 && Double.compare(triangle.s2, this.s2) == 0 && Double.compare(triangle.s3, this.s3) == 0)
             ||   (Double.compare(triangle.s1, this.s2) == 0 && Double.compare(triangle.s2, this.s3) == 0 && Double.compare(triangle.s3, this.s1) == 0)
            || (Double.compare(triangle.s1, this.s3) == 0 && Double.compare(triangle.s2, this.s1) == 0 && Double.compare(triangle.s3, this.s2) == 0)
                || (Double.compare(triangle.s1, this.s1) == 0 && Double.compare(triangle.s2, this.s3) == 0 && Double.compare(triangle.s3, this.s2) == 0)
                || (Double.compare(triangle.s2, this.s2) == 0 && Double.compare(triangle.s1, this.s3) == 0 && Double.compare(triangle.s3, this.s1) == 0)
                || (Double.compare(triangle.s3, this.s3) == 0 && Double.compare(triangle.s2, this.s1) == 0 && Double.compare(triangle.s1, this.s2) == 0)) {
            return true;
        }
        else {
            return false;
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
