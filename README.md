# Simple Weather Application
 
# Overview of Weather App

Weather Application using Kotlin which takes city name from user and will show weather details for that city with Following information:
temperature, humidity, wind speed, and weather description

# Screenshots

<img width="298" alt="image" src="https://github.com/user-attachments/assets/6e31cfff-ae8b-4164-8e2f-1cad4d6c4ea3">

<img width="297" alt="image" src="https://github.com/user-attachments/assets/1ab2603c-63d6-491b-815e-fecdc5a9700a">

# Features
- Shows the weather forecast, including temperature, humidity, and conditions.

- View Binding simplifies interaction with views by replacing findViewById, making your code cleaner.

- Retrieves weather data from the OpenWeatherMap API and stores it in a Room database for offline access.

- Adheres to clean architecture with distinct data and domain layers, ensuring separation of concerns and enhancing scalability, performance, security, and code efficiency.

- Utilizes Hilt for dependency injection.

# Getting Started
## Prerequisites
Before you get started, make sure you have the following prerequisites:

- Android Studio installed.
- A basic understanding of Kotlin, Room, and Retrofit.

## Technical specification
Language - Kotlin (with using coroutines)
Support library - androidx

## Open Weather API
We will use Open Weather Map API for collecting weather information. To get the key you need to sign up and get your own APP ID. Otherwise you can test the API with their sample BASE URL and sample APP ID without creating account also.

## Built With

**OpenWeatherMap** - Weather API used

**Hilt Dependency** - Enable dependency injection of certain classes from the androidx libraries.

**Retrofit** - A type-safe HTTP client for Android

**Gson** - A Java serialization/deserialization library to convert Java Objects into JSON and back

**Room Database** – for Caching

## Installation
Clone this repository and import into Android Studio

git clone https://github.com/rjjohar/WeatherApplication.git

## Run the project
Sync the Gradle and run the project. Install APK on your emulator or real device. Turn on the internet of your testing device.
