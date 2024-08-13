package ru.stqa.qeometry;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.geometry.figure.Triangle;

public class TriangleTests {

    @Test
    void CheckPerimeter() {
        Triangle triangle = new Triangle(8, 5, 5);
        double result = triangle.perimeter();
        Assertions.assertEquals(18, result);
    }

    @Test
    void CheckArea() {
        Triangle triangle = new Triangle(8, 5, 5);
        double result = triangle.area();
        Assertions.assertEquals(12, result);
    }
}
