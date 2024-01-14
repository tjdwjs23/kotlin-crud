Kotlin Spring Boot CRUD 프로젝트
Overview
This project is a simple CRUD (Create, Read, Update, Delete) RESTful API developed using Kotlin and Spring Boot.

Tech Stack
Kotlin
Spring Boot
Spring Data JPA
ModelMapper
HikariCP (Connection Pooling)
Querydsl
Coroutines (Asynchronous Programming)
Mustache (Template Engine)
Spring Security
Log4jdbc-Log4j2 (JDBC Logging)
MariaDB (Database)
Project Structure
arduino
Copy code
src
├── main
│   ├── kotlin.demo.kotlin.crud
│   │   ├── config
│   │   │   └── AppConfig.kt
│   │   ├── controller
│   │   │   └── BoardController.kt
│   │   ├── dto
│   │   │   ├── BoardFormDto.kt
│   │   │   └── Board.kt
│   │   ├── repository
│   │   │   └── BoardRepository.kt
│   │   ├── service
│   │   │   └── BoardService.kt
│   │   └── CrudApplication.kt
│   └── resources
│       ├── application.yml
│       └── templates
│           └── (Mustache template files)
└── test
    └── kotlin.demo.kotlin.crud
        ├── controller
        │   └── BoardControllerTest.kt
        └── service
            └── BoardServiceTest.kt
Build and Run
To build the project, run the following command:

bash
Copy code
./gradlew build
After building, run the Spring Boot application with:

bash
Copy code
./gradlew bootRun
Once the application is running, you can access the API at http://localhost:8080.

API Endpoints
Add Post

POST /board
Request Body: BoardFormDto
json
Copy code
{
  "writer": "John Doe",
  "password": "password123",
  "title": "Sample Title",
  "content": "This is a sample content for testing."
}
Read Post

GET /board/{id}
Delete Post

DELETE /board/{id}
Update Post

PUT /board/{id}
Request Body: BoardFormDto
json
Copy code
{
  "writer": "Updated Writer",
  "password": "updatedPassword",
  "title": "Updated Title",
  "content": "This is an updated content."
}
List Posts

GET /board/list
Tests
Unit tests for the project are implemented in BoardControllerTest and BoardServiceTest. Run the tests with:

bash
Copy code
./gradlew test
Notes
This project is a simple example. In a real-world scenario, additional considerations such as security, exception handling, logging, and transaction management should be taken into account.
Database connection information and other environment settings are managed in the application.yml file.
License
This source code is distributed under the MIT License. See the LICENSE file for details.
