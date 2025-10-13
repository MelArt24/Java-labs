package Lab4.test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import Lab4.src.people.*;

public class HumanTest {

    @Test
    void testHumanNameGetter() {
        Human human = new Human("Олег");
        assertEquals("Олег", human.getName());
    }

    @Test
    void testHumanToString() {
        Human human = new Human("Іван");
        assertTrue(human.toString().contains("Іван"));
        assertTrue(human.toString().contains("Human"));
    }

    @Test
    void testFirefighterIsSubclassOfHuman() {
        Firefighter firefighter = new Firefighter("Петро");
        assertInstanceOf(Human.class, firefighter);
        assertEquals("Петро", firefighter.getName());
    }

    @Test
    void testPolicemanIsSubclassOfHuman() {
        Policeman policeman = new Policeman("Андрій");
        assertInstanceOf(Human.class, policeman);
        assertEquals("Андрій", policeman.getName());
    }

    @Test
    void testToStringOfSubclasses() {
        Firefighter firefighter = new Firefighter("Петро");
        Policeman policeman = new Policeman("Андрій");

        assertTrue(firefighter.toString().contains("Firefighter"));
        assertTrue(policeman.toString().contains("Policeman"));
    }
}
