package Lab4.src.transport;

import Lab4.src.people.Human;
import java.util.ArrayList;
import java.util.List;

public abstract class Vehicle<T extends Human> {
    private final int capacity;
    private final List<T> passengers = new ArrayList<>();

    public Vehicle(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getOccupiedSeats() {
        return passengers.size();
    }

    public void boardPassenger(T passenger) {
        if (passengers.size() >= capacity) {
            throw new IllegalStateException("Усі місця вже зайняті!");
        }
        passengers.add(passenger);
    }

    public void disembarkPassenger(T passenger) {
        if (!passengers.remove(passenger)) {
            throw new IllegalArgumentException("Пасажира не знайдено в транспорті!");
        }
    }

    public List<T> getPassengers() {
        return passengers;
    }
}