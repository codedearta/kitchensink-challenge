1. initialise basic quarkus project
```shell script
mvn io.quarkus.platform:quarkus-maven-plugin:3.19.3:create \
    -DprojectGroupId=org.acme \
    -DprojectArtifactId=modern \
    -Dextensions='rest'
cd modern
```
make sure the project is in the ./modern folder.

add 'quarkus.http.port=8081' to the application.properties file to avoid port conflicts with the legacy app.

2.
Add application.properties to configure application settings 
Add JPA TestEntity, and test for H2 database
Add application.properties to configure application settings 

3. Replace H2 with oracle database (I had some issues with h2 not seeing the data in the database)
This involved adding testcontainers to the project and setting up the oracle container in the test.
I use ChatGPT for this task to guide me through hte changes: Here the Testfile that I would like to run against oracle with thestcontainers instead of H2: <the testclass>
- update the pom to add additional dependencies
- 

