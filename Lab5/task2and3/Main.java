package Lab5.task2and3;

import Lab5.task2and3.controller.ShapeController;
import Lab5.task2and3.model.Circle;
import Lab5.task2and3.model.Rectangle;
import Lab5.task2and3.model.Shape;
import Lab5.task2and3.model.Triangle;
import Lab5.task2and3.view.ShapeView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String[] colors = {"Red", "Green", "Blue", "Yellow", "Black"};
        double[] sizes = {2, 3, 4, 5, 6, 7, 8};
        Shape[] shapes = new Shape[10];
        Random rand = new Random();

        for (int i = 0; i < shapes.length; i++) {
            String color = colors[rand.nextInt(colors.length)];
            int type = rand.nextInt(3);

            switch (type) {
                case 0 -> { // Rectangle
                    double width = sizes[rand.nextInt(sizes.length)];
                    double height = sizes[rand.nextInt(sizes.length)];
                    shapes[i] = new Rectangle(color, width, height);
                }
                case 1 -> { // Circle
                    double radius = sizes[rand.nextInt(sizes.length)];
                    shapes[i] = new Circle(color, radius);
                }
                case 2 -> { // Triangle
                    double base = sizes[rand.nextInt(sizes.length)];
                    double height = sizes[rand.nextInt(sizes.length)];
                    shapes[i] = new Triangle(color, base, height);
                }
            }
        }

        ShapeView view = new ShapeView();
        ShapeController controller = new ShapeController();
        Scanner scanner = new Scanner(System.in);

        boolean exit = false;
        while (!exit) {
            System.out.println("\n--- Shape Menu ---");
            System.out.println("1. Display shapes");
            System.out.println("2. Show total area of all shapes");
            System.out.println("3. Show total area by type (Circle, Rectangle, Triangle)");
            System.out.println("4. Search shapes by type and/or color");
            System.out.println("5. Sort shapes by area");
            System.out.println("6. Sort shapes by color");
            System.out.println("7. Save shapes to file");
            System.out.println("8. Load shapes from file");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number between 1 and 9.");
                continue;
            }

            switch (choice) {
                case 1 -> view.displayShapes(shapes);
                case 2 -> {
                    double totalArea = controller.calculateTotalArea(shapes);
                    view.displayMessage("Total area of all shapes: " + totalArea);
                }
                case 3 -> {
                    System.out.print("Enter type (Circle, Rectangle, Triangle): ");
                    String typeName = scanner.nextLine();
                    Class<?> typeClass = switch (typeName.toLowerCase()) {
                        case "circle" -> Circle.class;
                        case "rectangle" -> Rectangle.class;
                        case "triangle" -> Triangle.class;
                        default -> null;
                    };
                    if (typeClass != null) {
                        double areaByType = controller.calculateAreaByType(shapes, typeClass);
                        view.displayMessage("Total area of " + typeName + "s: " + areaByType);
                    } else {
                        view.displayMessage("Unknown type.");
                    }
                }
                case 4 -> {
                    System.out.print("Enter type to search (or leave empty for any): ");
                    String typeName = scanner.nextLine().trim();
                    System.out.print("Enter color to search (or leave empty for any): ");
                    String colorName = scanner.nextLine().trim();

                    List<Shape> results = new ArrayList<>();
                    for (Shape s : shapes) {
                        boolean typeMatches = typeName.isEmpty() || s.getClass().getSimpleName().equalsIgnoreCase(typeName);
                        boolean colorMatches = colorName.isEmpty() || s.shapeColor.equalsIgnoreCase(colorName);
                        if (typeMatches && colorMatches) {
                            results.add(s);
                        }
                    }

                    if (results.isEmpty()) {
                        view.displayMessage("No shapes found with given criteria.");
                    } else {
                        view.displayMessage("Found shapes:");
                        view.displayShapes(results.toArray(new Shape[0]));
                    }
                }
                case 5 -> {
                    controller.sortByArea(shapes);
                    view.displayMessage("Shapes sorted by area:");
                    view.displayShapes(shapes);
                }
                case 6 -> {
                    controller.sortByColor(shapes);
                    view.displayMessage("Shapes sorted by color:");
                    view.displayShapes(shapes);
                }
                case 7 -> {
                    System.out.print("Enter filename to save: ");
                    String saveFile = scanner.nextLine().trim();
                    ShapeFileHandler.saveShapes(saveFile, shapes);
                }
                case 8 -> {
                    System.out.print("Enter filename to load: ");
                    String loadFile = scanner.nextLine();
                    Shape[] loadedShapes = ShapeFileHandler.loadShapes(loadFile);
                    if (loadedShapes.length > 0) {
                        shapes = loadedShapes;
                        view.displayMessage("Shapes after loading:");
                        view.displayShapes(shapes);
                    } else {
                        view.displayMessage("Loading failed or file is empty. Shapes array unchanged.");
                    }
                }
                case 9 -> {
                    System.out.println("Exiting...");
                    exit = true;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }

        scanner.close();
    }
}
