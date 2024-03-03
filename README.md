# Flickr Cat App

This is a Kotlin-based Android application that fetches and displays cat images from Flickr's public feed. 
The application uses the Flickr API, Room for local data persistence, and follows the repository pattern for data management. 
The application fetches data from the API and stores it in a local database, making it available even when the network is not accessible.

## Architecture

The application follows the Model-View-ViewModel (MVVM) architecture. This architecture allows for a clear separation of concerns, easy testing, and efficient data management. 
The main components of this architecture in the application are:

- **Model**: This includes the data sources and the repository. The data is fetched from the Flickr API and stored in a local database using Room.
- **View**: The views are defined in XML files. The main view varies for phones and tablets, providing an optimized user experience for different screen sizes.
- **ViewModel**: The ViewModel acts as a bridge between the Model and the View. It exposes the data to the View and handles user interactions.

## Features

### 1. External Browser Opening

On clicking an image, the application opens the image in an external browser, providing a full-screen view of the adorable cat image. 
This feature enhances the user experience by allowing users to view images in greater detail.

<img src="https://github.com/m-ewa/FlickrApp/blob/master/screenshots/openbrowser.gif" width="500" height="600">

### 2. Dark Mode

The application supports both light and dark themes. Users can switch between these modes according to their preference. 
The application automatically adjusts the color scheme to provide a seamless user experience.

![Dark Mode GIF](https://github.com/m-ewa/FlickrApp/blob/master/screenshots/daynight.gif)

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
