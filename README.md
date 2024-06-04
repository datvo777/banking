## Project Overview
This project is a Spring Boot application that interacts with the Coindesk API to manage currency data and provides various APIs for currency information. The project includes several advanced features such as request/response logging, Swagger UI, internationalization, design patterns, Docker support, error handling, and encryption.

## Features
1. **Print out the request and response body log of all APIs**: Logs all incoming requests and outgoing responses.
2. **Currency DB maintenance function**: Provides interactive APIs maintenance functions.
3. **Schedule synchronization of exchange rates**: Automatically update latest exchange rates into database.
4. **Implementation of more than 2 design patterns**: Uses Singleton and Builder design patterns.
5. **Able to run on Docker**: Includes a Dockerfile for containerization.
6. **Error handling**: Global exception handler to decorate all API responses.

## Running the Application

### Prerequisites
- Java 17
- Maven 3.8.5
- Docker (optional)

### Build and Run
1. Clone the repository:
    ```sh
    git clone <repository-url>
    ```
2. Build the project with Maven:
    ```sh
    mvn clean package
    ```
3. Run the application:
    ```sh
    java -jar target/banking-0.0.1-SNAPSHOT.jar
    ```

### Run with Docker
1. Build the Docker image:
    ```sh
    docker build -t banking . 
    ```
2. Run the Docker container:
    ```sh
    docker run -p 8080:8080 banking
    ```

## API Endpoints
- **Currency Management APIs**:
  - `GET /currency/all`: Get all currencies
  - `GET /currency/{code}`: Get a currency by code
  - `POST /currency/add`: add a currency
  - `DELETE /currency/{code}`: delete a currency by code
  - `PUT /currency/update`: Get all currencies
   Example of a Currency data JSON payload:

    ```json
    {
        "code": "USD",
        "name": "United States Dollar",
        "exchangeRate": 50000,
        "updateTime": "2024/06/01 12:00:00"
    }
    ```