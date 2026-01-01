# Java Maven

### 📌 What Maven is?
Maven is a build and `dependency management` tool for Java projects.

Maven manages `three` things:
- Project structure
- Dependencies
- Build lifecycle

### 📌 Standard Maven Structure
Maven expect this structure - don't fight it.

    project/
    ├─ pom.xml
    └─ src/
        ├─ main/
        │   ├─ java/
        │   └─ resources/
        └─ test/
            ├─ java/
            └─ resources/

### 📌 Pom.xml
Think to pom.xml as a project `contract`.
- Project metadata
- Dependency list
- Build instructions


### 📌 Dependencies
You declare `what you want`, Maven gets it.

    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter</artifactId>
        <version>5.x</version>
    </dependency>


### 📌 Rules
- You don't download `jars` manually
- Maven `resolves` transitive dependencies
- Dependencies to to local `.m2` repo

### 📌 Common Commands

    mvn clean -> deletes target/
    mvn compile -> compiles code
    mvn test -> runs tests
    mvn package -> creates the .jar in target/
    mvn install -> copies the .jar to ~/.m2/repository

Rule: Running a phase runs `all` phases before it.

### 📌 Install
- Builds your project and copies the resulting `artifact` into your local Maven repository.
- Other `local projects` can use it as a dependency.

#
### 💡 Active Recall Questions
1. What `problem` does Maven solve?
2. What is `pom.xml`?
3. Why `shouldn't` you manage jars manually?
4. What happen when you run `mvn package`?