package Lab4.test;

import Lab4.src.people.Human;
import Lab4.src.transport.Automobile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AutomobileTest {

    static class Car extends Automobile<Human> {
        public Car(int capacity) {
            super(capacity);
        }
    }

    Car car = new Car(2);
    Human person1 = new Human("Alice");
    Human person2 = new Human("Bob");

    @Test
    void testCapacityIsSetCorrectly() {
        assertEquals(2, car.getCapacity());
    }

    @Test
    void testBoardPassengerSuccessfully() {
        car.boardPassenger(person1);
        assertEquals(1, car.getOccupiedSeats());
        assertTrue(car.getPassengers().contains(person1));
    }

    @Test
    void testBoardPassengerThrowsWhenFull() {
        car.boardPassenger(person1);
        car.boardPassenger(person2);
        assertThrows(IllegalStateException.class, () -> car.boardPassenger(new Human("Charlie")));
    }

    @Test
    void testDisembarkPassengerSuccessfully() {
        car.boardPassenger(person1);
        car.disembarkPassenger(person1);
        assertEquals(0, car.getOccupiedSeats());
        assertFalse(car.getPassengers().contains(person1));
    }

    @Test
    void testDisembarkPassengerThrowsIfNotOnBoard() {
        assertThrows(IllegalArgumentException.class, () -> car.disembarkPassenger(person1));
    }

    @Test
    void testPassengersListIsMutableInternallyButNotExternally() {
        car.boardPassenger(person1);
        var passengers = car.getPassengers();
        passengers.clear();
        assertEquals(0, car.getOccupiedSeats());
    }
}
