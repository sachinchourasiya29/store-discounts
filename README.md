# Store Discount Calculator

## Overview

This project is a Spring Boot application that calculates discounts for a retail store based on several rules. The discounts include employee discounts, affiliate discounts, long-term customer discounts, and flat discounts based on the total bill amount.

## Features

- Employee, affiliate, and customer discounts
- Flat discount for every $100 spent
- Percentage-based discounts do not apply to groceries
- Only one percentage-based discount applies per bill
- RESTful endpoint for discount calculation
- Basic authentication
- Input validation
- Logging and global exception handling

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven
- An IDE (IntelliJ IDEA, Eclipse, etc.)

### Running the Application

1. Clone the repository:

    ```bash
    git clone https://github.com/sachinchourasiya29/store-discounts.git
    cd store-discounts
    ```

2. Build the project:

    ```bash
    mvn clean install
    ```

3. Run the application:

    ```bash
    mvn spring-boot:run
    ```

4. The application will start on `http://localhost:8080`.

### Testing the Application

To run the tests, use:

```bash
mvn test
```

## Note
- Java 11 has been used since Installing java 17 on local machine required admin privileges