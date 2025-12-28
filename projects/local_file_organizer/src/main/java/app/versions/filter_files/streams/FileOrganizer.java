/**
 * READ FILES - STREAMS API
 * ------------------------
 *  - Strings
 *  - Collections
 *  - Streams
 *  - Lambdas
 *  - Method references
 */

package app.versions.filter_files.streams;

import java.io.File;
import java.util.Arrays;
import java.util.Set;

public class FileOrganizer {
    public static void main(String[] args) {

        File dir = new File("/home/catalin/Downloads");
        if (!dir.exists()) {
            System.out.println("Not a directory: " + dir.getAbsolutePath());
            return;
        }

        File[] files = dir.listFiles();
        if (files == null) {
            return;
        }

        Set<String>extensions = Set.of("pdf", "sql");

        Arrays.stream(files)
              .filter(File::isFile)
              .map(File::getName)
              .filter(name -> {
                    int dot = name.lastIndexOf(".");
                    return dot != -1 &&
                           extensions.contains(name.substring(dot + 1).toLowerCase());
              })
              .forEach(System.out::println);
              
        System.out.println("Done!");
    }
}
