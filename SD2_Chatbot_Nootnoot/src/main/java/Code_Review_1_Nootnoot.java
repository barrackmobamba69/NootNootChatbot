//These are the necessary import statements for using classes from the Java API and for making HTTP requests
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Code_Review_1_Nootnoot {

    private static final String open_weather_api_key = "b369057d518ed3e182c04c76c1ec73fe"; //This is the OpenWeatherMap API key
    
	public static void main(String[] args) throws Exception {
        welcomeMessage(); //Calling welcomeMessage() method

        while (true) {
            String city = inputLocation(); //Calling inputLocation() method
            
            delayedExecution(); //Calling pauseExecution() method

            if (endConversation(city)) { //Calling endConversation() method
                break;
            }

            String weatherData = fetchWeatherData(city, open_weather_api_key); //Calling fetchWeatherData() method
            String temperature = extractTemperatureInCelsius(weatherData); //Calling extractTemperature() method

            //We print out the temperature in Celsius degrees
            System.out.println("The temperature in " + city + " is " + temperature + " degrees Celsius.");

        }
	}

	//------- All the methods are called below ---------
	
	//This method greets the user by printing welcoming message
	private static void welcomeMessage() {
	    System.out.println("Welcome to the Weather ChatBot! \nHi my name's Nootnoot");
	}

	//This method uses Scanner object to ask the user to input the city name
	private static String inputLocation() {
	    Scanner scan = new Scanner(System.in); //Created a scanner object
	    System.out.println("What location would you like to know the weather for? "); //Printing users input
	    return scan.nextLine(); //Reading users' input
	}
	
	//This method prints a message and simulates processing time by pausing execution for 2 seconds
	private static void delayedExecution() throws InterruptedException{
    	System.out.println("\nPlease hang on a second....."); //Printing user to wait for couple of seconds
    	//This pauses the execution of the thread for 2000 milliseconds
    	//From this feature creates a more realistic experience for the user by introducing a delay that simulates actual processing time
    	Thread.sleep(2000); //Waiting for 2 seconds before printing the weather
	}

	//This code represents how the user can end the conversation with Nootnoot
	//This code functionality lets the user too quit the Nootnoot chatbot via "quit" or "q" commands
	private static boolean endConversation(String city) {
	    if (city.equalsIgnoreCase("quit") || city.equalsIgnoreCase("q")) {
	        System.out.println("Thank you and have a great day :)");
	        return true;
	    }
	    return false;
	}
	
	//This method fetches weather data from the OpenWeatherMap.org API website and returns the weather date in String format 
	//It also handles any exceptions that may occur while trying to fetch the data
	private static String fetchWeatherData(String city, String open_weather_api_key) {
	    StringBuilder weatherDataString = new StringBuilder();
    	//Implementing try-catch block code for handling any kind of errors that occur during I/O operations
	    try {
	        URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + open_weather_api_key);
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection(); //created a new connection to the API using HTTP protocol
	        connection.setRequestMethod("GET"); //setting request method as 'GET'
	        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream())); //Processes the response from the website
	        String line;

	        //Reading the response from the OpenWeatherMap.org api website
	        while ((line = reader.readLine()) != null) {
	            weatherDataString.append(line);
	        }
	        reader.close(); //Close BufferedReader object
	    }
        //This block of code handles any exceptions that occur while trying to fetch weather data from OpenWeatherMap.org api website
	    catch (Exception e) {
	        System.out.println("\nOops!! Error fetching weather data. Please try again."); //Prints error statement
	    }
	    return weatherDataString.toString(); //Returns the weather data into string format
	}

    //This method extracts the weather in degrees Celsius from the raw weather data
    //Now the extracted temperature value is converted into String format with 2 decimal places.
	private static String extractTemperatureInCelsius(String weatherData) {
		int startIndex = weatherData.indexOf("temp\":") + 6; //Getting start and end index
	    int endIndex = weatherData.indexOf(",", startIndex);
	    double kelvinTemperature = Double.parseDouble(weatherData.substring(startIndex, endIndex));
	    double celsiusTemperature  = kelvinTemperature - 273.15; //Converting Kelvin to Celsius, because Celsius scale is also commonly 
	    return String.format("%.2f", celsiusTemperature); //Returns the temperature as a String with 2 decimal places
	}

}

