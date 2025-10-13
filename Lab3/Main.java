/* Завдання (UA):
3.1. Напишіть консольний додаток, використовуючи архітектурний шаблон
MVC, який:
- описує інтерфейс Drawable з методом побудови фігури draw();
- описує абстрактний клас Shape, який реалізує інтерфейс Drawable і містить поле shapeColor типу String для кольору фігури і конструктор для його ініціалізації, абстрактний метод обчислення площі фігури calcArea() і перевизначений метод toString();
- описує класи Rectangle, Triangle, Circle, які успадковуються від класу Shape і реалізують метод calcArea(), а також перевизначають метод toString ();
- створює набір даних типу Shape (масив розмірністю не менш 10 елементів);
- обробляє масив:
    - відображає набір даних;
    - обчислює сумарну площу всіх фігур набору даних;
    - обчислює сумарну площу фігур заданого виду;
    - впорядковує набір даних щодо збільшення площі фігур,
    - використовуючи об'єкт інтерфейсу Comparator;
    - впорядковує набір даних за кольором фігур, використовуючи об'єкт інтерфейсу Comparator.
Значення для ініціалізації об'єктів вибираються з заздалегідь підготовлених
даних (обраних випадковим чином або по порядку проходження). */

package Lab3;

import Lab3.model.*;
import Lab3.view.ShapeView;
import Lab3.controller.ShapeController;
import java.util.Random;

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

        view.displayMessage("Initial shapes:");
        view.displayShapes(shapes);

        double totalArea = controller.calculateTotalArea(shapes);
        view.displayMessage("\nTotal area of all shapes: " + totalArea);

        double circleArea = controller.calculateAreaByType(shapes, Circle.class);
        view.displayMessage("Total area of all circles: " + circleArea);

        controller.sortByArea(shapes);
        view.displayMessage("\nShapes sorted by area:");
        view.displayShapes(shapes);

        controller.sortByColor(shapes);
        view.displayMessage("\nShapes sorted by color:");
        view.displayShapes(shapes);
    }
}
