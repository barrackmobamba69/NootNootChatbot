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


	@Test
	public void testExtractTemperatureInCelsius() {
	    String weatherData = "{\"coord\":{\"lon\":-73.99,\"lat\":40.73},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"clear sky\",\"icon\":\"01d\"}],\"base\":\"stations\",\"main\":{\"temp\":289.72,\"feels_like\":287.95,\"temp_min\":288.15,\"temp_max\":291.48,\"pressure\":1017,\"humidity\":42},\"visibility\":10000,\"wind\":{\"speed\":2.6,\"deg\":240},\"clouds\":{\"all\":1},\"dt\":1605761370,\"sys\":{\"type\":1,\"id\":4610,\"country\":\"US\",\"sunrise\":1605737093,\"sunset\":1605770379},\"timezone\":-18000,\"id\":5128581,\"name\":\"New York\",\"cod\":200}";
	    String expected = "16.57";
	    assertEquals(expected, Code_Review_1_Nootnoot.extractTemperatureInCelsius(weatherData));
	}


}

