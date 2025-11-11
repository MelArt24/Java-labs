package lab10.task2;

import lab10.task2.controller.ShapeController;
import lab10.task2.model.Circle;
import lab10.task2.model.Rectangle;
import lab10.task2.model.Shape;
import lab10.task2.model.Triangle;
import lab10.task2.view.ShapeView;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.*;
import java.util.logging.*;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    private static ResourceBundle messages = ResourceBundle.getBundle(
            "location.messages",
            new Locale("uk"),
            new UTF8Control());

    public static void main(String[] args) {
        setupLogger();
        logger.info(messages.getString("log.programStart") + " " + messages.getLocale());

        String[] colors = {"red", "green", "blue", "yellow", "black"};
        double[] sizes = {2, 3, 4, 5, 6, 7, 8};
        Shape[] shapes = new Shape[10];
        Random rand = new Random();

        for (int i = 0; i < shapes.length; i++) {
            try {
                String color = colors[rand.nextInt(colors.length)];
                int type = rand.nextInt(3);

                switch (type) {
                    case 0 -> {
                        double width = sizes[rand.nextInt(sizes.length)];
                        double height = sizes[rand.nextInt(sizes.length)];
                        shapes[i] = new Rectangle(color, width, height);
                    }
                    case 1 -> {
                        double radius = sizes[rand.nextInt(sizes.length)];
                        shapes[i] = new Circle(color, radius);
                    }
                    case 2 -> {
                        double base = sizes[rand.nextInt(sizes.length)];
                        double height = sizes[rand.nextInt(sizes.length)];
                        shapes[i] = new Triangle(color, base, height);
                    }
                }
            } catch (Exception e) {
                logger.log(Level.WARNING, messages.getString("msg.unknownType"), e);
            }
        }

        ShapeView view = new ShapeView();
        ShapeController controller = new ShapeController();
        Scanner scanner = new Scanner(System.in);

        boolean exit = false;
        while (!exit) {
            System.out.println("\n" + messages.getString("menu.title"));
            System.out.println(messages.getString("menu.option1"));
            System.out.println(messages.getString("menu.option2"));
            System.out.println(messages.getString("menu.option3"));
            System.out.println(messages.getString("menu.option4"));
            System.out.println(messages.getString("menu.option5"));
            System.out.println(messages.getString("menu.option6"));
            System.out.println(messages.getString("menu.option7"));
            System.out.println(messages.getString("menu.option8"));
            System.out.println(messages.getString("menu.option9"));
            System.out.println(messages.getString("menu.option10"));
            System.out.print(messages.getString("menu.prompt") + " ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(messages.getString("invalid.input"));
                continue;
            }

            switch (choice) {
                case 1 -> {
                    logger.info(messages.getString("msg.foundShapes"));
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ignored) {}
                    view.displayShapes(shapes);
                }
                case 2 -> {
                    logger.info(messages.getString("msg.totalAreaCalculation"));
                    double totalArea = controller.calculateTotalArea(shapes);
                    view.displayMessage(messages.getString("msg.totalArea") + " " + totalArea);
                }
                case 3 -> {
                    System.out.print(messages.getString("msg.enterType"));
                    String typeName = scanner.nextLine();
                    Class<?> typeClass = switch (typeName.toLowerCase()) {
                        case "circle", "коло" -> Circle.class;
                        case "rectangle", "прямокутник" -> Rectangle.class;
                        case "triangle", "трикутник" -> Triangle.class;
                        default -> null;
                    };
                    if (typeClass != null) {
                        double areaByType = controller.calculateAreaByType(shapes, typeClass);
                        String msg = MessageFormat.format(messages.getString("msg.totalAreaType"), typeName);
                        view.displayMessage(msg + " " + areaByType);
                    } else {
                        view.displayMessage(messages.getString("msg.unknownType"));
                    }
                }
                case 4 -> {
                    System.out.print(messages.getString("msg.enterTypeSearch"));
                    String typeName = scanner.nextLine().trim();
                    System.out.print(messages.getString("msg.enterColorSearch"));
                    String colorName = scanner.nextLine().trim();

                    Map<String, String> colorMap = Map.ofEntries(
                            Map.entry("червоний", "red"),
                            Map.entry("зелений", "green"),
                            Map.entry("синій", "blue"),
                            Map.entry("жовтий", "yellow"),
                            Map.entry("чорний", "black"),
                            Map.entry("red", "red"),
                            Map.entry("green", "green"),
                            Map.entry("blue", "blue"),
                            Map.entry("yellow", "yellow"),
                            Map.entry("black", "black")
                    );

                    if (colorMap.containsKey(colorName)) {
                        colorName = colorMap.get(colorName);
                    }

                    List<Shape> results = new ArrayList<>();
                    for (Shape s : shapes) {
                        boolean typeMatches = typeName.isEmpty() || s.getClass().getSimpleName().equalsIgnoreCase(typeName);
                        boolean colorMatches = colorName.isEmpty() || s.shapeColor.equalsIgnoreCase(colorName);
                        if (typeMatches && colorMatches) {
                            results.add(s);
                        }
                    }

                    if (results.isEmpty()) {
                        view.displayMessage(messages.getString("msg.noShapes"));
                    } else {
                        view.displayMessage(messages.getString("msg.foundShapes"));
                        view.displayShapes(results.toArray(new Shape[0]));
                    }
                }
                case 5 -> {
                    controller.sortByArea(shapes);
                    view.displayMessage(messages.getString("msg.sortedByArea"));
                    view.displayShapes(shapes);
                }
                case 6 -> {
                    controller.sortByColor(shapes);
                    view.displayMessage(messages.getString("msg.sortedByColor"));
                    view.displayShapes(shapes);
                }
                case 7 -> {
                    System.out.print(messages.getString("msg.enterFileSave"));
                    String saveFile = scanner.nextLine().trim();
                    logger.info(messages.getString("log.saveToFile") + " " + saveFile);
                    ShapeFileHandler.saveShapes(saveFile, shapes);
                }
                case 8 -> {
                    System.out.print(messages.getString("msg.enterFileLoad"));
                    String loadFile = scanner.nextLine();
                    Shape[] loadedShapes = ShapeFileHandler.loadShapes(loadFile);
                    if (loadedShapes.length > 0) {

                        Map<String, String> colorMap = Map.ofEntries(
                                Map.entry("червоний", "red"),
                                Map.entry("зелений", "green"),
                                Map.entry("синій", "blue"),
                                Map.entry("жовтий", "yellow"),
                                Map.entry("чорний", "black"),
                                Map.entry("red", "red"),
                                Map.entry("green", "green"),
                                Map.entry("blue", "blue"),
                                Map.entry("yellow", "yellow"),
                                Map.entry("black", "black")
                        );

                        for (Shape s : loadedShapes) {
                            String lower = s.shapeColor.toLowerCase(Locale.ROOT);
                            if (colorMap.containsKey(lower)) {
                                s.shapeColor = colorMap.get(lower);
                            }
                        }

                        shapes = loadedShapes;
                        System.out.flush();

                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException ignored) {}

                        view.displayShapes(shapes);
                    } else {
                        view.displayMessage(messages.getString("msg.loadFail"));
                    }
                }
                case 9 -> {
                    logger.info(messages.getString("log.exit"));
                    System.out.println(messages.getString("exit.message"));
                    exit = true;
                }
                case 10 -> {
                    System.out.print(messages.getString("language.prompt") + " ");
                    String lang = scanner.nextLine().trim().toLowerCase();

                    Locale locale = switch (lang) {
                        case "uk", "ua" -> new Locale("uk");
                        default -> new Locale("en");
                    };

                    Locale.setDefault(locale);

                    messages = ResourceBundle.getBundle("location.messages", locale);
                    System.out.println(messages.getString("language.changed"));
                    logger.info(messages.getString("log.langChanged") + " " + locale);
                }
                default -> logger.warning(messages.getString("invalid.input"));
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException ignored) {}
            System.out.flush();
        }

        scanner.close();
    }

    private static void setupLogger() {
        try {
            Logger rootLogger = Logger.getLogger("");
            Handler[] handlers = rootLogger.getHandlers();
            if (handlers[0] instanceof ConsoleHandler ch) {
                ch.setLevel(Level.INFO); // консоль: тільки INFO і вище
            }

            FileHandler fileHandler = new FileHandler("lab10/task2/shapes.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            fileHandler.setLevel(Level.WARNING); // у файл записуємо лише WARNING і SEVERE

            logger.addHandler(fileHandler);
            logger.setLevel(Level.ALL);

        } catch (IOException e) {
            System.out.println(messages.getString("logCreationError") + e.getMessage());
        }
    }

    static class UTF8Control extends ResourceBundle.Control {
        @Override
        public ResourceBundle newBundle(
                String baseName, Locale locale, String format, ClassLoader loader, boolean reload)
                throws IllegalAccessException, InstantiationException, IOException {

            String bundleName = toBundleName(baseName, locale);
            String resourceName = toResourceName(bundleName, "properties");
            try (var stream = loader.getResourceAsStream(resourceName)) {
                if (stream == null) return null;
                try (var reader = new java.io.InputStreamReader(stream, java.nio.charset.StandardCharsets.UTF_8)) {
                    var bundle = new java.util.PropertyResourceBundle(reader);
                    return bundle;
                }
            }
        }
    }
}
