/**
 * READ FILES / GROUP BY EXTENSION - STREAMS
 * ------------------------------------------
 * Stream version (recommended for production)
 * This is exactly how production Java code is written.
 * 
 * You just combined:
 *  - Files
 *  - Strings
 *  - Maps
 *  - Lists
 *  - Streams
 *  - Method references
 *  - Static methods
 * 
 * What You Just Mastered:
 *  - Stream ordering
 * - Predicate filtering
 * - Set for O(1) lookups
 * - Real-world stream pipelines
 * - Overall complexity is O(n)
 */

package app.versions.group_by.streams;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class FileOrganizer {
    public static void main(String[] args) {
        File dir = new File(System.getProperty("user.home"), "Downloads");

        if (!dir.exists() || !dir.isDirectory()) {
            System.out.println("Not a directory");
            return;
        }

        File[] files = dir.listFiles();
        if (files == null) return;

        Set<String>allowedExtensions = Set.of("pdf", "sql");

        // HashMap (with stream)
        Map<String, List<File>> filesByExtensions = 
            Arrays.stream(files)
                  .filter(File::isFile)
                  .filter(f -> allowedExtensions.contains(getExtension(f)))
                  .collect(Collectors.groupingBy(FileOrganizer::getExtension));

        // Print the result
        filesByExtensions.forEach((ext, list) -> {
            System.out.println(ext + ":");
            list.forEach(f -> System.out.println("  " + f.getName()));
        });

        System.out.println("Done!");

    }
    
    private static String getExtension(File file) {
        String name = file.getName();
        int dot = name.lastIndexOf(".");

        if (dot == -1 || dot == name.length() - 1) {
            return "no_extension";
        }

        return name.substring(dot + 1).toLowerCase();
    }
}
