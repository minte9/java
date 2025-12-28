/**
 * READ FILES - FILTER BY EXTENSION
 * --------------------------------
 *   - String endsWidth()
 */

package app.versions.filter_files.endswidth;

import java.io.File;

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

        for(File file : files) {
            if (hasExtension(file, ".pdf")) {
                System.out.println(file.getName());
            }
        }

        System.out.println("Done!");
    }

    private static boolean hasExtension(File file, String extension) {
        return file.isFile() &&
               file.getName().endsWith(extension);
    }
}
