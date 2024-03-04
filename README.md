# Flickr Cat App

This is a Kotlin-based Android application that fetches and displays cat images from Flickr's public feed. 
The application fetches data from the API and stores it in a local database, making it available even when the network is not accessible.

## Architecture

The application follows the Model-View-ViewModel (MVVM) architecture. This architecture allows for a clear separation of concerns, easy testing, and efficient data management. 
The main components of this architecture in the application are:

- **Model**: This includes the data sources and the repository. The data is fetched from the Flickr API and stored in a local database using Room.
- **View**: The views are defined in XML files. The main view varies for phones and tablets, providing an optimized user experience for different screen sizes.
- **ViewModel**: The ViewModel acts as a bridge between the Model and the View. It exposes the data to the View and handles user interactions.

## Project Structure

The project is structured into multiple modules, each with a specific responsibility, adhering to the principles of Clean Architecture and SOLID principles. Here's a brief overview of the modules:  

- **app**: This is the main module of the application. It contains the UI layer of the application, including activities, fragments, and view models. It depends on the domain and data modules.  
- **data**: This module is responsible for data management. It includes the implementation of the data sources (both local and remote) and the repository. It depends on the domain module.  
- **domain**: This module contains the business logic of the application. It defines the use cases and the repository interfaces.  

The separation of concerns is evident in the structure of these modules. Each module has a specific role and does not interfere with the responsibilities of the other modules. This structure adheres to the Single Responsibility Principle, one of the SOLID principles.  
The Open/Closed Principle is also respected. Each module is open for extension (we can add new features or modify existing ones without affecting other modules) but closed for modification (changes in one module do not require changes in other modules).  
The Liskov Substitution Principle is maintained as there are no subclasses altering the behavior of their parent classes.  
The Interface Segregation Principle is followed as the modules do not depend on interfaces they do not use. Each module communicates with others through minimal interfaces.  
The Dependency Inversion Principle is respected as high-level modules do not depend on low-level modules. Both depend on abstractions. For example, the app module does not depend directly on the data module. Instead, both depend on the abstractions defined in the domain module.  
In terms of Clean Architecture, the domain module represents the innermost circle (Entities), the data module represents the next circle (Use Cases), and the app module represents the outer circles (Interface Adapters, and Frameworks and Drivers). The dependency rule of Clean Architecture is maintained as inner circles do not depend on outer circles.  

This modular structure makes the code more maintainable, scalable, and testable. It also improves the build speed as Gradle can compile independent modules in parallel.

## Features

### 1. External Browser Opening

On clicking an image, the application opens the image in an external browser, providing a full-screen view of the adorable cat image. 
This feature enhances the user experience by allowing users to view images in greater detail.

<img src="https://github.com/m-ewa/FlickrApp/blob/master/screenshots/openbrowser.gif" width="300" height="650">

### 2. Adaptive Display Feature

The application is designed with a responsive layout to provide an optimal user experience across different device types. 
This includes a unique feature that adapts the display of the main screen based on the device being used.
On mobile phones, the main screen of the application is designed for a compact and efficient display. The images are arranged in a single column, allowing users to scroll vertically through the content.
For tablet users, I have enhanced the display to take advantage of the larger screen size. The main screen on tablets presents images in a three-column layout. 

<img src="https://github.com/m-ewa/FlickrApp/blob/master/screenshots/tablet.webp">

### 3. Dark Mode

The application supports both light and dark themes. Users can switch between these modes according to their preference. 
The application automatically adjusts the color scheme to provide a seamless user experience.

<img src="https://github.com/m-ewa/FlickrApp/blob/master/screenshots/daynight.gif" width="300" height="650">

## Libraries Used

- **Kotlin**: The project is entirely written in Kotlin.
- **Room**: Used for local data persistence.
- **Retrofit**: Used for network requests.
- **Coroutines**: Used for asynchronous programming.
- **Dagger Hilt**: Used for dependency injection.
- **Coil**: Used for loading image from url.

## Requirements

- Android Studio Hedgehog 2023.1.1 Patch 2 or later
- Java 17
- Android SDK with API level 24 or later

## Setup

1. Install Android Studio and JDK.
2. Clone the repository.
3. Open the project in Android Studio.
4. Sync the Gradle files.
5. Run the project on your Android emulator or physical device.

## License

This project is licensed under the MIT License.
