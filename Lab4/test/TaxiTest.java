package Lab4.test;

import Lab4.src.people.*;
import Lab4.src.transport.Taxi;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaxiTest {
    @Test
    void testPoliceCarOnlyForPolicemen() {
        Taxi car = new Taxi(2);
        Human taxiDriver = new Human("Сергій");
        Policeman passenger = new Policeman("Олег");

        assertEquals(2, car.getCapacity());

        car.boardPassenger(taxiDriver);
        assertEquals(1, car.getOccupiedSeats());

        car.boardPassenger(passenger);
        assertEquals(2, car.getOccupiedSeats());

        car.disembarkPassenger(passenger);
        assertEquals(1, car.getOccupiedSeats());
    }
}

