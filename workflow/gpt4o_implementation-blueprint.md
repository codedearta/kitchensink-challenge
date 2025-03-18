# Blueprint for Migrating Project to Quarkus  
   
The following blueprint outlines the incremental migration of the project to Quarkus, step-by-step, ensuring strong testing, best practices, and logical progression. Each step is broken down into right-sized chunks, after iterative review. The prompts generated will guide an LLM to produce code iteratively, emphasizing test-driven development (TDD) and integration completeness.   
  
---  
   
## **Phase 1: Set Up Base Quarkus Project**  
   
### Step 1: Generate an Empty Quarkus Project    
Objective: Produce the foundational Quarkus project using Quarkus CLI or Maven.  
   
- Initialize a new Quarkus application with Maven or the Quarkus CLI:  
  ```bash  
  mvn io.quarkus.platform:quarkus-maven-plugin:create \  
    -DprojectGroupId=org.jboss.as.quickstarts.kitchensink \  
    -DprojectArtifactId=kitchensink-quarkus \  
    -DclassName="org.jboss.kitchensink.Application" \  
    -Dpath="/"  
  ```  
- Select extensions:   
  - JPA/Hibernate ORM (`quarkus-hibernate-orm`)  
  - RESTEasy Reactive (`quarkus-resteasy-reactive`)  
  - CDI (`quarkus-arc`)  
  - Bean Validation (`quarkus-hibernate-validator`)  
   
---  
   
## **Phase 2: Incremental Migration of Features**  
   
### Step 2: Migrate Entity Classes    
Objective: Start the migration by converting `Member.java` to a Quarkus-compatible JPA entity.  
   
#### Chunk 1: Create the `Member` Entity    
- Reuse the fields and constraints (like `@NotEmpty`, `@Email`) from the legacy `Member` model. Use proper annotations for Quarkus JPA integration.    
  - Add an integration test to validate that the entity persists correctly using Quarkus's in-memory database.  
   
#### Chunk 2: Add `import.sql` for Initial Data    
- Port the `import.sql` used in the legacy project. Ensure seed data (e.g., John Smith) is automatically loaded on application start due to JPA configuration.  
   
#### Chunk 3: Configure Persistence Layer    
- Create `application.properties` to configure the H2 database:  
  ```properties  
  quarkus.datasource.db-kind=h2  
  quarkus.datasource.jdbc.url=jdbc:h2:mem:kitchensink  
  quarkus.datasource.username=sa  
  quarkus.datasource.password=sa  
  quarkus.hibernate-orm.database.generation=drop-and-create  
  ```  
   
---  
   
### Step 3: Establish the Repository Layer    
Objective: Port `MemberRepository.java` to Quarkus.    
Quarkus recommends using Panache repositories.  
   
#### Chunk 1: Set Up `MemberRepository` with Panache    
- Use annotations like `@ApplicationScoped` and `extends PanacheRepository<Member>` to simplify repository definitions.  
- Add methods for `findById`, `findByEmail`, and `findAllOrderedByName`.  
   
#### Chunk 2: Write Unit Tests for Repository Layer    
- Add Quarkus test resources for H2 database testing.  
- Ensure repository methods behave as expected via test cases using `@QuarkusTest`.  
   
---  
   
### Step 4: Build the REST Layer    
Objective: Migrate the REST API in `MemberResourceRESTService.java`.  
   
#### Chunk 1: Implement Basic CRUD REST API    
- Migrate endpoints for listing all members and looking up a member by ID (`/members` and `/members/{id}`).  
   
#### Chunk 2: Add Validation and Exception Handling    
- Use `@Valid` and `@RestControllerAdvice` to enforce validation and handle errors seamlessly.  
   
#### Chunk 3: Write REST Tests    
- Create an automated test suite for REST endpoints using REST-assured and `@QuarkusTest`.  
   
---  
   
## **Phase 3: Migrate Business Logic (Service Layer)**  
   
### Step 5: Migrate the `MemberRegistration` Service    
Objective: Port the transaction-enabled service in `MemberRegistration.java`.  
   
#### Chunk 1: Set Up CDI-Based Service    
- Use `@ApplicationScoped` and inject `MemberRepository` for business logic.  
- Implement the logic to register a new `Member`.  
   
#### Chunk 2: Add Unit Tests for Service Logic    
- Write tests for the registration logic ensuring persistence works through the repository.  
   
---  
   
### Step 6: Migrate the Frontend (Controller Layer)    
Objective: Migrate the Faces Controller to REST-based or Angular-based interaction.  
   
#### Chunk 1: Refactor `MemberController` Logic    
- Rewrite controller logic as REST endpoints (replacing JSF interaction with Reactivity).  
   
#### Chunk 2: Validate Frontend Interactions    
- Write UI integration tests to ensure the REST endpoints are usable via a frontend.  
   
---  
   
## **Phase 4: Testing and Integration**  
   
### Step 7: Add Integration Testing    
Objective: Ensure all parts of the migrated code work together seamlessly.  
   
#### Chunk 1: Write End-to-End Tests for Features    
- Use Quarkus integration testing and REST-assured to simulate user interactions.  
   
#### Chunk 2: Introduce Test Coverage Metrics    
- Add `Jacoco` Maven plugin to generate reports on unit and integration test coverage.  
   
---  
   
## **Phase 5: Deployment and Optimization**  
   
### Step 8: Optimize Application for Production    
Objective: Ensure the migrated application is ready for production.  
   
#### Chunk 1: Package into a Native Build    
- Use Quarkus native compilation (`mvn package -Pnative`) for reduced memory consumption and faster startup.  
   
#### Chunk 2: Add Health Checks and Metrics    
- Use `quarkus-smallrye-health` for health endpoints and `quarkus-micrometer` for metrics.  
   
---  
   
## Final Prompts for Step-by-Step Implementation  
   
Below are the prompts for code-generation based on the blueprint. Each prompt builds incrementally and ensures completeness. Utilize the prompts to generate both migration and testing code iteratively:  
   
---  
   
### **Prompt 1: Create the Base Quarkus Project**  
   
```text  
Generate a new Quarkus Maven project. Use the groupId `org.jboss.as.quickstarts.kitchensink` and artifactId `kitchensink-quarkus`. Add these extensions: JPA (Hibernate ORM), RESTEasy Reactive, CDI, and Hibernate Validator. Set up `application.properties` for an H2 in-memory database with drop-and-create schema generation.  
```  
   
---  
   
### **Prompt 2: Migrate the `Member` Entity**  
   
```text  
Create a JPA entity class named `Member` with fields `id` (auto-generated), `name`, `email`, and `phoneNumber`. Apply Bean Validation annotations like `@NotEmpty`, `@Email`, and `@Size`. Ensure the table has a unique constraint on `email`. Provide basic getters and setters for the fields.   
Write a Quarkus test class to verify that the `Member` entity persists correctly and query results match expectations.  
```  
   
---  
   
### **Prompt 3: Add `MemberRepository` with Panache**  
   
```text  
Create a repository named `MemberRepository` using Quarkus Panache. Add CRUD methods for `findById`, `findByEmail`, and `findAllOrderedByName`. Implement unit tests to verify repository functionality against an H2 database using test fixtures.  
```  
   
---  
   
### **Prompt 4: Create Basic REST API for Members**  
   
```text  
Create a REST resource class named `MemberResource`. Implement endpoints for:    
1. Listing all members (`GET /members`).    
2. Fetching a member by ID (`GET /members/{id}`).    
Add unit tests using REST-assured to verify API behavior against test data loaded from `import.sql`.  
```  
   
---  
   
### **Prompt 5: Add Business Logic in `MemberRegistration`**  
   
```text  
Create an application-scoped service named `MemberRegistration`. Add a method to register a new member which checks for duplicate email addresses and persists valid members using `MemberRepository`. Write unit tests to verify service behavior under valid and invalid input conditions.  
```  
   
---  
   
### **Prompt 6: Implement End-to-End Integration**  
   
```text  
Create integration tests to simulate end-to-end application behavior. Verify the workflow where a new member is added via `MemberResource` and verified via database queries through `MemberRepository`.  
```  
   
---  
   
### **Prompt 7: Package for Native Deployment**  
   
```text  
Add instructions for Quarkus native compilation using GraalVM. Generate a native image for the `kitchensink-quarkus` application. Verify that the application runs correctly and integrates well with the REST endpoints and database.  
```   
  
By following this structure, you can ensure that every migration step integrates cleanly into the overall Quarkus architecture, while maintaining iterative test-driven development principles.