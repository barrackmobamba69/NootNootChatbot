import static org.junit.Assert.*;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.io.PrintStream;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.time.Duration;


public class Code_Review_1_NootnootTest {

	@Test
	public void testWelcomeMessage() {
	    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(byteArrayOutputStream));
	    Code_Review_1_Nootnoot.welcomeMessage();
	    String expected = "Hey there, Welcome to Weather ChatBot! \nMy name's Nootnoot, nice to meet you!\n";
	    assertEquals(expected, byteArrayOutputStream.toString());
	}
	
	
	@Test
	public void testInputLocation() {
	    ByteArrayInputStream in = new ByteArrayInputStream("Dublin".getBytes());
	    System.setIn(in);
	    Scanner scan = new Scanner(System.in);
	    String expected = "New York";
	    assertEquals(expected, Code_Review_1_Nootnoot.inputLocation());
	}

	
	@Test
	public void testEndConversationQuit() {
	    String input = "quit";
	    String inputTwo = "q";
	    assertTrue(Code_Review_1_Nootnoot.endConversation(input));
	    assertTrue(Code_Review_1_Nootnoot.endConversation(inputTwo));
	}

	
	@Test
	public void testFetchWeatherData() {
	    String city = "New York";
	    String weatherData = Code_Review_1_Nootnoot.fetchWeatherData(city, Code_Review_1_Nootnoot.OPEN_WEATHER_API_KEY);
	    assertTrue(weatherData.contains("weather") && weatherData.contains("main") && weatherData.contains("temp"));
	}


}

