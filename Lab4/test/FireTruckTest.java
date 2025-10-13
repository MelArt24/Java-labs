package Lab4.test;

import Lab4.src.people.*;
import Lab4.src.transport.FireTruck;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FireTruckTest {
    @Test
    void testFireTruckOnlyForFirefighter() {
        FireTruck car = new FireTruck(1);
        Firefighter firefighter = new Firefighter("Сергій");

        car.boardPassenger(firefighter);
        assertEquals(1, car.getOccupiedSeats());

        assertEquals(1, car.getCapacity());

        car.disembarkPassenger(firefighter);
        assertEquals(0, car.getOccupiedSeats());
    }
}
