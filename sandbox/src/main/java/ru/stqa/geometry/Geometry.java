package ru.stqa.geometry;

import ru.stqa.geometry.figure.Triangle;

public class Geometry {
    public static void main(String[] args) {

        Triangle triangle = new Triangle(15, 13, 4);

        Triangle.printPerimeterResult(triangle);
        Triangle.printAreaResult(triangle);

    }
}
