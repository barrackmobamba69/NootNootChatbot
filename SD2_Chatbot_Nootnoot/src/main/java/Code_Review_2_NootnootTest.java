import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.PrintStream;
import static org.junit.Assert.assertEquals;

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
        String expectedOutput = "Naas";
        assertEquals(expectedOutput, Code_Review_2_Nootnoot.inputLocation());
    }
	
	@Test //Function to check if the user input command is to end the conversation or not.
	public void testEndConversationQuit() {
	    String input = "quit";
	    String inputTwo = "q";
	    assertEquals(true, Code_Review_2_Nootnoot.endConversation(input));
	    assertEquals(true, Code_Review_2_Nootnoot.endConversation(inputTwo));
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
	    assertEquals(expectedOutput, outContent.toString());
	}
	
	@Test // Function to show the weather data, according to the place name from open weather api
    public void testFetchWeatherData() {
        String city = "San Francisco";
        String api_key = "1234567890abcdef";
        String weatherData = Code_Review_2_Nootnoot.fetchWeatherData(city, api_key);
        Assertions.assertNotNull(weatherData);
    }
	
    // Test extractTemperatureInCelsius method
    @Test
    public void testExtractTemperatureInCelsius() {
        String weatherData = "{\"coord\":{\"lon\":-122.42,\"lat\":37.77},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10n\"}],\"base\":\"stations\",\"main\":{\"temp\":280.45,\"pressure\":1017,\"humidity\":71,\"temp_min\":277.04,\"temp_max\":284.82},\"visibility\":16093,\"wind\":{\"speed\":2.6,\"deg\":160},\"clouds\":{\"all\":90},\"dt\":1548108720,\"sys\":{\"type\":1,\"id\":392,\"message\":0.0026,\"country\":\"US\",\"sunrise\":1548130445,\"sunset\":1548165152},\"id\":420006353,\"name\":\"San Francisco\",\"cod\":200}";
        String expected = "7.30";
        String result = Weather.extractTemperatureInCelsius(weatherData);
        Assertions.assertEquals(expected, result);
    }
    
    // Test extractPrecipitationChance method
    @Test
    public void testExtractPrecipitationChance() {
        String weatherData = "{\"coord\":{\"lon\":-122.42,\"lat\":37.77},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10n\"}],\"base\":\"stations\",\"main\":{\"temp\":280.45,\"pressure\":1017,\"humidity\":71,\"temp_min\":277.04,\"temp_max\":284.82},\"visibility\":16093,\"wind\":{\"speed\":2.6,\"deg\":160},\"clouds\":{\"all\":90},\"dt\":1548108720,\"sys\":{\"type\":1,\"id\":392,\"message\":0.0026,\"country\":\"US\",\"sunrise\":1548130445,\"sunset\":1548165152},\"id\":420006353,\"name\":\"San Francisco\",\"cod\":200}";
        String expected = "high";
        String result = Weather.extractPrecipitationChance(weatherData);
        Assertions.assertEquals(expected, result);
    }
	
	 @Test // function to extract the chance of precipitation by taking a weather data.
	    public void test_extractPrecipitationChance() {
	        String weatherData = "{\"main\":{\"temp\":288.7,\"feels_like\":288.7,\"pressure\":1024,\"humidity\":93,\"temp_min\":288.7,\"temp_max\":288.7,\"sea_level\":1024,\"grnd_level\":1015},\"clouds\":{\"all\":75},\"wind\":{\"speed\":4.63,\"deg\":270},\"precipitation\":{\"mode\":\"no\"},\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"broken clouds\",\"icon\":\"04d\"}]}";
	        String expected = "75";
	        String actual = Code_Review_2_Nootnoot.extractPrecipitationChance(weatherData);
	        assertEquals(expected, actual);
	    }
	 
	 @Test // Function to extract wind speed in meter per second from the given weather data.
	    public void test_extractWind() {
	        String weatherData = "{\"main\":{\"temp\":288.7,\"feels_like\":288.7,\"pressure\":1024,\"humidity\":93,\"temp_min\":288.7,\"temp_max\":288.7,\"sea_level\":1024,\"grnd_level\":1015},\"clouds\":{\"all\":75},\"wind\":{\"speed\":4.63,\"deg\":270},\"precipitation\":{\"mode\":\"no\"},\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"broken clouds\",\"icon\":\"04d\"}]}";
	        String expected = "4.63 m/s";
	        String actual = Code_Review_2_Nootnoot.extractWind(weatherData);
	        assertEquals(expected, actual);
	    }

    @Test // Function used to describe the weather codition by taking the weather data code.
    public void test_getWeatherCondition() {
        String expected = "Rainy";
        String actual = Code_Review_2_Nootnoot.getWeatherCondition(16);
        assertEquals(expected, actual);
    }

    @Test// Function that takes in the type of weather from weather data and suggests the type of best form of clothing. 
    public void test_suggestClothing() {
        String expected = "It's quite warm, you can wear shorts and a t-shirt.";
        String actual = Code_Review_2_Nootnoot.suggestClothing("23.5");
        assertEquals(expected, actual);
    }

}

