Draft a detailed, step-by-step blueprint for migrating the project summarized in the <repomix-output.md> to quarkus. Set up an empty quarkus project in the subfolder ./modern and add iteratively add from the legacy code step-by-step. Then, once you have a solid plan, break it down into small, iterative chunks that build on each other. Look at these chunks and then go another round to break it into small steps. Review the results and make sure that the steps are small enough to be implemented safely with strong testing, but big enough to move the project forward. Add E2E testing from the start to ensure feature parrity of the lagacy app with the modernised app. Iterate until you feel that the steps are right sized for this project.

use the following command to initialize a new quarkus project:

```shell script
mvn io.quarkus.platform:quarkus-maven-plugin:3.19.3:create \
    -DprojectGroupId=org.acme \
    -DprojectArtifactId=modern \
    -Dextensions='rest'
cd modern
```
make sure the project is in the ./modern folder.

add 'quarkus.http.port=8081' to the application.properties file to avoid port conflicts with the legacy app.





From here you should have the foundation to provide a series of prompts for a code-generation LLM that will implement each step in a test-driven manner. Prioritize best practices, incremental progress, and early testing, ensuring no big jumps in complexity at any stage. Make sure that each prompt builds on the previous prompts, and ends with wiring things together. There should be no hanging or orphaned code that isn't integrated into a previous step.

Make sure and separate each prompt section. Use markdown. Each prompt should be tagged as text using code tags. The goal is to output prompts, but context, etc is important as well.