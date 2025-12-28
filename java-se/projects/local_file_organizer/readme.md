## Local File Organizer 

### What it does

- Scans a folder (e.g. Downloads)
- Groups files by extension (pdf, jpg, zip, etc.)
- Moves them into subfolders

### Concepts used

    Classes / Objects
    Arrays, Lists, Maps
    Loops
    Strings
    Regexp (file extensions)
    Exceptions (IO)
    Streams (filtering, grouping)
    Static utility methods
    JUnit tests for extension parsing

### Example features

- Dry-run mode (no actual move)
- Ignore hidden files
- Configurable via .properties

### Why it’s good

 - You’ll actually use it and learn java.io / java.nio.file


------

### Compile and run a project

pom.xml (example)

    <project>
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.minte9.tools</groupId>
    <artifactId>local-file-organizer</artifactId>
    <version>1.0.0</version>

    <build>
        <finalName>local-file-organizer</finalName>

        <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.11.0</version>
            <configuration>
            <release>17</release>
            </configuration>
        </plugin>

        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-jar-plugin</artifactId>
            <version>3.3.0</version>
            <configuration>
            <archive>
                <manifest>
                <mainClass>app.FileOrganizer</mainClass>
                </manifest>
            </archive>
            </configuration>
        </plugin>
        </plugins>
    </build>
    </project>

Compile:

    mvn clean package

Run: 

    java -jar target/local-file-organizer.jar 


### Project

Open ONLY the project you want to run in VsCode.

    code ./projects/local_file_organizer
