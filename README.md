# Portfolio Android App

A modern Android portfolio application built with Jetpack Compose and clean architecture principles.

## Features

- **Home**: Main landing page showcasing personal information
- **Projects**: Display of portfolio projects and work samples
- **About**: Personal information and background details
- **Contact**: Contact information and communication methods

## Tech Stack

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Architecture**: Clean Architecture with MVVM
- **Dependency Injection**: Koin
- **Navigation**: Navigation Compose
- **Build System**: Gradle with Kotlin DSL
- **Networking**: Custom network module

## Architecture

The project follows clean architecture principles with a modular structure:

```
├── app/                    # Main application module
├── core/                   # Core shared modules
│   ├── network/           # Network utilities and configuration
│   └── theme/             # UI theme and design system
├── data/                   # Data layer implementation
├── domain/                 # Business logic and use cases
├── features/               # Feature modules
│   ├── home/              # Home screen feature
│   ├── projects/          # Projects showcase feature
│   ├── about/             # About section feature
│   └── contact/           # Contact information feature
└── build-logic/           # Build configuration and conventions
```

## Getting Started

### Prerequisites

- Android Studio Flamingo (2022.2.1) or newer
- JDK 11 or higher
- Android SDK API level 21 or higher

### Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/portfolio.git
   cd portfolio
   ```

2. Open the project in Android Studio

3. Wait for Gradle sync to complete

4. Run the app on an emulator or physical device

### Building

To build the project:

```bash
./gradlew build
```

To run tests:

```bash
./gradlew test
```

## Project Structure

- **Modular Architecture**: Each feature is isolated in its own module
- **Clean Architecture**: Separation of concerns with data, domain, and presentation layers
- **Dependency Injection**: Using Koin for managing dependencies
- **Compose UI**: Modern declarative UI with Jetpack Compose
- **Material Design 3**: Following latest Material Design guidelines

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Run tests and ensure code quality
5. Submit a pull request

## License

This project is licensed under the MIT License - see the LICENSE file for details.