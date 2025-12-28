/**
 * READ FILES - FROM A DIRECTORY - NIO
 * --------------------------------------------------
 * Modern way, how it should be used in real projects.
 * Why this is better:
 *  - Clear exception handling
 *  - Safer resource management
 *  - More powerful API
 * 
 * Concepts used:
 *  - Streams
 *  - Lambdas
 *  - Method References
 *  - Exceptions
 */

package app.versions.read_files.nio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileOrganizer {
    public static void main(String[] args) {
        Path dir = Paths.get("/home/catalin/Downloads");      

        try (Stream<Path> paths = Files.list(dir)) {
            paths
                .filter(Files::isRegularFile)
                .forEach(x -> System.out.println(x.getFileName()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Done!");
    }
}
