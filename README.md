<hr><h1>Kotlin Spring Boot CRUD 프로젝트</h1><h2>Overview</h2><p>This project is a simple CRUD (Create, Read, Update, Delete) RESTful API developed using Kotlin and Spring Boot.</p><h2>Tech Stack</h2><ul><li>Kotlin</li><li>Spring Boot</li><li>Spring Data JPA</li><li>ModelMapper</li><li>HikariCP (Connection Pooling)</li><li>Querydsl</li><li>Coroutines (Asynchronous Programming)</li><li>Mustache (Template Engine)</li><li>Spring Security</li><li>Log4jdbc-Log4j2 (JDBC Logging)</li><li>MariaDB (Database)</li></ul><h2>Project Structure</h2><pre><div class="bg-black rounded-md"><div class="flex items-center relative text-gray-200 bg-gray-800 dark:bg-token-surface-primary px-4 py-2 text-xs font-sans justify-between rounded-t-md"><span class="" data-state="closed"></span></div><div class="p-4 overflow-y-auto"><code class="!whitespace-pre hljs language-arduino">src
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
│           └── (Mustache <span class="hljs-keyword">template</span> files)
└── test
    └── kotlin.demo.kotlin.crud
        ├── controller
        │   └── BoardControllerTest.kt
        └── service
            └── BoardServiceTest.kt
</code></div></div></pre><h2>Build and Run</h2><p>To build the project, run the following command:</p><pre><div class="bg-black rounded-md"><div class="flex items-center relative text-gray-200 bg-gray-800 dark:bg-token-surface-primary px-4 py-2 text-xs font-sans justify-between rounded-t-md"><span>bash</span><span class="" data-state="closed"></span></div><div class="p-4 overflow-y-auto"><code class="!whitespace-pre hljs language-bash">./gradlew build
</code></div></div></pre><p>After building, run the Spring Boot application with:</p><pre><div class="bg-black rounded-md"><div class="flex items-center relative text-gray-200 bg-gray-800 dark:bg-token-surface-primary px-4 py-2 text-xs font-sans justify-between rounded-t-md"><span>bash</span><span class="" data-state="closed"></span></div><div class="p-4 overflow-y-auto"><code class="!whitespace-pre hljs language-bash">./gradlew bootRun
</code></div></div></pre><p>Once the application is running, you can access the API at <a target="_new" href="http://localhost:8080">http://localhost:8080</a>.</p><h2>API Endpoints</h2><ul><li><p><strong>Add Post</strong></p><ul><li><code>POST /board</code></li><li>Request Body: BoardFormDto</li></ul><pre><div class="bg-black rounded-md"><div class="flex items-center relative text-gray-200 bg-gray-800 dark:bg-token-surface-primary px-4 py-2 text-xs font-sans justify-between rounded-t-md"><span>json</span><span class="" data-state="closed"></span></div><div class="p-4 overflow-y-auto"><code class="!whitespace-pre hljs language-json"><span class="hljs-punctuation">{</span>
  <span class="hljs-attr">"writer"</span><span class="hljs-punctuation">:</span> <span class="hljs-string">"John Doe"</span><span class="hljs-punctuation">,</span>
  <span class="hljs-attr">"password"</span><span class="hljs-punctuation">:</span> <span class="hljs-string">"password123"</span><span class="hljs-punctuation">,</span>
  <span class="hljs-attr">"title"</span><span class="hljs-punctuation">:</span> <span class="hljs-string">"Sample Title"</span><span class="hljs-punctuation">,</span>
  <span class="hljs-attr">"content"</span><span class="hljs-punctuation">:</span> <span class="hljs-string">"This is a sample content for testing."</span>
<span class="hljs-punctuation">}</span>
</code></div></div></pre></li><li><p><strong>Read Post</strong></p><ul><li><code>GET /board/{id}</code></li></ul></li><li><p><strong>Delete Post</strong></p><ul><li><code>DELETE /board/{id}</code></li></ul></li><li><p><strong>Update Post</strong></p><ul><li><code>PUT /board/{id}</code></li><li>Request Body: BoardFormDto</li></ul><pre><div class="bg-black rounded-md"><div class="flex items-center relative text-gray-200 bg-gray-800 dark:bg-token-surface-primary px-4 py-2 text-xs font-sans justify-between rounded-t-md"><span>json</span><span class="" data-state="closed"></span></div><div class="p-4 overflow-y-auto"><code class="!whitespace-pre hljs language-json"><span class="hljs-punctuation">{</span>
  <span class="hljs-attr">"writer"</span><span class="hljs-punctuation">:</span> <span class="hljs-string">"Updated Writer"</span><span class="hljs-punctuation">,</span>
  <span class="hljs-attr">"password"</span><span class="hljs-punctuation">:</span> <span class="hljs-string">"updatedPassword"</span><span class="hljs-punctuation">,</span>
  <span class="hljs-attr">"title"</span><span class="hljs-punctuation">:</span> <span class="hljs-string">"Updated Title"</span><span class="hljs-punctuation">,</span>
  <span class="hljs-attr">"content"</span><span class="hljs-punctuation">:</span> <span class="hljs-string">"This is an updated content."</span>
<span class="hljs-punctuation">}</span>
</code></div></div></pre></li><li><p><strong>List Posts</strong></p><ul><li><code>GET /board/list</code></li></ul></li></ul><h2>Tests</h2><p>Unit tests for the project are implemented in <code>BoardControllerTest</code> and <code>BoardServiceTest</code>. Run the tests with:</p><pre><div class="bg-black rounded-md"><div class="flex items-center relative text-gray-200 bg-gray-800 dark:bg-token-surface-primary px-4 py-2 text-xs font-sans justify-between rounded-t-md"><span>bash</span><span class="" data-state="closed"></span></div><div class="p-4 overflow-y-auto"><code class="!whitespace-pre hljs language-bash">./gradlew <span class="hljs-built_in">test</span>
</code></div></div></pre><h2>Notes</h2><ul><li>This project is a simple example. In a real-world scenario, additional considerations such as security, exception handling, logging, and transaction management should be taken into account.</li><li>Database connection information and other environment settings are managed in the <code>application.yml</code> file.</li></ul><h2>License</h2><p>This source code is distributed under the MIT License. See the <a target="_new">LICENSE</a> file for details.</p><hr></div>
