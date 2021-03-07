package model.persistence;

import model.User;
import org.junit.jupiter.api.Test;
import ui.DisplayInfo;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

//CITATION: Some methods in this class have been taken directly from the github
//          repo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
//          while others may have been slightly modified
// credits: Paul Carter

public class JsonReaderTest {

    @Test
    public void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./non_existent_file.json");
        try {
            User user = reader.read();
            fail();
        } catch (IOException e) {
            //stub
        }
    }

    @Test
    public void testReaderEmptyUser() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyUser.json");
        try {
            User user = reader.read();
            assertEquals(0, user.getCalorieTarget().getCaloriesRemaining());
            assertEquals(0, user.getFoodSize());
            assertEquals(0, user.getWater().getCupsConsumed());
            assertEquals(0, user.getCalorieTarget().getOriginalCalorieTarget());
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void testReaderGeneralUser() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralUser.json");
        try {
            User user = reader.read();
            assertEquals(1, user.getFoodSize());
            assertEquals(345, user.getCalorieTarget().getCaloriesConsumed());
            assertEquals(7, DisplayInfo.getDay());
        } catch (IOException e) {
            fail();
        }
    }
}
