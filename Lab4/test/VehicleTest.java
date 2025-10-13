package Lab4.test;

import Lab4.src.Road;
import Lab4.src.people.*;
import Lab4.src.transport.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VehicleTest {

    @Test
    void testBoardPassengerSuccess() {
        Taxi taxi = new Taxi(2);
        Human person = new Human("Олег");

        taxi.boardPassenger(person);

        assertEquals(1, taxi.getOccupiedSeats());
    }

    @Test
    void testBoardPassengerOverCapacity() {
        Taxi taxi = new Taxi(1);
        Human p1 = new Human("Олег");
        Human p2 = new Human("Іван");

        taxi.boardPassenger(p1);

        assertThrows(IllegalStateException.class, () -> taxi.boardPassenger(p2));
    }

    @Test
    void testDisembarkPassengerNotFound() {
        Bus bus = new Bus(2);
        Human p1 = new Human("Петро");

        assertThrows(IllegalArgumentException.class, () -> bus.disembarkPassenger(p1));
    }

    @Test
    void testFireTruckOnlyForFirefighters() {
        FireTruck fireTruck = new FireTruck(2);
        Firefighter ff = new Firefighter("Михайло");

        fireTruck.boardPassenger(ff);
        assertEquals(1, fireTruck.getOccupiedSeats());
    }

    @Test
    void testRoadCountOfHumans() {
        Road road = new Road();

        Taxi taxi = new Taxi(2);
        Bus bus = new Bus(2);
        FireTruck fireTruck = new FireTruck(1);
        PoliceCar policeCar = new PoliceCar(1);

        taxi.boardPassenger(new Human("Олег"));
        bus.boardPassenger(new Human("Іван"));
        fireTruck.boardPassenger(new Firefighter("Петро"));
        policeCar.boardPassenger(new Policeman("Андрій"));

        road.addCarToRoad(taxi);
        road.addCarToRoad(bus);
        road.addCarToRoad(fireTruck);
        road.addCarToRoad(policeCar);

        assertEquals(4, road.getCountOfHumans());
    }
}
