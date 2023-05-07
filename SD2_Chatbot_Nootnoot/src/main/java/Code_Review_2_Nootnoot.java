//These are the necessary import statements for using classes from the Java API and for making HTTP requests
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import javax.swing.*;
import com.sun.speech.freetts.VoiceManager;
import com.sun.speech.freetts.VoiceDirectory;
import com.sun.speech.freetts.Voice;

public class Code_Review_2_Nootnoot {

    public static final String OPEN_WEATHER_API_KEY = "b369057d518ed3e182c04c76c1ec73fe"; //This is the OpenWeatherMap API key
    
	public static void main(String[] args) throws Exception {
        welcomeMessage(); //Calling welcomeMessage() method
        displayWelcomeImageOfNootnoot(); //Calling Nootnoot picture
        System.out.println("Type 'help' to show the command list");
        
		//Adding scanner to read input from standard input stream
		Scanner scanner = new Scanner(System.in);
		
		while (true) {
			String city = inputLocation(); //Calling inputLocation() method
            
            delayedExecution(); //Calling pauseExecution() method

            if (endConversation(city)) { //Calling endConversation() method
                break;
            }
            
            //Calling plan function that takes care of the user requirement when user inputs 5 destinations 
            if (city.equalsIgnoreCase("plan")) {
                plan();
                continue;
            }
            
            //Calling the function which helps the user by showing all the possible chatbot commands
            if (city.equalsIgnoreCase("help")) {
                showCommands();
                continue;
            }

            String weatherData = fetchWeatherData(city, OPEN_WEATHER_API_KEY); //Calling fetchWeatherData() method
            String temperature = extractTemperatureInCelsius(weatherData); //Calling extractTemperature() method
            String precipitationChance = extractPrecipitationChance(weatherData);
            String wind = extractWind(weatherData);

            //Added a validation to ensure that the temperature value is not empty before parsing it into a double
            if(temperature.isEmpty()) {
                System.out.println("Please try again.");
                continue;
            }

          	//Converting the temperature string to double.	            	
           	double temp = Double.parseDouble(temperature);
           	
        	//Calling the weatherCondition method and also saving its results in a variable of string.
            String weatherCondition = getWeatherCondition(Double.parseDouble(temperature));
            
            //Calling the clothingSuggestion method and saving its results in a string variable.
            String clothingSuggestion = suggestClothing(temperature);
            
            //We print out the temperature in Celsius degrees and also speak using voice
            System.out.println("The temperature in " + city + " is " + temperature + " degrees Celsius.");
            VoiceManager voiceManager = VoiceManager.getInstance(); //instance of the VoiceManager class
            Voice[] voices = voiceManager.getVoices();
            Voice voice = voices[1];
            voice.allocate();
            voice.setRate(150);
            voice.setVolume(1.0f);
            voice.speak("The temperature in " + city + " is " + temperature + " degrees Celsius.");
            voice.deallocate(); //freeing up the memory occupied by the voice object

            
            //Printing out the weather condition
            System.out.println("According to the reports, the weather looks like " + weatherCondition);
            
            //Printing out the chance of precipitation
            System.out.println("The chance of precipitation is " + precipitationChance);

            //Printing out the wind speed
            System.out.println("The wind speed is " + wind); 

            //printing out the clothing suggestion
            System.out.println("The best choice of clothing is " + clothingSuggestion);
        }
        
        //Closing the scanner
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
	    System.out.println("\nWhat location would you like to know the weather for? (e.g. London, Cork)"); //Printing users input
	    return scan.nextLine(); //Reading users' input
	}
	
	//This method prints a message and simulates processing time by pausing execution for 2 seconds
	public static void delayedExecution() throws InterruptedException{
    	System.out.println("\nPlease hang on a second..."); //Printing user to wait for couple of seconds
    	//This pauses the execution of the thread for 2000 milliseconds
    	//From this feature creates a more realistic experience for the user by introducing a delay that simulates actual processing time
    	Thread.sleep(2000); //Waiting for 2 seconds before printing the weather
	}

	//This method prints out all the possible chatbot commands the user can perform
	public static void showCommands() {
		System.out.println("Here are the available commands:");
		System.out.println("- Type the name of a city to get its weather");
		System.out.println("- Type 'plan' to plan your trip");
		System.out.println("- Type 'quit' or 'q' to exit");
	}
	
	//This method displays a friendly picture of Nootnoot to welcome the user
	public static String displayWelcomeImageOfNootnoot() throws InterruptedException {
	    JFrame frame = new JFrame("Nootnoot Welcomes You!");
	    frame.add(new JLabel(new ImageIcon("nootnoot.jpg")));
	    frame.pack();
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
	    Thread.sleep(1000);
	    frame.dispose(); 
	    return "Nootnoot!";
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
	
    //Code or method, that suggest the type of cloth to wear while going outside, according to the temprature of the area.
	public static String suggestClothing(String temperature) {
        double temp = Double.parseDouble(temperature);
        if (temp < 0) {
            return "You'll need a heavy coat or jacket, thick gloves, a winter scarf, and a hat that covers your ears.";
        } 
        else if (temp >= 0 && temp < 10) {
            return "You'll need a warm coat, gloves, and hat if required.";
        } 
        else if (temp >= 10 && temp < 15) {
            return "You'll need a jacket, a hoodie, or a sweater.";
        } 
        else if (temp >= 15 && temp < 20) {
            return "You'll be comfortable in a half-sleeved shirt or nice-looking t-shirt and shorts or tracks.";
        } 
        else if (temp >= 20 && temp < 25) {
            return "You'll be comfortable in a normal t-shirt and joggers or shorts.";
        } 
        else {
            return "You'll need light-weight shorts, shirts or no shirt at all!";
        }
    }
	
	//Method used to express the type of weather/ weather condition, matching with the provided location and temprature.
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
	        
	        int start = weatherDataString.indexOf("main\":");
	        if (start < 0) {
	            return ""; // Return an empty string if the weather data does not exist
	        }
	        start += 7;
	        int end = weatherDataString.indexOf(",", start);
	        String weatherCondition = weatherDataString.substring(start, end).replaceAll("\"", "");
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

	//This method extracts the chance of precipitation from the weather API
	public static String extractPrecipitationChance(String weatherData) {
		int startIndex = weatherData.indexOf("weather\":");
		if (startIndex < 0) {
			return "unknown"; //Return an unknown value if there is no weather information
		}
		//Calling broken down functions
		String weatherInfo = extractWeatherInfo(startIndex, weatherData);
		String precipitationChance = getPrecipitationChance(weatherInfo);
		return precipitationChance; 
	}

	 //This function extracts the weather description or code field from the response
	 public static String extractWeatherInfo(int startIndex, String weatherData) {
		 startIndex = weatherData.indexOf("\"description\":", startIndex);
		 if (startIndex < 0) { //If the description field is not found, try searching for the id instead
			 startIndex = weatherData.indexOf("\"id\":", startIndex);
			 if (startIndex < 0) {
				 return "unknown"; //Return an unknown value if the weather information cannot be extracted
			 }
		 }
		 startIndex = weatherData.indexOf("\"", startIndex) + 1;
		 int endIndex = weatherData.indexOf("\"", startIndex);
		 String weatherInfo = weatherData.substring(startIndex, endIndex);
		    
		 return weatherInfo;
	 }

	 //This function checks if the description or code indicates any kind of precipitation
	 public static String getPrecipitationChance(String weatherInfo) {
		 if (weatherInfo.contains("rain")) {
			 return "high"; //Return high chance if there is rain
		 } else if (weatherInfo.contains("snow")) {
			 return "moderate"; //Return moderate chance if there is any snow
		 } else {
			 return "low"; //Default value for no precipitation recorded
		 }
	 }
	 
	 //This method extracts the wind speed from the weather API
	 public static String extractWind(String weatherData) {
		 int startIndex = weatherData.indexOf("wind\":{\"speed\":");
		 if (startIndex < 0) { //Checking if the 'wind' value exists or not
	        return "Wind data not available!";
		 }
	     startIndex += 16; //Getting start and end index
	     int endIndex = weatherData.indexOf(",", startIndex);
	     double windSpeed = Double.parseDouble(weatherData.substring(startIndex, endIndex));
	     return String.format("%.2f", windSpeed) + " km/h"; //Returns the wind speed in'km/h'
	 }
	 
	 //This method is a plan-trip planner which provides clothing requirements based on the weather conditions in all 5 locations
	 public static void plan() throws Exception {
		    Scanner scanner = new Scanner(System.in);
		    System.out.println("Welcome to the trip planner! Please enter 5 locations.");
		    //initialize array to store locations
		    String[] locations = getLocations(scanner);
		    //loop through each location and get weather data
		    displayWeatherData(locations);
		    scanner.close();
		}

	 //This function takes a Scanner object as an argument and returns an array of 5 locations
	 public static String[] getLocations(Scanner scanner) {
	    String[] locations = new String[5];

	    //Getting input locations
	    for (int i = 0; i < 5; i++) {
	        System.out.print("Location #" + (i + 1) + ": ");
	        if (scanner.hasNextLine()) { // Check if there is input available
	            locations[i] = scanner.nextLine();
	        } else {
	            System.out.println("Please provide a location!"); // Display an error message
	            i--; // Decrement the index so the current location can be reentered
	        }
	    }
	    return locations;
	}

	 //This method takes an array of locations as input and throws an exception if something goes wrong
	 public static void displayWeatherData(String[] locations) throws Exception {
		 //looping through each location and get weather data
		 for (int i = 0; i < 5; i++) {
			 String weatherData = fetchWeatherData(locations[i], OPEN_WEATHER_API_KEY);
	         String temperature = extractTemperatureInCelsius(weatherData);
	         String precipitationChance = extractPrecipitationChance(weatherData);
	         String wind = extractWind(weatherData);
	         String clothingSuggestion = suggestClothing(temperature);
	         
	         printWeatherData(locations[i], temperature, precipitationChance, wind, clothingSuggestion);
		 }
	 }

	 //This function prints weather data for a given location, including temperature, precipitation chance, wind speed and clothing suggestion.
	 public static void printWeatherData(String location, String temperature, String precipitationChance, String wind, String clothingSuggestion) {
	     System.out.println("===================================");
	     System.out.println("Location: " + location);
	     System.out.println("Temperature: " + temperature + " degrees Celsius");
	     System.out.println("Precipitation Chance: " + precipitationChance);
	     System.out.println("Wind Speed: " + wind);
	     System.out.println("Clothing Suggestion: " + clothingSuggestion);
	 }	
}

