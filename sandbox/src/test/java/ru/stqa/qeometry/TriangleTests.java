package ru.stqa.qeometry;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.geometry.figure.Triangle;

public class TriangleTests {

    @Test
    void checkPerimeter() {
        Triangle triangle = new Triangle(8, 5, 5);
        double result = triangle.perimeter();
        Assertions.assertEquals(18, result);
    }

    @Test
    void checkArea() {
        Triangle triangle = new Triangle(8, 5, 5);
        double result = triangle.area();
        Assertions.assertEquals(12, result);
    }

    @Test
    void checkExistenceNegativeSide() {
        try {
            new Triangle(-5.0, -1.0, -3.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {

        }
    }

    @Test
    void checkSumOfSides(){
        try {
            new Triangle(10.0, 1.0, 3.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {

        }
    }
}
