# Lab09 - Spring Boot Web Application with Thymeleaf

A Spring Boot web application demonstrating MVC pattern, form validation, REST API, and Thymeleaf templating.

## Overview

This lab focuses on building web applications with Spring Boot:
- **Spring MVC** - Web controllers and request handling
- **Thymeleaf** - Server-side template engine
- **REST API** - RESTful web services with `@RestController`
- **Data JPA** - Database persistence with Spring Data JPA
- **Validation** - Form and request validation with Jakarta Validation

## Project Structure

```
src/main/java/com/example/demo/
├── DemoApplication.java        # Main Spring Boot application
├── Result.java                 # Response wrapper class
├── controllers/                # Web controllers
│   ├── UserController.java     # MVC controller for Thymeleaf views
│   └── UserRestController.java # REST API controller
├── domain/                     # Domain models
│   └── User.java
├── repository/                 # JPA repositories
│   └── UserRepository.java
└── service/                    # Service layer
    └── UserService.java

src/main/resources/
├── application.properties      # Application configuration
├── static/                     # Static resources
│   └── validationClass.css
└── templates/                  # Thymeleaf templates
    ├── add-user.html
    └── user-info.html
```

## Technologies

- Java 17
- Spring Boot 3.0.6
- Spring Data JPA
- Spring Web MVC
- Thymeleaf
- Spring Validation
- Lombok

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

## API Endpoints

### Web Interface
- `GET /adduser` - Display user form
- `POST /adduser` - Submit user form

### REST API
- `POST /rest/adduser` - Create a new user (JSON body)
- `DELETE /rest/deleteuser/{name}` - Delete user by name

## Key Concepts Demonstrated

1. **MVC Pattern** - Model-View-Controller architecture
2. **Thymeleaf Integration** - Server-side templating
3. **Form Validation** - Using `@Valid` and `BindingResult`
4. **REST Controllers** - Building RESTful APIs
5. **Response Entities** - HTTP response handling with status codes
6. **Service Layer** - Business logic separation
