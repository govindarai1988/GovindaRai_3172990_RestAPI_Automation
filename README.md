# NAGP2023QABatch API Project

NAGP2023QABatch API Project is a Maven-based Java project for API testing. It uses popular libraries and tools like JUnit, RestAssured, Jackson, and ExtentReports to facilitate the testing process.

## Table of Contents

- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
    - [Installation](#installation)
    - [Running Tests](#running-tests)
- [Project Structure](#project-structure)
  - [Config & Utilities](#config--utilities)
  - [Hooks](#hooks-)
  - [Payloads](#payloads-)
  - [Tests](#tests-)
  - [BatchFile](#batchfile-)


## Prerequisites

Before you begin, ensure you have met the following requirements:

- **Java**: This project requires Java 8 or later.
- **Maven**: Make sure you have Maven installed to manage project dependencies and build processes.

## Getting Started

To get started with this project, follow these steps:

### Installation

1. Clone this Project to your local machine:

   ```sh
   Extract Zip and keep on local folder!

2. Navigate to the project directory:
    ```sh 
   cd NAGP2023QABatch

3. Build the project using Maven:
   ```sh 
   mvn clean install

### Running Tests

To run the tests, you can use the following options :

- Using IDE
    ```sh
    Right click on the folder/Test class and click on Run Tests
- Using CMD
    ```sh
    mvn test
- Using BatchFile
    ```sh
    Click on the TestRun.bat file it will execute all tests

### Project Structure

The project structure is organized as follows:

- src/main/java: Java source code for your project.
- src/main/resources: Resources, including configuration files.
- src/test/java: Test source code.
- src/test/resources: Test-specific resources.

#### Config & Utilities

- [Extent Reports](/Reports)
- [Log4j](log-20230908.log)
- [Configuration Files](/src/main/resources)

#### Hooks ####

- Auth API
- Booking API
- Ping API

#### Payloads ####

- AuthRequest/Response Payloads
- BookingRequest/Response Payloads
- Booking Dates Payload
- ApiEndpoints

#### Tests ####

- [CreateBooking Tests](/src/test/java/APITests/CreateBookingTests.java)
- UpdateBooking Tests
- GetBooking Tests
- Negative Tests
- DeleteBooking Tests

#### BatchFile ####

- [TestRun.bat](/TestRun.bat)





