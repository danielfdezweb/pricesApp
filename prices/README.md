# Price

## Description
A Spring Boot application following Hexagonal Architecture to retrieve product prices by date, brand, and product ID.

## Tech Stack
- Java 17
- Spring Boot
- H2 Database
- JPA
- JUnit 5

## How to Run
```bash
./mvnw spring-boot:run
```

## API Usage
```http
GET /api/prices?productId=35455&brandId=1&date=2020-06-14T10:00:00
```

## Tests
Run all unit and integration tests with:
```bash
./mvnw test
```
