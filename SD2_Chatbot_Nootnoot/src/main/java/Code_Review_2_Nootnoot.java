//These are the necessary import statements for using classes from the Java API and for making HTTP requests
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Code_Review_2_Nootnoot {

    public static final String open_weather_api_key = "b369057d518ed3e182c04c76c1ec73fe"; //This is the OpenWeatherMap API key
    
	public static void main(String[] args) throws Exception {
        welcomeMessage(); //Calling welcomeMessage() method
        
		// Adding scanner to read input from standard input stream
		Scanner scanner = new Scanner(System.in);

        while (true) {
            String city = inputLocation(); //Calling inputLocation() method
            
            delayedExecution(); //Calling pauseExecution() method

            if (endConversation(city)) { //Calling endConversation() method
                break;
            }

            String weatherData = fetchWeatherData(city, open_weather_api_key); //Calling fetchWeatherData() method
            String temperature = extractTemperatureInCelsius(weatherData); //Calling extractTemperature() method
            String precipitationChance = extractPrecipitationChance(weatherData);

            //Added a validation to ensure that the temperature value is not empty before parsing it into a double
            if(temperature.isEmpty() || precipitationChance.isEmpty()) {
                System.out.println("Please try again.");
                continue;
            }

          	 //Converting the temperature string to double.	            	
           	double temp = Double.parseDouble(temperature);
           	
        	//Calling the weatherCondition method and also saving its results in a variable of string.
            String weatherCondition = getWeatherCondition(Double.parseDouble(temperature));
            
            //Calling the clothingSuggestion method and saving its results in a string variable.
            String clothingSuggestion = suggestClothing(temperature);
            
            //We print out the temperature in Celsius degrees
            System.out.println("The temperature in " + city + " is " + temperature + " degrees Celsius.");
            
            //Printing out the weather condition
            System.out.println("According to the reports, the weather looks like " + weatherCondition);
            
            //
            System.out.println("The chance of precipitation is " + precipitationChance);

            //printing out the clothing suggestion
            System.out.println("The best choice of clothing is " + clothingSuggestion);
        }
        
        //closing the scanner
        scanner.close();

	}

	//------- All the methods are called below ---------
	
	//This method greets the user by printing welcoming message
	public static void welcomeMessage() {
	    System.out.println("Welcome to the Weather ChatBot! \nHi my name's Nootnoot");
	}

	//This method uses Scanner object to ask the user to input the city name
	public static String inputLocation() {
	    Scanner scan = new Scanner(System.in); //Created a scanner object
	    System.out.println("\nWhat location would you like to know the weather for? "); //Printing users input
	    return scan.nextLine(); //Reading users' input
	}
	
	//This method prints a message and simulates processing time by pausing execution for 2 seconds
	public static void delayedExecution() throws InterruptedException{
    	System.out.println("\nPlease hang on a second..."); //Printing user to wait for couple of seconds
    	//This pauses the execution of the thread for 2000 milliseconds
    	//From this feature creates a more realistic experience for the user by introducing a delay that simulates actual processing time
    	Thread.sleep(2000); //Waiting for 2 seconds before printing the weather
	}

	//This code represents how the user can end the conversation with Nootnoot
	//This code functionality lets the user too quit the Nootnoot chatbot via "quit" or "q" commands
	public static boolean endConversation(String city) {
	    if (city.equalsIgnoreCase("quit") || city.equalsIgnoreCase("q")) {
	        System.out.println("Thank you and have a great day :)");
	        return true;
	    }
	    return false;
	}
	
//	Code or method, that suggest the type of cloth to wear while going outside, according to the temprature of the area.
	private static String suggestClothing(String temperature) {
        double temp = Double.parseDouble(temperature);
        if (temp < 5) {
            return "a heavy coat or jacket, thick gloves, a winter scarf, and a hat that could cover your ears.";
        } 
        else if (temp >= 5 && temp < 10) {
            return "a warm coat, gloves, and hat if required.";
        } 
        else if (temp >= 10 && temp < 20) {
            return "a jacket, a hoodie or a sweater.";
        } 
        else if (temp >= 20 && temp < 25) {
            return "a half-sleeved shirt or nice looking T-shirt and  shorts or tracks.";
        } 
        else if (temp >= 25 && temp < 30) {
            return "a normal t-shirt and joggers or shorts.";
        } 
        else {
            return "light weight shorts, shirts or shirtless.";
        }
    }
	
//  Method used to express the type of weather/ weather ccondition, matching with the provided location and temprature.
	public static String getWeatherCondition(double temperature) {
        if (temperature < 0) {
            return "freezing.";
        } 
        else if (temperature >= 0 && temperature < 10) {
            return "very cold.";
        } 
        else if (temperature >= 10 && temperature < 20) {
            return "cold.";
        } 
        else if (temperature >= 20 && temperature < 30) {
            return "pleasant.";
        } 
        else {
            return "very hot.";
        }
    }

	//This method fetches weather data from the OpenWeatherMap.org API website and returns the weather date in String format 
	//It also handles any exceptions that may occur while trying to fetch the data
	public static String fetchWeatherData(String city, String open_weather_api_key) {
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
	        System.out.println("\nOops! Error fetching weather data."); //Prints error statement
	    }
	    return weatherDataString.toString(); //Returns the weather data into string format
	}

    //This method extracts the weather in degrees Celsius from the raw weather data
    //Now the extracted temperature value is converted into String format with 2 decimal places.
	 public static String extractTemperatureInCelsius(String weatherData) {
	    int startIndex = weatherData.indexOf("temp\":");
	    if (startIndex < 0) { //Checking if the 'temp' value exists or not
	        return ""; //Return an empty string as a fallback
	    }
	    startIndex += 6; //Getting start and end index
	    int endIndex = weatherData.indexOf(",", startIndex);
	    double kelvinTemperature = Double.parseDouble(weatherData.substring(startIndex, endIndex));
	    double celsiusTemperature = kelvinTemperature - 273.15; //Converting Kelvin to Celsius, because Celsius scale is commonly used
	    return String.format("%.2f", celsiusTemperature); //Returns the temperature as a String with 2 decimal places
	 }
	 
	 public static String extractPrecipitationChance(String weatherData) {
		 int startIndex = weatherData.indexOf("pop\":");
			 return "Nootnoot says:";
	 }
}