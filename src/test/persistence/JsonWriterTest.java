package persistence;

import model.Food;
import model.MealType;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//CITATION: Some methods in this class have been taken directly from the github
//          repo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
//          while others may have been slightly modified
// credits: Paul Carter

public class JsonWriterTest {

    User user;

    @BeforeEach
    public void setup() {
        user = new User();
    }

    @Test
    public void testWriterInvalidFile() {
        JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
        try {
            writer.open();
            fail();
        } catch (FileNotFoundException e) {
            //stub
        }
    }

    @Test
    public void testWriterEmptyUser() {
        try {
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyUser.json");
            writer.open();
            writer.write(user);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyUser.json");
            user = reader.read();
            assertEquals(0, user.getFoodSize());
            assertEquals(0, user.getWater().getCupsConsumed());
            assertEquals(0, user.getCalorieTarget().getOriginalCalorieTarget());
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void testWriterGeneralUser() {
        user.addFood(new Food("rice", 200, MealType.LUNCH, "8:00"));
        user.addSleep(5);

        try {
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralUser.json");
            writer.open();
            writer.write(user);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralUser.json");
            user = reader.read();
            assertEquals(5, user.getSleep().getSleepTime());
            List<Food> food = user.getFoodLog();
            assertEquals(1, food.size());
            assertEquals("rice", food.get(0).getFoodName());
            assertEquals(200, food.get(0).getTotalCalories());
            assertEquals("8:00", food.get(0).getTimeOfConsumption());
            assertEquals(0, user.getWater().getCupsConsumed());
        } catch (IOException e) {
            fail();
        }
    }
}
