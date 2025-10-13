package Lab4.test;

import Lab4.src.people.*;
import Lab4.src.transport.PoliceCar;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PoliceCarTest {
    @Test
    void testPoliceCarOnlyForPolicemen() {
        PoliceCar car = new PoliceCar(1);
        Policeman policeman = new Policeman("Сергій");

        car.boardPassenger(policeman);
        assertEquals(1, car.getOccupiedSeats());

        assertEquals(1, car.getCapacity());

        car.disembarkPassenger(policeman);
        assertEquals(0, car.getOccupiedSeats());
    }
}
