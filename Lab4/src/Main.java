package Lab4.src;

import Lab4.src.people.*;
import Lab4.src.transport.*;

public class Main {
    public static void main(String[] args) {
        Bus bus = new Bus(3);
        Taxi taxi = new Taxi(2);
        FireTruck fireTruck = new FireTruck(2);
        PoliceCar policeCar = new PoliceCar(2);

        Human person1 = new Human("Олег");
        Human person2 = new Human("Іван");
        Firefighter firefighter = new Firefighter("Петро");
        Policeman policeman = new Policeman("Андрій");

        bus.boardPassenger(person1);
        bus.boardPassenger(firefighter);

        taxi.boardPassenger(person2);

        fireTruck.boardPassenger(firefighter);
        policeCar.boardPassenger(policeman);

        Road road = new Road();
        road.addCarToRoad(bus);
        road.addCarToRoad(taxi);
        road.addCarToRoad(fireTruck);
        road.addCarToRoad(policeCar);

        System.out.println("Загальна кількість людей на дорозі: " + road.getCountOfHumans());
    }
}
