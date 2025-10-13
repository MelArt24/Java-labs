package Lab4.src;

import Lab4.src.people.Human;
import Lab4.src.transport.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class Road {
    private final List<Vehicle<? extends Human>> carsOnRoad = new ArrayList<>();

    public int getCountOfHumans() {
        int total = 0;
        for (Vehicle<? extends Human> v : carsOnRoad) {
            total += v.getOccupiedSeats();
        }
        return total;
    }

    public void addCarToRoad(Vehicle<? extends Human> vehicle) {
        if (vehicle == null) throw new IllegalArgumentException("vehicle is null");
        carsOnRoad.add(vehicle);
    }
}