/**
 * READ FILES - FROM A DIRECTORY
 * -----------------------------
 * Concepts used:
 *  - Classes / Objects
 *  - Loops
 *  - Strings
 *
 * Limitations:
 *  - Weak error handling
 *  - Hard to apply recursively
 *  - Old API 
 */

package app.versions.read_files.io;

import java.io.File;

public class FileOrganizer {
    public static void main(String[] args) {

        File dir = new File("/home/catalin/Downloads");
        if (!dir.exists()) {
            System.out.println("Not a directory");
            return;
        }

        File[] files = dir.listFiles();
        System.out.println(files);
        if (files == null) {
            return;
        }

        for(File file : files) {
            if (file.isFile()) {
                System.out.println(file.getName());
            }
        }

        System.out.println("Done!");
    }
}
