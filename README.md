# API Bookstore Framework

**API Bookstore Framework** is an automated testing framework for testing REST API of a Bookstore service.  
It’s built with **Java 17**, **RestAssured**, **TestNG**, **AssertJ**, and **Allure**.

---

## Key Features

- Full CRUD testing for Bookstore API
- Layered architecture with Clients, Services, and Models separation
- Allure integration for detailed reporting
- Fluent assertions with AssertJ
- Random data generation using Java Faker
- Easy scalability for new endpoints

---

## Tech Stack

| Component | Library / Version |
|------------|-------------------|
| Java | 17 |
| Test framework | TestNG 7.8.0 |
| HTTP client | RestAssured 4.4.0 |
| Reporting | Allure 2.27.0 |
| Assertions | AssertJ 3.27.6 |
| Utils | Guava 33.5.0, Apache Commons Lang 3.18.0 |
| Data generation | Java Faker 0.13 |
| JSON serialization | Jackson 2.15.2 |
| Lombok | 1.18.30 |

---

## Project Structure
```
api-bookstore-framework/
├── src/
│ ├── main/java/org/example/api/
│ │ ├── clients/ # REST clients (AuthorClient, BookClient)
│ │ ├── constants/ # Constants (AllureConstants, Endpoints)
│ │ ├── core/
│ │ │ ├── clients/ # REST base client (BaseClient)
│ │ │ ├── config/ # ConfigurationManager
│ │ │ ├── services/ # Base service layer
│ │ ├── 
│ │ ├── models/ # POJOs for requests/responses
│ │ ├── services/ # API business layer (AuthorService, BookService)
│ │ │
│ ├── test/java/org/example/
│ │ ├── tests/ # Test classes
│ │ │ ├── authors/
│ │ │ │ ├── happy_path/ # Author happy path test classes
│ │ │ │ ├── edge_cases/ # Author edge cases test classes
│ │ │ ├── books/
│ │ │ │ ├── happy_path/ # Book happy path test classes
│ │ │ │ ├── edge_cases/ # Book edge cases test classes
│ │ ├── assertions/ # Custom assertions
│ │ ├── utils/ # Helper utilities (RandomDataGenerator)
│
├── pom.xml
└── README.md
```

## How to Run

### 1. Clone the project
```
git clone https://github.com/veronikabarkovska/api-bookstore-framework.git
cd api-bookstore-framework
```
### 2. Install dependencies
```
mvn clean install
```
### 3. Run tests
```
mvn test
```
### 4. Generate Allure report
```
allure serve target/allure-results
```

### Core Classes
| Class                             | Description                                       |
| --------------------------------- | ------------------------------------------------- |
| `AuthorClient`                     | REST client for author endpoints                 |
| `BookClient`                       | REST client for book endpoints                   |
| `BaseClient`                       | Base class for shared REST client logic         |
| `BaseService`                      | Base class for parsing and wrapping API responses |
| `AuthorService`                    | Business-level operations for authors           |
| `BookService`                      | Business-level operations for books             |
| `ApiResponse<T>`                   | Generic response wrapper                          |
| `AuthorModel` / `BookModel`        | POJOs for API request/response                    |
| `ErrorResponse` / `EmptyResponse`  | Specialized response models                       |
| `ApiAssertions`                     | Custom assertions for API responses              |
| `RandomDataGenerator`               | Generates random test data for authors/books    |
| `ConfigurationManager`              | Reads configuration values (baseURL) |
| `AllureConstants`                   | Allure metadata (epics, owner)          |
| `Endpoints`                         | API endpoint paths (`/authors`, `/books`)       |




### Example Test
```
@Epic(BOOKS_API)
@Feature("Create Book")
@Owner(VERONIKA_BARKOVSKA)
public class CreateAndDeleteBookTest extends BaseBooksTest {

    @Test(description = "POST + DELETE book - should create and delete book successfully")
    public void shouldCreateAndDeleteBookSuccessfully() {
        BookModel newBook = RandomDataGenerator.generateRandomBook();
        ApiResponse<BookModel> createResponse = bookService.createBook(newBook);

        ApiAssertions.assertStatus(createResponse, 200);
        ApiAssertions.assertBodyMatches(createResponse, newBook);

        int createdId = createResponse.getBody().getId();

        ApiResponse<EmptyResponse> deleteResponse = bookService.deleteBook(createdId);
        ApiAssertions.assertStatus(deleteResponse, 200);
    }
}
```

### Design Principles

- SRP (Single Responsibility Principle): every class has one clear purpose
- DRY: no duplicated logic across tests and services
- KISS: simple and understandable design
- Reusable Components: clients, services, and utils can be easily reused
- Readable Tests: clear naming, structured checks, and rich Allure reports

| Principle / Concept                       | Description                                                                                         |
|-------------------------------------------| --------------------------------------------------------------------------------------------------- |
| **SRP (Single Responsibility Principle)** | Every class has one clear responsibility (e.g., client for REST calls, service for business logic). |
| **DRY (Don't Repeat Yourself)**           | Avoid duplicated logic across tests, services, and utilities.                                       |
| **KISS (Keep It Simple, Stupid)**         | Code is simple, readable, and easy to understand.                                                   |
| **Readable Tests**                        | Tests have clear naming, structured assertions, and descriptive Allure steps.                       |
| **Reusable Components**                   | Clients, services, assertions, and utilities can be reused across tests.                            |
| **Maintainability**                       | Easy to modify and extend when API changes.                                                         |



### Design Patterns
| Pattern                        | Usage in Framework                                                          |
| ------------------------------ |-----------------------------------------------------------------------------|
| **Builder**                    | Constructing request or response models (e.g., `AuthorModel.builder()`).    |
| **Facade**                     | Service layer acts as a facade over clients, simplifying test code.         |
| **DTO (Data Transfer Object)** | Models like `AuthorModel`, `BookModel` used for data transfer between layers. |
| **Decorator / Wrapper**        | `ApiResponse<T>` wraps API responses with status code and body.             |
| **Strategy**                   | Dynamic validation                              |


### Author

- Veronika Barkovska
- Senior QA Automation Engineer (Java / API Testing)
- Ukraine

### License

This project was created for educational and practical purposes.
It can be freely used and extended for other REST API testing projects.

