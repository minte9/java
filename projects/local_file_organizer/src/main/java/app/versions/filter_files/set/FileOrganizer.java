/**
 * READ FILES - FILTER BY EXTENSION
 * --------------------------------
 *  - Set<String> = Set.of()
 *  - String lastIndexOf()
 */

package app.versions.filter_files.set;

import java.io.File;
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

        Set<String>allowedExtensions = Set.of("pdf", "sql");

        for(File file : files) {
            if (!file.isFile()) continue;

            String name = file.getName().toLowerCase();
            int dotIndex = name.lastIndexOf(".");

            if (dotIndex == -1) continue;

            String ext = name.substring(dotIndex + 1);

            if (allowedExtensions.contains(ext)) {
                System.out.println(file.getName());
            }
        }

        System.out.println("Done!");
    }
}
