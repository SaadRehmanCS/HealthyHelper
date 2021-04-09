package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SleepTest {

    Sleep sleep;

    @BeforeEach
    public void setup() {
        sleep = new Sleep();

    }

    @Test
    public void testAddSleepTime() {
        sleep.addSleepTime(3);
        assertEquals(3, sleep.getSleepTime());
    }

    @Test
    public void testSleepTimePoor() {
        sleep.addSleepTime(4);
        assertEquals("You were not able to get enough sleep. Try to go to bed a little early"
                + " tonight", sleep.sleepAssessment());
    }

    @Test
    public void testSleepTimeGood() {
        sleep.addSleepTime(8);
        assertEquals("Great! You slept just the right amount", sleep.sleepAssessment());
    }

    @Test
    public void testSleepTimeExcess() {
        sleep.addSleepTime(10);
        assertEquals("You slept longer than necessary. This is great, "
                + "but be more resourceful with time!", sleep.sleepAssessment());
    }
}
