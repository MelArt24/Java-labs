package Lab4.test;

import Lab4.src.people.Human;
import Lab4.src.transport.Bus;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BusTest {

    @Test
    void testBusBoardAndDisembarkSuccess() {
        Bus bus = new Bus(2);
        Human p1 = new Human("Олег");

        bus.boardPassenger(p1);
        assertEquals(1, bus.getOccupiedSeats());

        assertEquals(2, bus.getCapacity());

        bus.disembarkPassenger(p1);
        assertEquals(0, bus.getOccupiedSeats());
    }

}
