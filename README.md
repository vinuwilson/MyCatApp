# My Cat App

## Description

This native Android mobile app utilizes The Cat API to showcase various breeds of cats, along with their details. The app features a list of different cat breeds, each accompanied by an image and additional information. Upon selecting a specific cat from the list, users are redirected to a separate page where they can explore further details about the selected cat breed. The project follows SOLID principles and clean code architecture design principles.

## Detailed Project Description

## 1. Technology Used:

  * Jetpack Compose for the UI
  * Hilt for dependency injection
  * Navigation Component for navigation
  * The Cat API for fetching data
  
## 2. Features:

  * Cat Breed List: A list of different cat breeds, each with an image and brief details.
  * Cat Breed Details: Detailed information about a selected cat breed on a separate page.
  * Cat Favorite List: A list of favorited cat breeds.
  * Add Favorite: Users can add cat breeds to the favorite list.

## 3. Architecture:

  * SOLID Principles: The app is designed following the SOLID principles to ensure a scalable and maintainable codebase.
  * Clean Code Architecture: The app architecture is designed to be clean and modular, separating concerns into different layers (e.g., UI, domain, data).

## 4. Implementation:

  * Shared ViewModel: A shared ViewModel is used to manage the state and share data between the list and detail screens.
  * Type-Safe Navigation: The Navigation Component is used with type-safe arguments to navigate between screens.
    
## Design Pattern

* The application is developed using Test-Driven Development (TDD) and follows the Model-View-ViewModel (MVVM) design pattern.

## Further enhancements

* Display the initial list of cat breeds with an indication of which breeds are already favorited.
* Separate different features into distinct modules. This approach improves maintainability, scalability, and reusability.
* Remove a breed from the favorite list.
* Support offline favorited cat view.
* Furthermore cosmetics and refactoring is an endless thought.

## Snapshots

![Screenshot_1](https://github.com/user-attachments/assets/05af0207-f688-4cb7-8572-59df74a7f6c8)

![Screenshot_2](https://github.com/user-attachments/assets/e3f22dce-50fc-4dbd-b27f-95e8f7ca4bab)

![Screenshot_3](https://github.com/user-attachments/assets/4581b3a5-23b6-4dba-8f79-a74b07b202f6)


