# Android Homework Project

This project is a starter template for coding challenges typically given during technical interviews. It demonstrates my ability to implement modern Android development practices and showcases my skills with various frameworks and libraries.

## Features

- **Architecture:** Implements the MVVM architecture for maintainable and testable code.
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

## Testing

This project includes:
- **Unit Tests:** Using MockK to mock dependencies and ensure robust ViewModel testing.
- **UI Tests:** Jetpack Compose screenshot tests to verify UI consistency across changes.

## License

This project is licensed under the Apache License. See the [LICENSE](LICENSE) file for details.
