package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WaterTest {

    Water water;

    @Test
    public void testIncrementWaterOverLimit() {
        water = new Water();
        water.setCupsConsumed(60);
        water.incrementWater();
        assertEquals(water.getCupsConsumed(), 60);
    }

    @Test
    public void testIncrementWaterUnderLimit() {
        water = new Water();
        water.incrementWater();
        assertEquals(water.getCupsConsumed(), 1);
    }
}
