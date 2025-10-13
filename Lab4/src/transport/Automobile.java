package Lab4.src.transport;

import Lab4.src.people.Human;

public abstract class Automobile<T extends Human> extends Vehicle<T> {
    public Automobile(int capacity) {
        super(capacity);
    }
}
