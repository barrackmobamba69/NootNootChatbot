# NootNootChatbot

Welcome to our official Nootnoot Weather API Chatbot.

## Introduction
Nootnoot is a chatbot application that uses weather API from the [OpenWeatherMap.org](https://home.openweathermap.org) website to provide users with real-time information on the weather of their desired cities.

## Functionality
Nootnoot understands user input and responds accurately using natural language processing. Its features include:

- Real-time weather updates
- Welcoming the user
- Inputting city name
- Searching weather data
- Extracting temperature data
- Providing more explanation of the climate
- Suggesting the type of clothing
- Weather forecast for the next 5 days
- Audio functionality

## Visuals
The following video gives you an overview of how the chatbot works and answers.
https://github.com/barrackmobamba69/NootNootChatbot/assets/106630515/a2fd00e5-74e6-4520-9d6f-b3cb95337b3b

## Installation
1. Project Setup
To get started with this project, there are a few prerequisites that need to be taken care of. Make sure you have the following:

- An IDE of your choice
- Java Development Kit (JDK)
- Maven
- JUnit 5

2. Cloning and Importing Project to IDE
- Clone the project repository from GitLab using either Eclipse/IntelliJ or the Git Bash terminal.
- Once the cloning is done, import the project into your IDE.

3. Running the Nootnoot java files
After importing the project into your IDE, you should be able to see the project structure, which is as follows:
<img src="https://github.com/barrackmobamba69/NootNootChatbot/assets/106630515/95f2dc65-b389-4240-b07c-66c1634223f4" width="500">


•	Code_Review_Final_Nootnoot: Contains the main function that includes all project methods
![image](https://github.com/barrackmobamba69/NootNootChatbot/assets/106630515/f5231ab5-d585-418d-936f-10a7483c5c65)

•	Code_Review_Final_NootnootTest: Contains all unit and integration tests
![image](https://github.com/barrackmobamba69/NootNootChatbot/assets/106630515/0fdcccfe-00cb-47d8-bf8c-9fb26be5fc89)

4. Output console view
The following snippet shows how the output console view looks like upon running the Code_Review_Final_Nootnoot. It provides a list of available commands such as getting weather, planning a trip, and quitting upon the user typing 'help'. The bot then asks for the location and gives the temperature, weather description, chance of precipitation, wind speed, and clothing recommendation. The bot continues prompting the user for more locations until the user types quit, after which the chatbot closes with a goodbye message.

![image](https://github.com/barrackmobamba69/NootNootChatbot/assets/106630515/7ffa67a5-0152-4816-8abf-80bd86667316)


## Usage
Use examples liberally, and show the expected output if you can. It's helpful to have inline the smallest example of usage that you can demonstrate, while providing links to more sophisticated examples if they are too long to reasonably include in the README.

## Weather API
To enable our chatbot to obtain current weather information, we integrated it with Nootnoot using OpenWeather (https://openweathermap.org/). 
OpenWeather is a highly efficient API for weather services that permits us to extract weather data by utilizing JSON strings and converting them into Celsius temperature units. This approach allows us to obtain weather data by submitting a GET request through the OpenWeatherMap API website for a particular city and API key before extracting solely the temperature component from the response string. The temperature is then converted from Kelvin to Celsius degrees at a later stage.
This approach allows us to obtain accurate current weather data and provide it to our users in a clear and concise manner.

## Technologies Used
NootNoot is built on a number of technologies such as:
- Java programming language (Eclipse Java (Version 2021-09 (4.21.0)) and IntelliJ IDEA (Version 2021.2.1))
- OpenWeatherMap API for fetching weather data
- GitLab is used as a repository to store this code
- FreeTTS for audio functionality (Kevin16)

![image](https://github.com/barrackmobamba69/NootNootChatbot/assets/106630515/c9468303-675e-4105-9c41-655233d1b654)
*Figure 1: Collage of tools used, May 15, 2023. (Source: canva.com, 2023:online)*

## Future Enhancements
NootNoot is an ongoing project and we plan to add more features in the future. We are currently working on adding additional functionalities, such as:
- GUI

## Credits
- [OpenWeatherMap.org](https://home.openweathermap.org) for providing free weather data and creating easy-to-use APIs.
- I would like to acknowledge and thank [iamashks](https://github.com/iamashks/OWM-JAPIs) for sharing their chatbot code and providing inspiration for your project.
- This project was built using Eclipse Java (Version 2021-09 (4.21.0)) and IntelliJ IDEA (Version 2021.2.1)

#### Contributors
- @AlbinDrag
- @barrackmobamba69
- @samuel

## License
This project is under the MIT License.

## Conclusion
In summary, Nootnoot is an innovative and useful chatbot application that makes it easy and fun to check the weather. Our team is continuously improving the project by adding new features to enhance user experience.
We hope you found this wiki helpful in understanding the code used for NootNoot chatbot functionality. Thank you for reading and happy coding!

