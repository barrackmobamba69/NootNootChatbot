import static org.junit.Assert.*;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.PrintStream;
import static org.junit.Assert.assertEquals;

public class Code_Review_2_NootnootTest {
	
	@Test
	public void testWelcomeMessage() {
	    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(byteArrayOutputStream));
	    Code_Review_2_Nootnoot.welcomeMessage();
	    String expected = "Hey there, Welcome to Weather ChatBot! \nMy name's Nootnoot, nice to meet you!\n";
	    assertEquals(expected, byteArrayOutputStream.toString());
	}
	
    @Test
    public void testInputLocation() {
        // test 1
        String expected = "Dublin";
        ByteArrayInputStream in1 = new ByteArrayInputStream(expected.getBytes());
        System.setIn(in1);
        assertEquals(expected, Code_Review_2_Nootnoot.inputLocation());

        // test 2 - testing, weather the function arises any exception, when given an invalid input.
        expected = "Seattle";
	    String input = "Seattle";
	    System.setIn(new java.io.ByteArrayInputStream(input.getBytes()));
	    String actual = Code_Review_2_Nootnoot.inputLocation();
	    assertEquals(expected, actual);
    }
	
	@Test
	public void testEndConversationQuit() {
	    String input = "quit";
	    String inputTwo = "q";
	    assertEquals(true, Code_Review_2_Nootnoot.endConversation(input));
	    assertEquals(true, Code_Review_2_Nootnoot.endConversation(inputTwo));
	}
	
	@Test
	public void testFetchWeatherData() {
	    String city = "Dublin";
	    String weatherData = Code_Review_2_Nootnoot.fetchWeatherData(city, Code_Review_2_Nootnoot.open_weather_api_key);
	    assertTrue(weatherData.contains("weather") && weatherData.contains("main") && weatherData.contains("temp"));
	}


    @Test
    public void test_extractTemperatureInCelsius() {
        String weatherData = "{\"main\":{\"temp\":288.7,\"feels_like\":288.7,\"pressure\":1024,\"humidity\":93,\"temp_min\":288.7,\"temp_max\":288.7,\"sea_level\":1024,\"grnd_level\":1015}}";
        String expected = "15.6";
        String actual = Code_Review_2_Nootnoot.extractTemperatureInCelsius(weatherData);
        assertEquals(expected, actual);
    }

    @Test
    public void test_extractPrecipitationChance() {
        String weatherData = "{\"main\":{\"temp\":288.7,\"feels_like\":288.7,\"pressure\":1024,\"humidity\":93,\"temp_min\":288.7,\"temp_max\":288.7,\"sea_level\":1024,\"grnd_level\":1015},\"clouds\":{\"all\":75},\"wind\":{\"speed\":4.63,\"deg\":270},\"precipitation\":{\"mode\":\"no\"},\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"broken clouds\",\"icon\":\"04d\"}]}";
        String expected = "75";
        String actual = Code_Review_2_Nootnoot.extractPrecipitationChance(weatherData);
        assertEquals(expected, actual);
    }

    @Test
    public void test_extractWind() {
        String weatherData = "{\"main\":{\"temp\":288.7,\"feels_like\":288.7,\"pressure\":1024,\"humidity\":93,\"temp_min\":288.7,\"temp_max\":288.7,\"sea_level\":1024,\"grnd_level\":1015},\"clouds\":{\"all\":75},\"wind\":{\"speed\":4.63,\"deg\":270},\"precipitation\":{\"mode\":\"no\"},\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"broken clouds\",\"icon\":\"04d\"}]}";
        String expected = "4.63 m/s";
        String actual = Code_Review_2_Nootnoot.extractWind(weatherData);
        assertEquals(expected, actual);
    }


    @Test
    public void test_getWeatherCondition() {
        String expected = "Rainy";
        String actual = Code_Review_2_Nootnoot.getWeatherCondition(16);
        assertEquals(expected, actual);
    }

    @Test
    public void test_suggestClothing() {
        String expected = "It's quite warm, you can wear shorts and a t-shirt.";
        String actual = Code_Review_2_Nootnoot.suggestClothing("23.5");
        assertEquals(expected, actual);
    }

}

