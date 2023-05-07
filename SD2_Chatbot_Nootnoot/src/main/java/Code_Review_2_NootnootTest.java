import static org.junit.Assert.*;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Code_Review_2_NootnootTest {

    @Test
    public void testWelcomeMessage() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Code_Review_2_Nootnoot.welcomeMessage();
        String expectedOutput = "Welcome to the Weather ChatBot! \nHi my name's Nootnoot\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testInputLocation() {
        ByteArrayInputStream in = new ByteArrayInputStream("London\n".getBytes());
        System.setIn(in);
        String expectedOutput = "London";
        assertEquals(expectedOutput, Code_Review_2_Nootnoot.inputLocation());
    }

    @Test //Function to check if the user input command is to end the conversation or not.
    public void testEndConversationQuit() {
        String input = "quit";
        String inputTwo = "q";
        assertTrue(Code_Review_2_Nootnoot.endConversation(input));
        assertTrue(Code_Review_2_Nootnoot.endConversation(inputTwo));
    }

    @Test
    public void testShowCommands() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Code_Review_2_Nootnoot.showCommands();
        String expectedOutput = "Here are the available commands:\n-Type the name of a city to get its weather\n-Type 'plan' to plan your trip\n-Type 'quit' or 'q' to exit\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testDisplayWelcomeImageOfNootnoot() throws InterruptedException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Code_Review_2_Nootnoot.displayWelcomeImageOfNootnoot();
        Thread.sleep(2000);
        String expectedOutput = "Nootnoot!";
        assertEquals(expectedOutput, outContent.toString().trim());
    }

    //Test getPrecipitationChance() with weatherInfo containing rain
    @Test
    public void testGetPrecipitationChanceRain() {
        String weatherInfo = "Today's forecast predicts heavy rain in the evening";
        String result = Code_Review_2_Nootnoot.getPrecipitationChance(weatherInfo);
        assertEquals("high", result);
    }

    @Test
    //Test getPrecipitationChance() with weatherInfo containing snow
    public void testGetPrecipitationChanceSnow() {
        String weatherInfo = "Snow expected in the northern region tomorrow";
        String result = Code_Review_2_Nootnoot.getPrecipitationChance(weatherInfo);
        assertEquals("moderate", result);
    }

    @Test
    //Test extractWind() with valid weatherData
    public void testExtractWindValidData() {
        String weatherData = "{\"wind\":{\"speed\":5.67,\"deg\":90},\"name\":\"London\",\"cod\":200}";
        String result = Code_Review_2_Nootnoot.extractWind(weatherData);
        assertEquals("5.67 km/h", result);
    }

    @Test
    public void testGetLocations() {
        String input = "New York\nParis\nTokyo\nLondon\nSydney\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);
        String[] result = Code_Review_2_Nootnoot.getLocations(scanner);
	    String[] expected = {"New York", "Paris", "Tokyo", "London", "Sydney"};
	    assertArrayEquals(expected, result);
	}

	@Test
	public void testDisplayWeatherData() throws Exception {
	    String[] locations = {"New York", "Paris", "Tokyo", "London", "Sydney"};
	    Code_Review_2_Nootnoot.displayWeatherData(locations);
	}
}

