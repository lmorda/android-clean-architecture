# Android Homework Project

This project serves as a starter template for coding challenges commonly encountered during technical interviews. It highlights my ability to implement modern Android development practices and demonstrates proficiency with various frameworks and libraries. Although relatively small in scope, it employs a multi-module clean architecture to showcase my expertise in designing scalable and maintainable code.

## Features

- **Architecture:** Implements a clean MVVM architecture for maintainable and testable code.
- **UI:** Built with Jetpack Compose for declarative UI and Compose Navigation for seamless screen transitions.
- **Networking:** Utilizes Retrofit for API calls to fetch data from the GitHub API.
- **Dependency Injection:** Leverages Dagger Hilt for dependency management.
- **Testing:** Includes unit tests with MockK and UI screenshot tests with Jetpack Compose.
- **Modularization:** Follows a modularized approach to separate concerns and improve scalability.

## Project Overview

The app fetches a list of GitHub repositories from the [GitHub API](https://docs.github.com/en/rest) and displays them in a list. When a repository is tapped, the app navigates to a detailed view showing additional information about the selected repository.

### Key Screens:
1. **Repository List:** Displays a list of repositories with key details like name, description, and star count.
2. **Repository Details:** Shows more detailed information about a specific repository, including contributors, readme, and other metadata.

## Tech Stack

- **Language:** Kotlin
- **UI Framework:** Jetpack Compose
- **Navigation:** Compose Navigation
- **Networking:** Retrofit
- **Dependency Injection:** Dagger Hilt
- **Architecture:** MVVM
- **Testing:** MockK for unit testing, Jetpack Compose Screenshot Tests
- **Build System:** Gradle with Kotlin DSL

## Setup

### Prerequisites
- Android Studio Ladybug or newer

### Steps to Run
1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/android-homework-project.git
   ```
2. Open the project in Android Studio.
3. Sync Gradle.
4. Run the app on an emulator or a physical device.

## Modularization

The project is organized into separate modules to improve code reusability and maintainability:
- **App Module:** Contains the application entry point and common configurations.
- **Domain Module:** Contains business logic and repository interface.
- **Data Module:** Handles data fetching and repository implementation.
- **UI Module:** Contains all UI components built with Jetpack Compose.

## Libraries Used

| Library                        | Purpose                                                   |
|--------------------------------|-----------------------------------------------------------|
| Jetpack Compose                | For building declarative UI                                |
| Compose Navigation             | For in-app navigation                                     |
| Compose Screenshot Tests | For UI verification via screenshot testing              |
| Retrofit                        | For making network requests                               |
| Timber                          | For logging                                              |
| Dagger Hilt                    | For dependency injection                                  |
| MockK                           | For mocking dependencies in unit tests                   |
| Kotlin                          | Primary programming language                              |


## Clean Architecture

This project adheres to clean architecture principles to ensure clear separation of concerns. Unlike the domain layer explanation in the Android Developer documentation, this implementation follows a true clean architecture approach. The data and UI layers are fully decoupled, with the domain layer serving as the intermediary. The domain layer contains only business logic.  
<img width="1243" alt="Screenshot 2025-01-16 at 8 56 30 AM" src="https://github.com/user-attachments/assets/f0bc299f-e7ef-434c-a8c5-76f54d87e54d" />
Note: UseCases were not utilized in this project due to its small scope.

## Testing

This project includes:
- **Unit Tests:** Using MockK to mock dependencies and ensure robust ViewModel testing.
- **UI Tests:** Jetpack Compose screenshot tests to verify UI consistency across changes.

## License

This project is licensed under the Apache License. See the [LICENSE](LICENSE) file for details.
