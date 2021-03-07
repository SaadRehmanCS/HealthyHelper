package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WaterTest {

    Water water;

    @BeforeEach
    public void setup() {
        water = new Water();
    }

    @Test
    public void testIncrementWaterOverLimit() {
        water.setCupsConsumed(60);
        water.incrementWater();
        assertEquals(water.getCupsConsumed(), 60);
    }

    @Test
    public void testIncrementWaterUnderLimit() {
        water.incrementWater();
        assertEquals(water.getCupsConsumed(), 1);
    }

    @Test
    public void testGetDailyRequirement() {
        assertEquals(8, water.getDailyRequirement());
    }
}
