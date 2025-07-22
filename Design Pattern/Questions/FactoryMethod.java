// FactoryMethodDemo.java

// Step 1: Common interface
interface Shape {
    void draw();
}

// Step 2: Concrete implementations
class Circle implements Shape {
    public void draw() {
        System.out.println("Drawing a Circle 🟠");
    }
}

class Rectangle implements Shape {
    public void draw() {
        System.out.println("Drawing a Rectangle ⬛");
    }
}

// Step 3: Factory class
class ShapeFactory {
    public Shape createShape(String type) {
        if (type == null) return null;
        switch (type.toLowerCase()) {
            case "circle":
                return new Circle();
            case "rectangle":
                return new Rectangle();
            default:
                throw new IllegalArgumentException("Unknown shape type: " + type);
        }
    }
}

// Step 4: Client code



public class FactoryMethod {
       public static void main(String[] args) {
        ShapeFactory factory = new ShapeFactory();

        Shape shape1 = factory.createShape("circle");
        shape1.draw();

        Shape shape2 = factory.createShape("rectangle");
        shape2.draw();
    }
}
