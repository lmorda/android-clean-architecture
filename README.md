# android-clean-architecture

This project demonstrates Clean Architecture principles in an Android app.  The project highlights scalability, maintainability, and testability, 
making it a strong foundation for building robust Android applications.

## Features

- **Architecture:** Implements MVVM architecture
- **UI:** Built with Jetpack Compose for UI and Compose Navigation for screen transitions
- **Networking:** Utilizes Retrofit for API calls to fetch data from the GitHub API
- **Dependency Injection:** Leverages Dagger Hilt for dependency management
- **Testing:** Includes unit tests with MockK and Compose UI screenshot tests with Espresso
- **Modularization:** Follows a modularized approach to separate concerns and improve scalability

## Project Overview

The app fetches a list of GitHub repositories from the [GitHub API](https://docs.github.com/en/rest) and displays them in a list. When a repository is tapped, the app 
navigates to a detailed view showing additional information about the selected repository.

### Key Screens
1. **Repository List:** Displays a list of repositories with key details like name, description, and star count
2. **Repository Details:** Shows detailed information about a specific repository

## Libraries

- **Language:** Kotlin
- **UI Framework:** Jetpack Compose
- **Navigation:** Compose Navigation
- **Networking:** Retrofit
- **Dependency Injection:** Dagger Hilt
- **Architecture:** MVVM
- **Testing:** MockK for unit testing, Jetpack Compose for screenshot tests
- **Build System:** Gradle with Kotlin DSL

## Setup

### Prerequisites
- Android Studio Ladybug or newer

### Steps to Run
1. Clone the repository:
   ```bash
   git clone https://github.com/lmorda/android-clean-architecture.git
   ```
2. Open the project in Android Studio
3. Sync Gradle
4. Run the app on an emulator or a physical device

## Modularization

The project is organized into separate modules to improve code reusability and maintainability:
- **App Module:** Contains the application, main activity, and navigation host
- **Data Module:** Handles data fetching and repository implementation
- **Domain Module:** Contains DTOs and repository interface
- **Explore Module:** Contains all Explore page UI components built with Jetpack Compose
- **Design Module:** Home to the project's Compose Theme, Typography, and Colors 
- **Utils Module:** Utility functions to be shared across the app

## Clean Architecture

This project adheres to clean architecture principles to ensure clear separation of concerns. Unlike the domain layer explanation 
in the Android Developer documentation, this implementation follows a true clean architecture approach. The data and UI layers are 
fully decoupled, with the domain layer serving as the intermediary. The domain layer contains only business logic.  
<img width="1243" alt="Screenshot 2025-01-16 at 8 56 30 AM" src="https://github.com/user-attachments/assets/f0bc299f-e7ef-434c-a8c5-76f54d87e54d" />
Note: UseCases were not utilized in this project due to its small scope.

## Asset Acknowledgement

Thanks to Agung Rama for the app icon:

https://www.flaticon.com/authors/agung-rama

## License

This project is licensed under the Apache License. See the [LICENSE](LICENSE) file for details.
