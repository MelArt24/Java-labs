package Lab4.test;

import Lab4.src.Road;
import Lab4.src.people.Human;
import Lab4.src.transport.Automobile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoadTest {

    static class Car extends Automobile<Human> {
        public Car(int capacity) {
            super(capacity);
        }
    }

    Road road = new Road();
    Car car1 = new Car(2);
    Car car2 = new Car(3);
    Human person1 = new Human("Alice");
    Human person2 = new Human("Bob");
    Human person3 = new Human("Charlie");

    @Test
    void testAddCarToRoadSuccessfully() {
        road.addCarToRoad(car1);
        assertDoesNotThrow(() -> road.addCarToRoad(car2));
    }

    @Test
    void testAddCarToRoadThrowsIfNull() {
        assertThrows(IllegalArgumentException.class, () -> road.addCarToRoad(null));
    }

    @Test
    void testGetCountOfHumansWhenEmpty() {
        assertEquals(0, road.getCountOfHumans());
    }

    @Test
    void testGetCountOfHumansWithCars() {
        car1.boardPassenger(person1);
        car1.boardPassenger(person2);

        car2.boardPassenger(person3);

        road.addCarToRoad(car1);
        road.addCarToRoad(car2);

        assertEquals(3, road.getCountOfHumans());
    }

    @Test
    void testGetCountOfHumansAfterDisembarking() {
        car1.boardPassenger(person1);
        car1.boardPassenger(person2);
        road.addCarToRoad(car1);

        car1.disembarkPassenger(person2);

        assertEquals(1, road.getCountOfHumans());
    }
}