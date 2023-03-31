//These are the necessary import statements for using classes from the Java API and for making HTTP requests
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Code_Review_1_Nootnoot {

	public static void main(String[] args) throws Exception {
    	String open_weather_api_key = "b369057d518ed3e182c04c76c1ec73fe"; //This is the OpenWeatherMap API key
    	Scanner scan = new Scanner(System.in); //Created a scanner object
    	System.out.println("Welcome to the Weather ChatBot! \nHi my name's Nootnoot"); //Nootnoot introduces itself and prints a welcome message to the user

        while (true) { //Inifite loop until user types 'quit' or 'q'
        	System.out.println("What location would you like to know the weather for? "); //Printing users input
        	String city = scan.nextLine(); //Reading users input

        	//This code represnts how the user can end the conversation with Nootnoot
        	//This code functionality lets the user too quit the Nootnoot chatbot via "quit" or "q" commands
        	if (city.equalsIgnoreCase("quit")) {
                System.out.println("Thank you and have a great day :)");
                break;
            }
            else if (city.equalsIgnoreCase("q")) {
                System.out.println("Thanks you and have a great day :)");
                break;
            }

        	//Implementing try-catch block code for handling any kind of errors
	    	try {
				URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + open_weather_api_key);
		        HttpURLConnection connection = (HttpURLConnection) url.openConnection(); //created a new connection to the API using HTTP protocol
		        connection.setRequestMethod("GET");
		        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		        String line;
		        StringBuilder weatherDataString = new StringBuilder();
	
		        //Reading the response from the OpenWeatherMap.org api website
		        while ((line = reader.readLine()) != null) {
		        	weatherDataString.append(line);
		        }
		        reader.close();
	
		        //Extracting temperature data from the response
		        String weatherData = weatherDataString.toString();
		        String temperature = extractTemperatureInCelsius(weatherData);
		        
		        //This print statements prints out the temperature of the city in degrees Celsius
		        System.out.println("The temperature in " + city + " is " + temperature + " degrees Celsius.");

	        }
            //This block of code handles any exceptions that occur while trying to fetch weather data from OpenWeatherMap.org api website
	    	catch(Exception e) {
	    		System.out.println("Error!! Please try again.");
	    	}
        }
	}

	//This function returns the weather in String format which is extracted from the raw weather data from weatherData
	private static String extractTemperatureInCelsius(String weatherData) {
		int startIndex = weatherData.indexOf("temp\":") + 6; //Getting start and end index
	    int endIndex = weatherData.indexOf(",", startIndex);
	    double kelvinTemperature = Double.parseDouble(weatherData.substring(startIndex, endIndex));
	    double celsiusTemperature  = kelvinTemperature - 273.15; //Converting Kelvin to Celsius, because Celsius scale is also commonly 
	    return String.format("%.2f", celsiusTemperature); //Returns the temperature as a String with 2 decimal places
	}

}
