# Lab07 - Spring Boot Dependency Injection

A Spring Boot demonstration project showcasing dependency injection, component scanning, and the layered architecture pattern.

## Overview

This lab focuses on core Spring Framework concepts including:
- **Dependency Injection** - Using `@Autowired` for constructor and field injection
- **Component Scanning** - Using `@Component`, `@Service`, `@Repository` annotations
- **Configuration** - Using `@Configuration` and `@Bean` annotations
- **DAO Pattern** - Data Access Object implementation with JDBC
- **Logging** - Integrated logging with Log4j2

## Project Structure

```
src/main/java/com/example/demo/
├── AppRunner.java              # CommandLineRunner implementation
├── DemoApplication.java        # Main Spring Boot application
├── components/                 # Spring components
│   ├── Car.java
│   ├── Engine.java
│   ├── Metrics.java
│   ├── Oil.java
│   └── Transmission.java
├── config/                     # Configuration classes
│   └── AppConfig.java
├── dao/                        # Data Access Objects
│   ├── CarDao.java
│   └── CarDaoImpl.java
├── domain/                     # Domain models
│   ├── CarOwner.java
│   └── PureCar.java
├── exceptions/                 # Custom exceptions
│   └── EngineNotStartetException.java
├── repository/                 # Repository interfaces
│   ├── CarOwnerRepository.java
│   └── PureCarRepository.java
└── services/                   # Service layer
    ├── CarOwnerService.java
    └── PureCarService.java
```

## Technologies

- Java 17
- Spring Boot 3.0.4
- Spring JDBC
- Spring AOP
- Lombok
- Log4j2

## Building and Running

### Prerequisites
- Java 17 or higher
- Maven 3.6+

### Build
```bash
./mvnw clean install
```

### Run
```bash
./mvnw spring-boot:run
```

## Key Concepts Demonstrated

1. **Constructor Injection** - Recommended way of dependency injection
2. **Field Injection** - Using `@Autowired` on fields
3. **Component Scanning** - Auto-detection of Spring beans
4. **Layered Architecture** - Separation of concerns (Controller/Service/Repository/DAO)
5. **Logging** - Application logging using SLF4J with Log4j2
