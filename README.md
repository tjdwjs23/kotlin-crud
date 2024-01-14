# Kotlin Spring Boot CRUD 프로젝트

## 개요
이 프로젝트는 Kotlin 및 Spring Boot를 사용하여 개발된 간단한 CRUD (Create, Read, Update, Delete) RESTful API입니다.

## 기술 스택
- Kotlin
- Spring Boot
- Spring Data JPA
- ModelMapper
- HikariCP (Connection Pooling)
- Querydsl
- Coroutines (Asynchronous Programming)
- Mustache (Template Engine)
- Spring Security
- Log4jdbc-Log4j2 (JDBC Logging)
- MariaDB (Database)

## 프로젝트 구조
```plaintext
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
```

빌드 및 실행
프로젝트를 빌드하려면 다음 명령어를 실행하세요:

bash
Copy code
./gradlew build
빌드가 완료되면 Spring Boot 애플리케이션을 다음과 같이 실행하세요:

bash
Copy code
./gradlew bootRun
애플리케이션이 실행되면 http://localhost:8080에서 API에 액세스할 수 있습니다.

API 엔드포인트
게시글 추가

POST /board
요청 바디: BoardFormDto
json
Copy code
{
  "writer": "John Doe",
  "password": "password123",
  "title": "Sample Title",
  "content": "This is a sample content for testing."
}
게시글 읽기

GET /board/{id}
게시글 삭제

DELETE /board/{id}
게시글 수정

PUT /board/{id}
요청 바디: BoardFormDto
json
Copy code
{
  "writer": "Updated Writer",
  "password": "updatedPassword",
  "title": "Updated Title",
  "content": "This is an updated content."
}
게시글 목록

GET /board/list
테스트
프로젝트의 유닛 테스트는 BoardControllerTest 및 BoardServiceTest에서 구현되었습니다. 다음 명령어로 테스트를 실행하세요:

bash
Copy code
./gradlew test
주의사항
이 프로젝트는 단순한 예제입니다. 실제 상황에서는 보안, 예외 처리, 로깅 및 트랜잭션 관리와 같은 추가 고려 사항이 필요합니다.
데이터베이스 연결 정보 및 기타 환경 설정은 application.yml 파일에서 관리됩니다.
라이선스
이 소스 코드는 MIT 라이선스에 따라 배포됩니다. 자세한 내용은 LICENSE 파일을 참조하세요.
