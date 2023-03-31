//These are the necessary import statements for using classes from the Java API and for making HTTP requests
import java.util.Scanner;

public class Code_Review_1_Nootnoot {

	public static void main(String[] args) throws Exception {
    	String open_weather_api_key = "b369057d518ed3e182c04c76c1ec73fe"; //This is the OpenWeatherMap API key
    	Scanner scan = new Scanner(System.in); //Created a scanner object
    	System.out.println("Heya");//Nootnoot saying hello
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
        }

    	//Implementing try-catch block
    	try {
    		//  Block of code to try
    		}
    	catch(Exception e) {
    		//  Block of code to handle errors
    	}

	}
}
