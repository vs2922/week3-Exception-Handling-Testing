# week3-Exception-Handling-Testing
Week 3: Exception Handling and Testing 

Day 11: Exception handling in Spring (ControllerAdvice). 
Day 12: Lombok and DTO mapping with ModelMapper/MapStruct.
Day 13: Unit testing with JUnit 5. 
Day 14: Mockito basics for mocking services. 
Day 15: Write unit tests for Product Catalog project



````markdown
# PharmaInventory: Product Catalog Service

A Spring Boot–based microservice powering the Product Catalog for the Pharmaceutical Distribution Center Inventory System.  
It supports robust CRUD operations on Medicines, Pallets, and Tasks; uses Spring Data JPA with PostgreSQL; leverages Lombok & MapStruct for DTO mapping; and includes centralized exception handling and a full JUnit 5 + Mockito test suite.



## Features

- **Domain Entities**: Medicine, Pallet, Task  
- **Persistence**: Spring Data JPA with PostgreSQL  
- **DTO Mapping**: Lombok for boilerplate reduction; MapStruct for entity↔DTO conversion  
- **Exception Handling**: Global handlers via `@ControllerAdvice`  
- **Build Tools**: Maven (with layered JARs)  
- **Testing**: JUnit 5 unit tests & Mockito for service/controller mocks  
- **Profiles**: `dev` vs. `prod` configuration isolation  

---

## Getting Started

### Prerequisites

- JDK 17  
- Maven 3.8+  
- PostgreSQL 12+  
- (Optional) Docker & Docker Compose  


Import into your IDE as a Maven project.

---

## Configuration

All settings live in `src/main/resources`.

* **Common**: `application.yml`
* **Dev**: `application-dev.yml`
* **Prod**: `application-prod.yml`

### Profiles

* **dev**:

  * `spring.jpa.hibernate.ddl-auto=update`
  * SQL logging enabled
  * Local Postgres URL in `application-dev.yml`
* **prod**:

  * `ddl-auto=validate`
  * Logging at WARN/ERROR
  * Datasource credentials pulled from environment variables

Activate a profile:

```bash
# Dev (default)
mvn spring-boot:run -Dspring-boot.run.profiles=dev

# Prod
mvn spring-boot:run -Dspring-boot.run.profiles=prod
```

---

## Building & Running

```bash
# Package the layered executable JAR
mvn clean package

# Run the JAR
java -jar target/inventory-system-0.2.0.jar --spring.profiles.active=dev
```

Or via Maven:

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

---

## API Endpoints

| Resource  | HTTP Method | Path                           | Description                              |
| --------- | ----------- | ------------------------------ | ---------------------------------------- |
| Medicines | GET         | `/api/medicines`               | List all medicines                       |
| Medicines | GET         | `/api/medicines/{id}`          | Get a single medicine by ID              |
| Medicines | POST        | `/api/medicines`               | Create new medicine                      |
| Medicines | PUT         | `/api/medicines/{id}`          | Update existing medicine                 |
| Medicines | DELETE      | `/api/medicines/{id}`          | Delete medicine                          |
| Pallets   | GET         | `/api/pallets`                 | List all pallets                         |
| Pallets   | GET         | `/api/pallets/{id}`            | Get a single pallet by ID                |
| Pallets   | GET         | `/api/pallets/priority/{prio}` | Filter pallets by priority (e.g. URGENT) |
| Pallets   | POST        | `/api/pallets`                 | Create new pallet                        |
| Pallets   | PUT         | `/api/pallets/{id}`            | Update existing pallet                   |
| Pallets   | DELETE      | `/api/pallets/{id}`            | Delete pallet                            |
| Tasks     | GET         | `/api/tasks`                   | List all tasks                           |
| Tasks     | GET         | `/api/tasks/{id}`              | Get a single task by ID                  |
| Tasks     | POST        | `/api/tasks`                   | Create new task                          |
| Tasks     | PUT         | `/api/tasks/{id}`              | Update existing task                     |
| Tasks     | PUT         | `/api/tasks/{id}/complete`     | Mark task as completed                   |
| Tasks     | DELETE      | `/api/tasks/{id}`              | Delete task                              |

---

## Testing

Run all unit and integration tests:

```bash
mvn test
```

* **Service Layer**: JUnit 5 + Mockito mocks
* **Controller Layer**: Spring’s MockMVC for HTTP contract validation

---

## Project Structure

```
├── pom.xml
└── src
    ├── main
    │   ├── java
    │   │   └── com.pharmainventory.inventory
    │   │       ├── InventorySystemApplication.java
    │   │       ├── model/            # JPA entities
    │   │       ├── dto/              # Lombok DTOs
    │   │       ├── mapper/           # MapStruct interfaces
    │   │       ├── repository/       # Spring Data JPA repos
    │   │       ├── service/          # Business logic
    │   │       ├── controller/       # REST endpoints
    │   │       └── exception/        # ControllersAdvice & custom exceptions
    │   └── resources
    │       ├── application.yml
    │       ├── application-dev.yml
    │       └── application-prod.yml
    └── test
        └── java
            └── com.pharmainventory.inventory
                ├── service/         # Unit tests
                └── controller/      # MockMVC tests
```

---

## Contributing

1. Fork the repo
2. Create a feature branch (`git checkout -b feature/foo`)
3. Commit your changes (`git commit -am 'Add feature'`)
4. Push (`git push origin feature/foo`)
5. Open a Pull Request

---

## License

This project is licensed under the MIT License. See [LICENSE](LICENSE) for details.

---



