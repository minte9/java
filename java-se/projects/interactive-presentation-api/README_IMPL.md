# Interactive Presentation Backend - Implementation (Level 1)

## Project structure

    interactive-presentation/
    ├─ pom.xml
    ├─ src/
    │  ├─ main/
    │  │  ├─ java/
    │  │  │  └─ com/example/presentation/
    │  │  │     ├─ PresentationApplication.java
    │  │  │     ├─ controller/
    │  │  │     │  └─ PresentationController.java
    │  │  │     ├─ repository/
    │  │  │     │  └─ PresentationRepository.java
    │  │  │     ├─ service/
    │  │  │     │  └─ PresentationService.java
    │  │  │     ├─ model/
    │  │  │     │  ├─ Presentation.java
    │  │  │     │  ├─ Poll.java
    │  │  │     │  └─ PollOption.java
    │  │  │     ├─ dto/
    │  │  │     │  ├─ CreatePresentationRequest.java
    │  │  │     │  ├─ CreatePresentationResponse.java
    │  │  │     │  └─ PollResponse.java
    │  │  │     └─ exception/
    │  │  │        └─ GlobalExceptionHandler.java
    │  │  └─ resources/
    │  │     └─ application.properties
    │  └─ test/
    │     └─ java/
    │        └─ com/example/presentation/
    │           └─ PresentationApplicationTests.java


## Steps list

    1. Create a Spring Boot Maven project using Java 17
    2. Add the required dependencies: Spring Web, Validation, and test dependencies
    3. Add the /ping endpoint returning HTTP 200
    4. Define the domain models: Presentation, Poll, and PollOption
    5. Define DTOs for request and response bodies
    6. Implement in-memory storage for presentations
    7. Implement validation for POST /presentations
    8. Implement POST /presentations returning 201 and presentation_id
    9. Implement PUT /presentations/{presentation_id}/polls/current
    10. Implement GET /presentations/{presentation_id}/polls/current
    11. Run the Cypress backend tests
    12. Add error handling for 400 and 404


## 1. Create Spring Boot Maven project - port 9090

Create pom.xml with Spring Boot starter web dependency.

~~~xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
~~~

Create the main app file:  
- src/main/../presentation/PresentationApplication.java

Change port to 9090 (if 8080 already in use):  
- src/main/resources/application.properties

Run it:

~~~sh
mvn spring:boot:run
~~~


## 2. Add Validation dependency

Validation will be used later for the 400 responses.

~~~xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
~~~
~~~sh
mvn clean install
mvn spring-boot:run
~~~


## 3. Implement /ping endpoing

Create a controller: PingController  

The endpoint only checks the status 200, so we can return empty (Void).  

~~~java
@RestController
public class PingController {
    
    @GetMapping("/ping")
    public ResponseEntity<Void> ping() {
        return ResponseEntity.ok().build();
    }
}
~~~

Run and verify:

~~~sh
mvn spring-boot:run

curl -i http://localhost:9090/ping
# HTTP/1.1 200 
# Content-Length: 0
~~~


## 4. Define domain models

- PollOption
- Poll
- Presentation

We'll mirror the OpenAPI spec and keep things simple (in-memory, no DB).  

~~~java
public class PollOption {
    
    @NotBlank
    private String key;

    @NotBlank
    private String value;

    public PollOption() {}

    // setters/getters
}
~~~
~~~java
public class Poll {

    private String poll_id;

    @NotBlank
    private String question;

    @NotEmpty
    @Valid
    private List<PollOption> options;

    public Poll() {
        this.poll_id = UUID.randomUUID().toString();
    }

    // setters/getters
}
~~~
~~~java
public class Presentation {
    
    private int current_poll_index = -1;  // important for the "next poll" logic

    @NotEmpty
    @Valid
    private List<Poll>polls;

    public Presentation() {}

    // setters/getters
}
~~~


## 5. Define DTOs

Define DTOs for request and response bodies.  

src/main/../presentation/dto/CreatePresentationRequest.java

~~~java
public class CreatePresentationRequest {
    
    @NotEmpty
    @Valid
    private List<Poll> polls;

    public CreatePresentationRequest() {}

    // setters/getters
}
~~~

src/main/../presentation/dto/CreatePresentationResponse.java

~~~java
public class CreatePresentationResponse {
    
    private String presentation_id;

    public CreatePresentationResponse(String presentation_id) {
        this.presentation_id = presentation_id;
    }

    // setters/getters
}
~~~

src/main/../presentation/dto/PollResponse.java

~~~java
public class PollResponse {
    
    private String poll_id;
    private String question;
    private List<PollOption> options;

    public PollResponse(String poll_id, String question, List<PollOption> options) {
        this.poll_id = poll_id;
        this.question = question;
        this.options = options;
    }

    // setters/getters
}
~~~


## 6. In-memory storage and service

Implement in-memory storage for presentations.

src/main/../presentation/repository/PresentationRepository.java

~~~java
@Repository
public class PresentationRepository {
    
     /** In-memory storage for presentations.
     * 
     * ConcurrentHashMap insteed of HashMap because:
     *   - It is thread-safe
     *   - Multiple HTTP request can access/modify the map concurrently
     *   - Avoids race conditions without requiring explicit synchronization
     */
    private final Map<String, Presentation> presentations = new ConcurrentHashMap<>();

    public String save(Presentation presentation) {
        String presentationId = UUID.randomUUID().toString();
        presentations.put(presentationId, presentation);
        return presentationId;
    }

    /**
     * Returns a Presentation wrapped in Optional.
     * 
     * Optional:
     *   - The value may or many not be present
     *   - Avoids returning null and reduse risk of NullPointerException 
     */
    public Optional<Presentation> findById(String presentationId) {
        return Optional.ofNullable(presentations.get(presentationId));
    }
}
~~~

src/main/../presentation/service/PresentationService.java

~~~java
@Service
public class PresentationService {
    
    private final PresentationRepository repository;

    public PresentationService(PresentationRepository repository) {
        this.repository = repository;
    }

    public String save(Presentation presentation) {
        return repository.save(presentation);
    }

    public Optional<Presentation> findById(String presentationId) {
        return repository.findById(presentationId);
    }
}
~~~


## 7. Implement validation for POST /presentations

We already added validation annotations in the DTO/model classes.  
Now we need to make Spring actually use them.  

~~~java
public class CreatePresentationRequest {  // dto

    @NotEmpty(message = "Presentation must contain at least one poll")
    @Valid
    private List<Poll> polls;

    ...
~~~
~~~java
public class Poll {  // model

    private String poll_id;

    @NotBlank(message = "Question is required")
    private String question;

    @NotEmpty(message = "Poll must contain at least one options")
    @Valid
    private List<PollOption> options;

    ...
~~~
~~~java
public class PollOption {  // model
    
    @NotBlank(message = "Option key is required")
    private String key;

    @NotBlank(message = "Option value is required")
    private String value;

    ...
~~~

Later, the controller must use Valid:

~~~java
@PostMapping("/presentations")
public ResponseEntity<CreatePresentationResponse> createPresentation(
        @Valid @RequestBody CreatePresentationRequest request
) {
    // implementation comes next
}
~~~


## 8. Implement POST /presentations 

Implement POST /presentations returning 201 and presentation_id.  

src/main/../presentation/controller/PresentationController.java

~~~
@RestController
@RequestMapping("/presentations")
public class PresentationController {
    
    private final PresentationService presentationService;

    public PresentationController(PresentationService presentationService) {
        this.presentationService = presentationService;
    }

    @PostMapping
    public ResponseEntity<CreatePresentationResponse> createPresentation(
        @Valid @RequestBody CreatePresentationRequest request
    ) {
        Presentation presentation = new Presentation();
        presentation.setPolls(request.getPolls());

        String presentationId = presentationService.save(presentation);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new CreatePresentationResponse(presentationId));
    }
}
~~~

Now test:

~~~sh
curl -i -X POST http://localhost:9090/presentations \
  -H "Content-Type: application/json" \
  -d '{}'

# HTTP/1.1 400 
# Content-Type: application/json
# Transfer-Encoding: chunked
# Date: Sat, 02 May 2026 10:52:05 GMT
# Connection: close
~~~

Test the POST:

~~~sh
curl -i -X POST http://localhost:9090/presentations \
  -H "Content-Type: application/json" \
  -d '{
    "polls": [
      {
        "question": "What is your favorite pet?",
        "options": [
          {"key": "A", "value": "Dog"},
          {"key": "B", "value": "Cat"}
        ]
      }
    ]
  }'

# HTTP/1.1 201 
# Content-Type: application/json
# Transfer-Encoding: chunked
# Date: Sat, 02 May 2026 10:54:17 GMT

# {"presentation_id":"c113e728-03b7-42c7-8961-acba7b286fc6"}
~~~


## 9. Implement PUT /presentations

Implement PUT /presentations/{presentation_id}/polls/current.  

~~~java
@PutMapping("/{presentationId}/polls/current")
public ResponseEntity<PollResponse> showNextPoll(
    @PathVariable("presentationId") String presentationId
) {
    Presentation presentation = presentationService.findById(presentationId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    int nextPollIndex = presentation.getCurrent_poll_index() + 1;

    if (nextPollIndex >= presentation.getPolls().size()) {
        throw new ResponseStatusException(HttpStatus.CONFLICT);
    }

    presentation.setCurrent_poll_index(nextPollIndex);

    Poll currentPoll = presentation.getPolls().get(nextPollIndex);

    return ResponseEntity.ok(
            new PollResponse(
                currentPoll.getPoll_id(),
                currentPoll.getQuestion(),
                currentPoll.getOptions()
            )
    );
}
~~~

Test it manually:

~~~sh
curl -i -X PUT http://localhost:9090/presentations/2da6bb49-4062-4075-b262-aa5e9cfd5c26/polls/current

# HTTP/1.1 200 
# Content-Type: application/json
# Transfer-Encoding: chunked
# Date: Sat, 02 May 2026 11:19:54 GMT

# {"poll_id":"1ebabe34-bdff-40eb-9c8c-35879071373e",
#  "question":"What is your favorite pet?",
#  "options":[{"key":"A","value":"Dog"},{"key":"B","value":"Cat"}]}
~~~


## 10. Implement GET /presentations

Implement GET /presentations/{presentation_id}/polls/current.   
This endpoint returns the currently active poll (without advancing).  

~~~java
@GetMapping("/{presentationId}/polls/current")
public ResponseEntity<PollResponse> getCurrentPoll(
    @PathVariable("presentationId") String presentationId
) {
    Presentation presentation = presentationService.findById(presentationId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    int currentIndex = presentation.getCurrent_poll_index();

    if (currentIndex < 0) {
        throw new ResponseStatusException(HttpStatus.CONFLICT);
    }

    Poll currentPoll = presentation.getPolls().get(currentIndex);

    return ResponseEntity.ok(
            new PollResponse(
                currentPoll.getPoll_id(),
                currentPoll.getQuestion(),
                currentPoll.getOptions()
            )
    );
}
~~~

Test it manually (after spring-boot restart)

a) POST a presentation:

~~~sh
curl -s -X POST http://localhost:9090/presentations \
  -H "Content-Type: application/json" \
  -d '{
    "polls": [
      {
        "question": "Favorite pet?",
        "options": [
          {"key": "A", "value": "Dog"},
          {"key": "B", "value": "Cat"}
        ]
      },
      {
        "question": "Best country?",
        "options": [
          {"key": "A", "value": "Argentina"},
          {"key": "B", "value": "Austria"}
        ]
      }
    ]
  }'
# {"presentation_id":"c58f5c32-2777-4522-9750-414246309f20"}
~~~

b) GET current poll (before any PUT)

~~~sh
curl -i http://localhost:9090/presentations/c58f5c32-2777-4522-9750-414246309f20/polls/current

# HTTP/1.1 409 
# {"timestamp":"2026-05-02T11:42:49.938Z","status":409,
#  "error":"Conflict","path":"/presentations/c58f5c32-2777-4522-9750-414246309f20/polls/current"
~~~

c) PUT move to first poll

~~~sh
curl -i -X PUT http://localhost:9090/presentations/c58f5c32-2777-4522-9750-414246309f20/polls/current

# HTTP/1.1 200 
# {"poll_id":"77b63ec6-3485-4f9a-ade1-c7b00418f646",
#  "question":"Favorite pet?",
#  "options":[{"key":"A","value":"Dog"},{"key":"B","value":"Cat"}]}
~~~

d) GET current poll

Should return the same pull as above.

~~~sh
curl -i http://localhost:9090/presentations/c58f5c32-2777-4522-9750-414246309f20/polls/

# HTTP/1.1 200 
# {"poll_id":"77b63ec6-3485-4f9a-ade1-c7b00418f646",
#  "question":"Favorite pet?",
#   "options":[{"key":"A","value":"Dog"},{"key":"B","value":"Cat"}]}
~~~

e) PUT move to next poll

~~~sh
curl -i -X PUT http://localhost:9090/presentations/c58f5c32-2777-4522-9750-414246309f20/polls/current

# HTTP/1.1 200 
# {"poll_id":"f0d07cc5-25a6-4d1f-b919-da9f12a35022",
#  "question":"Best country?",
#   "options":[{"key":"A","value":"Argentina"},{"key":"B","value":"Austria"}]}
~~~

f) PUT move beyound last poll

No more polls expected (conflict response).

~~~sh
curl -i -X PUT http://localhost:9090/presentations/c58f5c32-2777-4522-9750-414246309f20/polls/current

# HTTP/1.1 409
# {"timestamp":"2026-05-02T11:49:39.642Z","status":409,
#  "error":"Conflict","path":"/presentations/c58f5c32-2777-4522-9750-414246309f20/polls/current
~~~


## 11. Run the Cypress backend tests

~~~sh
npm install
# added 176 packages, and audited 177 packages in 16s

npx cypress run --env apiUrl=http://localhost:9090

# Running:  backend.cy.js
# Backend Test Spec
#     ✓ should call ping (173ms)
#     ✓ creating a presentation with invalid data should result with 400 status code (181ms)
#     ✓ reading a presentation using an unknown presentation id should result with 404 status code (45ms)
#     ✓ creating a presentation, and showing a poll (132ms)

# 4 passing (554 ms)
~~~

## 12. Add error handling

Add error handling for 400 and 404.  

Create:

src/main/../presentation/exception/GlobalExceptionHandler.java

And Spring Boot integration tests with MockMvc.

src/test/../presentation/PresentationControllerTest.java

And run:

~~~sh
mvn test
~~~


## 13. Final test

Rerun:

~~~sh
npx cypress run --env apiUrl=http://localhost:9090

# All specs passed!
~~~


## 14. Refactoring

The business logic happens in the controller layer, domain models are returned directly in response dto, and that there are no custom exceptions.  
The controller does the poll index calculation, bounds checking, state mutation, and domain-to-response mapping.  

~~~java
@RestController
@RequestMapping("/presentations")
public class PresentationController {
    
    private final PresentationService presentationService;

    public PresentationController(PresentationService presentationService) {
        this.presentationService = presentationService;
    }

    @PostMapping
    public ResponseEntity<CreatePresentationResponse> createPresentation(
        @Valid @RequestBody CreatePresentationRequest request
    ) {
        Presentation presentation = new Presentation();
        presentation.setPolls(request.getPolls());

        String presentationId = presentationService.save(presentation);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new CreatePresentationResponse(presentationId));
    }

    @PutMapping("/{presentationId}/polls/current")
    public ResponseEntity<PollResponse> showNextPoll(
        @PathVariable("presentationId") String presentationId
    ) {

        // Business-logic in controller & NO custom exceptions (not good)
        Presentation presentation = presentationService.findById(presentationId)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        int nextPollIndex = presentation.getCurrent_poll_index() + 1;
        if (nextPollIndex >= presentation.getPolls().size()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        presentation.setCurrent_poll_index(nextPollIndex);
        Poll currentPoll = presentation.getPolls().get(nextPollIndex);

        return ResponseEntity.ok(
                new PollResponse(
                    currentPoll.getPoll_id(),
                    currentPoll.getQuestion(),
                    currentPoll.getOptions()
                )
        );
    }

    @GetMapping("/{presentationId}/polls/current")
    public ResponseEntity<PollResponse> getCurrentPoll(
        @PathVariable("presentationId") String presentationId
    ) {

        // Business-logic in controller & NO custom exceptions (not good)
        Presentation presentation = presentationService.findById(presentationId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        int currentIndex = presentation.getCurrent_poll_index();

        if (currentIndex < 0) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        Poll currentPoll = presentation.getPolls().get(currentIndex);

        return ResponseEntity.ok(
                new PollResponse(
                    currentPoll.getPoll_id(),
                    currentPoll.getQuestion(),
                    currentPoll.getOptions()
                )
        );
    }
}
~~~

We moved all of that out of the controller.  
Also, we replace ResponseStatusException with custom exceptions.  

~~~java
@RestController
@RequestMapping("/presentations")
public class PresentationController {
    
    private final PresentationService presentationService;

    public PresentationController(PresentationService presentationService) {
        this.presentationService = presentationService;
    }

    @PostMapping
    public ResponseEntity<CreatePresentationResponse> createPresentation(
        @Valid @RequestBody CreatePresentationRequest request
    ) {
        Presentation presentation = new Presentation();
        presentation.setPolls(request.getPolls());

        String presentationId = presentationService.save(presentation);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new CreatePresentationResponse(presentationId));
    }

    @PutMapping("/{presentationId}/polls/current")
    public ResponseEntity<PollResponse> showNextPoll(
        @PathVariable("presentationId") String presentationId
    ) {
        // Better (clean code)
        Poll currentPoll = presentationService.showNextPoll(presentationId);
        return ResponseEntity.ok(
                new PollResponse(
                    currentPoll.getPoll_id(),
                    currentPoll.getQuestion(),
                    currentPoll.getOptions()
                )
        );
    }

    @GetMapping("/{presentationId}/polls/current")
    public ResponseEntity<PollResponse> getCurrentPoll(
        @PathVariable("presentationId") String presentationId
    ) {
        // Better (clean code)
        Poll currentPoll = presentationService.getCurrentPoll(presentationId);
        return ResponseEntity.ok(
                new PollResponse(
                    currentPoll.getPoll_id(),
                    currentPoll.getQuestion(),
                    currentPoll.getOptions()
                )
        );
    }
}
~~~
~~~java
@Service
public class PresentationService {
    
    private final PresentationRepository repository;

    public PresentationService(PresentationRepository repository) {
        this.repository = repository;
    }

    public String save(Presentation presentation) {
        return repository.save(presentation);
    }

    public Optional<Presentation> findById(String presentationId) {
        return repository.findById(presentationId);
    }

    // moved from Controller (better)
    public Poll showNextPoll(String presentationId) {
        Presentation presentation = repository.findById(presentationId)
            .orElseThrow(PresentationNotFoundException::new);

        int nextPollIndex = presentation.getCurrent_poll_index() + 1;
        if (nextPollIndex >= presentation.getPolls().size()) {
            throw new NoMorePollException();
        }

        presentation.setCurrent_poll_index(nextPollIndex);  // update current poll

        Poll nextPoll = presentation.getPolls().get(nextPollIndex);
        return nextPoll;
    }

    // moved from Controller (better)
    public Poll getCurrentPoll(String presentationId) {
        Presentation presentation = repository.findById(presentationId)
            .orElseThrow(PresentationNotFoundException::new);

        int currentIndex = presentation.getCurrent_poll_index();

        if (currentIndex < 0) {
            throw new NoCurrentPollException();
        }

        Poll currentPoll = presentation.getPolls().get(currentIndex);
        return currentPoll;
    }
}
~~~
~~~java
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(PresentationNotFoundException.class)
    public ResponseEntity<Void> handlePresentationNotFound() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ExceptionHandler(NoCurrentPollException.class)
    public ResponseEntity<Void> handleNoCurrentPoll() {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @ExceptionHandler(NoMorePollException.class)
    public ResponseEntity<Void> handNoMorePolls() {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
}
~~~
~~~java
package com.minte9.presentation.exception;

public class PresentationNotFoundException extends RuntimeException {
    
}
~~~